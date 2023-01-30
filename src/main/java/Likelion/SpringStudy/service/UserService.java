package Likelion.SpringStudy.service;

import Likelion.SpringStudy.domain.UserDomain;
import Likelion.SpringStudy.dto.UserForm;
import Likelion.SpringStudy.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private void validateDuplicateUser(UserDomain userDomain) {
        userRepo.findByName(userDomain.getNickname())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Long register(UserForm form) {
        UserDomain userDomain = form.toEntity();
        if (userDomain.getId() == null) validateDuplicateUser(userDomain);
        userRepo.save(userDomain);
        return userDomain.getId();
    }

    public List<UserDomain> findUsers() {
        return userRepo.findAll();
    }

    public Optional<UserDomain> findUserById(Long userId) {
        return userRepo.findById(userId);
    }
    public Optional<UserDomain> findUserByName(String username) { return userRepo.findByName(username); }
    public Optional<UserDomain> findUserByNickname(String nickname) { return userRepo.findByNickname(nickname);}

    public void deleteUserByNickname(String nickname) {
        userRepo.deleteByNickname(nickname);}
}
