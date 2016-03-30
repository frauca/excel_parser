package frauca

import grails.rest.RestfulController

class FileSourceController extends RestfulController<FileSource>{

	FileSourceController(){
		super(FileSource)
	}
	
	def index(Integer max) {
		def q=FileSource.where{isNull("type")}
		if(params.ccc){
			q=q.where{account{id==params.ccc}}
		}
		if(params.withRows=="true"){
			q=q.where{isNotEmpty("rawMovs")}
		}
		respond q.list(params+[sort:"creationTime",order:"desc"])
	}
	
}
