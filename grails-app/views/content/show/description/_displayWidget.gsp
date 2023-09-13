%{--<g:link controller="team" action="show" id="${value.id}">${value.name}</g:link>--}%
%{--<p class="attention" title="content/show/description/_displayWidge.gsp">Display widget</p>--}%
%{--note: CSS was preventing me from seeing the styled (strong) tags for result highlighting from Brave--}%
%{--<g:set var="foo" value="${'More text inside <strong>strong</strong> test'}" />--}%
%{--<p>My test ${raw(foo)} thingie</p>--}%
%{--<p class="foo">${value}</p>--}%
<p class="foo">${raw(value)}</p>
%{--<div class="foo">${raw(value)}</div>--}%
%{--<div style="color:gray;"><g:encodeAs codec="raw">${value}</g:encodeAs></div>--}%
%{--<div>${raw('<b>bold</b><strong>strong</strong>')}</div>--}%
