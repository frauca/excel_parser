package frauca.command.dimension

import frauca.AccountMov;
import frauca.command.times.Week;

class WeekWithAmountDimension extends TotalDimension {

	def weeks= [:];
	
	def process(AccountMov mov){
		def date=mov.operationDate;
		Week week=new Week(year:date[Calendar.YEAR],week:date[Calendar.WEEK_OF_YEAR]);
		AccountsDimension dim=weeks[week.toString()];
		if(dim==null){
			dim = new AccountsDimension();
			weeks[week.toString()]=dim;
		}
		dim.process(mov);
		super.process(mov);
	}
	
	def removeDetails(){
		super.removeDetails();
		weeks.entrySet().each { entry ->
		  entry.value.removeDetails();
		}
	}
	
}
