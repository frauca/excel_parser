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
		if(params.uncat=="true"){
			q=q.where{isNull("categoritzation")}
		}
		if(params.unSubCat=="true"){
			q=q.where{categoritzation{isNull("subcat")}}
		}

		if(params.category){
			q=q.where{categoritzation{category{id==params.category}}}
		}
		if(params.year){
			q=q.where{year(valueDate)==params.year}
		}
		if(params.month){
			q=q.where{month(valueDate)==params.month}
		}
		respond q.list(params).collect(){
			[
				id:it.id,
				operationDate:it?.operationDate?.format("yyyy/MM/dd"),
				valueDate:it?.valueDate?.format("yyyy/MM/dd"),
				concept:it.concept,
				conceptRaw:it?.conceptRaw,
				amount:it.amount,
				total:it.totalAmount,
				categoritzaion:it.categoritzation?.id,
				categoryName:it.categoritzation?.category?.name,
				categoryType:it.categoritzation?.type,
				filePath:it?.original?.sourceFile?.path,
				ccc:it?.account?.name
			]
		}
	}

	def showUncatPending() {
		def res;
		if(params.concept){
			res = categorizerService.categorizedByConcept(params.concept)
		}else{
			res = categorizerService.orderedUncategorizedConcepts()
		}

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
	def similarConcepts(long id){
		def res = AccountMov.executeQuery("select distinct(concept) from AccountMov where conceptRaw = (select conceptRaw from AccountMov where id=?)",id)
		switch(params.format){
			case  "xml":
				render( res  as XML)
			default:
				render( res  as JSON)
		}
	}
}

