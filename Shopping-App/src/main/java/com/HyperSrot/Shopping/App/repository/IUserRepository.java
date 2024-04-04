package com.HyperSrot.Shopping.App.repository;

import com.HyperSrot.Shopping.App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
}
