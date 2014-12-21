package frauca.readers.banc

import frauca.Account;

class MedBankReader extends BaseBanckReader {

	@Override
	public Account getAccount() {
		def ccc = sheettable.getCeilValue("B1")
		if(ccc!=null){
			Account ac = new Account()
			ac.rawCCC = ccc
			log.debug "the ccc is on a place "+ac.rawCCC
			return ac
		}
		return null;
	}

}
