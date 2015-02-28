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
		Row row = sheet.getRow(ref.getRow());
		Cell cell = row.getCell(ref.getCol());
		log.trace "reading '$ceil' '${ref.row}' '${ref.col}' "
		return getCeilVaule(cell);
	}

	public Object getCeilVaule(Cell cell){
		try{
			if (cell!=null) {
				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
					return cell.getBooleanCellValue()
					case Cell.CELL_TYPE_NUMERIC:
					double dv = cell.getNumericCellValue();
						if(HSSFDateUtil.isCellDateFormatted(cell)){
							Date date = HSSFDateUtil.getJavaDate(dv);
							return date
						}
						return dv
					case Cell.CELL_TYPE_STRING:
					return cell.getStringCellValue()
					case Cell.CELL_TYPE_BLANK:
						return null
					default:
					log.trace "unkonw cell type '$cell' ${cell.getRowIndex()}-${cell.getColumnIndex()} "+cell.getCellType()
					return cell.getStringCellValue()
				}
			}
		}catch(e){
			log.trace "could not parse cell",e
		}
	}

	@Override
	public int getLastRowNum() {
		return sheet.getLastRowNum()
	}
}

