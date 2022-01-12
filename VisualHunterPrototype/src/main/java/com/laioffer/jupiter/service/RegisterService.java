package com.laioffer.jupiter.service;

import com.laioffer.jupiter.dao.RegisterDao;
import com.laioffer.jupiter.entity.db.User;
import com.laioffer.jupiter.util.Util;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

  @Autowired
  private RegisterDao registerDao;

  public boolean register(User user) throws IOException  {
    user.setPassword(Util.encryptPassword(user.getUserId(), user.getPassword()));
    return registerDao.register(user);
  }
}
