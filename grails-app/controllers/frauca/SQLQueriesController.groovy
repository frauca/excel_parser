package frauca

import grails.rest.RestfulController

class SQLQueriesController extends RestfulController<SQLQueries>{
	
	static responseFormats = ['json', 'xml']
	
	
	SQLQueriesController(){
		super(SQLQueries)
	}

	def execute(Integer id) {
		def q = SQLQueries.get(id)
		respond SQLQueries.executeQuery(q.sql)
	}
}
