<%@ page import="com.oconeco.Search; com.oconeco.SearchConfiguration; com.oconeco.SearchResult; com.oconeco.Analyzer; com.oconeco.Context;" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>OconEco Search</title>
</head>

<body>
<div id="content" role="main">
    <div class="container-fluid" id="summary-section">

        <section class="row searching" id="search-section">
            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="search.png" alt="Searches" class="icon"/></span>
                    <span class="card-title">Searches</span>
                    <span class="card-text">(${Search.count()})</span>
                    <ul>
                        <g:each in="${Search.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="search" action="show" id="${it.id}"
                                        title="${i + 1}:${it.configuration}">${it.query}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="search" class="btn btn-primary">Searches</g:link>
                </div>
            </div>

            <div class="card summary" class="col-lg-3 mb-4">
                <div class="card-body">
                    <span><asset:image src="configure24.svg" alt="Search Configs Panel" class="icon"/></span>
                    <span class="card-title">Search Configs</span>
                    <span class="card-text">(${SearchConfiguration.count()})</span>
                    <ul>
                        <g:each in="${SearchConfiguration.list(max: 15)}" status="i" var="it">
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
                    <span class="card-text">(${SearchResult.count()})</span>
                    <ul>
                        <g:each in="${SearchResult.list(max: 15)}" status="i" var="it">
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
                    <span class="card-text">(${Context.count()})</span>
                    <ul>
                        <g:each in="${Context.list(max: 15)}" status="i" var="it">
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
                    <span class="card-text">(${Analyzer.count()})</span>
                    <ul>
                        <g:each in="${Analyzer.list(max: 15)}" status="i" var="it">
                            <li>
                                <g:link controller="analyzer" action="show" id="${it.id}"
                                        title="${i + 1}: ${it.label ?: 'no text??'}">${it.label}</g:link>
                            </li>
                        </g:each>
                    </ul>
                    <g:link controller="analyzer" class="btn btn-primary">Analyzer</g:link>
                </div>
            </div>

        </section>

    </div>
</div>

</body>
</html>
