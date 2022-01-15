package com.laioffer.jupiter.dao;

import com.laioffer.jupiter.entity.db.User;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RegisterDao {

  @Autowired
  private SessionFactory sessionFactory;

  public boolean register(User user) {
    Session session = null;
    try {
      session = sessionFactory.openSession();
      session.beginTransaction();
      session.save(user);
      session.getTransaction().commit();
    } catch (PersistenceException | IllegalStateException ex) {
      // if hibernate throws this exception, it means the user already be register
      ex.printStackTrace();
      if (session != null) session.getTransaction().rollback();
      return false;
    } finally {
      if (session != null) session.close();
    }
    return true;
  }
}
