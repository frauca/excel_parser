package frauca

import frauca.readers.BaseReader
import grails.transaction.Transactional

@Transactional
class ReaderService {

	def fileSourceService
	
    /**
     * Persist all files to database
     * @param path
     * @return
     */
    def readFile(String path) {
		log.error "reading "+path
		fileSourceService.importAllFiles(path)
    }
	
	/**
	 * It will test all the files that are on pending states
	 * @return
	 */
	def processAllSourceFiles(){
		processAllNewFiles()
	}
	
	/**
	 * Process all Files that are on new state
	 * @return
	 */
	def processAllNewFiles(){
		FileSource.findAllByState("new").each {fs ->
			process(fs)
		}
	}

	
	/**
	 * Process a file source
	 * @param FileSource
	 * @return
	 */
	def process(FileSource file){
		BaseReader reader=new BaseReader(file)
		log.debug "Read all moves ${reader.bnkReader}"
		AccountMovRaw[] movs = reader.readAllMovements()
		log.debug "show all movs ${movs.size()}"
		movs.each {
			log.info "${it.concept} ${it.conceptRaw} ${it.valueDate} ${it.operationDate} ${it.amount} ${it.totalAmount}"
		}
	}
}
