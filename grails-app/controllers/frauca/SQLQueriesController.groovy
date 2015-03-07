package frauca

import grails.rest.RestfulController
import groovy.json.JsonSlurper;

class SQLQueriesController extends RestfulController<SQLQueries>{
	
	static responseFormats = ['json', 'xml']
	
	
	SQLQueriesController(){
		super(SQLQueries)
	}

	def execute(Integer id) {
		def jsonSlurper = new JsonSlurper()
		def q = SQLQueries.get(id)
		def args= jsonSlurper.parseText(q.arguments)
		def sql=q.sql
		args.each {key,val->
			log.debug key+"="+val
			sql=sql.replaceAll(key, val)
		}
		log.debug "SQL:"+sql
		respond SQLQueries.executeQuery(sql)
	}
}
