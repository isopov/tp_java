package ru.mail.park.java;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login", method = RequestMethod.GET)
public class SimpleController {

  public SimpleController() {
    super();
  }

  private final AtomicReference<String> lastLogin = new AtomicReference<String>("");

  @RequestMapping
  public ResponseEntity<Object> login(@RequestParam(required = false) String login) {
    if (login == null || login.isEmpty()) {
      return new ResponseEntity<>(new ForbidenResponce(lastLogin.get()), HttpStatus.FORBIDDEN);
    } else {
      lastLogin.set(login);
      return new ResponseEntity<>(new SuccessfulResponce(login), HttpStatus.FORBIDDEN);
    }
  }

  public static final class ForbidenResponce {
    private final String lastLogin;

    public ForbidenResponce(String lastLogin) {
      super();
      this.lastLogin = lastLogin;
    }

    public String getLastLogin() {
      return lastLogin;
    }

  }

  public static final class SuccessfulResponce {
    private final String currentLogin;

    public SuccessfulResponce(String currentLogin) {
      super();
      this.currentLogin = currentLogin;
    }

    public String getCurrentLogin() {
      return currentLogin;
    }
  }

}
