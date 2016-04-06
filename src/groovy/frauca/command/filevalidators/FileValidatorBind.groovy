package frauca.command.filevalidators

import frauca.AccountMov
import frauca.AccountMovRaw

/**
 * @author rofc
 * This command will bind the AccountMovRaw with the AccountMov that could 
 */
class FileValidatorBind {
	AccountMovRaw correctMov;
	AccountMov[] possibles;
	
	
	def getCountMatches(){
		if(possibles){
			return possibles.length
		}else{
			return 0;
		}
	}
	
	/**
	 * @return true if binding is one to one
	 */
	boolean isCleanMatch(){
		return getCountMatches()==1
	}
	
	/**
	 * associate the clean match to the bind one 
	 * The original of the mov will become this match
	 * @return
	 */
	def persistTheAssociation(){
		if(correctMov&&possibles&&isCleanMatch()){
			boolean hasBeenModified=false
			AccountMov mov=possibles[0]
			if(mov.original!=correctMov){
				mov.original.state='duplicated'
				mov.original.save()
				mov.original=correctMov;
				correctMov.state='copied'
				hasBeenModified=true
			}
			if(mov.totalAmountRaw!=mov.original.totalAmount){
				mov.totalAmountRaw=mov.original.totalAmount
				hasBeenModified=true
			}
			if(hasBeenModified){
				mov.save()
			}
		}
	}
	
	def getOrderOfDoc(){
		if(correctMov){
			return correctMov.orderOfDoc
		}else{
			return -1
		}
	}
}
