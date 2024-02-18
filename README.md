# Overview of CourageousSearch (CS)
CourageousSearch is an opensource project intended to empower Power Users and beyond.  
The goal is to augment the current rush to Machine Learning and LLM.  
**More importantly** however: it bravely posits that technology should empower people rather than attempting to replace personal thought and analysis.  
Indeed CourageousSearch celebrates and promotes that marvelous **grey matter between our ears**. 


##  ¡Own Your Own Search!
- **What** you search: Search Engines
- **How** you search: Boosting/Filtering, tweaking
- **Why** you search: Contexts/Intents
- **Annotate** Results: boost/bury/tag/comment/... 
- **Analyze** Results: Shared & Customizable Analysis
- **Collaborate**: Share as much as you like
 
### Why be Courageous in Search?
Courageous Search takes more **initial** effort than just hitting one of the mega-corp search engines.  
So **why bother**?

In short: because humans[h] are still better, faster, and smarter than the machines when we have the right tools for the job. At least for the next few years.   
AND: after an initial '_getting started_' investment of time, effort, and thinking, the **search and discovery process** will be significantly more rewarding and less taxing.  

Use [BogoMips](https://en.wikipedia.org/wiki/BogoMips) to better use.  
Let your brain do what it does best: important decisions.  
Let technology do what it does best: empower our courageous brains. :-) 

This project is specifically focused on the courageous folks who want to **leverage technology** rather than _bow to it_.

**¡Own your Own Search!**

Take the Power back from the mega-corps and tech-bros.  
Take ownership of your discovery process and more.  
Make tech work for you, rather than you being the raw material feeding the big black boxes.

Curious? Skeptical? Hopeful?  
**Read on!**

## What you search
  - Brave Search [API](https://brave.com/search/api/)
    - start with system default API account
    - upgrade to user/specific API account when ready _(i.e. request limits)_
  - Solr deployment/collections
    - e.g. [SolrSystem](https://github.com/seanoc5/solr-system)
  - various other sources depending on extending/customizing elements of CS 
    - [Marginalilia](https://search.marginalia.nu/) _(coming soon...?)_
      - [self hosted](https://github.com/MarginaliaSearch/MarginaliaSearch)
    - Lucidworks [Fusion](https://lucidworks.com/fusion-platform/)
    - AWS [OpenSearch](https://aws.amazon.com/opensearch-service/) service  
    - SERPs...?
    - internal/custom search engines

## How you search
- Control sites/sources
  - boost and bury or hide/block various sites 
    - rely on crowdsourcing to start...
    - customize your preferences if/when necessary 
      - [Brave Goggles](https://github.com/brave/goggles-quickstart/blob/main/README.md) - very cool [functionality](https://support.brave.com/hc/en-us/articles/6959189556237-How-do-I-use-Goggles) from the folks at Brave _(currently [beta](https://www.reddit.com/r/brave_browser/comments/viai3r/goggles_beta_is_now_available_in_brave_search/))_  
        - [discover](https://search.brave.com/goggles/discover) goggles at Brave
        - [growing list](goggles) in this repo...
- Add enhanced **result** analysis & filtering
  - add custom/personalized boosting
  - filter/boost sites, authors, documents, content
    - based on system/communal preferences initially 
    - optionally switch to more bespoke filtering/boosting/analyzing 

## Why you search: Search Contexts/Intents
- What is a CS Context? 
  - Contexts are user-defined grouping of search actions, i.e. search intent or goal
  - CS starts with a shared foundation of common search contexts
  - users are able to add custom search contexts (shared, or private)
- What is the Benefit?
  - Using search Contexts can help the system provide shared and/or personalized boosts, analysis, and other **improvements in results**.
  - For the truly adventurous: Contexts help improve machine learning and model building
    - both self-hosted AND OconEco-hosted CourageousSearch encourage users to access/export any and all of their data and perform analysis outside CS when desired.

## Annotate Results: for Memory and Learning
- Most parts of CS have multiple possible annotations
  - Contexts, Searches, Sources/Sites
  - Results Docs/Content, Content Fragments (e.g. paragraphs) 

## Analyze Results: Shared & Customizable Analysis
- CS has several analysis "agents" built-in
  - _coming soon..._
  - e.g.: 
    - detect code snippet language(s) _(i.e. only Scala, not python examples)_
    - detect software versions _(e.g. JDK 11+, Excel 2010+, Autocad2018+,...)_
    - detect author/publisher
    - detect content date
    - apply machine learning pipelines to result document, augmenting stored results/content _(e.g. SparkNLP)_ 
    - auto-tag result docs _(e.g. import Firefox bookmarks with tags, CS will use ML to predict tags on new content)_
    - ...
- Create/use bespoke analysis agents as desired
  - users can create private or shared custom analysis agents

## Collaborate: Share as much as you like
- Common elements to share:
  - Contexts, and related: 
    - searches, results, annotations
  - Analysis agents
    - simple regex/term matching/boosting
    - more advanced NLP/NLU matching/boosting
- "Offline" deep analysis
  - Machine learning
  - Model building/deployment

 

## Getting Started
- Request a hosted account via [project issue](https://github.com/seanoc5/courageoussearch/labels/account)
  - note hosted accounts are currently (Feb 2024) very limited
  - more available hosted accounts will be coming in the near/medium future _(driven by demand/interest)_

or 

- Go to [README.Deployment.md](README.Deployment.md) and host your own search


## More information

### other & misc
See Readme.Developer.md if you have interest in the code, grails, etc
  
  


##### footnotes etc.

[h] _(us, people, or as some future SkyNet might call us: Meat Puppets)_ 
