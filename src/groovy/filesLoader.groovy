import org.apache.commons.logging.LogFactory
import frauca.FileSource

def readFile(def path){
    println "reading"
    c = ctx.getBean('readerService')
    c.readFile(path)
    c.processAllSourceFiles()
    println "readed"
}

def showAllFiles(){
    FileSource.findAll().each {
    
        println "File ${it.path} ${it.state}"
    }
}

def showAllMovsAllFiles(){
	FileSource.findAll().each {
		println "File ${it.path} ${it.state}"
		it.rawMovs.each {mov->
			println "\t Move ${mov.concept} ${mov.operationDate}"
		}
	}
}

readFile('D:/proyectos/docs/personal/learn/docs/ing.ods')

 //showAllFiles()