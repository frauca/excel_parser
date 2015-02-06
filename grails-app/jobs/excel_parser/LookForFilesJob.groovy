package excel_parser

import frauca.ReaderService



class LookForFilesJob {
	
	def readerService
	
    static triggers = {
      simple name: 'lookOnFolders', startDelay: 1000, repeatInterval: 3600000   // execute job once in 5 seconds
    }

    def execute() {
		log.debug "looking for files"
        readerService.readFile('D:/proyectos/docs/personal/learn/docs/')
		readerService.processAllSourceFiles()
    }
}
