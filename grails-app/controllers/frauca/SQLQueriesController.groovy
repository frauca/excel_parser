package frauca

import grails.rest.RestfulController

class SQLQueriesController extends RestfulController<SQLQueries>{
	
	static responseFormats = ['json', 'xml']
	
	
	SQLQueriesController(){
		super(SQLQueries)
	}

}
