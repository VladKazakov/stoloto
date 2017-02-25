package com.dao;

import com.model.Root;
import com.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Создал Vlad Kazakov дата: 22.02.2017.
 */
public class RootImpl implements RootDao {

    public void add(Root root) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(root);
        tx.commit();
        session.close();
    }
}
