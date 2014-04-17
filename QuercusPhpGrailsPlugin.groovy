class QuercusPhpGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Quercus Php Plugin" // Headline display name of the plugin
    def author = "Your name"
    def authorEmail = ""
    def description = '''\
Brief summary/description of the plugin.
'''

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

	def doWithWebDescriptor = { xml ->
		def grailsApplication = grails.util.Holders.getGrailsApplication()
		def config =  grailsApplication.config
  
		def servlets = xml.'servlet'
		servlets[servlets.size()-1] + {
		  servlet{
			'servlet-name'('quercus')
			'servlet-class'('com.caucho.quercus.servlet.QuercusServlet')
			def initParams = config.'initParameters'.with{(it instanceof Map?it:it.flatten())}
			initParams.each{k,v-> 'init-param'{'param-name'(k);'param-value'(v)}}
  
			if (!(config.'loadOnStartup' instanceof ConfigObject)){
			  'load-on-startup'(config.'loadOnStartup')
			}
		  }
		}
  
		def mappings = config.mappings instanceof groovy.util.ConfigObject ? ['*.php'] : config.mappings
  
		def servletMappings = xml.'servlet-mapping'
		servletMappings[servletMappings.size()-1] + {
		 'servlet-mapping'{
			'servlet-name'('quercus')
			mappings.each{ m ->
			'url-pattern'(m)
			}
		  }
		}
  
		def welcomeFiles = xml.'welcome-file-list'.'welcome-file'
		welcomeFiles[0] + {
			'welcome-file'('index.php')
		}
	  }


    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
