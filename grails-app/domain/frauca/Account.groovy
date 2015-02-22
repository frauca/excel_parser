package frauca

import grails.rest.Resource;

@Resource
class Account {
	String name
	String comments
	String ccc
	String iban
	/**
	 * ccc or iban as readed on file
	 */
	String rawCCC

	
	static hasMany = [files: FileSource]
	
    static constraints = {
		name nullable:true
		comments nullable:true
		ccc nullable:true
		iban nullable:true
    }
}
