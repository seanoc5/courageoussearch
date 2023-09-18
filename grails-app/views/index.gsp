<%@ page import="com.oconeco.Context; com.oconeco.User; com.oconeco.SearchConfiguration; com.oconeco.SearchResult; com.oconeco.Analyzer; com.oconeco.Organization; com.oconeco.Tag; com.oconeco.Comment; com.oconeco.Search" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
%{--    <g:set var="entityName" value="${message(code: 'subject.text', default: 'Subject')}"/>--}%
    <title>Courageous Search</title>
</head>

<body>
<div id="content" role="main">
    <div class="container-fluid">
        <section class="row">
            <h1>Welcome to 'Courageous Search'</h1>

            <div class="row">
                <div class="col-lg-3">
                    <g:dbCard domainItems="${Search.list()}" label="Search"/>
                </div>

                <div class="col-lg-3">
                    <g:dbCard domainItems="${Context.list()}" label="Context" />
                </div>

                <div class="col-lg-3">
                    <g:dbCard domainItems="${User.list()}" label="User"/>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-3">
                    <g:dbCard domainItems="${SearchConfiguration.list()}" label="SearchConfiguration"/>
                </div>

                <div class="col-lg-3">
                    <g:dbCard domainItems="${SearchResult.list()}" label="SearchResult"/>
                </div>

                <div class="col-lg-3">
                    <g:dbCard domainItems="${Analyzer.list()}" label="Analyzer"/>
                </div>

%{--                <div class="col-lg-3">--}%
%{--                    <g:dbCard domainItems="${Analyzer.list()}" class="Search"/>--}%
%{--                </div>--}%
            </div>

            <div class="row">
                <div class="col-lg-3">
                    <g:dbCard domainItems="${Organization.list()}" label="Organizations"/>
                </div>
                <div class="col-lg-3">
                    <g:dbCard domainItems="${Tag.list()}" label="Tags"/>
                </div>
                <div class="col-lg-3">
                    <g:dbCard domainItems="${Comment.list()}" label="Comments"/>
                </div>
            </div>

        </section>
    </div>
</div>
</body>
</html>
