package frauca

class AccountMovRaw {
	
	Date operationDate
	Date valueDate
	Double amount
	Double totalAmount
	/**
	 * Concept procesed
	 */
	String concept
	/**
	 * Concept excatly as excel file
	 */
	String conceptRaw

    static constraints = {
    }
}
