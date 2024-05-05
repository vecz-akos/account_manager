package com.akosoravecz.accountmanager.service;

import com.akosoravecz.accountmanager.controller.request.RegisterRequest;
import com.akosoravecz.accountmanager.dto.mapper.UserMapper;
import com.akosoravecz.accountmanager.dto.model.RoleDto;
import com.akosoravecz.accountmanager.dto.model.UserDto;
import com.akosoravecz.accountmanager.model.user.Role;
import com.akosoravecz.accountmanager.model.user.User;
import com.akosoravecz.accountmanager.repository.RoleRepository;
import com.akosoravecz.accountmanager.repository.UserRepository;
import com.akosoravecz.accountmanager.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public UserDto register(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isEmpty()) {
            UserDto newUser = new UserDto();
            newUser.setName(registerRequest.getName());
            newUser.setUsername(registerRequest.getUsername());
            newUser.setPassword(registerRequest.getPassword());
            newUser.setRoles(Set.of(
                    modelMapper.map(
                            roleRepository.getReferenceById(registerRequest.getRole()),
                            RoleDto.class)));
            User user = modelMapper.map(newUser, User.class);
            User savedUser = userRepository.save(user);
            return UserMapper.toUserDto(savedUser);
        }
        return null;
    }

    @Override
    public String loginUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                return JwtUtil.generateToken(username);
            }
        }
        return null;
    }

    public Collection<UserDto> getAllUser() {
        return userRepository.findAll().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
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
