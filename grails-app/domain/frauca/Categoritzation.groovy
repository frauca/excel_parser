package frauca

import grails.rest.Resource;

final enum SetterType{
	MANUAL,AUTOMATIC,MULTIPLE
}

@Resource
class Categoritzation {
	SetterType type;
	Category category;
	Category subcat;
	String comment;
	Date dateCreated
	Date lastUpdated
	static belongsTo = AccountMov
    static constraints = {
		comment nullable: true, blank: true
		subcat nullable:true
    }
	
	String getCategoryName(){
		category.name;
	}
}
