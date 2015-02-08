package frauca

import java.nio.file.Files
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes

class FileSource {
	String path
	String name
	String state
	Date creationTime
	
	
	static hasMany = [rawMovs: AccountMovRaw]
	static belongsTo = [account:Account]
    static constraints = {
		path blank:false
		name blank:false
		state inList: ["new", "parsed", "error","error_not_readed"]
		account nullable: true
    }
	
	static mapping = {
		sort creationTime: "asc"
	}
	
	FileSource(File file){
		name=file.name
		path = file.path
		state="new"
		fillAttr(file)
	}
	
	def fillAttr(File file){
		BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		log.trace "date"+attr.creationTime()+"mod"+attr.lastModifiedTime()
		creationTime=new Date(attr.creationTime().toMillis());
	}
}
