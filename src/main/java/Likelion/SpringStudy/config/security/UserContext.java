package Likelion.SpringStudy.config.security;


import Likelion.SpringStudy.domain.UserDomain;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserContext extends User {
    private final UserDomain userDomain;

    public UserContext(UserDomain userDomain, Collection<? extends GrantedAuthority> authorities) {
        super(userDomain.getUsername(), userDomain.getPassword(), authorities);
        this.userDomain = userDomain;
    }

    public UserDomain getUser() {
        return userDomain;
    }
}
