package com.oconeco

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

interface ISearchConfigurationService {

    SearchConfiguration get(Serializable id)

    List<SearchConfiguration> list(Map args)

    Long count()

    void delete(Serializable id)

    SearchConfiguration save(SearchConfiguration searchConfiguration)

    List<SearchConfiguration> findAllByLabelIlike(String label)

}

@Service(Search)
abstract class SearchConfigurationService implements ISearchConfigurationService {

    @Transactional
    def updateBraveHeaderTokens(List<SearchConfiguration> configs, String tokenToSet) {
        log.info "Call to cheater SystemService.updateBraveHeaderTokens(--redacted--) to set sample configs (${configs}) with valid token (which user/developer gets from: https://api.search.brave.com/app/keys)"
//        List<SearchConfiguration> configs = SearchConfiguration.findAllByLabelIlike("")
        configs.each {
            String s = it.headersJson
            if(s.contains(SystemService.TOKEN_PLACEHOLDER)){
                String updated = s.replaceAll(SystemService.TOKEN_PLACEHOLDER, tokenToSet)
                it.headersJson = updated
                log.info "updated old value($s)..."
                if(it.validate()) {
                    it.save()
                } else {
                    log.warn "Validation errors: ${it.errors}"
                }
            }
        }
        log.info "updated brave configs to update: $configs"
    }


}
