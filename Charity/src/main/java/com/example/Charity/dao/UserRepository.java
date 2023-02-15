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

    User findById(long id);

    @Modifying
    @Query("from USERS where usersStatus='ACTIVE'")
    List<User> findAll();

    User findFirstByEmail(String email);

    @Modifying
    @Query("UPDATE USERS u set u.usersStatus = :status where u.email = :email")
    void update(@Param("email")String email,@Param("status") String status);

    @Modifying
    @Query(" from USERS where roles = 'USER'and usersStatus='ACTIVE'")
    List<User>findAllUsers();

    @Modifying
    @Query(" from USERS where roles = 'MODER'and usersStatus='ACTIVE'")
    List<User>findAllModers();

    @Modifying
    @Query(" from USERS where roles = 'RECIPIENT'and usersStatus='ACTIVE'")
    List<User>findAllRecipients();

    @Modifying
    @Query(" from USERS where roles = 'ADMIN'and usersStatus='ACTIVE'")
    List<User>findAllAdmins();

    @Modifying
    @Query(" from USERS where usersStatus='BANNED'")
    List<User>findAllBanned();

    @Modifying
    @Query(" from USERS where usersStatus='DELETED'")
    List<User>findAllDeleted();
}
