package com.example.geofenceapp.services.Impl;

import com.example.geofenceapp.model.Enums.UserRoleType;
import com.example.geofenceapp.model.User;
import com.example.geofenceapp.model.dto.UserDto;
import com.example.geofenceapp.repository.IUserRepository;
import com.example.geofenceapp.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(IUserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addNewUser(UserDto userDto) {
        User newUser = modelMapper.map(userDto, User.class);
        newUser.setUserRoleType(UserRoleType.USER);
        userRepository.save(newUser);
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findByUserId(id);
    }

    @Override
    public void updateEmail(UserDto userDto) {
        User user = this.userRepository.findByUserId(userDto.getUserId());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
    }

    @Override
    public void updateUsername(UserDto userDto) {
        User user = this.userRepository.findByUserId(userDto.getUserId());
        //TODO
        userRepository.save(user);
    }

    @Override
    public boolean isUserExistByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public boolean isUserExistById(Long id) {
        return userRepository.existsUserByUserId(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteByUserId(id);
    }
}