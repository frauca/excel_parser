package frauca

import grails.converters.JSON
import grails.converters.XML
import grails.rest.RestfulController

class AccountMovController extends RestfulController<AccountMov>{

	def categorizerService;
	
	AccountMovController(){
		super(AccountMov)
	}
	
	
	def index(Integer max) {
		def q=AccountMov.where{}
		if(params.file){
			q=q.where{original{sourceFile{id==params.file}}}
		}
		if(params.ccc){
			q=q.where{account{id==params.ccc}}
		}
		respond q.list(params).collect(){
			[
				id:it.id,
				operationDate:it?.operationDate?.format("yyyy/MM/dd"),
				valueDate:it?.valueDate?.format("yyyy/MM/dd"),
				concept:it.concept,
				amount:it.amount,
				total:it.totalAmount,
				categoritzaion:it.categoritzation?.id,
				categoryName:it.categoritzation?.category?.name,
				categoryType:it.categoritzation?.type
			]
		}
	}
	
	def showUncatPending() {
		def res = categorizerService.orderedUncategorizedConcepts()
		switch(params.format){
			case  "xml":
				render( res  as XML)
			default:
			render( res  as JSON)
		}
		
	}
	
	def categorizedAll(String concept,long cat) {
		log.debug "all ${concept} as ${cat}"
		categorizerService.manualCatUncategorized(concept,Category.get(cat))
		render "Done"
	}
	
	def availableYears(){
		render AccountMov.executeQuery("select distinct(year(valueDate)) as years from AccountMov order by years desc")
	}
	def availableMonth(int year){
		render AccountMov.executeQuery("select distinct(month(valueDate)) as months from AccountMov where year(valueDate)=? order by months desc",[year])
	}
}

