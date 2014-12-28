package frauca

class FileSource {
	String path
	String name
	String state
	
	
	static hasMany = [rawMovs: AccountMovRaw]
	static belongsTo = [account:Account]
    static constraints = {
		path blank:false
		name blank:false
		state inList: ["new", "parsed", "error","error_not_readed"]
		account nullable: true
    }
	
	FileSource(File file){
		name=file.name
		path = file.path
		state="new"
	}
}
