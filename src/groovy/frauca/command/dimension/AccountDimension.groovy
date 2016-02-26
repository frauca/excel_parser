package frauca.command.dimension

import frauca.Account;
import frauca.AccountMov;

class AccountDimension extends TotalDimension{
 
	def account;
	
	def process(AccountMov mov){
		if(mov.account.name==account){
			super.process(mov);
		}
	}
	
}
