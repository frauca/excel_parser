package frauca

import frauca.command.BasicAnalyzerCommand;
import frauca.command.BasicAnalyzerResult;
import grails.transaction.Transactional

@Transactional
class QueryService {

    def totalMovsAmount(params) {
		def query =""""""
		def ands=""
		def pars=[]
		if(params.ccc){
			ands +=" and account=?"
			pars<<params.ccc
		}
		if(params.year){
			ands +=" and year(valueDate)=?"
			pars<<params.year.toInteger()
		}
		if(params.month){
			ands +=" and month(valueDate)=?"
			pars<<params.month.toInteger()
		}
		if(params.dateBigger){
			ands +=" and valueDate>=?"
			pars<<Date.parse("yyyy-MM-dd",params.dateBigger)
		}
		if(params.dateLower){
			ands +=" and valueDate<=?"
			pars<<Date.parse("yyyy-MM-dd",params.dateLower)
		}
		if(params.week){
			ands +=" and week(valueDate)=?"
			pars<<params.week.toInteger()
		}
		log.info "totalAmount ${ands} ${pars}"
		def res=[total:AccountMov.executeQuery("select sum(amount) from AccountMov where 1=1"+ands,pars)[0]?.round(2)
				,ingresos:[
						total:AccountMov.executeQuery("select sum(amount) from AccountMov where amount>=0"+ands,pars)[0]?.round(2)
						,categoria:AccountMov.executeQuery("""select categoritzation.category.name,sum(amount) 
																	from AccountMov where  amount>=0 """
													+ands+" group by categoritzation.category.name",pars)
						,concepte:AccountMov.executeQuery("""select categoritzation.category.name,concept,sum(amount) 
																	from AccountMov where  amount>=0 """
													+ands+" group by categoritzation.category.name,concept",pars)
						]
				,gastos:[
						total:AccountMov.executeQuery("select sum(amount) from AccountMov where amount<0"+ands,pars)[0]?.round(2)
						,categoria:AccountMov.executeQuery("""select categoritzation.category.name,sum(amount) 
																	from AccountMov where  amount<0 """
													+ands+" group by categoritzation.category.name",pars)
						,concepte:AccountMov.executeQuery("""select categoritzation.category.name,concept,sum(amount) 
																	from AccountMov where  amount<0 """
													+ands+" group by categoritzation.category.name,concept",pars)]
				,categoria:AccountMov.executeQuery("""select categoritzation.category.name,sum(amount) 
																	from AccountMov where 1=1 """
													+ands+" group by categoritzation.category.name",pars)
				,concepte:AccountMov.executeQuery("""select categoritzation.category.name,concept,sum(amount)
																	from AccountMov where 1=1 """
													+ands+" group by categoritzation.category.name,concept",pars)
				,detalls:AccountMov.executeQuery("from AccountMov where amount>=0"+ands,pars)
			]
		return res

    }
	
	
}
