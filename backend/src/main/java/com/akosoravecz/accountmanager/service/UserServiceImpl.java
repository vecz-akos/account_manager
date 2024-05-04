package com.akosoravecz.accountmanager.service;

import com.akosoravecz.accountmanager.dto.mapper.UserMapper;
import com.akosoravecz.accountmanager.dto.model.UserDto;
import com.akosoravecz.accountmanager.model.user.User;
import com.akosoravecz.accountmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public UserDto register(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isEmpty()) {
            User user = modelMapper.map(userDto, User.class);
            User savedUser = userRepository.save(user);
            return UserMapper.toUserDto(savedUser);
        }
        return null;
    }

    @Override
    public boolean delete(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername()).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }
}
