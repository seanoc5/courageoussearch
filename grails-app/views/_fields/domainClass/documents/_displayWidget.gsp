<div class="attention" hidden="hidden" title="_fields/domainClass/documents/_displayWidget.gsp">LIST: _displayWidget.gsp</div>

<div class="doc-list">
    <g:each in="${value}" var="doc" status="i">
        <div class="result">
            <div class="row doc-summary">
                <div class="col-md-2 source" title="Show all Content (docs) from this source/site (regardless of parent search)">
%{--                    <g:link url="${doc.uri}" title="Go to Source/site">${doc.source}</g:link>--}%
                    <g:link controller="content" action="listBySource" id="${doc.id}">${doc.source}</g:link>
                </div>

                <div class="col-md-8 title" title="Open the Content Show view (i.e. retrieved domain object)">
                    <g:link url="${doc.uri}" title="Go to Source/site">${doc.title}</g:link>
                    <g:link controller="content" action="show" id="${doc.id}">(${doc.id})</g:link>
                </div>

                <div class="col-md-2 controls">
                    <g:link controller="content" action="vote" params="[value: '1', type:'down']" id="${doc.id}" title="Down-Vote content/doc">
                        <asset:image src="skin/database_delete.png"/>
                    </g:link>
                    <g:link controller="content" action="vote" params="[value: '1', type:'up']" id="${doc.id}" title="Up-Vote content/doc">
                        <asset:image src="skin/database_add.png"/>
                    </g:link>
                </div>
            </div>

            <div class="row doc-details">
                <div class="col-md-2 ">
                    <g:link controller="content" action="vote" params="[value: '1', type:'down']"  id="${doc.id}" title="Down-Vote source/site">
                        <asset:image src="skin/database_delete.png"/>
                    </g:link>
                    <g:link controller="content" action="vote" params="[value: '1', type:'up']" id="${doc.id}" title="Up-Vote source/site">
                        <asset:image src="skin/database_add.png"/>
                    </g:link>
                </div>

                <div class="col-md-8">
                    ${raw(doc.description)}
                </div>

                <div class="col-md-2">
                    %{--                    <g:link controller="content" action="show" id="${doc.id}">${doc.id}</g:link>--}%
                    <g:link controller="content" action="tag" id="${doc.id}" title="Tag content/document">
                        <asset:image src="tag.png" width="22"/>
                    </g:link>
                    <g:link controller="content" action="show" id="${doc.id}" title="Show concepts found in content">
                        <asset:image src="concepts.svg" width="22"/>
                    </g:link>
                </div>

            </div>
        </div>
    </g:each>
</div>
