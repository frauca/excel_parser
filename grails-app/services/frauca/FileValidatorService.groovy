package frauca

import frauca.command.filevalidators.FileValidatorBind;
import frauca.command.filevalidators.SimpleFileValidatorMatcher;
import grails.transaction.Transactional

/**
 * @author rofc
 *
 * This services load a file with a big amount of movements. They are trated as the correct ones and look for the data on the AccountMov. It will try to find the elements that match and order them by that order. It will be the main order if it is available waile the program
 * 
 * It will try to make a screen where the user could see and re-apply all that matches. If there are movements that the service could not match then it will be show to the user to try to link them
 */
@Transactional
class FileValidatorService {

	def readerService
	
    /**
     * load All the new files. Mark them as validator type
     * @param validPath
     * @return
     */
    def loadPathForValidFiles(String validPath){
		readerService.readFile(validPath)
		log.debug "mark new files as pending"
		FileSource.findAllByState("new").each {fs ->
			log.debug "file ${fs.id} is going to be changed ${fs.state}"
			fs.state="justAdded"
			fs.type="validator"
			fs.save()
			log.debug "file ${fs.name} updated ${fs.state} ${fs.errors}"
		}
		
	}
	
	def validatorsFiles(){
		return FileSource.findAllByType("validator");
	}
	
	/**
	 * @param fs
	 * @return the diferences to be processed by the user.
	 */
	def process(FileSource fs){
		if(fs?.type!="validator"){
			throw new Exception("Could not porcess ${fs?.type} type")
		}
		if(fs?.state=="justAdded"){
			readerService.parse(fs)
			fs.save(flush:true)
		}
		if(fs?.state=="parsed"){
			AccountMovRaw[] newMoves=AccountMovRaw.executeQuery("From AccountMovRaw where sourceFile.id=? order by orderOfDoc asc",[fs.id])
			log.debug "Load all the moves from the file"
			return process(newMoves)
		}else{
			return []
		}
	}
	
	/**
	 * @param fs
	 * @return the diferences to be processed by the user.
	 */
	def process(AccountMovRaw[] rawMovs){
		def fromDate=rawMovs.min{it.operationDate}.operationDate
		def toDate=rawMovs.max{it.operationDate}.operationDate
		FileSource fs = rawMovs[0].sourceFile
		if(validateAllSameFile(rawMovs)&&fs){
			AccountMov[] movs = AccountMov.executeQuery("From AccountMov where operationDate >= ? and operationDate <= ? and account.id=? order by operationDate,original.orderOfDoc,id",[fromDate,toDate,fs.account.id]);
			return bindThem(rawMovs,movs)
		}else{
			throw new Exception("all the movs must be from the same file")
		}
	}
	
	/**
	 * @return false if not all from the same filesource
	 */
	boolean validateAllSameFile(AccountMovRaw[] rawMovs){
		if(rawMovs){
			FileSource firstfile=rawMovs[0].sourceFile
			rawMovs.each {mov->
				if(mov.sourceFile!=firstfile){
					return false
				}
			}
			
		}
		return true;
	}
	
	/**
	 * @param rawMovs
	 * @param movs
	 * @return FileValidatorBind array. with the binds founded
	 */
	def bindThem(AccountMovRaw[] rawMovs,AccountMov[] movs){
		log.debug "Bind all the rawMovs with the possible moves"
		SimpleFileValidatorMatcher matcher=new SimpleFileValidatorMatcher();
		matcher.addAllMovs(movs)
		log.debug "For each loaded mov of the excel bind its movs"
		FileValidatorBind[] binds=matcher.getTheBinds(rawMovs)
		log.debug "Try to find binders for that ones not bind directly"
		matcher.tryToCorrectSimpleUnbindings(binds)
		persistsTheBinds(binds)
		log.debug "Add all the elements not found"
		FileValidatorBind reminder=matcher.makeABindWithAllRemineders()
		if(reminder){
			binds.putAt(0,reminder)
		}
		binds.sort{bindThemSorter(it)}
		log.info("All ${rawMovs?.size()} has been bind");
		return binds
	}
	
	/**
	 * return the value to order the bind
	 * @param bind
	 * @return
	 */
	def bindThemSorter(FileValidatorBind bind){
		if(!bind){
			return 10000;
		}else if(!bind.correctMov){
				return 9999;
		}else if(!bind.correctMov.orderOfDoc){
			return 9998;
		}else{
			return bind.correctMov.orderOfDoc;
		}
	}
	
	/**
	 * mark all movs that state is new as duplicated
	 * @param rawMovs
	 * @return
	 */
	def markAsDuplicated(AccountMovRaw[] rawMovs){
		rawMovs.each {rawMov->
			if(rawMov.state=='new'){
				rawMov.state='duplicated'
				rawMov.save()
			}
		}
	}
	/**
	 * for each bind will persists the match if it is a clean one
	 * @param binds
	 * @return
	 */
	def persistsTheBinds(FileValidatorBind[] binds){
		log.debug "Persists the binds that match clean (1 to 1)"
		binds.each {bind->
			if(bind.isCleanMatch()){
				bind.persistTheAssociation()
			}
		}
	}
	
}
