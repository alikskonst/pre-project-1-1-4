package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        Session session = null;
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (id bigint NOT NULL AUTO_INCREMENT, name varchar(255) NOT NULL, last_name varchar(255) NOT NULL, age tinyint NOT NULL, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3");
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user");
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            NativeQuery<User> nativeQuery = session.createNativeQuery("INSERT INTO user (name, last_name, age) VALUES (?, ?, ?)", User.class);
            nativeQuery.setParameter(1, name);
            nativeQuery.setParameter(2, lastName);
            nativeQuery.setParameter(3, age);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            NativeQuery<Integer> query = session.createNativeQuery("DELETE FROM user WHERE id = ?", Integer.class);
            query.setParameter(1, id);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            NativeQuery<User> nativeQuery = session.createNativeQuery("SELECT id, name, last_name, age FROM user", User.class);
            session.getTransaction().commit();
            session.close();
            return nativeQuery.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = util.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createNativeQuery("DELETE FROM user");
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
