import frauca.AccountMov
import frauca.FileSource
import frauca.readers.sheetables.BaseSheetable;
import frauca.readers.sheetables.JODSheetableReader

def readFile(def path){
    println "reading"
    c = ctx.getBean('readerService')
    
    c.readFile(path)
    c.processAllSourceFiles()
    println "readed"
}

def readBarCCC(file){
    BaseSheetable ss=new JODSheetableReader(file)
    return ss.getSheetName()
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

//readFile('C:\\Users\\Oscar\\Desktop\\EXTRACTES\\27012015.xls')
println readBarCCC(new File('/home/rofc/Documents/projects/docs/personal/learn/docs/bar.ods'))
//showAllMovsAllFiles()
//showAllFiles()
//showAllMovs()
"Done"