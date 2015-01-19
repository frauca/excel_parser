package frauca

import grails.rest.RestfulController;

class AccountMovRawController extends RestfulController {

	static responseFormats = ['json', 'xml']

	AccountMovRawController(){
		super(AccountMovRaw)
	}
	
   
}
