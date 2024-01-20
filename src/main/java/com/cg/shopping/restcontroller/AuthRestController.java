package com.cg.shopping.restcontroller;

import com.cg.shopping.model.dto.AuthResponse;
import com.cg.shopping.model.dto.req.RegisterReqDto;
import com.cg.shopping.model.dto.req.UserReqDto;
import com.cg.shopping.model.dto.req.UserUpdateReqDto;
import com.cg.shopping.model.dto.res.RegisterResDto;
import com.cg.shopping.model.dto.res.UserResDto;
import com.cg.shopping.repository.RoleRepository;
import com.cg.shopping.repository.UserRepository;
import com.cg.shopping.service.JwtService;
import com.cg.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    @Autowired
    private UserService userService;
    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterReqDto registerReqDto) {
        RegisterResDto registerResDto = userService.save(registerReqDto);
        return new ResponseEntity<>(registerResDto, HttpStatus.OK);
    }
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserReqDto userReqDto) {

        try{
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userReqDto.getEmail(), userReqDto.getPassword()));
            UserDetails user = (UserDetails) authentication.getPrincipal();
            String accessToken = jwtService.generateToken(user.getUsername());

            AuthResponse response = new AuthResponse(user.getUsername(), accessToken);

            return ResponseEntity.ok().body(response);
        }catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

}
