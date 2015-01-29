package frauca

import grails.rest.Resource;

@Resource(uri='/category')
class Category {

	String name
	
    static constraints = {
    }
}
