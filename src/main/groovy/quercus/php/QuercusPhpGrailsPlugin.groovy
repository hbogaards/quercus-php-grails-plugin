package quercus.php

import com.caucho.quercus.servlet.QuercusServlet
import grails.plugins.*
import org.springframework.boot.context.embedded.ServletRegistrationBean

class QuercusPhpGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.1.6 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Grails Quercus PHP plugin" // Headline display name of the plugin
    def author = "Hans Bogaards"
    def authorEmail = "hbogaards@gmail.com"
    def description = '''\
Grails plugin to embed php pages in a grails project. Uses the quercus/resin java library.
'''
    def profiles = ['web']

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/quercus-php"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    Closure doWithSpring() { {->
        def config =  grailsApplication.config

        def mappings = config.getProperty('quercus.mappings', List, ['*.php'])

        // add the quercus servlet
        quercusServlet(QuercusServlet)
        quercusServletRegistration(ServletRegistrationBean, ref("quercusServlet"), mappings) {
            if (config.hasProperty('quercus.loadOnStartup')) {
                loadOnStartup = config.quercus.loadOnStartup
            }
            initParameters = config.quercus.initParameters
        }

//        def welcomeFiles = xml.'welcome-file-list'.'welcome-file'
//        welcomeFiles[0] + {
//            'welcome-file'('index.php')
//        }
    }}

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    void doWithApplicationContext() {
        // TODO Implement post initialization spring config (optional)
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
