package br.com.wa.service;

import br.com.wa.domain.user.User;
import br.com.wa.http.domain.request.UserRequest;
import br.com.wa.http.domain.request.UsersRequest;

import java.util.List;

public interface UserService {

    public User save(UserRequest userRequest);

    public List<User> saveAll(UsersRequest usersRequest);

    public List<User> findAll();

}
