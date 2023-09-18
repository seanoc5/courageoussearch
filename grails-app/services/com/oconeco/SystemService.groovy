package com.oconeco

import grails.gorm.transactions.Transactional
import groovy.json.JsonOutput

//import org.springframework.transaction.annotation.Transactional

class SystemService {
    public static final String TOKEN_PLACEHOLDER = 'REPLACE-ME-WITH-REAL-KEY'

    @Transactional
    void setupObjects() {
        try {
            log.info "begin SystemService.setupObjects()..."
            User robot = new User(firstName: 'Sourcer', lastName: "System", username: 'robot1', human: false).save()

            User u2 = new User(firstName: 'Scarlet', lastName: "Johansson", username: 'lucy').save()

            Comment commentTest = new Comment(label: 'Testing placeholder comment', description: 'bootstrap created -- delete me...', user: robot)
                    .save(flush: true)


            Context contextTesting = addContextItems(robot)

            Tag tagTest = new Tag(label: "Test", description: 'This is a test', defaultTag: true, createdBy: robot)
                    .addToComments(commentTest)
                    .save(flush: true)


            Tag tagTodo = new Tag(label: "Todo", description: 'More todo here (placeholder)', createdBy: robot)
                    .addToComments(commentTest)
                    .save(flush: true)

            Tag tagGood = new Tag(label: "Good", description: 'General positive feedback', createdBy: robot).save(flush: true);
            Tag tagBad = new Tag(label: "Bad", description: 'General negative feedback', createdBy: robot).save(flush: true);
            Tag tagBrave = new Tag(label: "Brave(API)", description: 'Related to Brave Search API', createdBy: robot).save(flush: true)

            Tag tagTech = new Tag(label: "Tech", description: 'Technology related', createdBy: robot)
                    .save(flush: true)

            Analyzer analyzer = new Analyzer(label: 'Sample Analyzer',
                    description: 'Simple "Analyzer" that does the bare minimum: print a statis message.',
                    code: 'println("Hello Test Analyzer -- replace me with something useful")', createdBy: robot)
                    .save(flush: true)


            log.info "Added basic boostrap thingies..."


            contextTesting.addToAnalyzers(analyzer)
                    .addToTags(tagTech)
                    .addToTags(tagTest)
                    .addToComments(commentTest)
                    .save(flush: true)


            // todo replace this token with a valid one from Brave:
            // https://api.search.brave.com/app/keys
            String hdrsJson = JsonOutput.toJson([Accept: "application/json", 'X-Subscription-Token': TOKEN_PLACEHOLDER])
            String paramsJson = JsonOutput.toJson([count: 20])
            SearchConfiguration cfgDefaultBrave = new SearchConfiguration(label: 'Brave Default',
                    description: "simple/general Brave API configuration",
                    url: "https://api.search.brave.com/res/v1/web/search",
                    headersJson: hdrsJson, paramsJson: paramsJson,
                    defaultConfig: true,
                    createdBy: robot)
                    .addToTags(tagTodo)
                    .addToTags(tagTodo)
                    .save(flush: true)


            String paramsTechBlogsGoggle = JsonOutput.toJson([goggles_id: 'https://github.com/brave/goggles-quickstart/blob/main/goggles/tech_blogs.goggle'])
            SearchConfiguration cfgBraveTech = new SearchConfiguration(label: 'Brave Tech Blogs goggle',
                    description: "Brave API with tech-blog oriented Goggle/filter",
                    url: "https://api.search.brave.com/res/v1/web/search",
                    headersJson: hdrsJson, paramsJson: paramsTechBlogsGoggle, defaultConfig: true,
                    createdBy: robot)
                    .addToTags(tagBrave)
                    .save(flush: true)
            String paramsLeftGoggle = JsonOutput.toJson([goggles_id: 'https://raw.githubusercontent.com/allsides-news/brave-goggles/main/left.goggles'])
            SearchConfiguration cfgBraveLeft = new SearchConfiguration(label: "Brave 'News from the LEFT' goggle",
                    description: "'News from the LEFT' Goggle/filter: https://raw.githubusercontent.com/allsides-news/brave-goggles/main/left.goggles",
                    url: "https://api.search.brave.com/res/v1/web/search",
                    headersJson: hdrsJson, paramsJson: paramsLeftGoggle, defaultConfig: false,
                    createdBy: robot)
                    .addToTags(tagBrave)
                    .save(flush: true)

            String paramsRightGoggle = JsonOutput.toJson([goggles_id: 'https://raw.githubusercontent.com/allsides-news/brave-goggles/main/right.goggles'])
            SearchConfiguration cfgBraveRight = new SearchConfiguration(label: "Brave 'News from the RIGHT' goggle",
                    description: "Brave API with 'News from the Right: https://raw.githubusercontent.com/allsides-news/brave-goggles/main/right.goggles' Goggle/filter",
                    url: "https://api.search.brave.com/res/v1/web/search",
                    headersJson: hdrsJson, paramsJson: paramsRightGoggle, defaultConfig: false,
                    createdBy: robot)
                    .addToTags(tagBrave)
                    .save(flush: true)

            Organization orgBrave = new Organization(name: 'Brave', description: 'Cool search browser and engine/api that people should checkout')
                    .save()

            Organization orgOconeco = new Organization(name: 'OconEco', description: 'Tech company(small) focused on Economics, Search, Stakeholder engagement, and a few other things...')
                    .save()

            log.info "finished setupObjects()..."

        } catch (Exception e) {
            log.error " SystemService.setupObjects() error: $e"
        }
        log.debug "finished db init setup code... (?)"
    }

    public Context addContextItems(User robot) {
        Context contextTesting = new Context(label: 'General Testing', description: 'Bootstrapped placeholder, replace me',
                defaultContext: true,
                time: 'unknown',
                location: 'unknown',
                intent: 'unknown',
                createdBy: robot)
                .save(flush: true)

        Context contextResearch = new Context(label: 'Research', description: 'General context of fact finding and related research', intent: 'Fact finding', createdBy: robot)
                .save(flush: true)

        Context contextCurrentPolitics = new Context(label: 'Current US Politics', description: 'Recent(ish) political interests, information, news for domestic United States', intent: 'Information about US national politics', createdBy: robot)
                .save(flush: true)

        Context contextLeftVsRight = new Context(label: 'Left Vs Right (political)', description: 'Compare Left/Dem and Right/Rep stances, actions, news,...', intent: 'Compare different perspectives', createdBy: robot)
                .save(flush: true)

        Context contextAnswerQuestion = new Context(label: 'Answer Question(s)', description: 'Search for answers to specific questions. More flexible than "Research" context.', intent: 'Find content to solve a specific problem', createdBy: robot)
                .save(flush: true)

        Context contextTechIssues = new Context(label: 'Technical Issues', description: 'Technical questions, issues, and various things...', intent: 'Find content related to technical issues', createdBy: robot)
                .save(flush: true)
        contextTesting
    }

}
