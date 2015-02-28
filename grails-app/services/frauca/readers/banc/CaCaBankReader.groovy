package frauca.readers.banc

import frauca.readers.sheetables.POISheetableReader

class CaCaBankReader  extends BaseBankReader{

	@Override
	public BaseBankReader iCouldHandle(File file) {
		CaCaBankReader me = new CaCaBankReader()
		me.sheettable = new POISheetableReader(file)
		log.debug "CaCa iCouldHandle ${me.sheettable}"
		if(me.itsMine()){
			return me
		}
		return null;
	}

	@Override
	public Object getAccountCeilVal() {
		def ccc=sheettable.getCeilValue("B6")
		return ccc
	}

	@Override
	public Object itsMine() {
		log.debug "CaCa ${sheettable?.getCeilValue('A7')}==Consulta de moviments"
		return "IBAN:".equalsIgnoreCase(sheettable.getCeilValue("A7"))
	}

	@Override
	public int getFirstDataRow() {
		return 13;
	}

	@Override
	public Object getOperationDateCell(int row) {
		sheettable.getCeilValue("C"+row)
	}

	@Override
	public Object getValueDateCell(int row) {
		sheettable.getCeilValue("D"+row)
	}

	@Override
	public Object getConcpetCell(int row) {
		sheettable.getCeilValue("A"+row)
	}

	@Override
	public Object getAmountCell(int row) {
		sheettable.getCeilValue("E"+row)
	}

	@Override
	public Object getTotalAmountCell(int row) {
		sheettable.getCeilValue("G"+row)
	}

	@Override
	public String getConceptFromRaw(String raw) {
		return raw;
	}
}
