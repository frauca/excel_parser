package frauca

import grails.rest.RestfulController;

class CategoryController extends RestfulController<Category>{

    CategoryController(){
		super(Category)
	}
	
	def index(Integer max) {
		def q=Category.where{}
		if(params.father){
			q=q.where{father{id==params.father}}
		}else{
			q=q.where {isNull("father")}
		}
		respond q.list(params+[sort:"name",order:"asc"])
	}
		
}
