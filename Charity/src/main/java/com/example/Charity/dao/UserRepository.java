package com.example.Charity.dao;

import com.example.Charity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    List<User> findAll();
    User findFirstByEmail(String email);
    @Modifying
    @Query("UPDATE USERS u set u.usersStatus = :status where u.email = :email")
    void update(@Param("email")String email,@Param("status") String status);

}
