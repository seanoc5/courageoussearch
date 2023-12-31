<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'content.text', default: 'Content')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container-fluid">
            <section class="row">
                <a href="#show-content" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>

                        <li title="Try to get full page content from URL: ${content.uri}">
                            <g:link class="fetch" action="fetchContent" id="${content.id}">
                                <g:message message="Fetch Remote Content" args="[entityName]"/>
                            </g:link></li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="show-content" class="col-12 content scaffold-show" role="main">
                    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>

%{--                    <div>TEST: ${raw(content.description)}</div>--}%
                    <f:display bean="content" />

                    <g:form resource="${this.content}" method="DELETE">
                        <fieldset class="buttons">
                            <g:link class="edit" action="edit" resource="${this.content}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                        </fieldset>
                    </g:form>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
