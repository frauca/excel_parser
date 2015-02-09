package frauca

import java.util.Date;

import grails.rest.RestfulController;

class AccountMovController extends RestfulController<AccountMov>{

	AccountMovController(){
		super(AccountMov)
	}
	
	
	def index(Integer max) {
		def q=AccountMov.where{}
		if(params.file){
			q=q.where{original{sourceFile{id==params.file}}}
		}
		if(params.ccc){
			q=q.where{account{id==params.ccc}}
		}
		respond q.list(params).collect(){
			[
				id:it.id,
				operationDate:it?.operationDate?.format("yyyy/MM/dd"),
				valueDate:it?.valueDate?.format("yyyy/MM/dd"),
				concept:it.concept,
				amount:it.amount,
				total:it.totalAmount,
				categoritzaion:it.categoritzation?.id,
				categoryName:it.categoritzation?.category?.name,
				categoryType:it.categoritzation?.type
			]
		}
	}
}
