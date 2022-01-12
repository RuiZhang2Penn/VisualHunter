package com.laioffer.jupiter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.jupiter.entity.request.LoginRequestBody;
import com.laioffer.jupiter.entity.response.LoginResponseBody;
import com.laioffer.jupiter.service.LoginService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

  @Autowired
  private LoginService loginService;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public void login(@RequestBody LoginRequestBody requestBody, HttpServletRequest request, HttpServletResponse response) throws
                                                                                                                         IOException {
    String firstname = loginService.verifyLogin(requestBody.getUserId(), requestBody.getPassword());

    if(!firstname.isEmpty()) {
      HttpSession session = request.getSession();
      session.setAttribute("user_id", requestBody.getUserId());
      session.setMaxInactiveInterval(600);

      LoginResponseBody loginResponseBody = new LoginResponseBody(requestBody.getUserId(), firstname);
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().print(new ObjectMapper().writeValueAsString(loginResponseBody));
    } else {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

  }
}
