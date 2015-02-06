package frauca

import grails.rest.Resource;

final enum SetterType{
	MANUAL,AUTOMATIC
}

@Resource
class Categoritzation {
	SetterType type;
	Category category;
	String comment;
	Date dateCreated
	Date lastUpdated
	static belongsTo = AccountMov
    static constraints = {
		comment nullable: true, blank: true
    }
	
	String getCategoryName(){
		category.name;
	}
}
