package com.oconeco

class BootStrap {
    SystemService systemService

    def init = { servletContext ->
        environments {
            production {
                servletContext.setAttribute("env", "prod")
                log.debug "We are in PRODUCTION context (remove me)"
            }

            development {
                log.info "We are in Dev context (remove me)"
                servletContext.setAttribute("env", "dev")
                int existingUsersCount = User.count()
                if (existingUsersCount == 0) {
                    def foo = systemService.setupObjects()
                    log.info "Foo: [$foo] from systemService.setupObjects()"
                } else {
                    log.debug "Users already exist, skipping bootstrap population"
                }

                log.info "finished bootstrap code!"
            }

            test {
                servletContext.setAttribute("env", "test")
                log.info "We are in Testing context (remove me)"
            }
        }
    }

    def destroy = {
    }

}
