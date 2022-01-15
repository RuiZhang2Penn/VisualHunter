package com.laioffer.jupiter.controller;

import com.laioffer.jupiter.entity.db.User;
import com.laioffer.jupiter.service.RegisterService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

  @Autowired
  private RegisterService registerService;

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public void register(@RequestBody User user, HttpServletResponse response) throws IOException {
    if (!registerService.register(user)) {
      response.setStatus(HttpServletResponse.SC_CONFLICT);
    }
  }
}
