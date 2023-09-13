package com.oconeco

import grails.gorm.transactions.Transactional
import groovy.json.JsonOutput

//import org.springframework.transaction.annotation.Transactional

class SystemService {
    @Transactional
    void setupObjects() {
        try {
            log.info "begin SystemService.setupObjects()..."
            User robot = new User(firstName: 'Sourcer', lastName: "System", username: 'robot1', human: false).save()

            User u2 = new User(firstName: 'Sean', lastName: "O'Connor", username: 'seanoc5').save()
            User u3 = new User(firstName: 'John', lastName: "O'Connor", username: 'john').save()

            Comment commentTest = new Comment(label: 'Testing placeholder comment', description: 'bootstrap created -- delete me...', user: robot)
                    .save(flush: true)


            Context contextTesting = new Context(label: 'General Testing', description: 'Bootstrapped placeholder, replace me', time: 'unknown',
                    location: 'unknown', intent: 'unknown', createdBy: robot)
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

            ThingType typeWho = new ThingType(label: 'Who', description: 'General type of "Who"').save()
            ThingType typeWhat = new ThingType(label: 'What', description: 'General type of "What"').save()
            ThingType typeWhere = new ThingType(label: 'Where', description: 'General type of "Where"').save()
            ThingType typeWhen = new ThingType(label: 'When', description: 'General type of "When"').save()
            ThingType typeWhy = new ThingType(label: 'Why', description: 'General type of "Why"').save()

            ThingType typeStmtAffirmative = new ThingType(label: 'Affirmation', description: 'Affirmation type of "Statement"').save()
            ThingType typeNegation = new ThingType(label: 'Negation', description: 'Negation type of "Statement"').save()
            ThingType typeInconclusive = new ThingType(label: 'Inconclusive', description: 'Inconclusive type of "Statement"').save()

            Analyzer analyzer = new Analyzer(label: 'Sample Analyzer',
                    description: 'Simple "Analyzer" that does the bare minimum: print a statis message.',
                    code: 'println("Hello Test Analyzer -- replace me with something useful")', createdBy: robot)
                    .save(flush: true)


            Topic topicDefault = new Topic(label: "Testing", defaultTopic: true, createdBy: robot)
                    .addToTags(tagTodo)
                    .addToTags(tagTodo)
                    .save(failOnError: true, flush: true)

            Topic topicTech = new Topic(label: "Vetting Generative machine learning (ML)", createdBy: robot)
                    .addToTags(tagBad)
                    .addToTags(tagTodo)
                    .save(failOnError: true, flush: true)

            Topic topicPolitics = new Topic(label: "Hunter Biden's Laptop", createdBy: robot)
                    .save(failOnError: true, flush: true)

            Topic topicEconomics = new Topic(label: "Kentucky State budget spending on education", createdBy: robot, description: 'added in SystemService setupObjects()')
                    .addToTags(tagBad)
                    .save(failOnError: true, flush: true)


            Subject subjectTech = new Subject(label: 'Technology', description: 'testing general technology subject (more todo', createdBy: robot)
                    .addToTopics(topicTech)
                    .save(failOnError: true, flush: true)

            Subject subjectPolitics = new Subject(label: "Politics", description: 'General politics subject', createdBy: robot)
                    .addToTopics(topicPolitics)
                    .addToTags(tagTodo)
                    .save(failOnError: true, flush: true)

            Subject subjectEconomics = new Subject(label: "Economics", createdBy: robot)
                    .addToSubSubjects(new Subject(label: "Kentucky State Budget", description: 'added as test in bootstrap'))
                    .addToTopics(topicEconomics)
                    .save(failOnError: true, flush: true)


            Question question = new Question(text: 'What can we do to improve this?', topic: topicDefault, createdBy: robot)
                    .addToTags(tagTodo)
                    .addToComments(commentTest)
                    .save(flush: true)

            Answer answer = new Answer(label: 'Do the Work', topic: topicDefault, createdBy: robot, question: question,
                    description: 'Spend time and effort on incremental improvements.')
                    .addToComments(commentTest)
                    .addToTags(tagTodo)
//                    .addToStatements(statement)
            if (answer.validate()) {
                answer.save(flush: true)
            } else {
                log.warn "Errors with robot answer: ${answer.errors}"
            }

            log.info "Added basic boostrap thingies: (${robot}, ${u2}, ${topicPolitics}, ${topicTech}, .... ${tagTodo}, ${tagGood}, ${tagBad})"

            String hdrsJson = JsonOutput.toJson([Accept: "application/json", 'X-Subscription-Token': 'BSAUlGxLKl1TEnzyzhH755sBpllHQIw'])
            String paramsJson = JsonOutput.toJson([count: 20])
            SearchConfiguration cfgDefaultBrave = new SearchConfiguration(description: "simple/general configuration", label: 'Brave Default',
                    url: "https://api.search.brave.com/res/v1/web/search",
                    headersJson: hdrsJson, paramsJson: paramsJson, defaultConfig: true,
                    createdBy: robot)
                    .addToTags(tagTodo)
                    .addToTags(tagTodo)
                    .save(flush: true)

            SearchTemplate templateDefault = new SearchTemplate(label: "Default Template", defaultTemplate: true, description: "Default (Brave API) search template", createdBy: robot)
                    .addToSearchConfigs(cfgDefaultBrave)
                    .addToTags(tagTodo)
                    .addToTags(tagTodo)
                    .addToContexts(contextTesting)
                    .addToAnalyzers(analyzer)
                    .save(flush: true)

            String paramsTechBlogsGoggle = JsonOutput.toJson([goggles_id: 'https://github.com/brave/goggles-quickstart/blob/main/goggles/tech_blogs.goggle'])
            SearchConfiguration cfgBraveTech = new SearchConfiguration(description: "Brave API with tech-blog oriented Goggle/filter", label: 'Brave Tech Blogs goggle',
                    url: "https://api.search.brave.com/res/v1/web/search",
                    headersJson: hdrsJson, paramsJson: paramsTechBlogsGoggle, defaultConfig: true,
                    createdBy: robot)
                    .addToTags(tagBrave)
                    .save(flush: true)
            SearchTemplate templateTech = new SearchTemplate(label: "Tech/IT", description: "Focus on technology, computers, programming (Brave API)", createdBy: robot)
                    .addToSearchConfigs(cfgDefaultBrave)
                    .addToSearchConfigs(cfgBraveTech)
                    .addToTags(tagBrave)
                    .addToContexts(contextAnswerQuestion)
                    .addToAnalyzers(analyzer)
                    .save(flush: true)

            String paramsLeftGoggle = JsonOutput.toJson([goggles_id: 'https://raw.githubusercontent.com/allsides-news/brave-goggles/main/left.goggles'])
            SearchConfiguration cfgBraveLeft = new SearchConfiguration(label: "'News from the LEFT' goggle",
                    description: "'News from the LEFT' Goggle/filter: https://raw.githubusercontent.com/allsides-news/brave-goggles/main/left.goggles",
                    url: "https://api.search.brave.com/res/v1/web/search",
                    headersJson: hdrsJson, paramsJson: paramsLeftGoggle, defaultConfig: false,
                    createdBy: robot)
                    .addToTags(tagBrave)
                    .save(flush: true)
            String paramsRightGoggle = JsonOutput.toJson([goggles_id: 'https://raw.githubusercontent.com/allsides-news/brave-goggles/main/right.goggles'])
            SearchConfiguration cfgBraveRight = new SearchConfiguration(label: "'News from the RIGHT' goggle",
                    description: "Brave API with 'News from the Right: https://raw.githubusercontent.com/allsides-news/brave-goggles/main/right.goggles' Goggle/filter",
                    url: "https://api.search.brave.com/res/v1/web/search",
                    headersJson: hdrsJson, paramsJson: paramsRightGoggle, defaultConfig: false,
                    createdBy: robot)
                    .addToTags(tagBrave)
                    .save(flush: true)
            SearchTemplate templateLeftRight = new SearchTemplate(label: "News from the Left and Right",
                    description: "template with both News from the Left and News from the Right (Brave API)", createdBy: robot)
                    .addToSearchConfigs(cfgBraveLeft)
                    .addToSearchConfigs(cfgBraveRight)
                    .addToTags(tagBrave)
                    .addToContexts(contextAnswerQuestion)
                    .addToAnalyzers(analyzer)
                    .save(flush: true)

            contextTesting.addToSubjects(subjectTech)
                    .addToTopics(topicTech)
                    .addToTags(tagTech)
                    .addToTags(tagTest)
                    .addToConcepts(conceptTesting)
                    .addToComments(commentTest)
                    .addToAnalyzers(analyzer)
                    .save(flush: true)

            Content doc1 = new Content(title:'System test doc', uri:'test')
            if(doc1.validate()) {
                doc1.save()
            } else {
                log.warn "Content validation errors: ${doc1.errors}"
            }
            log.info "finished setupObjects()..."
            Content doc2 = new Content(title:'System test doc2', uri:'test').save()

            SearchResult result = new SearchResult(query: 'system test result')
            .addToDocuments(doc1)
            .addToDocuments(doc2)

            if(result.validate()) {
                result.save()
            } else {
                log.warn "SearchResult errors: ${result.errors}"
            }

        } catch (Exception e) {
            log.error " SystemService.setupObjects() error: $e"
        }
        log.debug "finished db init setup code... (?)"
    }

}
