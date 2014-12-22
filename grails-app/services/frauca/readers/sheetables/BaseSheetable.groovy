package frauca.readers.sheetables

abstract class BaseSheetable {
	
	File file

	/**
	 * Get the value for a specific cell (values are expecte as B1 )
	 * @param ceil
	 * @return
	 */
	def abstract getCeilValue(def ceil)
	
	abstract int getLastRowNum()
}
