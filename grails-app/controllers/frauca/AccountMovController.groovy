package frauca

import java.util.Date;

import grails.rest.RestfulController;

class AccountMovController extends RestfulController<AccountMov>{

	AccountMovController(){
		super(AccountMov)
	}
	
	
	def index(Integer max) {
		respond AccountMov.list(params).collect(){
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
