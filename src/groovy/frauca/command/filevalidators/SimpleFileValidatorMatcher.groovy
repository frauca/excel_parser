package frauca.command.filevalidators

import frauca.AccountMov
import frauca.AccountMovRaw

/**
 * @author rofc
 *
 *	This class will organize the accounts movs in two maps. The first one with the operationDate as key, the second one with the amount
 * this will let a simple matcher by keys to simple maps the AccountMovRaw to get the element. If only one is done, thent the match will be slected
 */
class SimpleFileValidatorMatcher {
	def matcherMap=[:]
	
	/**
	 * @param movs all this elements will be added to the map
	 * @return
	 */
	def addAllMovs(AccountMov[] movs){
		movs.each {mov->
			def operationDate=mov.operationDate
			def amount=mov.amount
			def list=findMov(operationDate,amount)
			list <<mov
		}
	}	
	
	def findMov(def operationDate,def amount){
		if(!matcherMap[operationDate]){
			matcherMap[operationDate]=[:]
			matcherMap[operationDate][amount]=[]
		}else if (!matcherMap[operationDate][amount]){
			matcherMap[operationDate][amount]=[]
		}
		
		return matcherMap[operationDate][amount]
	}
	
	
	def removeMov(def operationDate,def amount){
		matcherMap[operationDate].remove(amount);
		if(!matcherMap[operationDate]){
			matcherMap.remove(operationDate)
		}
	}
	/** 
	 * Find 
	 * @param rawMov
	 * @return
	 */
	def popMov(AccountMovRaw rawMov){
		def operationDate=rawMov.operationDate
		def amount=rawMov.amount
		def list=findMov(operationDate,amount)
		removeMov(operationDate,amount)
		return list
	}
	
	/**
	 * look for all unbindings where the size is diferent from 0, ang group for operation dates. 
	 * the ones unbindend that match exactly with the number of unbindend will be rebinded one by one
	 * @param bindings
	 * @return
	 */
	def tryToCorrectSimpleUnbindings(FileValidatorBind[] bindings){
		FileValidatorBind[] notBindeds=bindings.findAll({!it.isCleanMatch()})
		def byOperationDate=[:]
		log.debug "set all unbindend by operation date"
		notBindeds.each { bind->
			if(byOperationDate[bind.correctMov.operationDate]){
				if(byOperationDate[bind.correctMov.operationDate][bind.correctMov.amount]){
					byOperationDate[bind.correctMov.operationDate][bind.correctMov.amount]<<bind
				}else{
					byOperationDate[bind.correctMov.operationDate][bind.correctMov.amount]=[bind]
				}
			}else{
				byOperationDate[bind.correctMov.operationDate]=[:]
				byOperationDate[bind.correctMov.operationDate][bind.correctMov.amount]=[bind]
			}
		}
		log.debug "try to correct each by operation date"
		byOperationDate.values().each{ byAmount->
			byAmount.values().each{
				tryCorrectThisUnbinding((FileValidatorBind[])it)
			}
		}
	}
	
	
	/**
	 * It must be called from @see #tryToCorrectSimpleUnbindings
	 * if the number of binginds are equal to all the movs, then each one will be set to each bind
	 * @param bindings
	 * @return
	 */
	def tryCorrectThisUnbinding(FileValidatorBind[] bindings){
		def movs=[]
		bindings.each {bind->
			movs.addAll(bind.possibles)
		}
		if(bindings.size() == movs.size()){
			for(int i=0;i<bindings.length;i++){
				bindings[i].possibles=[movs[i]]
			}
		}
	}
	
	/**
	 * @return a new FileValidatorBind with empty correct one and all the elements not set as a remineders
	 */
	def makeABindWithAllRemineders(){
		def allReminders=[]
		matcherMap.values().each{byDate->
			byDate.values().each{
				allReminders.addAll(it)
			}
		}
		if(allReminders){
			AccountMovRaw reminder=new AccountMovRaw(concept: "Not set",orderOfDoc:0 )
			FileValidatorBind bind=new FileValidatorBind(correctMov:reminder,possibles:allReminders)
			return bind
		}
	}
	
	/**
	 * Colled from the bindThem it just make a list of binds, with the matcher binds
	 * @param rawMovs
	 * @param matcher
	 * @return
	 */
	def getTheBinds(AccountMovRaw[] rawMovs){
		FileValidatorBind[] binds=[]
		rawMovs.each {rawMov->
			def bind = new FileValidatorBind(correctMov: rawMov, possibles: popMov(rawMov))
			binds+=bind
		}
		return binds
	}
}
