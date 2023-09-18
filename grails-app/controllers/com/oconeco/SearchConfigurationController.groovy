package com.oconeco

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SearchConfigurationController {

    SearchConfigurationService searchConfigurationService

    static scaffold = SearchConfiguration

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def updateTokens() {
        String token = params.token
        String label = params.label ?: 'brave'
        List configs = searchConfigurationService.findAllByLabelIlike("%${label}%")
        if (configs) {
            if (token) {
                searchConfigurationService.updateBraveHeaderTokens(configs, token)
            } else {
                log.warn "No update token found, skipping call... params:$params"
            }
        } else {
            log.warn "No valid configs found with label($label) from params($params)"
        }
        redirect action: 'index'
    }

    def autoComplete() {
        List<SearchConfiguration> configurations = SearchConfiguration.findAllByLabelIlike("%${params.term}%")
        log.debug "SearchConfigurationController autocomplete, params: $params, results: ${configurations*.label}"
        respond configurations, model: [searchConfigurationCount: searchConfigurationService.count()]
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond searchConfigurationService.list(params), model: [searchConfigurationCount: searchConfigurationService.count()]
    }

    def show(Long id) {
        respond searchConfigurationService.get(id)
    }

    def create() {
        respond new SearchConfiguration(params)
    }

    def save(SearchConfiguration searchConfiguration) {
        if (searchConfiguration == null) {
            notFound()
            return
        }

        try {
            searchConfigurationService.save(searchConfiguration)
        } catch (ValidationException e) {
            respond searchConfiguration.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'searchConfiguration.text', default: 'SearchConfiguration'), searchConfiguration.id])
                redirect searchConfiguration
            }
            '*' { respond searchConfiguration, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond searchConfigurationService.get(id)
    }

    def update(SearchConfiguration searchConfiguration) {
        if (searchConfiguration == null) {
            notFound()
            return
        }

        try {
            searchConfigurationService.save(searchConfiguration)
        } catch (ValidationException e) {
            respond searchConfiguration.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'searchConfiguration.text', default: 'SearchConfiguration'), searchConfiguration.id])
                redirect searchConfiguration
            }
            '*' { respond searchConfiguration, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        searchConfigurationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'searchConfiguration.text', default: 'SearchConfiguration'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'searchConfiguration.text', default: 'SearchConfiguration'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
