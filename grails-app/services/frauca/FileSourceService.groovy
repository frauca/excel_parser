package frauca

import org.springframework.stereotype.Component;

import frauca.FileSource
import groovy.io.FileType

class FileSourceService {

	/**
	 * Add all files from dir or the file it self if it is not present on database
	 * @return
	 */
	def importAllFiles(path){
		def files=[]
		def dir = new File(path)
		if(dir.isDirectory()){
			dir.eachFileRecurse (FileType.FILES) { file -> 
				files << file 
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
			log.info "looking for "+file.name
			def fs=FileSource.findByName(file.name)
			if(fs){
				log.info "file already exists "+fs.path
			}else{
				fsource.save()
			}
		}
	}
}
