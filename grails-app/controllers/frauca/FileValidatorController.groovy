package frauca

import grails.converters.JSON

/**
 * @author rofc
 *
 * It will help the user to find the errors on the procesed movements. It assumes that the files are big files, with all the movements in the correct place
 * 
 * It will show the user the diferences
 */
class FileValidatorController {
	
	def fileValidatorService;

    def index() { }
	
	/**
	 * It load the directory for the valid files
	 * @param validatePath
	 * @return
	 */
	def loadPathOfValidateFiles(String validatePath){
		fileValidatorService.loadPathForValidFiles(validatePath)
		return validatorsFiles();
	}
	
	/**
	 * @return the validators files aviable
	 */
	def validatorsFiles(){
		render( fileValidatorService.validatorsFiles() as JSON)	
	}	
	
	def processFileSource(long fsid){
		render( fileValidatorService.process(FileSource.get(fsid)) as JSON)	
	}
}
