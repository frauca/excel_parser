package frauca.readers.banc

import frauca.readers.sheetables.JODSheetableReader

class BarBank2Reader extends BarBankReader{
	
	@Override
	public BaseBankReader iCouldHandle(File file) {
		BarBank2Reader me = new BarBank2Reader()
		me.sheettable = new JODSheetableReader(file)
		if(me.itsMine()){
			return me
		}
		return null;
	}

	@Override
	public Object itsMine() {
		log.trace "Its bar ${sheettable.getCeilValue('F4')}==Saldo"
		return "Saldo y movimientos".equalsIgnoreCase(sheettable.getCeilValue("A1")) && 
				"Saldo".equalsIgnoreCase(sheettable.getCeilValue("F4"))
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
		sheettable.getCeilValue("E"+row)
	}

	@Override
	public Object getTotalAmountCell(int row) {
		sheettable.getCeilValue("F"+row)
	}
}
