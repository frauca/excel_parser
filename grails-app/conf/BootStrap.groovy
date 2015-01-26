import frauca.Category;

class BootStrap {

    def init = { servletContext ->
		new Category(name:"capricis").save()
		new Category(name:"nomines").save()
    }
    def destroy = {
    }
}
