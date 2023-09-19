<%@ page import="com.oconeco.Context; com.oconeco.SearchConfiguration" %>
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

<nav class="navbar navbar-expand-lg">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    %{--    <nav class="navbar navbar-expand-lg navbar-light bg-light">--}%
    <div class="container-fluid">
        <a class="navbar-brand" href="/#"><asset:image src="oconeco_logo.jpg" alt="OconEco Logo"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        %{--https://getbootstrap.com/docs/5.0/components/navbar/--}%
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">Home</a>
                </li>

                <!-- SEARCH -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown-search" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Search Related
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown-search">
                        <li><g:link controller="search" class="dropdown-item">Search</g:link></li>
                        <li><g:link controller="searchResult" class="dropdown-item">Search Results</g:link></li>

                        <div class="dropdown-divider"></div>
                        <li><g:link controller="searchConfiguration" class="dropdown-item">Search Configurations</g:link></li>
                    </ul>
                </li>

                <!-- STRUCTURE -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown-structure" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Structure Related
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown-search">
                        <li><g:link controller="subject" class="dropdown-item">Subjects</g:link></li>

                        <div class="dropdown-divider"></div>
                        <li><g:link controller="context" class="dropdown-item">Contexts</g:link></li>
                    </ul>
                </li>

                <!-- CONTENT -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown-content" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Content Related
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown-content">
                        <li><g:link controller="content" class="dropdown-item">Content/Docs</g:link></li>
                        <li><g:link controller="contentFragment" class="dropdown-item">Content Fragments</g:link></li>
                    </ul>
                </li>

                <!-- INTERACTIONS -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown-interactions" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Interactions Related
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown-content">
                        <li><g:link controller="tag" class="dropdown-item">Tags</g:link></li>
                        <li><g:link controller="comment" class="dropdown-item">Comments</g:link></li>
                    </ul>
                </li>

                <!-- SYSTEMM -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown-system" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        System Related
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown-content">
                        <li><g:link controller="system" action="setup" class="nav-link" title="Development hack to get demo/starter domain objects to work with.">Dev Setup</g:link></li>
                        <li><g:link controller="system" class="dropdown-item"></g:link></li>
                        <li><div class="dropdown-divider"/></li>
                        <li><g:link controller="system" class="nav-link" title="Overview/dashboard.">Overview/dashboard</g:link></li>
                        <li><g:link controller="system" action="grails" class="nav-link" title="Grails details and backend information">Grails Information</g:link></li>
                        <li><g:link controller="user" class="nav-link" title="">Users</g:link></li>
                        <li><g:link controller="organization" class="nav-link" title="">Organizations</g:link></li>
                        <li><div class="dropdown-divider"/></li>
                        <li><g:link controller="analyzer" class="nav-link" title="">Analyzers</g:link></li>
                    </ul>
                </li>

            </ul>
        </li>

        </ul>

        </div>
    </div>
</nav>


<div class="container-fluid">
    <g:form action="save" controller="search" class="">
    %{--        <div class="row input-group">--}%
    %{--            <input id="contexts-global" class="col-3 my-1 me-sm-2 " type="search" name="contexts-select" placeholder="pick a context" aria-label="Context" />--}%
    %{--            <input id="context-global-id" class="col-1" type="hidden" name="context" />--}%
    %{--            <label for="contexts-global" class="col-1 my-1 me-2">Context</label>--}%
    %{--        </div>--}%

    %{--        <div class="row input-group">--}%
    %{--            <input id="search-config-global" class="col-3 my-1 me-sm-2" type="search" name="search-config-select" placeholder="pick a search config" aria-label="Search Configuration" />--}%
    %{--            <input id="search-config-global-id" class="" type="hidden" name="configuration" />--}%
    %{--            <label for="search-config-global" class="col-1 my-1 me-2">Search Config</label>--}%
    %{--        </div>--}%
        <div class="row input-group">
            <g:select name="configuration"
                      from="${SearchConfiguration.list()}"
                      optionKey="id"
                      optionValue="label"
                      class="col-3 my-1 me-sm-2"/>
            <g:select name="context"
                      from="${Context.list()}"
                      optionKey="id"
                      optionValue="label"
                      class="col-3 my-1 me-sm-2"/>
        </div>

        <div class="row">
            <input id="search-global" class="form-control mr-sm-2 col-3" type="search" name="query" placeholder="query text">
            <button class="btn btn-outline-success my-2 my-sm-0 col-1" type="submit">Search</button>
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


        $("#search-config-global").autocomplete({
            source: "${createLink(controller: 'searchConfiguration', action: 'autoComplete')}",
            minLength: 1,
            response: function (event, ui) {
                console.info(ui);
            },
            select: function (event, ui) {
                let selectedLabel = ui.item.label;
                let selectedValue = ui.item.id;
                $("#search-config-global-id").val(selectedValue);

                console.log("Search Template Selected Label/value: " + selectedLabel + "/" + selectedValue);
            }
        });

    });
</script>
</body>
</html>
