import org.apache.commons.logging.LogFactory


println "reading"
c = ctx.getBean('readerService')
c.readFile('D:/proyectos/docs/personal/learn/docs/med1.xls')
c.processAllSourceFiles()
println "readed"