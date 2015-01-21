package frauca

import grails.rest.RestfulController;

class AccountMovRawController extends RestfulController<AccountMovRaw> {

	static responseFormats = ['json', 'xml']

	AccountMovRawController(){
		super(AccountMovRaw)
	}
	
    def index() { 
		def res=AccountMovRaw.list(max:3)
		respond res, model:[movRawCount:res.size()]
	}
}
