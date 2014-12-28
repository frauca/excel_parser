package frauca

import java.util.Date;

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
	
	static hasMany = [rawMoves:AccountMovRaw]
	
    static constraints = {
		operationDate blank:false
		valueDate blank:false
		amount blank:false
		totalAmountRaw blank:false
		totalAmount nullable:true 
    }
	
	public AccountMov(AccountMovRaw raw){
		operationDate=raw.operationDate
		valueDate=raw.valueDate
		amount=raw.amount
		totalAmountRaw=raw.totalAmount
		concept=raw.concept
		conceptRaw=raw.conceptRaw
		addToRawMoves(raw)
	}
}
