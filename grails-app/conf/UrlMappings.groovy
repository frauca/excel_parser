class UrlMappings {

	static mappings = {
		
		"/acount_raw_movs"(resources:"accountMovRaw")
		
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
