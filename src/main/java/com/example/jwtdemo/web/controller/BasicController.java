
package com.example.jwtdemo.web.controller;

import com.example.jwtdemo.pojo.RespBean;
import com.example.jwtdemo.utils.AESUtil;
import com.example.jwtdemo.utils.JWTUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.example.jwtdemo.Constants.*;

@Controller
public class BasicController {

    @GetMapping("/login")
    @ResponseBody
    public RespBean login(@RequestParam("name") String name, @RequestParam("password") String password) {
        try {
            if (!mockDB_name_password.containsKey(name)) {
                return RespBean.error("user name not exist!");
            }
            if (!password.equals(mockDB_name_password.get(name))) {
                return RespBean.error("The password is not correct!");
            }
            String token = JWTUtils.create(mockDB_id_name.get(name), name);
            String encrypt = AESUtil.encrypt(token.getBytes(), key.getBytes());
            return RespBean.ok("login success", encrypt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/hello")
    @ResponseBody
    public RespBean hello() {
        return RespBean.ok("hello!");
    }


}
