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
	Categoritzation categoritzation;
	
	
	static hasMany = [files: FileSource]
	
    static constraints = {
		name nullable:true
		comments nullable:true
		ccc nullable:true
		iban nullable:true
    }
}
