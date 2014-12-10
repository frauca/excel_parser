package frauca.readers

import frauca.readers.sheetables.*

class BaseReader {

	def sheetable
	String path
	
	// first default constructor 
    BaseReader (String path) {
        super()
        this.path=path
        afterInit()
    }

    // second default constructor
    BaseReader (java.util.LinkedHashMap map) {
        super(map)
        afterInit()
        
    }
    
    def afterInit(){
		//Find the reader who could read file
		log.info "try with IESH"
		//IESheetable st=new IESheetable(path)
    }
}
