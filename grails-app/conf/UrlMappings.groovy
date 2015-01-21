class UrlMappings {

	static mappings = {
		
		"/acount_movs"(resources:"accountMov")
		"/acount_raw_movs"(resources:"accountMovRaw")
		
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"page")
        "500"(view:'/error')
	}
}
