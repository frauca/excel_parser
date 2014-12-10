package frauca

class Account {
	String name
	String comments
	String ccc
	String iban
	
	static hasMany = [files: FileSource]
	
    static constraints = {
    }
}
