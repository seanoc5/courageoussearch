<%@ page import="com.oconeco.Tag" %>

<p>search/create/tags/_widget.gsp</p>
<g:select name="tag" multiple="true" from="${com.oconeco.Tag.list()}" optionKey="id" optionValue="label" title="search/create/tags/_widget.gsp"/>
