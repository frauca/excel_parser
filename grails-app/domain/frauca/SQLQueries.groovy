package frauca

import grails.rest.Resource;

import java.util.Date;

@Resource
class SQLQueries {

	String name
	String sql
	String arguments
	String description
	Date dateCreated
	Date lastUpdated
	
	static mapping = {
		sql type: "text"
		sort name: "asc"
	}
	
    static constraints = {
		arguments nullable: true 
		description nullable: true, maxSize: 3999
    }
}
