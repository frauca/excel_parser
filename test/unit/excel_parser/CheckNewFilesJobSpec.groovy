package excel_parser

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class CheckNewFilesJobSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

     void "test dontborder"() {
		given:
		expect:
			1+1==2
    }
}
