package Likelion.SpringStudy.repository;

import Likelion.SpringStudy.domain.UserDomain;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryInterface {
    UserDomain save(UserDomain userDomain);
    Optional<UserDomain> findById(Long id);
    Optional<UserDomain> findByName(String name);
    Optional<UserDomain> findByNickname(String nickname);
    List<UserDomain> findAll();
    void delete(UserDomain userDomain);
    void deleteByNickname(String nickname);
}
