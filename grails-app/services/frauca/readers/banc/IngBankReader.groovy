package frauca.readers.banc

import frauca.readers.sheetables.JODSheetableReader
import frauca.utils.StringUtils

class IngBankReader extends BaseBankReader {
	
	@Override
	public BaseBankReader iCouldHandle(File file) {
		IngBankReader me = new IngBankReader()
		me.sheettable = new JODSheetableReader(file)
		if(me.itsMine()){
			return me
		}
		return null;
	}

	@Override
	public Object getAccountCeilVal() {
		def ccc=sheettable.getCeilValue("C2")
		def match=(ccc =~ /[\d+ ]+/)
		if(match)
			return match[0]
		else
			return ccc
	}

	@Override
	public Object itsMine() {
		return "Titulars".equalsIgnoreCase(sheettable.getCeilValue("B3"))
	}

	@Override
	public int getFirstDataRow() {
		return 10;
	}

	@Override
	public Object getOperationDateCell(int row) {
		sheettable.getCeilValue("B"+row)
	}

	@Override
	public Object getValueDateCell(int row) {
		sheettable.getCeilValue("D"+row)
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

	@Override
	public String getConceptFromRaw(String raw) {
		String concept =StringUtils.removeAnyReplacements(raw, /TARGETA \*\d+ /)
		 concept =(concept =~ /CAIXER TARG\. \*\d+ /).replaceAll("CAIXER ")
		 concept =StringUtils.removeAnyReplacements(concept, /DEBIT COMERC /)
		 log.trace "from ${raw} to ${concept}"
		return concept 
	}

}
