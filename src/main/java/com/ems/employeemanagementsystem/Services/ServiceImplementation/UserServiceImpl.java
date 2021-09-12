package com.ems.employeemanagementsystem.Services.ServiceImplementation;

import com.ems.employeemanagementsystem.Models.UserEnum;
import com.ems.employeemanagementsystem.Models.Users;
import com.ems.employeemanagementsystem.Repositories.UserRepository;
import com.ems.employeemanagementsystem.RequestEntities.ActivateRequest;
import com.ems.employeemanagementsystem.RequestEntities.SignupRequest;
import com.ems.employeemanagementsystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users login(String email, String password) {
        return userRepository.findUsersByEmailAndPassword(email,password);
    }

    @Override
    public Users signup(SignupRequest signupRequest) {
        Users users = new Users();
        users.setFirstName(signupRequest.getFirstName());
        users.setLastName(signupRequest.getLastName());
        users.setEmail(signupRequest.getEmail());
        users.setPassword(signupRequest.getPassword());
        users.setPhone(signupRequest.getPhone());
        users.setPin(signupRequest.getPin());
        users.setUserEnum(UserEnum.ADMIN);

       return userRepository.save(users);

    }

    public Users activateUser(ActivateRequest activateRequest, Long id) {
//        Users users = new Users();
        Users users = userRepository.getById(id);
        users.setActivated(true);
        return userRepository.save(users);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<Users> users = userRepository.findById(id);
        userRepository.delete(users.get());
    }

    @Override
    public List<Users> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }


//    public Users getUserByName(String name) {
//        return null;
//    }

    @Override
    public Users getUserByFirstName(String firstName) {
        return userRepository.findAllByFirstName(firstName);
    }

    @Override
    public Users getUserByFirstAndLastName(String firstName, String lastName) {
        return userRepository.findAllByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Users getUserByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName);
    }


}
