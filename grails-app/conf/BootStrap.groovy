import frauca.Category
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
		def currentEnv = Environment.current
		
		if (currentEnv == Environment.DEVELOPMENT) {
			//new Category(name:"capricis").save()
			//new Category(name:"nomines").save()
		} else if (currentEnv == Environment.TEST) {} 
		else if (currentEnv == Environment.PRODUCTION) {}
		
    }
    def destroy = {
    }
}
