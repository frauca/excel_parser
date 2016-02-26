package frauca.command

import frauca.AccountMov;
import frauca.command.dimension.MonthWithAmountDimension;
import frauca.command.dimension.TotalDimension;
import frauca.command.dimension.WeekWithAmountDimension;
import frauca.command.dimension.YearWithAmountDimension;

class BasicAnalyzerResult {
	BasicAnalyzerCommand query;
	TotalDimension total =new TotalDimension();
	YearWithAmountDimension year=new YearWithAmountDimension();
	MonthWithAmountDimension month=new MonthWithAmountDimension();
	WeekWithAmountDimension week=new WeekWithAmountDimension();
	TotalDimension[] all=[total,year,month,week];
	
	def process(AccountMov[] details){
		all.each {dim->
			details.each {detail->
				dim.process(detail);
			}
		}
	}
	
	def removeDetails(){
		all.each{dimension-> dimension.removeDetails()}
	}
}
