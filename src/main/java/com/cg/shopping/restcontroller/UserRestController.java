package com.cg.shopping.restcontroller;

import com.cg.shopping.model.dto.req.UserUpdateReqDto;
import com.cg.shopping.model.dto.res.UserResDto;
import com.cg.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private UserService userService;
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserUpdateReqDto userUpdateReqDto) {
        userUpdateReqDto.setId(id);

        UserResDto userResDto = userService.update(userUpdateReqDto);
        return new ResponseEntity<>(userResDto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);

        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
    }
}
