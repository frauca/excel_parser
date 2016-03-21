package frauca.readers.banc

import java.text.SimpleDateFormat

import org.hibernate.NonUniqueResultException

import frauca.Account
import frauca.AccountMov
import frauca.AccountMovRaw
import frauca.readers.sheetables.BaseSheetable

abstract class BaseBankReader {

	BaseSheetable sheettable

	/**
	 * Read the accound from the sheettable
	 * @return null if can not be found or Account if it could
	 */
	 Account getAccount() {
		log.debug "get the account of the bank"
		String ccc = getAccountCeilVal()
		if(ccc!=null){
			log.trace "loking for ${ccc}"
			ccc=ccc.trim()
			def ac = Account.findByRawCCC(ccc)
			if(ac){
				return ac
			}else{
				ac = new Account()
				ac.rawCCC = ccc
				ac.save()
				if(ac.hasErrors()){
					ac.errors.allErrors.each{log.error "could not save account "+it}
				}else{
					log.debug "the ccc is on a place "+ac.rawCCC
					return ac
				}
			}
		}
		return null;
	}
	 
	/**
	 * It will return the reader if it could read the file
	 * @param file
	 * @return
	 */
	abstract BaseBankReader  iCouldHandle(File file)
	 
	 def abstract getAccountCeilVal()
	 
	 /**
	 * @return true if the bankReader knows how to process the file
	 */
	def abstract itsMine()
	 
	 
	 
	 /**
	 * @return all the rows of the file
	 */
	AccountMovRaw[] readAllMovements(){
		 def rows=[]
		 log.debug "we are going to read from ${getFirstDataRow()} to ${sheettable.getLastRowNum()}"
		 for(int i in getFirstDataRow()..sheettable.getLastRowNum()){
			 try{
				 AccountMovRaw row=readRowMovements(i)
				 if(row.amount&&row.concept){
				 	rows << row
				 }else{
				 	log.debug "The row ${i} has not ammount value and has not been added from ${row?.sourceFile?.name}"
				 }
			 }catch(e){
			 	log.debug "stop reading file at ${i} because ${e.message}"
				log.trace "stop reading because: ",e 
				 break
			 }
		 }
		 return rows
	 }
	 
	 AccountMovRaw readRowMovements(int rownum){
		 AccountMovRaw row = new AccountMovRaw()
		 row.operationDate=getDateVale( getOperationDateCell(rownum))
		 row.valueDate=getDateVale(getValueDateCell(rownum))
		 row.conceptRaw=getConcpetCell(rownum)
		 row.concept = getConceptFromRaw(row.conceptRaw).replaceAll("'", " ")
		 row.amount=getDoubeVale(getAmountCell(rownum))
		 row.totalAmount=getDoubeVale(getTotalAmountCell(rownum))
		 row.rowOfDoc=rownum
		 return row
	 }
	 /**
	 * @return the row num where the data starts
	 */
	abstract int getFirstDataRow()
	
	def abstract getOperationDateCell(int row)
	def abstract getValueDateCell(int row)
	def abstract getConcpetCell(int row)
	def abstract getAmountCell(int row)
	def abstract getTotalAmountCell(int row)
	/**
	 * Parse the raw concept and make better understandable
	 * @param raw concepte to process
	 * @return processed concept
	 */
	def abstract String getConceptFromRaw(String raw)
	
	
	public Date getDateVale(def posdate){
		if(posdate instanceof Date){
			return posdate
		}else{
			String[] formats = ["dd/MM/yyyy","dd-MM-yyyy"]
			for(String it in formats){
				try{
					log.trace "parsing ${posdate} with ${it}"
					SimpleDateFormat sf = new SimpleDateFormat(it)
					return sf.parse(posdate)
				}catch(e){log.trace "could not parse ",e}
			}
		}
		return null
	}
	
	public Double getDoubeVale(def posdouble){
		if(!posdouble){
			return null
		}
		if(posdouble instanceof Double){
			return posdouble
		}else if(posdouble instanceof Number){
			return posdouble.toDouble()
		}else{
			log.debug "i must format ${posdouble}"
			return Double.parseDouble(posdouble)
		}
		return null
	}
	
	/**
	 * Look for already insert movements
	 * The find movement is done in the basebank to let in the future find by diferent way depending on the bank
	 *
	 * @param rawmove
	 * @return
	 */
	public AccountMov findRawMove(AccountMovRaw rawmove){
		//TODO detectar duplicats a autopista
		def dup = AccountMov.where{
			(operationDate == rawmove.operationDate
			&& conceptRaw == rawmove.conceptRaw
			&& amount == rawmove.amount
			&& original.sourceFile != rawmove.sourceFile)
		}
		try{
			return dup.find()
		}catch(NonUniqueResultException e){
			log.debug "Looking for duplicates i found ${dup?.size()}  on ${rawmove?.sourceFile?.name} ${rawmove?.rowOfDoc}"
			dup.each {mov->
				log.trace "dup ${mov.concept} ${mov?.original?.sourceFile?.name} ${mov?.original?.rowOfDoc}"
			}
			dup[0]
		}
	}
	
}
