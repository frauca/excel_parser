package frauca

import grails.rest.Resource;

@Resource
class Category {

	String name
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
    }
}
