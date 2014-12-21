package frauca.readers

import frauca.FileSource
import frauca.readers.banc.BaseBanckReader
import frauca.readers.banc.MedBankReader
import frauca.readers.sheetables.*

class BaseReader {

	FileSource fileS
	File file
	def stReader
	BaseBanckReader bnkReader


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
		if(findSuitableSTReader(file)){
			
		}
		
		//IESheetable st=new IESheetable(path)
	}

	/**
	 * Will try sheetable readers until find one that could read it
	 * @return
	 */
	def findSuitableSTReader(File file){
		//POI
		try{
			stReader=new POISheetableReader(file)
			log.debug "Opened with poi"
			return stReader
		}catch(e){
			log.debug "POI could not read "+file.name,e
		}
		//JOpenDocument
		try{
			stReader=new JODSheetableReader(file)
			log.debug "Openend with JOpenDocument"
			return stReader
		}catch(e){
			log.debug "JOpenDocument could not read "+file.name,e
		}
		return null
	}
	
	def findSuitableBankReader(BaseSheetable sheet){
		//Try Med
		try{
			bnkReader = new MedBankReader(sheet)
			log.info "The ccc is "+bnkReader.getAccount().rawCCC
		}catch(e){
		 log.debug "It is not a Med bank type ",e
		}

	}
}
