package com.oconeco

class TestTagLib {
    static defaultEncodeAs = [taglib: 'text']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]


    def dbCard = { attrs ->
        if (attrs.domainItems==null) {
            throwTagError("Tag [dbCard] is missing required attribute [domainItems]")
        }
        out << render(template: "taglibTemplates/dashboardCard", model: [domainItems: attrs.domainItems, label:attrs.label],)
    }

}
