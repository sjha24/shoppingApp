package com.HyperSrot.Shopping.App.service;

import com.HyperSrot.Shopping.App.exception.UserNotFoundException;
import com.HyperSrot.Shopping.App.model.User;
import com.HyperSrot.Shopping.App.repository.IUserRepository;
import com.HyperSrot.Shopping.App.service.serviceInterface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public ResponseEntity<String> registerUser(User user) {
        if(user != null){
            userRepository.save(user);
            return new ResponseEntity<>("User Register Successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User Not Register",HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<?> getUserById(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("User Not Found !!!"));

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }
}
