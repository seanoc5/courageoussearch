<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'search.text', default: 'Search')}"/>
    <title><g:message message="Browse" args="[entityName]"/></title>
</head>

<body>
<div id="content" role="main">
    <section class="container-fluid" >
        <div class="row">
            <a href="#show-browse" class="skip" tabindex="-1"><g:message default="Skip to content&hellip;"/></a>

            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                    </li>
                    <li><g:link class="list" action="index">
                        <g:message code="default.list.label" args="[entityName]"/></g:link>
                    </li>
                    <li><g:link class="create" action="create">
                        <g:message code="default.new.label" args="[entityName]"/></g:link>
                    </li>
                </ul>
            </div>
        </div>

        <div class="row">
            <div id="show-search" class="col-12 content scaffold-show" role="main">
                <h1><g:message code="default.browse.label" args="[entityName]"/></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
            </div>
        </div>

        <div id="search-data">
            <f:display bean="search" order="query, searchTemplates, context, tags, createdBy"/>

            <div class="row">
                <g:each in="${search.searchResults}" var="result" status="i">
                    <div class="col-lg-6 border-success">
                        Show Result: <g:link controller="searchResult" action="show" id="${search.searchResults[i].id}" >${search.searchResults[i]}</g:link>
                        <f:display bean="${search.searchResults[i]}" order="config, documents"/>
                    </div>
                </g:each>
            </div>
        </div>
    </section>

    %{--Todo - rearrange container--}%
    <section class="container-fluid" >
        <g:form resource="${this.search}" method="DELETE">
            <fieldset class="buttons">
                <g:link class="edit" action="edit" resource="${this.search}"><g:message
                        code="default.button.edit.label" default="Edit"/></g:link>
                <input class="delete" type="submit"
                       value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                       onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
            </fieldset>
        </g:form>
    </section>
</div>
</body>
</html>
