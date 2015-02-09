package frauca

import grails.rest.RestfulController

class FileSourceController extends RestfulController<FileSource>{

	FileSourceController(){
		super(FileSource)
	}
	
	def index(Integer max) {
		def q=FileSource.where{}
		if(params.ccc){
			q=q.where{account{id==params.ccc}}
		}
		respond q.list(params)
	}
	
}
