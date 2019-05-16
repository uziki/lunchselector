package ru.uziki.Util;

import ru.uziki.AuthorizedUser;
import ru.uziki.model.Role;

public class UserUtil {
    public static boolean isAdmin(AuthorizedUser authorizedUser) {
        return (authorizedUser.getAuthorities().contains(Role.ROLE_ADMIN));
    }
}
