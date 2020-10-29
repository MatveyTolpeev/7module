package ru.service.db.utils;

import ru.service.db.dao.UserProfile;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    private static SessionFactory sessionFactory;


        public static SessionFactory getSessionFactory() {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();
            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                    configuration.addAnnotatedClass(UserProfile.class);
                    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                    sessionFactory = configuration.buildSessionFactory(builder.build());

                } catch (Exception e) {
                    StandardServiceRegistryBuilder.destroy(registry );
                    System.out.println("Исключение!" + e);
                }
            }
            return sessionFactory;
        }


    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}
