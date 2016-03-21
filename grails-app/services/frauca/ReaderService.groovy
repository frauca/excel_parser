package frauca

import javax.transaction.Transactional;

import frauca.readers.BaseReader
import frauca.readers.banc.BaseBankReader;
import grails.transaction.NotTransactional


@Transactional
class ReaderService {
	
	def fileSourceService
	def accountMovRawService
	
    /**
     * Persist all files to database
     * @param path
     * @return
     */
    def readFile(String path) {
		log.debug "reading "+path
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
		log.debug "process all new files"
		FileSource.findAllByState("new").each {fs ->
			process(fs)
		}
		log.debug "all new files procesed"
	}

	
	/**
	 * Process a file source
	 * @param FileSource
	 * @return
	 */
	def process(FileSource file){
		log.debug "process "+file.path
		BaseReader reader=new BaseReader(file)
		if(!reader.bnkReader){
			log.debug "Could not read the file"
			file.state="error_not_readed"
		}else{
			log.debug "read all moves"
			AccountMovRaw[] all=reader.readAllMovements();
			log.debug "set order of Document"
			accountMovRawService.setOrder(all);
			accountMovRawService.saveAllMov(file,all)
			file.state="parsed"
			file.account=reader.getAccount()
		}
		file.save()
		if(reader.bnkReader){
			accountMovRawService.processAllPendingMoves(reader.bnkReader)
		}
	}
	
}
