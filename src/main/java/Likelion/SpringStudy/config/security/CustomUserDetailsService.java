package Likelion.SpringStudy.config.security;

import Likelion.SpringStudy.domain.UserDomain;
import Likelion.SpringStudy.repository.UserRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service("userDetailsService") // bean 등록
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepositoryInterface userRepositoryInterface;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        UserDomain userDomain = userRepositoryInterface.findByNickname(nickname).orElseThrow(() ->
                new UsernameNotFoundException("해당 닉네임을 찾을 수 없습니다."));

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(userDomain.getRole().toString()));

        return new UserContext(userDomain, roles);
    }
}
