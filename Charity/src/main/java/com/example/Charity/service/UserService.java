package com.example.Charity.service;

import com.example.Charity.dao.UserRepository;
import com.example.Charity.entity.User;
import com.example.Charity.enums.RoleStatus;
import com.example.Charity.enums.UsersStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;
    public void save(User user){
        user.setDate(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRecipent(false);
        user.setRoles((RoleStatus.USER));
        user.setUsersStatus(UsersStatus.ACTIVE);
        user.setActive(Boolean.TRUE);
            this.userRepository.save(user);
    }

    public void getEmail(String email){
        User user = this.userRepository.findByEmail(email);
    }

    public void delete(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setUsersStatus(UsersStatus.DELETED);
            this.userRepository.save(user);
        }
    }
    public void ban(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setUsersStatus(UsersStatus.BANNED);
            this.userRepository.save(user);
        }
    }

    public void active(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setUsersStatus(UsersStatus.ACTIVE);
            this.userRepository.save(user);
        }
    }

    public void admin(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setRoles(RoleStatus.ADMIN);
            this.userRepository.save(user);
        }
    }

    public void moder(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setRoles(RoleStatus.MODER);
            this.userRepository.save(user);
        }
    }

    public void user(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setRoles(RoleStatus.USER);
            this.userRepository.save(user);
        }
    }

    public void recipient(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setRoles(RoleStatus.RECIPIENT);
            this.userRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findFirstByEmail(username));
        if(!optionalUser.isPresent()){
            throw new UsernameNotFoundException("User not found");
        }
        User user = optionalUser.get();
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(String.valueOf(user.getRoles()));
        grantedAuthorities.add(simpleGrantedAuthority);

        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),grantedAuthorities);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}
