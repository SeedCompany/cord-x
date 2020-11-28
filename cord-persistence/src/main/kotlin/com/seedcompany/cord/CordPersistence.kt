package com.seedcompany.cord

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory
import org.springframework.boot.web.server.ErrorPage
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class CordPersistence

fun main(args: Array<String>) {
	// needed for tomcat to handle our dependencies
	// https://github.com/spring-projects/spring-boot/issues/21535
	TomcatURLStreamHandlerFactory.disable()
	
	runApplication<CordPersistence>(*args)
}
