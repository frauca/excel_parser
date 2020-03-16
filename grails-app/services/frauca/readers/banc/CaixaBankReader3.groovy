package frauca.readers.banc

import java.io.File;

class CaixaBankReader3 extends CaixBankReader  {
	
	@Override
	protected CaixBankReader instance(){
		return new CaixaBankReader3();
	}

	@Override
	public Object itsMine() {
		log.trace "Its caix ${sheettable.getCeilValue('E3')}==Saldo"
		return "Saldo".equalsIgnoreCase(sheettable.getCeilValue("E3"))
	}
	
	@Override
	public Object getOperationDateCell(int row) {
		getValueDateCell(row)
	}

	
	@Override
	public Object getValueDateCell(int row) {
		String with3letters = sheettable.getCeilValue("A"+row)
		java8DoNotWorkWithCatalanMonth(with3letters)
	}

	@Override
	public Object getConcpetCell(int row) {
		sheettable.getCeilValue("B"+row)+" "+sheettable.getCeilValue("C"+row)
	}

	@Override
	public Object getAmountCell(int row) {
		
		Double pagos=getDoubeVale(sheettable.getCeilValue("D"+row))
		if(!pagos){
			pagos=0
		}
		return pagos;
	}

	@Override
	public Object getTotalAmountCell(int row) {
		sheettable.getCeilValue("E"+row)
	}
	
	private String java8DoNotWorkWithCatalanMonth(String date){
		return date
		.replace(" Gen ","/01/")
		.replace(" Feb ","/02/")
		.replace(" Mar ","/03/")
		.replace(" Abr ","/04/")
		.replace(" Mai ","/05/")
		.replace(" Jun ","/06/")
		.replace(" Jul ","/07/")
		.replace(" Ago ","/08/")
		.replace(" Set ","/09/")
		.replace(" Oct ","/10/")
		.replace(" Nov ","/11/")
		.replace(" Des ","/12/")
	}
}
