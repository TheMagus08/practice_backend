package com.webmasterperu.backend.controllers;

import com.webmasterperu.backend.Entities.User;
import com.webmasterperu.backend.Request.UserRequest;
import com.webmasterperu.backend.Response.UserResponse;
import com.webmasterperu.backend.Services.UserService;
import com.webmasterperu.backend.utils.dto.UserDto;
import com.webmasterperu.backend.jwt.JwtService;
import com.webmasterperu.backend.utils.dto.UserListDTO;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {
    //private final UserService userService;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final UserService userService;


    /*@GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id)
    {


        UserDto userDTO = userService.getUser(id);
        if (userDTO==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDTO);
    }*/

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id/*, @RequestHeader("Authorization") String token*/)
    {

        /*Claims claims = jwtService.getAllClaims(token.substring(7));

        Integer userIdFromToken = claims.get("userId", Integer.class);*/

        UserDto userDTO = userService.getUser(id);
        if (userDTO==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDTO);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDTO = userService.getUser(id);
        return ResponseEntity.ok(userDTO);
    }*/


    @PutMapping(value = "{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest)
    {
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }


    @GetMapping("/list")
    public List<UserListDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}