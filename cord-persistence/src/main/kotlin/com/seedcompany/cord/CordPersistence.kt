package com.seedcompany.cord

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CordPersistence

fun main(args: Array<String>) {
	// needed for tomcat to handle our dependencies
	// https://github.com/spring-projects/spring-boot/issues/21535
	TomcatURLStreamHandlerFactory.disable()

	runApplication<CordPersistence>(*args)
}
