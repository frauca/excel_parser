package frauca

import grails.converters.*

class QueryController {
	
	def queryService
	def categorizerService

    def overView() { 
		
		def res =queryService.totalMovsAmount(params)
		
		switch(params.format){
			case  "xml":
				render( res  as XML)
			default:
				render( res  as JSON)
		}
		
	}
	
	def byWeekOv(int year,int month) {
		
		def from = new GregorianCalendar(year,month.toInteger()-1,1)
		def to = from.copyWith(date: from.getActualMaximum( Calendar.DATE ))
		def weeks=[] as Set
		from.upto(to){
		    weeks << it.get(Calendar.WEEK_OF_YEAR)
		}
		def res =queryService.totalMovsAmount(params)
		def wdata=[:]
		def pars=params.clone()
		weeks.each {
			pars["week"]=it
			wdata[it]=queryService.totalMovsAmount(pars)
		}
		res["weeks"]=wdata
		
		switch(params.format){
			case  "xml":
				render( res  as XML)
			default:
				render( res  as JSON)
		}
		
	}
	
	def catByConcept() {
		
		def res = categorizerService.categorizedByConcept(params.concept)
		
		switch(params.format){
			case  "xml":
				render( res  as XML)
			default:
				render( res  as JSON)
		}
		
	}
}
