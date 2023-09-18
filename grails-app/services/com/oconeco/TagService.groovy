package com.oconeco

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

interface ITagService {

    Tag get(Serializable id)

    List<Tag> list(Map args)

    Long count()

    void delete(Serializable id)

//    Tag save(Tag tag)

}
@Transactional
@Service(Tag)
abstract class TagService implements ITagService {

    @Transactional
    Tag save(tag){
        log.info "Tag($tag) "
        if(tag.validate()){
            def foo = tag.save(flush:true, failOnError:true)
            log.info "Saved foo: $foo"
        }
        return tag
    }
}
