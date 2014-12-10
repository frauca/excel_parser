package frauca

import grails.transaction.Transactional
import frauca.readers.BaseReader

@Transactional
class ReaderService {

	
	
    def readFile(String path) {
    
		BaseReader reader = new BaseReader(path)
    }
}
