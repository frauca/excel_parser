package frauca.readers.sheetables

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.CellReference
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.FormulaEvaluator
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory

class POISheetableReader extends BaseSheetable  {

	// Use a file
	Workbook wb
	Sheet sheet

	def POISheetableReader(File file){
		file=file
		wb=WorkbookFactory.create(file);
		sheet = wb.getSheetAt(0)
	}

	@Override
	public Object getCeilValue(Object ceil) {
		CellReference ref = new CellReference(ceil)
		log.trace "reading '$ceil' '${ref.row}' '${ref.col}'"
		Row row = sheet.getRow(ref.getRow());
		Cell cell = row.getCell(ref.getCol());
		return getCeilVaule(cell);
	}

	public Object getCeilVaule(Cell cell){
		if(HSSFDateUtil.isCellDateFormatted(cell)){
			
			double dv = cell.getNumberValue();
			Date date = HSSFDateUtil.getJavaDate(dv);

			return date

		}
		if (cell!=null) {
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue()
				case Cell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue()
				case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue()
				default:
				log.debug "unkonw cell type '$cell' "+cell.getCellType()
				return cell.getStringCellValue()
			}
		}
	}

	@Override
	public int getLastRowNum() {
		return sheet.getPhysicalNumberOfRows()
	}
}

