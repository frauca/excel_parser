package excel_parser

import org.quartz.DisallowConcurrentExecution

class LookForFilesJob {
	
	def readerService
	def concurrent = false
	
	
    static triggers = {
      simple name: 'lookOnFolders', startDelay: 1000, repeatInterval: 300000   // execute job once in 5 seconds
    }

	def execute(){
		log.info "looking for files job started"
		readerService.readFile('D:/proyectos/docs/personal/learn/docs/toprocess/')
		readerService.processAllSourceFiles()
		log.info "looking for files job ended"
	}
}
