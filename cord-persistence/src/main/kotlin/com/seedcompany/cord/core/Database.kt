package com.seedcompany.cord.core

import org.neo4j.configuration.connectors.BoltConnector
import org.neo4j.configuration.helpers.SocketAddress
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.Driver
import org.neo4j.driver.GraphDatabase
import org.neo4j.graphdb.GraphDatabaseService
import org.springframework.context.annotation.Bean
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager
import org.springframework.stereotype.Component
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.TransactionManager
import org.springframework.transaction.annotation.TransactionManagementConfigurer
import java.io.File

@Component
class Database{
    final val managementService = DatabaseManagementServiceBuilder(File("database"))
            .setConfig(BoltConnector.enabled, true)
            .setConfig(
                    BoltConnector.listen_address,
                    SocketAddress("localhost", 7002))
            .build()
    private final val db: GraphDatabaseService
    private final val driver: Driver

    init {
        db = managementService.database("neo4j")
        Runtime.getRuntime().addShutdownHook(Thread(managementService::shutdown))
        driver =
                GraphDatabase.driver(
                        "bolt://localhost:7002",
                        AuthTokens.basic(
                                "neo4j",
                                "neo4j"))
    }

    @Bean
    fun reactiveTransactionManager(): ReactiveNeo4jTransactionManager? {
        return ReactiveNeo4jTransactionManager(driver)
    }

    @Component
    internal class ReactiveTransactionManagementConfigurer(
            private val reactiveTransactionManager: ReactiveTransactionManager
    ) : TransactionManagementConfigurer {
        override fun annotationDrivenTransactionManager(): TransactionManager {
            return reactiveTransactionManager
        }
    }

}
