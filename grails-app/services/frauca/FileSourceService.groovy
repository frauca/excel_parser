package frauca

import org.springframework.stereotype.Component;

import frauca.FileSource
import grails.transaction.Transactional;
import groovy.io.FileType

/**
 * @author rofc
 * This service will manage the fileSource and account instances
 */
@Transactional
class FileSourceService {

	/**
	 * Add all files from dir or the file it self if it is not present on database
	 * @return
	 */
	def importAllFiles(path){
		def files=[]
		def dir = new File(path)
		
		if(dir.isDirectory()){
			log.debug "recursive ${dir}"
			dir.eachFileRecurse (FileType.FILES) { file -> 
				log.trace "adding ${file}"
				if(!file.name.startsWith(".~")){//do not read linux temporal files
					files << file 
				}
			}
		}else{
			files<<dir
		}
		persistAllFiles(files)
	}
	
	


	/**
	 * Save all not existing files on databse
	 * @param fls
	 * @return
	 */
	def persistAllFiles(files){
		files.each{ file->
			def fsource = new FileSource(file)
			log.debug "looking for "+file.name
			def fs=FileSource.findByPath(file.path)
			if(fs){
				log.debug "file already exists "+fs.path
			}else{
				fsource.save()
			}
		}
	}
}
