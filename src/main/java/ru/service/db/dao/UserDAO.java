package ru.service.db.dao;

import ru.service.db.dao.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDAO {

    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }
    //    TODO: getUser
    public UserProfile get(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UserProfile.class);
        return (UserProfile) criteria.add(Restrictions.eq("userName", login)).uniqueResult();
    }


    public void insertUser(String login, String password) throws HibernateException {
        session.save(new UserProfile(login, password));
    }
}
