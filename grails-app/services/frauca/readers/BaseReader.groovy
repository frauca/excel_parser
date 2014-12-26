package frauca.readers

import frauca.AccountMovRaw;
import frauca.FileSource
import frauca.readers.banc.BarBankReader
import frauca.readers.banc.BaseBankReader
import frauca.readers.banc.IngBankReader
import frauca.readers.banc.MedBankReader
import frauca.readers.sheetables.*

class BaseReader {

	FileSource fileS
	File file
	BaseBankReader bnkReader


	// first default constructor
	BaseReader (FileSource file) {
		super()
		this.fileS=file
		this.file=new File(fileS.path)
		afterInit()
	}

	// second default constructor
	BaseReader (java.util.LinkedHashMap map) {
		super(map)
		afterInit()
	}

	def afterInit(){
		//Find the reader who could read file
		log.debug "Init from path "+fileS.path
		findSuitableBankReader(file)
	}

	
	
	def findSuitableBankReader(File file){
		BaseBankReader[] suitables = [new BarBankReader(), new IngBankReader()
							,new MedBankReader()]
		suitables.find {suitable->
			try{
				log.debug "try to read it with ${suitable}"
				bnkReader = suitable.iCouldHandle(file)
				return bnkReader
			}catch(e){
				log.debug "could not read it with ${suitable}"
				log.trace "reason",e
			}
		}
		return null //no one could read it
	}
	
	/**
	 * @return all the rows of the file
	 */
	AccountMovRaw[] readAllMovements(){
		log.debug "Reading from ${bnkReader}"
		 bnkReader.readAllMovements()
	 }
}
