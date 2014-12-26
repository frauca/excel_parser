package frauca

class FileSource {
	String path
	String name
	String state
	
	static hasMany = [rawMovs: AccountMovRaw]
	
    static constraints = {
		path blank:false
		name blank:false
		state inList: ["new", "parsed", "error","error_not_readed"]
    }
	
	FileSource(File file){
		name=file.name
		path = file.path
		state="new"
	}
}
