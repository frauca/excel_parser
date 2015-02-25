package frauca

import grails.converters.*

class QueryController {
	
	def queryService

    def overView() { 
		
		def res =queryService.totalMovsAmount(params.ccc)
		
		switch(params.format){
			case  "xml":
				render( res  as XML)
			default:
				render( res  as JSON)
		}
		
	}
}
