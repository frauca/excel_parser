package frauca

import frauca.command.BasicAnalyzerCommand
import frauca.command.times.Week;
import grails.converters.JSON
import grails.converters.XML

class BasicAnalyzerController {

	def basicAnalyzerService;
	
	def process(BasicAnalyzerCommand qry){
		log.debug("Start processing");
		def res = basicAnalyzerService.process(qry);
		res.removeDetails();
		log.debug("send the result of the query");
		switch(params.format){
			case  "xml":
				render( res  as XML)
			default:
				render( res  as JSON)
		}
	}
	
	def showWeekDetail(String sweek){
		Week week = new Week(sweek);
		def res = basicAnalyzerService.showWeekDetail(week);
		log.debug("send details");
		switch(params.format){
			case  "xml":
				render( res  as XML)
			default:
				render( res  as JSON)
		}
	}
}
