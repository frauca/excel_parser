package frauca

import grails.converters.JSON
import grails.rest.RestfulController;

class AccountMovRawController extends RestfulController<AccountMovRaw> {

	def accountMovRawService;
	
	static responseFormats = ['json', 'xml']

	AccountMovRawController(){
		super(AccountMovRaw)
	}
	
    def index() { 
		def res=AccountMovRaw.list(max:3)
		respond res, model:[movRawCount:res.size()]
	}
	
	/**
	 * @param rawId
	 * @return the copied move from the raw
	 */
	def copyToMov(long rawId){
		render( accountMovRawService.copyToMov(AccountMovRaw.get(rawId)) as JSON)
		
	}
	
}


