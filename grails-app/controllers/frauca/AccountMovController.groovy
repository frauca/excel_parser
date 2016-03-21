package frauca

import grails.converters.JSON
import grails.converters.XML
import grails.rest.RestfulController

import java.text.DateFormat

class AccountMovController extends RestfulController<AccountMov>{

	def categorizerService;
	def accountMovRawService;

	AccountMovController(){
		super(AccountMov)
	}


	def genericQuery(Integer max) {
		String conds=" 1=1"
		def prs=[];
		if(params.id){
			conds+=" and mov.id=?"
			prs<<params.id.toLong()
		}
		if(params.file){
			conds+=" and mov.original.sourceFile.id=?"
			prs<<params.file.toLong()
		}
		if(params.ccc){
			conds+=" and ac.id=?"
			prs<<params.ccc.toLong()
		}
		if(params.uncat=="true"){
			conds+=" and cat.id is null"
		}
		if(params.unSubCat=="true"){
			conds+=" and cat.subcat is null"
		}

		if(params.category){
			conds+=" and ctg.id=? "
			prs<<params.category.toLong()
		}
		if(params.subcat){
			conds+=" and cat.subcat.id=? "
			prs<<params.subcat.toLong()
		}
		if(params.year){
			conds+=" and year(mov.valueDate)=? "
			prs<<params.year.toInteger()
		}
		if(params.month){
			conds+=" and month(mov.valueDate)=? "
			prs<<params.month.toInteger()
		}
		def prodlist = index2Render(conds, prs, params)
		respond prodlist 
	}

	private List index2Render(String conds,  List prs, Map params) {
		String hql ="""select mov.id
								,mov.operationDate
                                ,mov.valueDate
                                ,mov.concept
                                ,mov.conceptRaw
                                ,mov.amount
                                ,mov.totalAmount as total
                                ,cat.id as categoritzation
                                ,ctg.name as categoryName
                                ,cat.type as categoryType
                                ,ac.name as ccc
                                ,mov.original.orderOfDoc as orderOfDoc
                             from AccountMov as mov
                                 left outer join mov.categoritzation as cat
                                 left outer join cat.category ctg
                                 join mov.account as ac
                             where ${conds}
                             order by mov.operationDate desc,mov.original.orderOfDoc desc,mov.id""";
		log.debug(hql)
		def prods=AccountMov.executeQuery(hql,prs,params)
		def prodlist = prods.collect { result ->
			[ 'id': result[0],
				'operationDate': result[1].format('yyyy-MM-dd'),
				'valueDate':result[2].format('yyyy-MM-dd'),
				'concept':result[3],
				'conceptRaw':result[4],
				'amount':result[5],
				'total':result[6],
				'categoritzaion':result[7],
				'categoryName':result[8],
				'categoryType':result[9],
				'ccc':result[10] ,
			    'orderOfDoc':result[11]]
		}
		return prodlist
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
	
	def additionalInfo(long id){
		AccountMov mov=AccountMov.get(id);
		def res =['filePath':mov?.original?.sourceFile.path]
		respond res
	}
	
	def recalcTotals(String sdate,long accountId){
		Date date=new Date().parse("yyyy-MM-dd", sdate);
		def res = accountMovRawService.recalcTotals(date, accountId);
		switch(params.format){
			case  "xml":
				render( res  as XML)
			default:
				render( res  as JSON)
		}
	}
	
	def setTotals(String sdate,long accountId){
		Date date=new Date().parse("yyyy-MM-dd", sdate);
		def res = accountMovRawService.setTotals(date, accountId);
		switch(params.format){
			case  "xml":
				render( res  as XML)
			default:
				render( res  as JSON)
		}
	}
	
	def seeDiferences(String sdate,long accountId){
		Date date=new Date().parse("yyyy-MM-dd", sdate);
		def res = accountMovRawService.seeDiferences(date, accountId);
		switch(params.format){
			case  "xml":
				render( res  as XML)
			default:
				render( res  as JSON)
		}
	}
	
	def reOrderDocs(long accountId){
		def res = accountMovRawService.reOrderDocs(accountId);
		return "done"
	}
}

