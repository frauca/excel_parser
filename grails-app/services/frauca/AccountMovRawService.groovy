package frauca

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
				log.error "could not save there are" + mov.errors.allErrors.size()
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
				log.trace "last"+lastest+"mov"+mov+" "+lastest.id+" mov"+mov.amount
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
