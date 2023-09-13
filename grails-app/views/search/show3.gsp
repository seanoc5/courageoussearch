<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'search.text', default: 'Search')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container-fluid">
            <section class="row">
                <a href="#show-search" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                        <li><g:link class="show" action="browse" id="${search.id}">
                            <g:message code="default.browse.label" args="[entityName]"/></g:link>
                        </li>

                    </ul>
                </div>
            </section>

            <section class="row">
                <div id="show-search" class="col-12 content scaffold-show" role="main">
                    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <f:display bean="search"  />
%{--                    <f:display bean="search" order="query, searchResults, tags" />--}%
                    <g:form resource="${this.search}" method="DELETE">
                        <fieldset class="buttons">
                            <g:link class="edit" action="edit" resource="${this.search}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                        </fieldset>
                    </g:form>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
