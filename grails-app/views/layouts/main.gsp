<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
    <g:layoutTitle default="System Search"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light">
    %{--    <a class="navbar-brand" href="/#"><asset:image src="oconeco_logo.jpg" alt="OconEco Logo"/></a>--}%
    <g:link controller="system" action="dashboard" class="navbar-brand"><asset:image src="oconeco_logo.jpg" alt="OconEco Logo"/></g:link>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/#">Home <span class="sr-only">(Home link)</span></a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown-search" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Search related...
                </a>

                <div class="dropdown-menu" aria-labelledby="navbarDropdown-search">
                    <g:link controller="search" class="dropdown-item">Search</g:link>
                    <g:link controller="searchResult" class="dropdown-item">Search Results</g:link>
                    <div class="dropdown-divider"></div>
                    <g:link controller="searchTemplate" class="dropdown-item">Search Templates</g:link>
                    <g:link controller="searchConfiguration" class="dropdown-item">Search Configurations</g:link>
                </div>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown-structure" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Structure related...
                </a>

                <div class="dropdown-menu" aria-labelledby="navbarDropdown-structure">
                    <g:link controller="subject" class="nav-link">Subjects</g:link>
                    <g:link controller="topic" class="nav-link">Topics</g:link>
                    <g:link controller="concept" class="nav-link">Concepts</g:link>
                    <g:link controller="context" class="nav-link">Contexts</g:link>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown-content" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Content related...
                </a>

                <div class="dropdown-menu" aria-labelledby="navbarDropdown-structure">
                    <g:link controller="content" class="nav-link">Content/Documents</g:link>
                    <g:link controller="contentFragment" class="nav-link">ContentFragments</g:link>
                </div>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown-interaction" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Interactions...
                </a>

                <div class="dropdown-menu" aria-labelledby="navbarDropdown-interaction">
                    <g:link controller="question" class="nav-link">Questions</g:link>
                    <g:link controller="answer" class="nav-link">Answers</g:link>
                    <g:link controller="statement" class="nav-link">Statements</g:link>
                    <div class="dropdown-divider"></div>
                    <g:link controller="tag" class="nav-link">Tags</g:link>
                    <g:link controller="comment" class="nav-link">Comments</g:link>
                </div>
            </li>


            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown-system" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    System
                </a>

                <div class="dropdown-menu" aria-labelledby="navbarDropdown-system">
                    <g:link controller="system" action="setup" class="nav-link" title="Development hack to get demo/starter domain objects to work with.">Dev Setup</g:link>
                    <div class="dropdown-divider"></div>

                    <g:link controller="system" class="nav-link" title="Overview/dashboard.">Overview/dashboard</g:link>
                    <g:link url="/grails" class="nav-link" title="Grails details and backend information">Grails Information</g:link>
                    <div class="dropdown-divider"></div>

                    <g:link controller="user" class="nav-link" title="">Users</g:link>
                    <g:link controller="organization" class="nav-link" title="">Organizations</g:link>
                    <div class="dropdown-divider"></div>
                    <g:link controller="analyzer" class="nav-link" title="">Analyzers</g:link>
                </div>
            </li>
        </ul>

    </div>
</nav>

<div class="container-fluid">
    <g:form action="save" controller="search" class="form-inline my-2 my-lg-0">
        <div class="row">
            <input id="search-global" class="form-control mr-sm-2 col-sm-9" type="search" name="query" placeholder="query text" value="current news">
            <button class="btn btn-outline-success my-2 my-sm-0 col-sm-3" type="submit">Search</button>
        </div>

        <div class="row">
            <label for="contexts-global" class="col-sm-3">Context:</label>
            <input id="contexts-global" class="form-control mr-sm-2 col-sm-9" type="search" name="contexts-select" placeholder="pick a context" aria-label="Context" %{--value="Current US Politics"--}%>
            <input id="context-global-id" type="hidden" name="context" %{--value="3"--}%/>
        </div>

        <div class="row">
            <label for="template-global" class="col-3">Search Template:</label>
            <input id="templates-global" class="form-control mr-sm-2 col-9" type="search" name="templates-select" placeholder="pick a search template" aria-label="Search Templates" %{--value="News from the Left and Right"--}%>
            <input id="template-global-id" type="hidden" name="searchTemplate" aria-label="SearchTemplate ID" %{--value="3"--}%/>
        </div>
    </g:form>
</div>

<g:layoutBody/>

<nav class="navbar navbar-expand-lg navbar-light" role="navigation">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
                aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
            <ul class="nav navbar-nav ml-auto">
                <g:pageProperty name="page.nav"/>
            </ul>
        </div>
    </div>
</nav>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

<script>
    $(document).ready(function () {
        // $("#search-global").autofocus(
        $("#contexts-global").autocomplete({
            source: "${createLink(controller: 'context', action: 'autoComplete')}",
            minLength: 1,
            response: function (event, ui) {
                console.info(event);
            },
            // onfocus: function ()
            select: function (event, ui) {
                // Access the display value and the hidden value
                let selectedLabel = ui.item.label;
                let selectedValue = ui.item.id;
                $("#context-global-id").val(selectedValue);

                console.log("Context Selected Label/value: " + selectedLabel + "/" + selectedValue);
            }
        });

        $("#templates-global").autocomplete({
            source: "${createLink(controller: 'searchTemplate', action: 'autoComplete')}",
            minLength: 1,
            response: function (event, ui) {
                console.info(event);
            },
            select: function (event, ui) {
                let selectedLabel = ui.item.label;
                let selectedValue = ui.item.id;
                $("#template-global-id").val(selectedValue);

                console.log("Search Template Selected Label/value: " + selectedLabel + "/" + selectedValue);
            }
        });
    });
</script>
</body>
</html>
