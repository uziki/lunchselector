package ru.uziki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.uziki.AuthorizedUser;
import ru.uziki.model.User;
import ru.uziki.repository.User.UserRepository;

@Service("securityService")
public class SecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    //TODO implement
    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userRepository.getByEmail(email);
        if (u == null) {
            throw new UsernameNotFoundException("User" + email + " is not found");
        }
        return new AuthorizedUser(u);
    }
}
