package frauca.readers.banc

import frauca.Account;
import frauca.AccountMovRaw;
import frauca.readers.sheetables.JODSheetableReader
import frauca.utils.StringUtils;

class BarBankReader extends BaseBankReader {

	/**
	 * This file has not account information a dummy value is setted. This dummy value must no be repeated in two files
	 * @return null if can not be found or Account if it could
	 */
	 Account getAccount() {
		log.debug "get the account of the bank"
		def ccc = "00001"
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
	 
	@Override
	public BaseBankReader iCouldHandle(File file) {
		BarBankReader me = new BarBankReader()
		me.sheettable = new JODSheetableReader(file)
		if(me.itsMine()){
			return me
		}
		return null;
	}

	@Override
	public Object getAccountCeilVal() {
		return null;
	}

	@Override
	public Object itsMine() {
		log.trace "Its bar ${sheettable.getCeilValue('E4')}==Saldo"
		return "Saldo".equalsIgnoreCase(sheettable.getCeilValue("E4"))
	}

	@Override
	public int getFirstDataRow() {
		return 6;
	}

	@Override
	public Object getOperationDateCell(int row) {
		sheettable.getCeilValue("A"+row)
	}

	@Override
	public Object getValueDateCell(int row) {
		sheettable.getCeilValue("B"+row)
	}

	@Override
	public Object getConcpetCell(int row) {
		sheettable.getCeilValue("C"+row)
	}

	@Override
	public Object getAmountCell(int row) {
		sheettable.getCeilValue("D"+row)
	}

	@Override
	public Object getTotalAmountCell(int row) {
		sheettable.getCeilValue("E"+row)
	}

	@Override
	public String getConceptFromRaw(String raw) {
		String concept =StringUtils.removeAnyReplacements(raw, /RECIBO SEPA \d+  /)
		concept =StringUtils.removeAnyReplacements(concept, /RECIBO A SU CARGO Nº \d+ /)
		concept =StringUtils.removeAnyReplacements(concept, /COMPRA CON TARJETA SERVIRED /)
		concept =StringUtils.removeAnyReplacements(concept, /DISPOSICION CAJERO AUTOMATICO /)
		concept =StringUtils.removeAnyReplacements(concept, /DISPOSICION /)
		return concept 
	}
	
	/**
	 * @return all the rows of the file
	 */
	AccountMovRaw[] readAllMovements(){
		 def rows=[]
		 log.trace "BarBankReader to read from ${getFirstDataRow()} to ${sheettable.getLastRowNum()}"
		 AccountMovRaw last;
		 int empties=0;
		 for(int i in getFirstDataRow()..sheettable.getLastRowNum()){
			 try{
				 AccountMovRaw tmp=readRowMovements(i)//this one could be just the concept value
				 if(!tmp.amount){
					 if(last){
						 last.conceptRaw=last.conceptRaw+"-"+tmp.conceptRaw
						 log.trace "${i}-${empties}-${last.concept}-${last.conceptRaw}"
						 if(++empties<=1){
							 last.concept=tmp.conceptRaw//This is the good value
						 }
						 
					 }else{
					 	log.info "A line with no values on first place ${tmp.rowOfDoc}"
					 }
				 }else{
				 	log.trace "This one is a good line ${tmp.rowOfDoc}"
					 last=tmp
					 rows << last
					 empties=0;
				 }
				 
			 }catch(e){
				log.debug "stop reading file at ${i} because ${e.message}"
				log.trace "stop reading because: ",e
				 break
			 }
		 }
		 return rows
	 }
	

}
