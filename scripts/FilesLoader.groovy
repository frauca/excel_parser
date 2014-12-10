includeTargets << grailsScript("_GrailsInit")
includeTargets << grailsScript("_GrailsBootstrap")


target(filesLoader: "The description of the script goes here!") {
   loadApp()
	for (grailsClass in grailsApp.allClasses) { println grailsClass }
	configureApp()
	appCtx.beanDefinitionNames.sort().each { println it }
	c = appCtx.getBean('readerService')
	c.readFile('/home/riro/Documents/Banc/2014/data/2014_01_11_barclays.xls')
}

setDefaultTarget(filesLoader)



