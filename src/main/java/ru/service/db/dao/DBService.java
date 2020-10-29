package ru.service.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBService {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    private static SessionFactory sessionFactory = null;

    public DBService() {
        Configuration configuration = getConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    public UserProfile getUser(String login){
        Session session = sessionFactory.openSession();
        UserProfile dataSet = new UserDAO(session).get(login);
        session.close();
        return dataSet;
    }

    public void addUser(UserProfile userProfile) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDAO dao = new UserDAO(session);
        dao.insertUser(userProfile.getUserName(), userProfile.getPassword());
        transaction.commit();
        session.close();
    }


    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserProfile.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "qwerty");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }
}
