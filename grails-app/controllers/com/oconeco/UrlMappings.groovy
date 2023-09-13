package com.oconeco

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        // custom urls
        "/subject/find/$label"(controller: "subject", action: "find")
        "/grails"(view:"/grails")

    }
}
