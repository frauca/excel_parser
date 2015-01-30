package frauca

import grails.rest.Resource;

final enum SetterType{
	MANUAL,AUTOMATIC
}

@Resource
class Categoritzation {
	SetterType setType;
	Category category;
	String comment;
	static belongsTo = Account  
    static constraints = {
    }
}
