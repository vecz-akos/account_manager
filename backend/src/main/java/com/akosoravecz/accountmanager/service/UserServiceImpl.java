package com.akosoravecz.accountmanager.service;

import com.akosoravecz.accountmanager.controller.request.RegisterRequest;
import com.akosoravecz.accountmanager.dto.mapper.UserMapper;
import com.akosoravecz.accountmanager.dto.model.user.CustomUserDetails;
import com.akosoravecz.accountmanager.dto.model.user.UserDto;
import com.akosoravecz.accountmanager.model.user.Role;
import com.akosoravecz.accountmanager.model.user.User;
import com.akosoravecz.accountmanager.repository.user.RoleRepository;
import com.akosoravecz.accountmanager.repository.user.UserRepository;
import com.akosoravecz.accountmanager.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
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
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public UserDto register(RegisterRequest registerRequest) {
        UserDto newUser = new UserDto();
        newUser.setName(registerRequest.getName());
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Role role = roleRepository.findById(registerRequest.getRole()).orElseThrow();

        User user = modelMapper.map(newUser, User.class);
        user.setRoles(Set.of(role));
        User savedUser = userRepository.save(user);
        return UserMapper.toUserDto(savedUser);
    }

    @Override
    public String loginUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {
            return JwtUtil.generateToken(username);
        }
        throw new UsernameNotFoundException("User not found.");
    }

    public Collection<UserDto> getAllUser() {
        return userRepository.findAll().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername()).orElseThrow();
        userRepository.delete(user);
        return true;
    }

    @Override
    public UserDto addRole(String username, String roleName) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Role role = roleRepository.findByName(roleName).orElseThrow();
        user.getRoles().add(role);
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto removeRole(String username, String roleName) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Role role = roleRepository.findByName(roleName).orElseThrow();
        user.getRoles().remove(role);
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User is not exists."));

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return CustomUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
