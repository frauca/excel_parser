package frauca

class Account {
	String name
	String comments
	String ccc
	String iban
	/**
	 * ccc or iban as readed on file
	 */
	String rawCCC
	
	
	static hasMany = [files: FileSource]
	
    static constraints = {
    }
}
