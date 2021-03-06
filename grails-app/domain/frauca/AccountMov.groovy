package frauca

import grails.rest.Resource


@Resource
class AccountMov {

	Date operationDate
	Date valueDate
	Double amount
	/**
	 * Total Amount without calculation
	 * 
	 */
	Double totalAmountRaw//TODO detectar files que s'han tret i  no s'haurien de treure, com moviments a autopista amb tot igual duplicat el mateix dia i que nomes varia el totalamoun
	
	/**
	 * TotalAmount calculated, to compare it with the Raw
	 */
	Double totalAmount
	/**
	 * Concept procesed
	 */
	String concept
	/**
	 * Concept excatly as excel file
	 */
	String conceptRaw
	Categoritzation categoritzation;
	Account account;
	AccountMovRaw original;
	
	static hasMany = [rawMoves:AccountMovRaw]
	
    static constraints = {
		operationDate blank:false
		valueDate blank:false
		amount blank:false
		totalAmountRaw blank:false
		totalAmount nullable:true 
		categoritzation nullable:true;
		original nullable:true
    }

	
	public AccountMov(AccountMovRaw raw){
		operationDate=raw.operationDate
		valueDate=raw.valueDate
		amount=raw.amount
		totalAmountRaw=raw.totalAmount
		concept=raw.concept
		conceptRaw=raw.concept
		original=raw
		addToRawMoves(raw)
	}
}
