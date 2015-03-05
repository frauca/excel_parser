package frauca

import grails.rest.Resource;

import java.util.Date;

@Resource
class SQLQueries {

	String name
	String sql
	String arguments
	Date dateCreated
	Date lastUpdated
	
	
    static constraints = {
		arguments nullable: true 
    }
}
