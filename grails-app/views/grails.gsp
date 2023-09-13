<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>

<body>

%{--<div class="svg" role="presentation">--}%
%{--    <div class="grails-logo-container">--}%
%{--        <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>--}%
%{--    </div>--}%
%{--</div>--}%

<div id="content" role="main">
    <div class="container">
        <section class="row colset-2-its">
            <h1>Welcome to Grails</h1>

            <p>
                Congratulations, you have successfully started your first Grails application! At the moment
                this is the default page, feel free to modify it to either redirect to a controller or display
                whatever content you may choose. Below is a list of controllers that are currently deployed in
                this application, click on each to execute its default action:
            </p>

            <div id="controllers" role="navigation" class="col-4">
                <h2>Available Controllers:</h2>
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
                        <li class="controller">
                            <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div>

            <div class="col-6">
                <p>Basics</p>
                <ul class="_dropdown-menu">
                    <li class="dropdown-item">Environment: ${grails.util.Environment.current.name}</li>
                    <li class="dropdown-item">App profile: ${grailsApplication.config.getProperty('grails.profile')}</li>
                    <li class="dropdown-item">App version: <g:meta name="info.app.version"/></li>
                    <li role="separator" class="dropdown-divider"></li>

                    <li class="dropdown-item">Grails version: <g:meta name="info.app.grailsVersion"/></li>
                    <li class="dropdown-item">Groovy version: ${GroovySystem.getVersion()}</li>
                    <li class="dropdown-item">JVM version: ${System.getProperty('java.version')}</li>
                    <li role="separator" class="dropdown-divider"></li>

                    <li class="dropdown-item">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
                </ul>

                <p>Grails thingies</p>
                <ul class="">
                    <li class="dropdown-item">Controllers: ${grailsApplication.controllerClasses.size()}</li>
                    <li class="dropdown-item">Domains: ${grailsApplication.domainClasses.size()}</li>
                    <li class="dropdown-item">Services: ${grailsApplication.serviceClasses.size()}</li>
                    <li class="dropdown-item">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
                </ul>

                <p>Plugins</p>
                <ul class="">
                    <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                        <li class="dropdown-item">${plugin.name} - ${plugin.version}</li>
                    </g:each>
                </ul>
            </div>
        </section>
    </div>
</div>

</body>
</html>
