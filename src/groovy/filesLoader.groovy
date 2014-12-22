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

readFile('D:/proyectos/docs/personal/learn/docs/med1.xls')

 //showAllFiles()