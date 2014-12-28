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
	/**
	 * Tell about the process for read its
	 */
	String state
	/**
	 * Wich row was on the document
	 */
	long rowOfDoc
	/**
	 * If it has been correctly imported (state=imported) then it point to the corresponding movent
	 */

	static belongsTo = [sourceFile:FileSource,mov:AccountMov]
	
    static constraints = {
		operationDate blank:false
		valueDate blank:false
		amount blank:false
		totalAmount blank:false
		sourceFile blank:false
		rowOfDoc blank:true 
		mov nullable: true
		state inList: ["new", "copied", "duplicated"]
    }
	
	AccountMovRaw(){
		state="new"
	}
	
	/**
	 * Set the moved as duplicated, no save operation is performed
	 * @param mov
	 * @return
	 */
	def duplicatedBy(AccountMov mov){
		state="duplicated"
		this.mov=mov
	}
	
	/**
	 * Make a move from this one, and change the state to copied
	 * @return
	 */
	AccountMov copiedBy(AccountMov mov){
		state="copied"
		this.mov=mov
	}
}
