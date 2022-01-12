package com.laioffer.jupiter.service;

import com.laioffer.jupiter.dao.LoginDao;
import com.laioffer.jupiter.util.Util;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  @Autowired
  private LoginDao loginDao;

  public String verifyLogin(String userId, String password) throws IOException {
    password = Util.encryptPassword(userId, password);
    return loginDao.verifyLogin(userId, password);
  }
}
