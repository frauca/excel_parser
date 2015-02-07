package excel_parser

import frauca.CategorizerService;

class AutoCategoritzatorJob {

	CategorizerService categorizerService
	
	static triggers = {
		simple name: 'autoCategorizer', startDelay: 1000, repeatInterval: 30000   // execute job once in 5 seconds
	  }
  
	  def execute() {
		  log.debug "autoCateogirzer"
		  categorizerService.replicateMostManualSetted()
	  }
}
