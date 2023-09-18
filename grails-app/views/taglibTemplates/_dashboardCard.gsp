<g:if test="${domainItems?.size() > 0}">
    <g:set var="domName" value="${domainItems[0].class.simpleName}"/>
</g:if>
<g:else>
    <g:set var="domName" value="${label}"/>
</g:else>

<div class="card ${domName}">
    <div class="card-body">
        <h5 class="card-title">
            ${label}
            <asset:image src="${domName.toLowerCase()}.png" alt="${domName.toLowerCase()}.png icon" class="icon"/>
        </h5>

        <g:if test="${domainItems.size() > 0}">
            <div class="card-text">
                <g:each in="${domainItems}" status="i" var="di">
                    <div class="item">
                        <g:link controller="${domName}" action="edit" id="${di.id}"><asset:image src="edit.png"></asset:image></g:link>
                        <g:link controller="${domName}" action="show" id="${di.id}">${di}</g:link>

%{--
                        <g:form action="" method="DELETE">
                            <fieldset class="buttons">
                                <g:link class="edit" action="edit" resource="${this.analyzer}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                                <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                            </fieldset>
                        </g:form>

                        <g:link controller="${domName}" action="delete" id="${di.id}"><asset:image src="delete.png"></asset:image></g:link>
--}%
                    </div>
                </g:each>
            </div>
            <g:link controller="search" class="btn btn-primary">Searches</g:link>
        </g:if>
        <g:else>
            <p>Nothing found for "${domName}"</p>
        </g:else>
    </div>
</div>
