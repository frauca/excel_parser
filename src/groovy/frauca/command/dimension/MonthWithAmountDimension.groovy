package frauca.command.dimension

import frauca.AccountMov;
import frauca.command.times.Month;

class MonthWithAmountDimension extends TotalDimension {

	def months= [:];
	
	def process(AccountMov mov){
		def date=mov.operationDate;
		Month month=new Month(year:date[Calendar.YEAR],month:date[Calendar.MONTH] );
		AccountsDimension dim=months[month.toString()];
		if(dim==null){
			dim = new AccountsDimension();
			months[month.toString()]=dim;
		}
		dim.process(mov);
		super.process(mov);
	}
	def removeDetails(){
		super.removeDetails();
		months.entrySet().each { entry ->
		  entry.value.removeDetails();
		}
	}
}
