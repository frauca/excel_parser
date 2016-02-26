package frauca.command
import org.grails.databinding.BindingFormat;

import grails.validation.Validateable


@Validateable
class BasicAnalyzerCommand {

	@BindingFormat("yyyy-MM-dd")
	Date fromDate;
	@BindingFormat("yyyy-MM-dd")
	Date toDate;
	
	static constraints = {
		fromDate(nullable: true)
		toDate(nullable:true)
	}
}
