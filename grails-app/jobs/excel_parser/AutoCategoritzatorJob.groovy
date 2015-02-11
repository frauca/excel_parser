package excel_parser

import frauca.CategorizerService;

class AutoCategoritzatorJob {

	CategorizerService categorizerService
	def concurrent = false
	
	static triggers = {
		simple name: 'autoCategorizer', startDelay: 1000, repeatInterval: 10000   // execute job once in 5 seconds
	  }
  
	  def execute() {
		  log.info "autoCateogirzer started"
		  categorizerService.replicateMostManualSetted()
		  log.info "autoCateogirzer end"
	  }
}
