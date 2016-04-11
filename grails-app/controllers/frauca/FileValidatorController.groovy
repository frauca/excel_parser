package frauca

import frauca.command.filevalidators.FileValidatorBind
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
		FileValidatorBind[] restmp=fileValidatorService.process(FileSource.get(fsid))
		def res=[]
		restmp.each {bind->
			def map=[:]
			map['countMatches']=bind.countMatches
			map['orderOfDoc']=bind.orderOfDoc
			
			map['correctMov']=[:]
			map['correctMov']['id']=bind.correctMov.id
			map['correctMov']['operationDate']=bind.correctMov.operationDate
			map['correctMov']['valueDate']=bind.correctMov.valueDate
			map['correctMov']['amount']=bind.correctMov.amount
			map['correctMov']['concept']=bind.correctMov.concept
		
			map['possibles']=[]
			bind.possibles.each {mov->
				def map2=[:]
				map2['operationDate']=mov.operationDate
				map2['amount']=mov.amount
				map2['concept']=mov.concept
				map2['id']=mov.id
				map['possibles']+=map2
			}
			
			res+=map
		}
		render(res  as JSON)	
	}
}
