package frauca

import com.sun.org.apache.bcel.internal.classfile.SourceFile;

import frauca.readers.banc.BaseBankReader;
import grails.transaction.Transactional

/**
 * @author rofc
 * This service will manage the AccountMov and AccountMovRaw instances
 */
@Transactional
class AccountMovRawService {

    def saveAllMov(FileSource fileSource,AccountMovRaw[] movs) {
		log.debug "${movs.size()} raw movements are going to be save"
		movs.each {mov->
			mov.sourceFile=fileSource
			mov.save()
			if(mov.hasErrors()){
				log.error "could not save there are ${fileSource.name} on ${mov.rowOfDoc} errors found:" + mov.errors.allErrors.size()
				mov.errors.allErrors.each {error->
					log.debug "error: "+error.toString()
				}
				
			}
		}
    }
	
	/**
	 * Process all the new movements that has not been processed and move them to AccountMov or marc it as duplicated
	 * @return
	 */
	def processAllPendingMoves(BaseBankReader bnkReader){
		log.trace "the new state move accounts are going to be saved"
		def newMoves=AccountMovRaw.findAllByState("new",[sort: "rowOfDoc", order: "asc"])
		Account ccc=bnkReader.getAccount()
		AccountMov lastest=findLatestMovForAccount(ccc)
		log.debug "${newMoves.size()} accountmoves are going to be procesed with ${bnkReader} in ${ccc}"
		newMoves.each {rawmove->
			AccountMov mov = bnkReader.findRawMove(rawmove)
			log.trace "find "+mov
			if(mov){
				log.trace "${rawmove.concept} on  ${rawmove.rowOfDoc} is duplicated"
				rawmove.duplicatedBy(mov)
			}else{
				log.trace "creating new mov"
				mov= new AccountMov(rawmove)
				mov = rawmove.copiedBy(mov)
				mov.account=ccc
				log.trace "last"+lastest+"mov"+mov+" "+lastest?.id+" mov"+mov?.amount
				mov.totalAmount=(lastest?.totalAmount?lastest.totalAmount + mov.amount:mov.totalAmountRaw).round(2)
				saveAndPrintErrors(rawmove)
				saveAndPrintErrors(mov)
				lastest=mov
			}
		}
	}
	
	
	/**
	 * save the object and print the errors if it has errors.
	 * 
	 * @param mov
	 */
	public void saveAndPrintErrors(def mov){
		mov.save()
		if(mov.hasErrors()){
			mov.errors.allErrors.each{log.error "could not save account "+it}
		}
	}
	
	public AccountMov findLatestMovForAccount(Account ccc){
		if(ccc){
			def last=AccountMov.executeQuery("""from AccountMov mov
	                                     where mov.id = (select max(m.id) from AccountMov m where m.account = :ccc)
	                                    """,[ccc:ccc])
			if(last){
				return last[0]
			}else{
				return last
			}
		}
	}
	
	public AccountMov[] nextNDaysByOperationDate(AccountMov[] movs,int n){
		AccountMov[] res=[];
		int totDays=0;
		Date currentDate=null;
		for(mov in movs){
			res+=mov;
			if(mov.operationDate!=currentDate){
				currentDate=mov.operationDate;
				if(totDays++>n){
					return res;
				}
			}
		}
		return res;
	}
	
	
	/**
	 * Make from the date to upper movements it add to the total the amount and compare with the total  of the previous
	 * If it is diferent thent it save the correct result and add it to the erroneous ones
	 * @param date
	 * @param accountId
	 * @return
	 */
	public AccountMov[] recalcTotals(Date date,long accountId){
		def all = AccountMov.executeQuery("From AccountMov where operationDate > ? and account.id=? order by operationDate,original.orderOfDoc,id",[date,accountId]);
		def res=[];
		for(int i=0;i<all.size()-1;i++){
			AccountMov current=all[i];
			AccountMov nextOne=all[i+1];
			if((current.totalAmount+nextOne.amount).round(2)!=nextOne.totalAmount){
				nextOne.totalAmount=(current.totalAmount+nextOne.amount).round(2);
				res+=nextOne;
			}
		}
		AccountMov.saveAll(res);
		return res;
 	}
	
	/**
	 * Make from the date to upper movements compare total and total raws
	 * If it is diferent thent it save the correct result and add it to the erroneous ones
	 * @param date
	 * @param accountId
	 * @return
	 */
	public AccountMov[] setTotals(Date date,long accountId){
		
		AccountMov[] all = AccountMov.executeQuery("From AccountMov where operationDate > ? and account.id=? order by operationDate,original.orderOfDoc,id",[date,accountId]);
		orderFromFileSource(all);
		def res=[];
		all.each {mov->
			if(mov.totalAmount!=mov.totalAmountRaw){
				res+=mov;
				mov.totalAmount=mov.totalAmountRaw;
				mov.save();
			}
		}
		return res;
	 }
	
	/**
	 * It is assumed that the ordered list is all ascending or descening so the first two diferent operation date will be used to tell if they are ascending or not
	 * @param ordered
	 * @return true if date are ascending
	 */
	public boolean areDateAscending(Object orderedO){
		if(orderedO instanceof Iterable<AccountMovRaw>){
			AccountMovRaw[] ordered=(AccountMovRaw[])orderedO
			for(int i=0;i<ordered.size()-1;i++){
				AccountMovRaw current=ordered[i];
				AccountMovRaw nextOne=ordered[i+1];
				if(current.operationDate<nextOne.operationDate){
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	/**
	 * Make from the date to upper movements compare total and total raws
	 * If it is diferent thent it save the correct result and add it to the erroneous ones
	 * @param date
	 * @param accountId
	 * @return
	 */
	public void orderFromFileSource(AccountMov[] movs){
		def all = movs*.original*.sourceFile.unique();
		all.each {file->
			setOrder(file)
		}
	 }
	
	/**
	 * If the order is not set on some child of file source it will be set
	 * @param fileSourceId
	 * @return
	 */
	public def setOrder(FileSource file){
		def all = AccountMovRaw.executeQuery("From AccountMovRaw where sourceFile = ? order by id",[file])
		if(!areDateAscending(all)){
			all=all.reverse();
		}
		all.eachWithIndex {mov,index->
			if(!mov.orderOfDoc){
				mov.orderOfDoc=index;
				mov.save();
			}
		}
	}
}
