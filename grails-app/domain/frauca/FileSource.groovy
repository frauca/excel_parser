package frauca

class FileSource {
	String path
	String name
	String state
	
    static constraints = {
		state inList: ["new", "parsed", "error"]
    }
}
