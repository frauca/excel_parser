package frauca

class FileSource {
	String path
	String name
	String state
	
    static constraints = {
		path blank:false
		name blank:false
		state inList: ["new", "parsed", "error"]
    }
	
	FileSource(File file){
		name=file.name
		path = file.path
		state="new"
	}
}
