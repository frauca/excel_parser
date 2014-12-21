package frauca.readers.sheetables

import org.apache.poi.hssf.util.CellReference
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory

class POISheetableReader extends BaseSheetable  {

	// Use a file
	Workbook wb
	Sheet sheet
	
	def POISheetableReader(File file){
		wb=WorkbookFactory.create(file);
		sheet = wb.getSheetAt(0)
	}

	@Override
	public Object getCeilValue(Object ceil) {
		CellReference ref = new CellReference(ceil)
		Row row = sheet.getRow(ref.getRow());
		Cell cell = row.getCell(ref.getCol());
		return null;
	}
}
