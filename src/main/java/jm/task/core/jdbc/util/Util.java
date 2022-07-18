package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class Util {

    public SessionFactory getSessionFactory() {
        return createSessionFactory();
    }

    private SessionFactory createSessionFactory() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(getSetting()).build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        Metadata metadata = metadataSources.buildMetadata();
        return metadata.getSessionFactoryBuilder().build();
    }

    private Map<String, String> getSetting() {
        Map<String, String> map = new HashMap<>();
        map.put("connection.driver_class", "com.mysql.cj.jdbc.Driver");
        map.put("dialect", "org.hibernate.dialect.MySQL8Dialect");
        map.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/pre_project_114?useSSL=false&useUnicode=true&characterEncoding=utf-8&characterSetResults=utf-8&autoReconnect=true");
        map.put("hibernate.connection.username", "root");
        map.put("hibernate.connection.password", "root");
        map.put("hibernate.current_session_context_class", "thread");
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.format_sql", "true");
        return map;
    }
}
