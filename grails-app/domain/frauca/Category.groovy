package frauca

import grails.rest.Resource;

@Resource
class Category {

	String name
	Date dateCreated
	Date lastUpdated
	Category father
	
    static constraints = {
		name unique: true
		father nullable: true
    }
	
	static mapping = {
		sort name: "asc"
	}
}
