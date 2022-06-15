package com.example.User.Controller;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.User.Model.UserModel;
import com.example.User.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel usermodel) {
        usermodel.setPassWord(BCrypt.hashpw(usermodel.getPassWord(), BCrypt.gensalt(6)));
        // BCrypt.checkpw(arg0, arg1)
        UserModel user = userService.createUser(usermodel);
        return user != null ? ResponseEntity.ok(user)
                : new ResponseEntity<UserModel>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping
    public List<UserModel> getAll() {
        return userService.getAll();
    }

    @GetMapping("/login")
    public RedirectView login(Integer id, String password) {
        UserModel user = userService.getById(id);
        if (user != null && BCrypt.checkpw(password, user.getPassWord())) {
            return new RedirectView("");
        } else {
            return new RedirectView("/");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable Integer id) {
        UserModel usermodel = userService.getById(id);
        return usermodel != null ? ResponseEntity.ok(usermodel)
                : new ResponseEntity<UserModel>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Integer id, @RequestBody UserModel usermodel) {
        if (id != null && usermodel != null && usermodel.getId() == id) {
            UserModel umodel = userService.createUser(usermodel);
            return umodel != null && umodel.getId() == id ? ResponseEntity.ok(umodel)
                    : new ResponseEntity<UserModel>(HttpStatus.NOT_MODIFIED);
        } else
            return new ResponseEntity<UserModel>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        if (id != null && userService.getById(id) != null) {
            userService.deleteUser(id);
            return ResponseEntity.ok("delete");
        } else
            return ResponseEntity.ok("hong be oi");

    }

    @GetMapping("/index")
    public String login(Model model) {
        List<UserModel> users = userService.getAll();
        model.addAttribute("listUsers", users);
        return "index";
    }

    @GetMapping("account")
    public String account() {
        return "display";
    }

}
