package com.seedcompany.cord

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@SpringBootApplication
class CordPersistence

fun main(args: Array<String>) {
	// needed for tomcat to handle our dependencies
	// https://github.com/spring-projects/spring-boot/issues/21535
	TomcatURLStreamHandlerFactory.disable()

	runApplication<CordPersistence>(*args)
}

@Configuration
class ApplicationSecurity : WebSecurityConfigurerAdapter() {
	@Throws(Exception::class)
	override fun configure(web: WebSecurity) {
		web
				.ignoring()
				.antMatchers("/**")
	}
}