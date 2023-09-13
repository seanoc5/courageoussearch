package com.oconeco

import grails.gorm.services.Service

@Service(SearchResult)
interface SearchResultService {

    SearchResult get(Serializable id)

    List<SearchResult> list(Map args)

    Long count()

    void delete(Serializable id)

    SearchResult save(SearchResult searchResult)

}