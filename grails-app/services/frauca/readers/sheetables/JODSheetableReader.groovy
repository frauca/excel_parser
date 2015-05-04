package frauca.readers.sheetables

import org.jopendocument.dom.ODValueType;
import org.jopendocument.dom.spreadsheet.Sheet
import org.jopendocument.dom.spreadsheet.SpreadSheet

class JODSheetableReader extends BaseSheetable  {

	SpreadSheet sheettable
	@Override
	public Object getSheetName() {
		return sheet.getName()
	}

	Sheet sheet
	
	JODSheetableReader(File  f){
		file =f
		sheettable = SpreadSheet.createFromFile(f)
		sheet = sheettable.firstSheet
	}

	@Override
	public def getCeilValue(Object ceil) {
		return sheet.getCellAt(ceil).getValue()
	}

	@Override
	public int getLastRowNum() {
		return sheet.getRowCount()
	}
}
