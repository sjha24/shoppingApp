package com.HyperSrot.Shopping.App.service.serviceInterface;

import com.HyperSrot.Shopping.App.exception.UserNotFoundException;
import com.HyperSrot.Shopping.App.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    ResponseEntity<String> registerUser(User user);

    ResponseEntity<?> getUserById(Long userId) throws UserNotFoundException;
}
