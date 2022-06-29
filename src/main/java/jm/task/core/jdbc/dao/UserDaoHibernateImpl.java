package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.Collections;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final Util util;

    public UserDaoHibernateImpl() {
        util = new Util();
    }

    @Override
    public void createUsersTable() {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS user (id bigint NOT NULL AUTO_INCREMENT, name varchar(255) NOT NULL, last_name varchar(255) NOT NULL, age tinyint NOT NULL, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3");
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS user");
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            NativeQuery<User> nativeQuery = session.createNativeQuery("INSERT INTO user (name, last_name, age) VALUES (?, ?, ?)", User.class);
            nativeQuery.setParameter(1, name);
            nativeQuery.setParameter(2, lastName);
            nativeQuery.setParameter(3, age);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            NativeQuery<Integer> query = session.createNativeQuery("DELETE FROM user WHERE id = ?", Integer.class);
            query.setParameter(1, id);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            NativeQuery<User> nativeQuery = session.createNativeQuery("SELECT id, name, last_name, age FROM user", User.class);
            session.getTransaction().commit();
            return nativeQuery.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void cleanUsersTable() {
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM user");
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }
}
