package com.dao;

import com.model.TestSingle;
import com.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Создал Vlad Kazakov дата: 26.02.2017.
 */
public class TestSingleImpl implements TestSingleDao {
    public void add(TestSingle testSingle) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(testSingle);
        tx.commit();
        session.close();
    }
}
