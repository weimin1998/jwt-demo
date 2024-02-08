package com.example.jwtdemo.web.interceptor;

import com.example.jwtdemo.utils.AESUtil;
import com.example.jwtdemo.utils.JWTUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.example.jwtdemo.Constants.*;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String Authorization = request.getHeader("Authorization").replace("Bearer ", "");
        if (ObjectUtils.isEmpty(Authorization)) {
            response.setStatus(401);
            return false;
        }

        byte[] decrypt = AESUtil.decrypt(Authorization.getBytes(), key.getBytes());
        String token = new String(decrypt);
        Map<String, String> user = JWTUtils.resolver(token);

        if (ObjectUtils.isEmpty(user)) {
            // token expired
            response.setStatus(401);
            return false;
        }

        request.setAttribute("user", user);
        return true;
    }
}
