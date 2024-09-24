package com.apps.wave.news.controller;



import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apps.wave.news.dto.AddUserRequest;
import com.apps.wave.news.dto.GeneralResponse;
import com.apps.wave.news.enums.Role;
import com.apps.wave.news.exception.BusinessExceptions;
import com.apps.wave.news.service.UserService;


@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    } 

    @GetMapping("/list")
    public ResponseEntity<GeneralResponse> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return userService.list(PageRequest.of(page, size));
    }
    @PostMapping("createUser")
    public ResponseEntity<GeneralResponse> addNewUser(@RequestBody @Valid AddUserRequest request ) throws BusinessExceptions {
    	userService.addUser(request );
        return ResponseEntity.ok(new GeneralResponse(200,"User added successfully")); 
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new GeneralResponse(200,"User deleted"));
    }
    @GetMapping("/api/roles")
    public List<Role> getAllRoles() {
        return Arrays.asList(Role.values());
    }

}
