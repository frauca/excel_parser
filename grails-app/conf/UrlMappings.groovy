class UrlMappings {

	static mappings = {
		
		"/acount_movs"(resources:"accountMov")
		"/acount_raw_movs"(resources:"accountMovRaw")
		"/categoritzation"(resources:"categoritzation")
		"/category"(resources:"category")
		"/account"(resources:"account")
		"/file"(resources:"fileSource")
		"/directory"(resources:"directorySource")
		"/sqlQuery"(resources:"SQLQueries")
		
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"page")
        "500"(view:'/error')
	}
}
