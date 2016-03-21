package frauca.command

import frauca.AccountMov;
import frauca.AccountMovRaw;
import frauca.FileSource;

class ValidateTotalsCommand {

	AccountMov mov
	AccountMovRaw movRaw
	Double total;
	Double totalRaw;
	
	def getIsValid(){
		if(mov){
			return mov.totalAmount?.round(2)==total?.round(2);
		}else {
			return false;
		}
	}
	
	def getIsValidRaw(){
		if(mov){
			return mov.totalAmountRaw?.round(2)==totalRaw?.round(2);
		}else {
			return false;
		}
	}
	
	def getSourceFile(){
		return movRaw.sourceFile
	}
}
