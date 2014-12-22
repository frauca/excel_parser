package frauca.readers.banc

import java.text.SimpleDateFormat

import frauca.Account
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
		def ccc = getAccountCeilVal()
		if(ccc!=null){
			Account ac = new Account()
			ac.rawCCC = ccc
			log.debug "the ccc is on a place "+ac.rawCCC
			return ac
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
				 rows << readRowMovements(i)
			 }catch(e){
			 	log.debug "stop reading file at ${i} because ${e.message}",e
				 
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
		 row.amount=getDoubeVale(getAmountCell(rownum))
		 row.totalAmount=getDoubeVale(getTotalAmountCell(rownum))
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
}
