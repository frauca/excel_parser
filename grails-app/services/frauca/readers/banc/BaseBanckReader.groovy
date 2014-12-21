package frauca.readers.banc

import frauca.Account
import frauca.readers.sheetables.BaseSheetable;

abstract class BaseBanckReader {

	BaseSheetable sheettable
	
	BaseBanckReader(def sheettable ){
		this.sheettable=sheettable
	}
	
	
	 /**
	  * Read the accound from the sheettable
	 * @return null if can not be found or Account if it could
	 */
	abstract Account  getAccount()
}
