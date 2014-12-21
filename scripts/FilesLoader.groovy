includeTargets << grailsScript("_GrailsInit")
includeTargets << grailsScript("_GrailsBootstrap")

target(showcontext: "Test to see if the context is available"){
	loadApp()
	for (grailsClass in grailsApp.allClasses) {
		println grailsClass
	}
	configureApp()
	appCtx.beanDefinitionNames.sort().each { println it }
}

target(filesLoader: "The description of the script goes here!") {
	loadApp()
	configureApp()
	c = appCtx.getBean('readerService')
	c.readFile('D:/proyectos/docs/personal/learn/docs/')
}

setDefaultTarget(filesLoader)



