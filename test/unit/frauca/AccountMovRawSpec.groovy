package frauca

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(AccountMovRaw)
class AccountMovRawSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test emptySave"() {
		given:
			AccountMovRaw raw1=getNewAccountRaw()
			raw1.save()
		expect:
			0 == raw1.errors.allErrors.size()
    }
	
	AccountMovRaw getNewAccountRaw(){
		AccountMovRaw raw1=new AccountMovRaw()
		
		raw1.operationDate=new Date()
		raw1.valueDate=new Date()
		raw1.amount=10
		raw1.totalAmount=1000
		raw1.sourceFile=new FileSource(new File("c:/tmp"))
		raw1
	}
}
