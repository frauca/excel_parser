package frauca.command.dimension

import frauca.Account;
import frauca.AccountMov;

class AccountsDimension extends TotalDimension {
	def accounts= [:];
	
	def process(AccountMov mov){
		def account=mov.account.name;
		AccountDimension dim=accounts[account];
		if(dim==null){
			dim = new AccountDimension(account: account);
			accounts[account]=dim;
		}
		dim.process(mov);
		super.process(mov);
	}
	
	def removeDetails(){
		super.removeDetails();
		accounts.entrySet().each { entry ->
		  entry.value.removeDetails();
		}
	}
}
