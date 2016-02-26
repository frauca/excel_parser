package frauca.command.dimension

import org.apache.ivy.core.module.descriptor.ExtendsDescriptor;

import frauca.AccountMov;

class YearWithAmountDimension extends TotalDimension {

	def years= [:];
	
	def process(AccountMov mov){
		int year=mov.operationDate[Calendar.YEAR]
		AccountsDimension dim=years[year];
		if(dim==null){
			dim = new AccountsDimension();
			years[year]=dim;
		}
		dim.process(mov);
		super.process(mov);
	}
	
	def removeDetails(){
		super.removeDetails();
		years.entrySet().each { entry ->
		  entry.value.removeDetails();
		}
	}
}
