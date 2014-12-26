package frauca

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ReaderService)
class ReaderServiceSpec extends Specification {

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
