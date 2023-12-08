package com.iftm.imobiliarianosqql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return "imobiliaria_keyspace"; // Substitua pelo nome do seu keyspace
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints("localhost"); // Substitua pelo endereço do seu cluster
        cluster.setPort(9042); // Substitua pela porta do seu cluster
        return cluster;
    }

    @Bean
    public CassandraSessionFactoryBean session() {
        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName(getKeyspaceName());
        return session;
    }
}
