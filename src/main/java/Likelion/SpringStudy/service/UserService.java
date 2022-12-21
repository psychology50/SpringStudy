package Likelion.SpringStudy.service;

import Likelion.SpringStudy.domain.UserDomain;
import Likelion.SpringStudy.dto.UserForm;
import Likelion.SpringStudy.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepositoryInterface userRepositoryInterface;

    @Autowired
    public UserService(UserRepositoryInterface userRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface;
    }

    private void validateDuplicateUser(UserDomain userDomain) {
        userRepositoryInterface.findByName(userDomain.getNickname())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Long register(UserForm form) {
        UserDomain userDomain = form.toEntity();
        if (userDomain.getId() == null) validateDuplicateUser(userDomain);
        userRepositoryInterface.save(userDomain);
        return userDomain.getId();
    }

    public List<UserDomain> findUsers() {
        return userRepositoryInterface.findAll();
    }

    public Optional<UserDomain> findUserById(Long userId) {
        return userRepositoryInterface.findById(userId);
    }
    public Optional<UserDomain> findUserByName(String username) { return userRepositoryInterface.findByName(username); }
    public Optional<UserDomain> findUserByNickname(String nickname) { return userRepositoryInterface.findByNickname(nickname);}

    public void deleteUserByNickname(String nickname) {userRepositoryInterface.deleteByNickname(nickname);}
}
