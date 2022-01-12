package com.laioffer.jupiter.dao;

import com.laioffer.jupiter.entity.db.Item;
import com.laioffer.jupiter.entity.db.User;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class FavoriteDao {

  @Autowired
  private SessionFactory sessionFactory;

  public void setFavoriteItem(String userId, Item item) {
    Session session = null;

    try {
      session = sessionFactory.openSession();
      User user = session.get(User.class, userId);
      user.getItemSet().add(item);
      session.beginTransaction();
      session.save(user);
      session.getTransaction().commit();
    } catch (Exception ex) {
      ex.printStackTrace();
      session.getTransaction().rollback();
    } finally {
      if (session != null) session.close();
    }

  }

  public void unsetFavoriteItem(String userId, String itemId) {
    Session session = null;

    try {
      session = sessionFactory.openSession();
      User user = session.get(User.class, userId);
      Item item = session.get(Item.class, itemId);
      user.getItemSet().remove(item);

      session.beginTransaction();
      session.update(user);
      session.getTransaction().commit();
    } catch (Exception ex) {
      ex.printStackTrace();
      session.getTransaction().rollback();
    } finally {
      if (session != null) session.close();
    }
  }

  public Set<Item> getFavoriteItems(String userId) {
    Session session = null;
    try  {
      session = sessionFactory.openSession();
      return session.get(User.class, userId).getItemSet();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      if (session != null) session.close();
    }
    return new HashSet<>();
  }

}
