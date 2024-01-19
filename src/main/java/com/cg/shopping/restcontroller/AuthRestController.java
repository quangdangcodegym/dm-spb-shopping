package com.cg.shopping.restcontroller;

import com.cg.shopping.model.dto.req.RegisterReqDto;
import com.cg.shopping.model.dto.req.UserReqDto;
import com.cg.shopping.model.dto.req.UserUpdateReqDto;
import com.cg.shopping.model.dto.res.RegisterResDto;
import com.cg.shopping.model.dto.res.UserResDto;
import com.cg.shopping.repository.RoleRepository;
import com.cg.shopping.repository.UserRepository;
import com.cg.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterReqDto registerReqDto) {
        RegisterResDto registerResDto = userService.save(registerReqDto);
        return new ResponseEntity<>(registerResDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserReqDto userReqDto) {
        UserResDto userResDto = userService.login(userReqDto);
        return new ResponseEntity<>(userResDto, HttpStatus.OK);
    }

}
