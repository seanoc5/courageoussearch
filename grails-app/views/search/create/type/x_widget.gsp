<p class="attention" title="searc/create/type/_widget.gsp">_widget.gsp</p>
%{--<g:if test="${required}">--}%
%{--    <g:textField name="${property}" value="${value}" required="required" class="form-control"/>--}%
%{--</g:if>--}%
%{--<g:else>--}%
%{--    <g:textField name="${property}" value="${value}" class="form-control"/>--}%
%{--</g:else>--}%
<g:select name="authors" multiple="true" from="${authorList}" optionKey="id" optionValue="name" />
<div class="fieldcontain">
    <label for="type">Type</label><input type="text" name="type" value="" id="type">
</div>