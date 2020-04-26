package frauca.readers.banc

import java.io.File;

class CaixaBankReader4MecaguenTusMuertos extends CaixBankReader  {
	
	@Override
	protected CaixBankReader instance(){
		return new CaixaBankReader4MecaguenTusMuertos();
	}

	@Override
	public Object itsMine() {
		log.trace "Its caix ${sheettable.getCeilValue('F3')}==Saldo"
		return "Saldo".equalsIgnoreCase(sheettable.getCeilValue("F3"))
	}
	
	@Override
	public Object getOperationDateCell(int row) {
		sheettable.getCeilValue("A"+row)
	}

	
	@Override
	public Object getValueDateCell(int row) {
		sheettable.getCeilValue("B"+row)
	}

	@Override
	public Object getConcpetCell(int row) {
		sheettable.getCeilValue("C"+row)+" "+sheettable.getCeilValue("D"+row)
	}

	@Override
	public Object getAmountCell(int row) {
		
		Double pagos=getDoubeVale(sheettable.getCeilValue("E"+row))
		if(!pagos){
			pagos=0
		}
		return pagos;
	}

	@Override
	public Object getTotalAmountCell(int row) {
		sheettable.getCeilValue("F"+row)
	}
}
