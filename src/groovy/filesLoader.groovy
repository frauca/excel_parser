import frauca.AccountMov
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
            println "\t MoveRaw ${mov.id} ${mov.concept} ${mov.operationDate} ${mov.amount} ${mov.state}"
        }
    }
}

def showAllMovs(){
    AccountMov.findAll().each {
        println "Move ${it.id} ${it.operationDate} ${it.concept} ${it.amount}"
    }
}

readFile('C:\\Users\\Oscar\\Desktop\\EXTRACTES\\27012015.xls')

showAllMovsAllFiles()
showAllFiles()
showAllMovs()
"Done"