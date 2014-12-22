package frauca.readers.banc

import java.io.File;

import frauca.readers.sheetables.BaseSheetable
import frauca.readers.sheetables.POISheetableReader

class MedBankReader extends BaseBankReader {
	
	@Override
	public BaseBankReader iCouldHandle(File file) {
		MedBankReader me = new MedBankReader()
		me.sheettable = new POISheetableReader(file)
		if(me.itsMine()){
			return me
		}
		return null;
	}

	@Override
	public Object getAccountCeilVal() {
		return sheettable.getCeilValue("B7")
	}

	@Override
	public Object itsMine() {	        
		return "Saldo".equalsIgnoreCase(sheettable.getCeilValue("F8"))
	}

	@Override
	public int getFirstDataRow() {
		return 9;
	}
	
	@Override
	public Object getOperationDateCell(int row) {
		sheettable.getCeilValue("A"+row)
	}

	@Override
	public Object getValueDateCell(int row) {
		sheettable.getCeilValue("C"+row)
	}

	@Override
	public Object getConcpetCell(int row) {
		sheettable.getCeilValue("B"+row)
	}

	@Override
	public Object getAmountCell(int row) {
		
		Double pagos=getDoubeVale(sheettable.getCeilValue("D"+row))
		if(!pagos){
			pagos=0
		}
		
		Double ingresos=getDoubeVale(sheettable.getCeilValue("E"+row))
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

}
