package excel_parser

import frauca.ReaderService



class CheckNewFilesJob {
	
	def readerService
	
    static triggers = {
      simple repeatInterval: 5000l // execute job once in 5 seconds
    }

    def execute() {
		log.debug "looking for files"
        readerService.readFile('D:/proyectos/docs/personal/learn/docs/')
    }
}
