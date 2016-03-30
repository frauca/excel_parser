package frauca

import grails.rest.Resource;

import java.nio.file.Files
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes


@Resource
class FileSource {
	String path
	String name
	String state
	Date creationTime
	/**
	 * This type is used to differ from the daily ones from the big ones who are used to reorder every thing
	 */
	String type
	
	static hasMany = [rawMovs: AccountMovRaw]
	static belongsTo = [account:Account]
    static constraints = {
		path blank:false
		name blank:false
		state inList: ["new", "parsed", "error","error_not_readed","justAdded"]
		account nullable: true
		type nullable:true
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
