package frauca.utils

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class StringUitlsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test replaceLast"() {
		given:
		expect:
			"ADAGIO, S.A."==StringUtils.replaceLast("ADAGIO, S.A. -BARCELONA", "-", "").trim() 
    }
	
	void "test removeAnyReplacements"(){
		given:
			String[] replacements = [/RECIBO SEPA \d+  /,/TARGETA \*\d+ /,/CAIXER TARG\. \*\d+ /]
		expect:
			"AJUNTAMENT DE BARCELONA" ==StringUtils.removeAnyReplacements("RECIBO SEPA 123456  AJUNTAMENT DE BARCELONA",replacements)
			"AUTOPISTAS TERR CAN SERR" ==StringUtils.removeAnyReplacements("TARGETA *1234 AUTOPISTAS TERR CAN SERR",replacements)
			"ENTITAT" ==StringUtils.removeAnyReplacements("CAIXER TARG. *1234 ENTITAT", replacements)
	}
}
