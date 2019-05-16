package ru.uziki.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.uziki.AuthorizedUser;
import ru.uziki.model.Role;
import ru.uziki.model.User;
import ru.uziki.repository.UserRepository;

@RestController
@RequestMapping(ProfileController.REST_URL)
public class ProfileController {
    static final String REST_URL = "/profiles";

    @Autowired
    private UserRepository userRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthorizedUser authUser) {
        if (authUser.getAuthorities().contains(Role.ROLE_ADMIN)) {
            return userRepository.get(100001);
        } else {
            return userRepository.get(100000);
        }
        //return userRepository.get(authUser.getId());
    }
}
