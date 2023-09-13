<%@ page import="java.text.SimpleDateFormat" %>
<div class="attention" hidden="hidden" title="_fields/domainClass/documents/_displayWidget.gsp">LIST: _displayWidget.gsp</div>

<div class="nameValue">${ new SimpleDateFormat("yyyy-MM-dd").format(value)}</div>
%{--<div class="nameValue">${new Date(value).toString().split(' ')}</div>--}%
