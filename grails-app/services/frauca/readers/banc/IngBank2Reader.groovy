package frauca.readers.banc


import frauca.readers.sheetables.JODSheetableReader
import frauca.utils.StringUtils

class IngBank2Reader extends BaseBankReader{
	@Override
	public BaseBankReader iCouldHandle(File file) {
		IngBank2Reader me = new IngBank2Reader()
		me.sheettable = new JODSheetableReader(file)
		log.trace "Bar2 iCouldHandle ${me.sheettable}"
		if(me.itsMine()){
			return me
		}
		return null;
	}

	@Override
	public Object getAccountCeilVal() {
		def ccc=sheettable.getCeilValue("A4")
		def match=(ccc =~ /\d+ \- \d+ \- \d+ \- \d+/)
		if(match[0]){
			log.trace "Ing2 has been found ${match[0]}"
			return match[0]
		}
		else
			return ccc
	}

	@Override
	public Object itsMine() {
		log.trace "Ing2 ${sheettable.getCeilValue('A3')}==Consulta de moviments"
		return "Consulta de moviments".equalsIgnoreCase(sheettable.getCeilValue("A3")) ||
					"Consulta de movimientos".equalsIgnoreCase(sheettable.getCeilValue("A3"))
	}

	@Override
	public int getFirstDataRow() {
		return 11;
	}

	@Override
	public Object getOperationDateCell(int row) {
		sheettable.getCeilValue("A"+row)
	}

	@Override
	public Object getValueDateCell(int row) {
		sheettable.getCeilValue("E"+row)
	}

	@Override
	public Object getConcpetCell(int row) {
		sheettable.getCeilValue("B"+row)
	}

	@Override
	public Object getAmountCell(int row) {
		sheettable.getCeilValue("F"+row)
	}

	@Override
	public Object getTotalAmountCell(int row) {
		sheettable.getCeilValue("G"+row)
	}

	@Override
	public String getConceptFromRaw(String raw) {
		String concept =StringUtils.removeAnyReplacements(raw, /TARGETA \*\d+ /)
		 concept =(concept =~ /CAIXER TARG\. \*\d+ /).replaceAll("CAIXER ")
		return concept 
	}
}
