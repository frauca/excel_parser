package frauca

import grails.transaction.Transactional

@Transactional
class QueryService {

    def totalMovsAmount(ccc=null) {
		def query =""""""
		def ands=""
		if(ccc){
			ands +=" and account=${ccc}"
		}
		log.info "totalAmount ${query}"
		def res=[total:AccountMov.executeQuery("select sum(amount) from AccountMov where 1=1"+ands)[0]?.round(2)
				,ingresos:[
						total:AccountMov.executeQuery("select sum(amount) from AccountMov where amount>=0"+ands)[0]?.round(2)
						,categoria:AccountMov.executeQuery("""select categoritzation.category.name,sum(amount) 
																	from AccountMov where  amount>=0 """
													+ands+" group by categoritzation.category.name")
						,concepte:AccountMov.executeQuery("""select categoritzation.category.name,concept,sum(amount) 
																	from AccountMov where  amount>=0 """
													+ands+" group by categoritzation.category.name,concept")
						]
				,gastos:[
						total:AccountMov.executeQuery("select sum(amount) from AccountMov where amount<0"+ands)[0]?.round(2)
						,categoria:AccountMov.executeQuery("""select categoritzation.category.name,sum(amount) 
																	from AccountMov where  amount<0 """
													+ands+" group by categoritzation.category.name")
						,concepte:AccountMov.executeQuery("""select categoritzation.category.name,concept,sum(amount) 
																	from AccountMov where  amount<0 """
													+ands+" group by categoritzation.category.name,concept")]
				,categoria:AccountMov.executeQuery("""select categoritzation.category.name,sum(amount) 
																	from AccountMov where 1=1 """
													+ands+" group by categoritzation.category.name")
				,detalls:AccountMov.executeQuery("from AccountMov where amount>=0"+ands)
			]
		return res

    }
	
}
