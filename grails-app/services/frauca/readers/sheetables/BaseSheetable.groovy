package frauca.readers.sheetables

abstract class BaseSheetable {

	/**
	 * Get the value for a specific cell (values are expecte as B1 )
	 * @param ceil
	 * @return
	 */
	def abstract getCeilValue(def ceil)
}
