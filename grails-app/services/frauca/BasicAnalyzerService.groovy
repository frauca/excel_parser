package frauca

import frauca.command.BasicAnalyzerCommand
import frauca.command.BasicAnalyzerResult
import frauca.command.dimension.AccountsDimension;
import frauca.command.dimension.WeekWithAmountDimension
import frauca.command.times.Week
import grails.transaction.Transactional

@Transactional
class BasicAnalyzerService {

	def accountMovRawService;
	
	/**
	 * return the result from the query in a BasicResult format
	 * @param qry
	 * @return
	 */
	BasicAnalyzerResult process(BasicAnalyzerCommand qry){
		def pars=[];
		log.debug("Prepare the query")
		def bscQry=basicCondition(qry,pars);
		BasicAnalyzerResult result=new BasicAnalyzerResult(query:qry);
		log.debug("Exec the query")
		AccountMov[] details=AccountMov.executeQuery("From AccountMov mov where 1=1 "+bscQry,pars);
		log.debug("Process the query")
		result.process(details);
		log.debug("query has been Procesed")
		return result;
	}
	
	
	AccountsDimension showWeekDetail(Week week){
		BasicAnalyzerCommand qry=new BasicAnalyzerCommand(fromDate:week.firstDay-1,toDate:week.lastDay+1);
		def pars=[];
		def bscQry=basicCondition(qry,pars);
		AccountMov[] details=AccountMov.executeQuery("From AccountMov mov where 1=1 "+bscQry,pars);
		WeekWithAmountDimension weekDim=new WeekWithAmountDimension();
		weekDim.process(details);
		return weekDim.weeks[week.toString()];
	}
	
	String basicCondition(BasicAnalyzerCommand qry,pars){
		String conditions="";
		if(qry.fromDate){
			conditions+=" and operationDate > ?";
			pars<<qry.fromDate;
		}
		if(qry.toDate){
			conditions+=" and operationDate < ?";
			pars<<qry.toDate;
		}
		return conditions;
	}
	
	def showYearDetailBottom(Date bottomDate,String account){
		def pars=[bottomDate,account]
		AccountMov[] bottom=AccountMov.executeQuery("From AccountMov mov where operationDate<=? and account.name=? order by operationDate desc",pars,[max:30]);
		bottom=accountMovRawService.nextNDaysByOperationDate(bottom, 2);
		bottom=bottom.reverse();
		bottom+=AccountMov.executeQuery("From AccountMov mov where operationDate>? and account.name=? order by operationDate desc",pars,[max:2]);
		return bottom;
	}
	
	def showYearDetailTop(Date topDate,String account){
		def pars=[topDate,account]
		AccountMov[] top=AccountMov.executeQuery("From AccountMov mov where operationDate<=? and account.name=? order by operationDate desc",pars,[max:30]);
		top=accountMovRawService.nextNDaysByOperationDate(top, 1);
		top+=AccountMov.executeQuery("From AccountMov mov where operationDate>? and account.name=? order by operationDate desc",pars,[max:2]);
		return top;
	}
}
