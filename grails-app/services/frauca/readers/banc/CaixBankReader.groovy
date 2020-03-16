package frauca.readers.banc

import frauca.readers.sheetables.JODSheetableReader

class CaixBankReader extends BaseBankReader {
	
	@Override
	public BaseBankReader iCouldHandle(File file) {
		CaixBankReader me = instance();
		me.sheettable = new JODSheetableReader(file)
		if(me.itsMine()){
			return me
		}
		return null;
	}
	
	protected CaixBankReader instance(){
		return new CaixBankReader();
	}

	@Override
	public Object getAccountCeilVal() {
		return sheettable.getCeilValue("A1")
	}

	@Override
	public Object itsMine() {	
		log.trace "Its caix ${sheettable.getCeilValue('F3')}==Saldo"
		return "Saldo".equalsIgnoreCase(sheettable.getCeilValue("F3"))
	}

	@Override
	public int getFirstDataRow() {
		return 4;
	}
	
	@Override
	public Object getOperationDateCell(int row) {
		sheettable.getCeilValue("B"+row)
	}

	
	@Override
	public Object getValueDateCell(int row) {
		sheettable.getCeilValue("C"+row)
	}

	@Override
	public Object getConcpetCell(int row) {
		sheettable.getCeilValue("A"+row)+" "+sheettable.getCeilValue("D"+row)
	}

	@Override
	public Object getAmountCell(int row) {
		
		Double pagos=getDoubeVale(sheettable.getCeilValue("E"+row))
		if(!pagos){
			pagos=0
		}
		return pagos;
	}

	@Override
	public Object getTotalAmountCell(int row) {
		sheettable.getCeilValue("F"+row)
	}

	@Override
	public String getConceptFromRaw(String raw) {
		def concept =(raw =~ /\d+-GMV /).replaceAll("GMV ")
		if(concept.contains("PAGAMENT TRANSFER")){
			concept=concept.substring(0, 38)
		}
		log.trace "from ${raw} to ${concept}"
		return concept;
	}

}
