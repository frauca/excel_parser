package frauca

import frauca.readers.banc.BaseBankReader;
import grails.transaction.Transactional

/**
 * @author rofc
 * This service will manage the AccountMov and AccountMovRaw instances
 */
@Transactional
class AccountMovRawService {

    def saveAllMov(FileSource fileSource,AccountMovRaw[] movs) {
		log.debug "${movs.size()} raw movements are going to be save"
		movs.each {mov->
			mov.sourceFile=fileSource
			mov.save()
			if(mov.hasErrors()){
				log.error "could not save there are" + mov.errors.allErrors.size()
				mov.errors.allErrors.each {error->
					log.debug "error: "+error.toString()
				}
				
			}
		}
    }
	
	/**
	 * Process all the new movements that has not been processed and move them to AccountMov or marc it as duplicated
	 * @return
	 */
	def processAllPendingMoves(BaseBankReader bnkReader){
		log.trace "the new state move accounts are going to be saved"
		def newMoves=AccountMovRaw.findAllByState("new")
		log.debug "${newMoves.size()} accountmoves are going to be procesed"
		newMoves.each {rawmove->
			AccountMov mov = bnkReader.findRawMove(rawmove)
			log.trace "find "+mov
			if(mov){
				log.trace "is duplicated"
				rawmove.duplicatedBy(mov)
			}else{
				log.trace "creating new mov"
				mov= new AccountMov(rawmove)
				mov = rawmove.copiedBy(mov)
				saveAndPrintErrors(rawmove)
				saveAndPrintErrors(mov)
			}
		}
	}
	
	/**
	 * The find movement is done in the basebank to let in the future find by diferent way depending on the bank
	 *
	 * Move out because on the inital plan there is a transitien state that i could not resolve, so i moved it out
	 * @param rawmove
	 * @return
	 */
	public AccountMov findRawMove(AccountMovRaw rawmove){
		def dup = AccountMov.where{
			operationDate == rawmove.operationDate
			concept == rawmove.concept
			amount == rawmove.amount
		}
		return dup.find()
	}
	
	
	/**
	 * save the object and print the errors if it has errors.
	 * 
	 * @param mov
	 */
	public void saveAndPrintErrors(def mov){
		mov.save()
		if(mov.hasErrors()){
			mov.errors.allErrors.each{log.error "could not save account "+it}
		}
	}
}
