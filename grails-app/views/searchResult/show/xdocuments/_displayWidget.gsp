<div class="attention" hidden="hidden" title="searchResult/show/list/_displayWidget.gsp">LIST: _displayWidget.gsp</div>

<div class="doc-list">
    <g:each in="${value}" var="doc" status="i">
        <div class="result">
            <div class="row doc-summary">
                <div class="col-md-2 source">
                    <g:link controller="content" action="listBySource" id="${doc.id}">${doc.source}</g:link>
                </div>

                <div class="col-md-8 title">
                    <g:link controller="content" action="show" id="${doc.id}">${doc.title}</g:link>
                </div>

                <div class="col-md-2 controls">
                    <g:link controller="content" action="show" id="${doc.id}">controls</g:link>
                </div>
            </div>

            <div class="row doc-details">
                <div class="col-md-2 ">
                    <g:link controller="content" action="show" id="${doc.id}">
                        <asset:image src="skin/database_delete.png"/>
                    </g:link>
                    <g:link controller="content" action="show" id="${doc.id}">
                        <asset:image src="skin/database_add.png"/>
                    </g:link>
                </div>

                <div class="col-md-8">
                    ${raw(doc.description)}
                </div>

                <div class="col-md-2">
                    <g:link controller="content" action="show" id="${doc.id}">${doc.id}</g:link>
                </div>

            </div>
        </div>
    </g:each>
</div>
