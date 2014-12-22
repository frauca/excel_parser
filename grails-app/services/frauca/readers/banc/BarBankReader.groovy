package frauca.readers.banc

import frauca.readers.sheetables.JODSheetableReader

class BarBankReader extends BaseBankReader {

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
	
	

}
