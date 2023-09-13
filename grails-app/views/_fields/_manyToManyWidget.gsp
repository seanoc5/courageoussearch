<%@ page contentType="text/html;charset=UTF-8" %>

<div class="many-to-many-widget">
    <label>Override: ${label ?: field.propertyName}</label>
    <ul class="selected-items">
        <g:each in="${value}" var="item">
            <li>${item}</li>
        </g:each>
    </ul>
    <g:hiddenField name="${property}" value="${value?.join(',')}" />
    %{--<g:autoComplete
        id="${property}"
        name="${property}"
        value=""
        controller="content"
        action="search"
        params="[field: 'author']"
        data="${value?.join(',')}"
        delimiter=","
    />--}%
</div>
