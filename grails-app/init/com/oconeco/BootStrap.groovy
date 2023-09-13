package corpusminder

import com.oconeco.Comment
import com.oconeco.Context
import com.oconeco.SystemService
import com.oconeco.Tag
import com.oconeco.User
import grails.gorm.transactions.Transactional

class BootStrap {
    SystemService systemService

    def init = { servletContext ->
        environments {
            production {
                servletContext.setAttribute("env", "prod")
                log.debug "We are in PRODUCTION context (remove me)"
            }

            development {
                log.info "We are in Dev context (remove me)"
                servletContext.setAttribute("env", "dev")
                int existingUsersCount = User.count()
                if (existingUsersCount == 0) {
//                    def foo = setupObjects()
                    def foo = systemService.setupObjects()
                    log.info "Foo: [$foo] from systemService.setupObjects()"
                } else {
                    log.debug "Users already exist, skipping bootstrap population"
                }

                log.debug "finished bootstrap code!"
            }

            test {
                servletContext.setAttribute("env", "test")
                log.info "We are in Testing context (remove me)"
            }
        }
    }

    def destroy = {
    }

    @Transactional
    def setupObjects() {
        log.info "Looks like a blank database, adding a few things..."

        User robot = new User(firstName: 'Sourcer', lastName: "System", username: 'robot1', human: false).save()
        User u2 = new User(firstName: 'Sean', lastName: "O'Connor", username: 'seanoc5').save()
        User u3 = new User(firstName: 'John', lastName: "O'Connor", username: 'john').save()

        Comment commentTest = new Comment(label: 'Testing placeholder comment', description: 'bootstrap created -- delete me...', user: robot)
                .save(flush: true)

        Tag tagTest = new Tag(label: "Test", description: 'This is a test', defaultTag: true, createdBy: robot)
                .addToComments(commentTest)
//                .addToContentContexts(contextTesting)
//                .addToContentContexts(contextResearch)
                .save(flush: true)

        Context contextTesting = new Context(label: 'General Testing', description: 'Bootstrapped placeholder, replace me', time: 'unknown',
                location: 'unknown', intent: 'unknown', createdBy: robot)
                .addToComments(commentTest)
                .addToTags(tagTest)
                .save(flush: true)

        Context contextResearch = new Context(label: 'Research', description: 'General context of fact finding and related research', intent: 'Fact finding', createdBy: robot)
                .save(flush: true)


        return [robot, u2, u3, commentTest, contextTesting, contextResearch]
    }
}
