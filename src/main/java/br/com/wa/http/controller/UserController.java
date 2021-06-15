package br.com.wa.http.controller;

import br.com.wa.http.URLMapping;
import br.com.wa.http.UserWS;
import br.com.wa.http.domain.request.UserRequest;
import br.com.wa.http.domain.request.UsersRequest;
import br.com.wa.http.domain.response.UserListResponse;
import br.com.wa.http.domain.response.UserResponse;
import br.com.wa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author paulo
 */
@RestController
public class UserController implements UserWS {
    
    @Autowired
    private UserService userService;

    @Override
    @PostMapping(URLMapping.POST_SAVE_REGISTER)
    public UserResponse saveRegister(@RequestBody UserRequest request) {
        return new UserResponse(userService.save(request));
    }

    @Override
    @PostMapping(URLMapping.POST_SAVE_REGISTERS)
    public UserListResponse saveRegisters(@RequestBody UsersRequest request) {
        return new UserListResponse(userService.saveAll(request));
    }

    @Override
    @GetMapping(URLMapping.GET_LIST_REGISTER)
    public UserListResponse listRegisters() {
        return new UserListResponse(userService.findAll());
    }
    
}
