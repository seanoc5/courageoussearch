<%@ page import="com.oconeco.SystemService" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'searchConfiguration.text', default: 'SearchConfiguration')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div id="content" role="main">
    <div class="container-fluid">
        <section class="row">
            <a href="#list-searchConfiguration" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
                </ul>
            </div>

            <div>
                <p>
                    <b>Note:</b> Bootstrap/SystemController.setup() code creates several (4) demo brave search configurations.
                <br/>
                    These configurations start with placeholder (invalid) tokens <span class="attention">${SystemService.TOKEN_PLACEHOLDER} </span>.
                <br/>
                    This form is a quick hack to replace that placeholder text with real tokens.
                    Go to: <a href="https://api.search.brave.com/register">Register for Brave API account...</a>
                </p>

                <g:form action="updateTokens">
                    <label for="test" class="my-1" style="width:120px;">Label:</label>
                    <g:field type="text" class="my-3" name="label" value="brave"/>
                    <br>
                    <label for="test" class="my-1" style="width:120px;">Brave API Token:</label>
                    <g:field type="text" class="my-3" name="token" value="" placeholder="replaceme..."/>
                    <br>
                    <g:submitButton name="Update Tokens..." class="save"/>
                </g:form>
            </div>
        </section>
        <section class="row">
            <div id="list-searchConfiguration" class="col-12 content scaffold-list" role="main">
                <h1><g:message code="default.list.label" args="[entityName]"/> Sean Test</h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <f:table collection="${searchConfigurationList}" properties="label, description, url, headersJson, paramsJson, createdBy"/>

                <g:if test="${searchConfigurationCount > params.int('max')}">
                    <div class="pagination">
                        <g:paginate total="${searchConfigurationCount ?: 0}"/>
                    </div>
                </g:if>
            </div>
        </section>
    </div>
</div>
</body>
</html>
