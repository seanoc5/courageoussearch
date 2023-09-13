<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'search.text', default: 'Search')}"/>
    %{--    <title><g:message code="default.show.text" args="[entityName]"/></title>--}%
    <title><g:message message="Browse" args="[entityName]"/></title>

</head>

<body>
<div id="content" role="main">
    <div class="container-fluid">
        <section class="row">
            <a href="#show-search" class="skip" tabindex="-1"><g:message default="Skip to content&hellip;"/></a>

            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="list" action="index">
                        <g:message code="default.list.label" args="[entityName]"/></g:link>
                    </li>
                    <li><g:link class="create" action="create">
                        <g:message code="default.new.label" args="[entityName]"/></g:link>
                    </li>
                    <li><g:link class="show" action="browse" id="${search.id}">
                        <g:message code="default.browse.label" args="[entityName]"/></g:link>
                    </li>
                </ul>
            </div>
        </section>

        <section class="row">
            <div id="show-search" class="col-12 content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]"/></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>

                <f:display bean="search" property="searchTemplates"/>
            %{--                <h3>Search Template:</h3>--}%
            %{--                <g:each in="${search.searchTemplates}" var="templ" status="i">--}%
            %{--                    <f:display bean="${search.searchTemplates[i]}"/>--}%
            %{--                </g:each>--}%

            </div>
        </section>

        %{--        <section class="row">--}%
        %{--            <H3>Search Results</H3>--}%
        %{--            <ul>--}%
        %{--                <g:each in="${search.searchTemplates}" var="templ" status="i">--}%
        %{--                    <li>${templ}--}%
        %{--                    --}%%{--                    <f:display bean="searchTemplate" property="${templ}"/>--}%
        %{--                    </li>--}%
        %{--                </g:each>--}%
        %{--            </ul>--}%
        %{--            --}%%{--            <f:display bean="searchResults" property="${searchResults}"/>--}%

        %{--        </section>--}%

        <section class="row">

            <g:form resource="${this.search}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.search}"><g:message
                            code="default.button.edit.label" default="Edit"/></g:link>
                    <input class="delete" type="submit"
                           value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                           onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                </fieldset>
            </g:form>
    </div>
</section>
</div>
</div>
</body>
</html>
