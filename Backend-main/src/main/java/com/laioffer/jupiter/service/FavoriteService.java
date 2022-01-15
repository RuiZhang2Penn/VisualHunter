package com.laioffer.jupiter.service;

import com.laioffer.jupiter.dao.FavoriteDao;
import com.laioffer.jupiter.entity.db.Item;
import com.laioffer.jupiter.entity.db.ItemType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FavoriteService {

  @Autowired
  private FavoriteDao favoriteDao;

  public void setFavoriteItem(String userId, Item item) {
    favoriteDao.setFavoriteItem(userId, item);
  }

  public void unsetFavoriteItem(String userId, String itemId) {
    favoriteDao.unsetFavoriteItem(userId, itemId);
  }

  public Map<String, List<Item>> getFavoriteItems(String userId) {
    Map<String, List<Item>> itemMap = new HashMap<>();
    for(ItemType itemType : ItemType.values()) {
      itemMap.put(itemType.toString(), new ArrayList<>());
    }

    Set<Item> favorites = favoriteDao.getFavoriteItems(userId);
    for(Item item : favorites) {
      itemMap.get(item.getType().toString()).add(item);
    }
    return itemMap;
  }
}
