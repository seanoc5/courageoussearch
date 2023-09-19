# Notes for developers/technical folks

## Why Grails?
Because I used it back in a time long-long ago, and I thought it would be easy to jump back in _(jury is still out on that assumption)_.

But...
I still like Grails, and look forward to hopefully porting to Micronaut, which seems to be the next best thing (for me).

Please assume all code, confiruration, and even documentaiton here is suspect until proven otherwise.

I am focused on rapid proof-of-concept rather than best practices. I welcome any constructive feedback and improvements. 
That said: if something smells questionable, assume I have misuderstood something (lots of things...?).

## Points of Potential (Grails) Interest
- async calls in FetchService (gather actual content from the links returned--in addition to snippets from Brave)
- templates and fields plugin 
  - this is likely to "old school" and replaced by Angular or other SPA
  - however: possibly useful for older-timers
- Readability4j
  - Nice Kotlin package mimicking Firefox's readability JS code
  - get the content for local display, but... 
  - more importantly: get structure of doc, for splitting into paragraphs, then sentences....
  - _more to come on this..._
- Tika 
  - mimeType detection
  - parsing non-html files 
  - I could not get tika to return "formatted" text, but there is likely a good way to do so...


## Search objects layout
### Description:

A search is tpyically a specific search (user, or system generated)
It will use a sepcific SearchConfiguration
The search Config will handle performing the relevant search,
as well as _converting the results into proper persistence objects_ (search results & docs, but possibly also Solr results docs)

## outline
- Search
  - SearchConfiguration+
  - SearchResults+
    - Content/Document+



## Grails GSP Templates and Views

- https://www.youtube.com/watch?v=6pIOgv7cZzo&t=2s  minute: ~26  _wrapper.gsp example
- many-to-many ui
  - https://www.youtube.com/watch?v=snW1pTHrcXY ~minute 38:40


[Fields Plugin Docs](https://grails-fields-plugin.github.io/grails-fields/snapshot/guide/index.html#customizingFieldRendering)

changes from old to current:
* _field to _wrapper
* _input to _widget
* _display to _displayWrapper
* _displayWidget was added


### Template based
* _wrapper.gsp
  * wraps
    * label and value (probably not other templates unless coded)
    * _widget.gsp ??
* _widget.gsp
    * basic html tags/styling to **show** the value(s)
    * interesting when talking about special values or parent/child (??true??)
* _displayWrapper.gsp
  * wraps label and value
  * shows on index/table only(??)
* _displayWidget.gsp
  * just for widget (after label)
  * shows in both index/table and show/detail


### examples
_displayWrapper (show)

    <div class="form-group">
        <label class="control-label">${label}</label>
        <p class="form-control-static">${widget}</p>
    </div>

_wrapper  (edit)

    <div class="form-group ${invalid ? 'has-error' : ''}">
        <label for="${field}" class="control-label">${label} <g:if test="${required}">*</g:if></label>
        <div>
            ${widget}
            <g:if test="${errors}">
                <g:each in="${errors}" var="error">
                    <span class="help-block"><g:message error="${error}"/></span>
                </g:each>
            </g:if>
        </div>
    </div>

_widget (edit)

    <g:if test="${required}">
        <g:textField name="${property}" value="${value}" required="required" class="form-control"/>
    </g:if>
    <g:else>
        <g:textField name="${property}" value="${value}" class="form-control"/>
    </g:else>

_displayWidget (show)

    <img src="${value}"/>


### View based:
* index/list:
  * _displayWrapper
  * _displayWidget.gsp
* Show/details
  *  _displayWidget.gsp
  * not label (need to override f:field??)
* edit/form
  * _wrapper.gsp
  * _widget.gsp

### pending
* _list.gsp
* _table.gsp
* embedded.gsp

**NOTE**
      plugin:
        fields:
            disableLookupCache: true


## Embedding templates
More to come, but here is a start:

* You can customize how embedded properties are surrounded by providing a layout at grails-app/views/layouts/_fields/embedded.gsp which will override the default layout provided by the plugin.
* When you use the f:all tag it will automatically handle embedded properties in this way.
* [stack overflow answer](https://stackoverflow.com/questions/49036354/custom-fields-displayed-by-grails-fields-plugin-with-fdisplay)
  * In your _displayWrapper you should use ${widget} instead of ${value} as you want the rendering of _displayWidget to get the specific types of rendering.

### interesting options:

* grails-app/views/controllerName/actionName/propertyName/
  * g-a/v/content/show/uri
    *
* grails-app/views/controllerName/actionName/propertyType/
* grails-app/views/_fields/associationType/

[Demo repo: g3-fields-plugin-demo.git](https://github.com/sbglasius/g3-fields-plugin-demo.git)

    <g:form resource="${this.rider}" method="PUT"> \
        <g:hiddenField name="version" value="${this.rider?.version}" />
        <fieldset class="form">
            <tmpl:form/>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        </fieldset>
    </g:form>

_displayWidget.gsp

## Todo
- views-json: apply plugin:"org.grails.plugins.json-views
  - build script dependencies:  classpath "org.grails.plugins.views-gradle:1.0.1"  _(or higher version)_
  -


# other & misc
[Micronaut data info](https://medium.com/agorapulse-stories/goodbye-grails-hello-micronaut-10-micronaut-data-759c6c36bc7)
Micronaut Data JPA is more powerful and a good fit if you require some advanced ORM features such as

http://docs.grails.org/latest/guide/services.html
def author = Author.findById(id, [fetch:[books:"eager"]])
http://gorm.grails.org/latest/hibernate/manual/index.html#fetching

    static mapping = {
        flights lazy: false
    }
