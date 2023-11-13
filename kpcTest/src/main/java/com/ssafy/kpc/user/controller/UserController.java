package com.ssafy.kpc.user.controller;

import com.ssafy.kpc.user.model.dto.Response;
import com.ssafy.kpc.user.model.dto.UserDto;
import com.ssafy.kpc.user.model.entity.User;
import com.ssafy.kpc.user.model.service.UserService;
import com.ssafy.kpc.user.model.service.UserServiceImpl;
import com.ssafy.kpc.user.util.CookieUtil;
import com.ssafy.kpc.user.util.JwtUtil;
import com.ssafy.kpc.user.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    private final JwtUtil jwtUtil;

    private final CookieUtil cookieUtil;

    private final RedisUtil redisUtil;
//    @GetMapping("/")
//    public ResponseEntity Hello (@RequestBody User user){
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping(value = "/register")
    public Response register(@RequestBody User user){
        try {
            System.out.println("register Controller 실행");
            userService.registUser(user);
            return new Response("success", "회원가입을 성공적으로 완료했습니다.", null);
        } catch (Exception e) {
            return new Response("error", "회원가입을 하는 도중 오류가 발생했습니다.", null);
        }
    }

    @PostMapping(value = "/login")
    public Response login(@RequestBody User userDto, HttpServletRequest req, HttpServletResponse res){
        try {
            System.out.println("loginUserController");
            User user = userService.loginUser(userDto.getEmail(), userDto.getPassword());
            System.out.println("1 = " + 1);
            final String token = jwtUtil.generateToken(user);
            System.out.println("2 = " + 2);
            final String refreshJwt = jwtUtil.generateRefreshToken(user);
            System.out.println("3 = " + 3);
            System.out.println("refreshJwt = " + refreshJwt);
            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);

            System.out.println("token = " + token);
            redisUtil.setDataExpire(refreshJwt, user.getName(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
            res.addCookie(accessToken);
            res.addCookie(refreshToken);

//            return new ResponseEntity<MemberDto>("success", "로그인에 성공했습니다.", token);
            return new Response("success", "로그인에 성공했습니다.", token);
        } catch (Exception e) {
            return new Response("error", "로그인에 실패했습니다.", e.getMessage());
        }
    }

    @GetMapping(value = "/detailUser/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable int user_id) {
        Long id = Long.valueOf(user_id);
        if(userService.getUser(id).isPresent()){
            return new ResponseEntity<User>(userService.getUser(id).get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Void> logout(HttpSession session){
        System.out.println("로그아웃");
        session.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
