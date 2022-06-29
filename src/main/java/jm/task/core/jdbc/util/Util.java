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
        //SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
        //Session session = sessionFactory.openSession();
        //return sessionFactory.getCurrentSession();
    }

    private Map<String, String> getSetting() {
        Map<String, String> map = new HashMap<>();
        map.put("connection.driver_class", "com.mysql.cj.jdbc.Driver");
        map.put("dialect", "org.hibernate.dialect.MySQL8Dialect");
        map.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/pre_project_114");
        map.put("hibernate.connection.username", "root");
        map.put("hibernate.connection.password", "root");
        map.put("hibernate.current_session_context_class", "thread");
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.format_sql", "true");
        return map;
    }
}