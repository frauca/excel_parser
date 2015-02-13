package excel_parser

import org.quartz.DisallowConcurrentExecution

import frauca.DirectorySource;

class LookForFilesJob {
	
	def readerService
	def concurrent = false
	
	
    static triggers = {
      simple name: 'lookOnFolders', startDelay: 1000, repeatInterval: 15000   // execute job once in 5 seconds
    }

	def execute(){
		log.info "looking for files job started"
		DirectorySource.getAll().each {dir->
			readerService.readFile(dir.path)
			readerService.processAllSourceFiles()
		}
		log.info "looking for files job ended"
	}
}
