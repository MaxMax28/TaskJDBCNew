package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (\n" +
                "    id INTEGER AUTO_INCREMENT PRIMARY KEY, \n" +
                "    name VARCHAR(30), \n" +
                "    lastName VARCHAR(30), \n" +
                "    age INTEGER\n" +
                ");");
        query.executeUpdate();
        tx1.commit();
        session.close();

//        try {
//            session.createNativeQuery("create table if not exists users (id bigint auto_increment, primary key (id), name varchar(245), " +
//                    "lastName varchar(245), age int)").executeUpdate();
//            tx1.commit();
//        } catch (Exception e) {
//            tx1.rollback();
//        } finally {
//            session.close();
//        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createSQLQuery("DROP TABLE IF EXISTS users");
        query.executeUpdate();
        tx1.commit();
        session.close();
//        try {
//            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
//            tx1.commit();
//            session.close();
//        } catch (Exception e) {
//            tx1.rollback();
//        } finally {
//            session.close();
//        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            //session.save(new User(name, lastName, age));
            Query query = session.createNativeQuery("insert into users (name, lastName, age) value (?,?,?)");
            query.executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
        } finally {
            session.close();
        }
    }


    //session.createQuery("insert into User(name, lastName, age)" + "value (name, lastName, age)");
    //        tx1.commit();
//        session.close();


    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE User WHERE id = :id");
            query.setParameter("id",id);
            query.executeUpdate();
            tx1.commit();

//            User user = session.load(User.class, id);
//            session.delete(user);
//            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
//        List<User> list = session.createQuery("FROM User").list();
//        tx1.commit();
//        session.close();
//        return list;
        List<User> list = null;
        try {
            list = session.createQuery("FROM User").list();
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
        } finally {
            session.close();
            return list;
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.createQuery("DELETE from User").executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
        } finally {
            session.close();
        }
    }
}