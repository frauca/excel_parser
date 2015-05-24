package frauca.readers.banc

import frauca.readers.sheetables.JODSheetableReader

class CaixBankReader extends BaseBankReader {
	
	
	@Override
	public BaseBankReader iCouldHandle(File file) {
		CaixBankReader me = new CaixBankReader()
		me.sheettable = new JODSheetableReader(file)
		if(me.itsMine()){
			return me
		}
		return null;
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
		return 9;
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
		
		Double ingresos=getDoubeVale(sheettable.getCeilValue("F"+row))
		if(!ingresos){
			ingresos=0
		}
		log.debug "${ingresos}-${pagos}=${ingresos-pagos} ${row} '${sheettable.getCeilValue("D"+row)}'"
		ingresos - pagos 
	}

	@Override
	public Object getTotalAmountCell(int row) {
		sheettable.getCeilValue("F"+row)
	}

	@Override
	public String getConceptFromRaw(String raw) {
		return raw;
	}

}
