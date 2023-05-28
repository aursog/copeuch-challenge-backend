package cl.copeuch.challenge.services.user.impl;

import cl.copeuch.challenge.dto.user.UserDto;
import cl.copeuch.challenge.dto.user.mapper.UserMapper;
import cl.copeuch.challenge.exceptions.NotFoundRecordException;
import cl.copeuch.challenge.repository.UserRepository;
import cl.copeuch.challenge.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto loginUser(String username, String password) throws NotFoundRecordException {
        return userRepository.findByUsername(username)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new NotFoundRecordException("User with username -> " + username + " not found"));
    }
}
