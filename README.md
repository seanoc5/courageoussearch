# Overview of CourageousSearch
This is aimed at being a personal search tool. At the moment it uses the Brave Search API to perform searches. 
Eventually I expect it will support multiple "SearhConfigurations" and provide an easy way for users to add context and tuning to their search process.

This is likely only appealing to technically adventurous folks at the moment. In a few months it may grow to be more user friendly.

I also aspire to including `Analysis` processes that will include Machine Learning to do significant "heavy lifting" in helping users search and find.

## Getting Started
- _(**optional** but helpful)_ [sdkman](https://sdkman.io/install) is a nice option for grails/groovy/jdk and a bunch of other things
- install groovy 3.0.11
- install [Grails](https://grails.org/) (5.2.4)  
  - grails will download/use the proper gradle (7.2)  
    - note: I had problems with Gradle 8 getting mixed in because I was sloppy, beware! 
- install/use JDK 11 (or perhaps up to 16??)
- clone this repo
- build
- bootRun

## More information
For now the functionality is very basic, but perhaps interesting:
- Use Brave Search API to:
  - send searches
  - retrieve source webpage content in addition to Brave's summary
- Allow for setting `SearchConfig`
  - currently only tested with Brave, but should be extensible
  - quite useful for [Brave Goggles](https://github.com/brave/goggles-quickstart) 
- Allow for setting `Context`
  - for use in future _Personal_ ML and result analysis
  - potentially also modify search parameters for a 'session' (aka context/intent/goal)



I stumbled across the `Brave` browser, and then continued blindly to discover the Brave API _(which I really like)_. 
Things got more interesting for me when I found the `Goggles` beta functionality. 

Two of the public Goggles are [News from the Left](https://raw.githubusercontent.com/allsides-news/brave-goggles/main/left.goggles) and [News from the Right](https://raw.githubusercontent.com/allsides-news/brave-goggles/main/right.goggles). 
My intent with `CourageousSearch` _(as opensource work)_ is to allow adventurous and technically-oriented users to have a tool that can help compare multiple "sources"
in a convenience and semi-objective manner. _(More on that later)_  


**Full Disclosure:** 
I am developing a larger, more involved project that builds upon these basics. That will focus on Enterprise interests, 
and bridging the "gap" between semantic search _(ala ChatGPT etc)_ and keyword search _(mostly Solr, which I am more familiar with)_.  
I (currently) intend to keep the Enterprise-oriented project proprietary. It is still just a vague thought, and does not even have a working name at the moment.

I also hope to find a few spare hours to document where I have implemented Grails-thingies and provide pointers to examples. I expect that the majority of people using Grails already know these things, and therefor these 'pointers' may not be of much use. If you are interested, please feel free to contact me on this github project and let me know what you are interested in. No promises, but I do welcome the chance to help give back a bit if I can.

For anyone interested, [OconEco](http://www.oconeco.com) is the start-up that has been primarily focused on economics. 
I am involved with the goal of improving stakeholder engagement and lowering the cost of decision making. More on that elsewhere...   

### Description:

`SearchConfigurations` roughly align to a specific search engine with a given (or default) set of parameters/setup. Please note that the initial configurations need valid Brave Search API tokens.
See: https://api.search.brave.com/login

A `Search` is tpyically a specific search (user, or system generated)
It will use a sepcific Search Tempalte or use the "default"
The Template can have multiple configs (search multiple engines or engine configs)

The `SearchConfig` will handle performing the relevant search,
as well as _converting the results into proper persistence objects_ (search results & docs, but possibly also Solr results docs)

## outline

- Search
  - SearchConfiguration 
  - SearchResults+
    - SearchConfiguration{1}
    - Document+


## Screenshots
Example of the dashboard:
![Sample Dashboard](./documentation/dashboard.png)

Example of a SearchResults page showing Brave `description` snippets:
![Search with Results](./documentation/searchShow.png)

Example of a single result (aka `Content`, or document) with `structuredContent` which is the result of [Readability4j](https://github.com/dankito/Readability4J) parsing.
Note: this is still a beta implementation on my part--more to come...
![Result Content with parsed (Readablity4j) structuredContent](./documentation/contentShow.png)

### Todo
- implement reasonable tagging/commenting etc
  - upvote/downvote sites, docs, searchResults,...
  - allow users (and analyzer-bots) to assign tags and topics to various things
- Fix ugly/bad UI
  - Proper approach for searchConfig setup and tokens
  - Better handling of picking contexts and configs
  - Better UI/UX in general
- views-json: apply plugin:"org.grails.plugins.json-views
  - build script dependencies:  classpath "org.grails.plugins.views-gradle:1.0.1"  _(or higher version)_
-Add `Analysis` functionality
  - e.g. rerank results based on personal preferences
  - NLP and other advanced ML analysis
    - sentence classifier to highlight questions, answers, interesting things, and fluff... (still very vague)
  - assess/rank the result set in general (not sure how or why yet, but seems interesting)
- Add searchConfig to process solr results
  - Specifically connecto to a [Solr-System](https://github.com/seanoc5/solr-system) solr instance contain local/personal crawled info
- migrate to Micronaut


# other & misc
[Micronaut data info](https://medium.com/agorapulse-stories/goodbye-grails-hello-micronaut-10-micronaut-data-759c6c36bc7)
Micronaut Data JPA is more powerful and a good fit if you require some advanced ORM features such as

http://docs.grails.org/latest/guide/services.html
def author = Author.findById(id, [fetch:[books:"eager"]])
http://gorm.grails.org/latest/hibernate/manual/index.html#fetching

    static mapping = {
        flights lazy: false
    }
