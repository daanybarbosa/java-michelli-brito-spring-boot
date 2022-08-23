package com.eventoapp.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class DataConfiguration {

    // Conexão com o banco de dados - MySQL
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/eventosapp?enabledTLSProtocols=TLSv1.2");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        return dataSource;
    }

    // Configuração do Hibernate
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true); //exibe as etapas para inserir/deletar algum dado
        adapter.setGenerateDdl(true); //permite que o Hibernate crie as tabelas automaticamente
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
        adapter.setPrepareConnection(true); //preparar a conexão do hibernate

        return adapter;
    }

}
