# Notes for developers/technical folks

## Why Grails?


## Search objects layout
### Description:
Templates describe a broad use-case (search domain, configs,....etc)
Each template can have one or more Configuration.
Search configurations roughly align to a specific search engine with a given (or default) set of parameters/setup

A search is tpyically a specific search (user, or system generated)
It will use a sepcific Search Tempalte or use the "default"
The Template can have multiple configs (search multiple engines or engine configs)

The search Config will handle performing the relevant search,
as well as _converting the results into proper persistence objects_ (search results & docs, but possibly also Solr results docs)

## outline
- SearchTemplate
  - SearchConfiguration+

- Search
  - SearchTemplate+
    - SearchConfiguration+
  - SearchResults+
    - SearchConfiguration{1}
    - Document+



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
