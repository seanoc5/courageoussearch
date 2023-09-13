<%@ page import="com.oconeco.Context; com.oconeco.Topic; com.oconeco.Subject" %>

<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>OconEco Search</title>
</head>

<body>
<div id="content" role="main">
    <div class="container-fluid" id="summary-section">
        <section class="row content-structure">
            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="subjects.svg" alt="Subjects Panel" class="icon"/></span>
                    <span class="card-title">Subjects</span>
                    <span class="card-text">(${Subject.count()})</span>
                    <ul>
                        <g:each in="${Subject.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="subject" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.description ?: 'no description'}">${it.label}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="subject" class="btn btn-primary">Subjects</g:link>
                </div>
            </div>

            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="topics.svg" alt="Topics Panel" class="icon"/></span>
                    <span class="card-title">Topics</span>
                    <span class="card-text">(${Topic.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.Topic.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="topic" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.description ?: 'no description'}">${it.label}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="topic" class="btn btn-primary">Topics</g:link>
                </div>
            </div>

%{--
            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="concepts.svg" alt="Concepts Panel" class="icon"/></span>
                    <span class="card-title">Concepts</span>
                    <span class="card-text">(${com.oconeco.Concept.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.Concept.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="concept" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.description ?: 'no description'}">${it.label}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="concept" class="btn btn-primary">Concepts</g:link>
                </div>
            </div>
--}%

            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="thingy.png" alt="Thing types" class="icon"/></span>
                    <span class="card-title">Thing Types</span>
                    <span class="card-text">(${com.oconeco.ThingType.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.ThingType.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="ThingType" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.description}">${it.label}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="thingType" class="btn btn-primary">Tag</g:link>
                </div>
            </div>

        </section>

        <section class="row searching" id="search-section">
            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="search.png" alt="Searches" class="icon"/></span>
                    <span class="card-title">Searches</span>
                    <span class="card-text">(${com.oconeco.Search.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.Search.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="search" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.searchTemplates.size()} templates">${it.query}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="search" class="btn btn-primary">Searches</g:link>
                </div>
            </div>

            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="template24.png" alt="Search Templates" class="icon"/></span>
                    <span class="card-title">Search Templates</span>
                    <span class="card-text">(${com.oconeco.SearchTemplate.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.SearchTemplate.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="searchTemplate" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.description ?: 'no description'}">${it.toString()}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="searchTemplate" class="btn btn-primary">Search Templates</g:link>
                </div>
            </div>

            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="configuration.png" alt="Search Configs Panel" class="icon"/></span>
                    <span class="card-title">Search Configs</span>
                    <span class="card-text">(${com.oconeco.SearchConfiguration.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.SearchConfiguration.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="searchConfiguration" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.description ?: 'no description'}">${it.label}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="searchConfiguration" class="btn btn-primary">Configurations</g:link>
                </div>
            </div>

            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="refresh-light.svg" alt="Search Results" class="icon"/></span>
                    <span class="card-title">Search Results</span>
                    <span class="card-text">(${com.oconeco.SearchResult.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.SearchResult.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="searchResult" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.query ?: 'no query??'}">${it.query}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="searchResult" class="btn btn-primary">Results</g:link>
                </div>
            </div>
        </section>

        <section class="row processing" id="processing-section">
            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="context24.svg" alt="Context Panel" class="icon"/></span>
                    <span class="card-title">Contexts</span>
                    <span class="card-text">(${com.oconeco.Context.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.Context.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="context" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.description ?: 'no description'}">${it.label}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="context" class="btn btn-primary">Contexts</g:link>
                </div>
            </div>

            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="analyzer.svg" alt="Analyzer Results" class="icon"/></span>
                    <span class="card-title">Analyzers</span>
                    <span class="card-text">(${com.oconeco.Analyzer.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.Analyzer.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="analyzer" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.label ?: 'no text??'}">${it.label}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="analyzer" class="btn btn-primary">Analyzer</g:link>
                </div>
            </div>

%{--
            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="slack.svg" alt="Vocabulary" class="icon"/></span>
                    <span class="card-title">Vocabulary</span>
                    <span class="card-text">(${com.oconeco.Vocabulary.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.Vocabulary.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="vocabulary" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.term ?: 'no term??'}">${it}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="vocabulary" class="btn btn-primary">Vocabulary</g:link>
                </div>
            </div>

            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="term.jpg" alt="Term" class="icon"/></span>
                    <span class="card-title">Term</span>
                    <span class="card-text">(${com.oconeco.Term.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.Term.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="term" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.text}">${it}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="term" class="btn btn-primary">Term</g:link>
                </div>
            </div>
--}%
        </section>


        <section class="row">
            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="question.svg" alt="Questions" class="icon"/></span>
                    <span class="card-title">Questions</span>
                    <span class="card-text">(${com.oconeco.Question.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.Question.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="question" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.topic ?: 'unknown topic'}">${it.text}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="question" class="btn btn-primary">Questions</g:link>
                </div>
            </div>


            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="answer.svg" alt="Answers" class="icon"/></span>
                    <span class="card-title">Answers</span>
                    <span class="card-text">(${com.oconeco.Answer.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.Answer.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="answer" action="show" id="${it.id}" title="${i + 1}: ${it.description ?: 'no description'}">${it.label}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="answer" class="btn btn-primary">Answers</g:link>
                </div>
            </div>

%{--
            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="statement.png" alt="Statements" class="icon"/></span>
                    <span class="card-title">Statements</span>
                    <span class="card-text">(${com.oconeco.Statement.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.Statement.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="statement" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.text}">${it.label}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="statement" class="btn btn-primary">Statement</g:link>
                </div>
            </div>
--}%

            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="tag.png" alt="Tag" class="icon"/></span>
                    <span class="card-title">Tags</span>
                    <span class="card-text">(${com.oconeco.Tag.count()})</span>
                    <ul>
                        <g:each in="${com.oconeco.Tag.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="Tag" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.description}">${it.label}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="tag" class="btn btn-primary">Tag</g:link>
                </div>
            </div>

        </section>

    </div>
</div>

</body>
</html>
