<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'content.text', default: 'Content')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div id="content" role="main">
    <div class="container-fluid">
        <section class="row">
            <a href="#show-content" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
                    <li title="Try to get full page content from URL: ${content.uri}"><g:link class="fetch" action="fetchContent" id="${content.id}"><g:message message="Fetch Remote Content" args="[entityName]"/></g:link></li>
                </ul>
            </div>
        </section>
        <section class="row">
            <div id="show-content" class="col-12 content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]"/></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <f:display bean="content" order="id, title, source, uri, author, language, mimeType, type, subtype"/>

                <div class="structuredContent">${raw(content.structuredContent)}</div>

%{--
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab" aria-controls="home" aria-selected="true">Home</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="false">Profile</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact" type="button" role="tab" aria-controls="contact" aria-selected="false">Contact</button>
                    </li>
                </ul>

                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        Home information here
                    </div>

                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">Profile tab</div>

                    <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">Contact us</div>
                </div>
--}%

%{--                <section class="container py-4">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Tabs</h2>
                            <ul id="tabs" class="nav nav-tabs">
                                <li class="nav-item"><a href="" data-target="#home1" data-toggle="tab" class="nav-link small text-uppercase">Home</a></li>
                                <li class="nav-item"><a href="" data-target="#profile1" data-toggle="tab" class="nav-link small text-uppercase active">Profile</a></li>
                                <li class="nav-item"><a href="" data-target="#messages1" data-toggle="tab" class="nav-link small text-uppercase">Messages</a></li>
                            </ul>
                            <br>

                            <div id="tabsContent" class="tab-content">
                                <div id="structuredContentTab" class="tab-pane fade">
                                    <div class="list-group"><a href="" class="list-group-item d-inline-block">
                                        <span class="float-right badge badge-pill badge-dark">51</span> Home Link</a>
                                        <a href="" class="list-group-item d-inline-block">
                                            <span class="float-right badge badge-pill badge-dark">8</span> Link 2</a>
                                            <a href="" class="list-group-item d-inline-block"><span class="float-right badge badge-pill badge-dark">23</span> Link 3</a> <a href="" class="list-group-item d-inline-block text-muted">Link n..</a></div>
                                </div>
                                <div id="profile1" class="tab-pane fade active show">
                                    <div class="row pb-2">
                                        <div class="col-md-7">
                                            <p>Tabs can be used to contain a variety of content &amp; elements. They are a good way to group <a href="" class="link">relevant content</a>. Display initial content in context to the user. Enable the user to flow through <a href="" class="link">more</a> information as needed. </p>
                                        </div>
                                        <div class="col-md-5"><img src="//dummyimage.com/1005x559.png/5fa2dd/ffffff" class="float-right img-fluid img-rounded"></div>
                                    </div>
                                </div>
                                <div id="messages1" class="tab-pane fade">
                                    <div class="list-group"><a href="" class="list-group-item d-inline-block"><span class="float-right badge badge-pill badge-dark">44</span> Message 1</a> <a href="" class="list-group-item d-inline-block"><span class="float-right badge badge-pill badge-dark">8</span> Message 2</a> <a href="" class="list-group-item d-inline-block"><span class="float-right badge badge-pill badge-dark">23</span> Message 3</a> <a href="" class="list-group-item d-inline-block text-muted">Message n..</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>--}%


                <section class="container">
                    <div class="row">
                            <div id="tabsContent" class="tab-content">
                                <div id="structuredContentTab" class="tab-pane fade show">
%{--                                    ${raw(content.structuredContent)}--}%
bar
                                    foo
                                </div>

                                <div id="bodyContentTab" class="tab-pane fade show">
                                    <div class="row pb-2">
                                        <div class="col-md-7">
                                            body text
                                            ${content.bodyText}
%{--                                            <p>Tabs can be used to contain a variety of content &amp; elements. They are a good way to group <a href="" class="link">relevant content</a>. Display initial content in context to the user. Enable the user to flow through <a href="" class="link">more</a> information as needed.--}%
%{--                                            </p>--}%
                                        </div>

%{--                                        <div class="col-md-5"><img src="//dummyimage.com/1005x559.png/5fa2dd/ffffff" class="float-right img-fluid img-rounded">--}%
%{--                                        </div>--}%
                                    </div>
                                </div>
                            </div>
                        </div>
                </section>

            %{--                    <ul class="nav-pills">--}%
            %{--                        <li>--}%
            %{--                            <a href="#">Content</a>--}%
            %{--                        </li>--}%
            %{--                        <li>Structured Content</li>--}%
            %{--                    </ul>--}%

                <g:form resource="${this.content}" method="DELETE">
                    <fieldset class="buttons">
                        <g:link class="edit" action="edit" resource="${this.content}"><g:message code="default.button.edit.label" default="Edit"/></g:link>
                        <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                    </fieldset>
                </g:form>
            </div>
        </section>
    </div>
</div>
</body>
</html>
