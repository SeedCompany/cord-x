package com.seedcompany.cord.core

import org.neo4j.common.DependencyResolver
import org.neo4j.configuration.connectors.BoltConnector
import org.neo4j.configuration.helpers.SocketAddress
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.Driver
import org.neo4j.driver.GraphDatabase
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.kernel.api.procedure.GlobalProcedures
import org.neo4j.kernel.internal.GraphDatabaseAPI
import org.springframework.context.annotation.Bean
import org.springframework.data.neo4j.core.Neo4jClient
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
                    SocketAddress("localhost", 7687))
            .build()
    private final val db: GraphDatabaseService
    private final val driver: Driver
    private final val client: Neo4jClient

    init {

        db = managementService.database("neo4j")

        val proceduresService: GlobalProcedures = (db as GraphDatabaseAPI).dependencyResolver.resolveDependency(GlobalProcedures::class.java, DependencyResolver.SelectionStrategy.FIRST)

        proceduresService.registerProcedure(apoc.periodic.Periodic::class.java)
        proceduresService.registerProcedure(apoc.warmup.Warmup::class.java)

        Runtime.getRuntime().addShutdownHook(Thread(managementService::shutdown))
        driver =
                GraphDatabase.driver(
                        "bolt://localhost:7687",
                        AuthTokens.basic(
                                "neo4j",
                                "neo4j"))
        client = Neo4jClient.create(driver)
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
