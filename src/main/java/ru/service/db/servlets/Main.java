package ru.service.db.servlets;

import org.hibernate.Session;
import ru.service.db.dao.UserProfile;
import ru.service.db.utils.HibernateSessionFactory;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hibernate tutorial");


        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        UserProfile userEntity = new UserProfile();

        userEntity.setUserId(5);
        userEntity.setUserName("Alexander");
        userEntity.setPassword("12345");

        session.save(userEntity);
        session.getTransaction().commit();

        session.close();


    }
}
