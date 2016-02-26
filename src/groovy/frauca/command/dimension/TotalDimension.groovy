package frauca.command.dimension

import frauca.AccountMov;

class TotalDimension {
	Float total=0;
	Float incomes=0;
	Float outcomes=0;
	AccountMov[] details=[];
	
	def process(AccountMov[] toProcess){
		total=incomes=outcomes=0;
		toProcess.each { mov->
			process(mov);
		}
	}
	
	def process(AccountMov mov){
		total+=mov.amount;
		details+=mov;
		if(mov.amount>0){
			incomes+=mov.amount;
		}else{
			outcomes+=mov.amount;
		}
	}
	
	def removeDetails(){
		details=[];
	}
}
