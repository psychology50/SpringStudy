package Likelion.SpringStudy.repository;

import Likelion.SpringStudy.domain.UserDomain;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepository implements UserRepositoryInterface {
    private final EntityManager em;
    public UserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public UserDomain save(UserDomain userDomain) {
        if (userDomain.getId() == null) {
            em.persist(userDomain);
            return userDomain;
        } else {
            return em.merge(userDomain);
        }
    }

    @Override
    public Optional<UserDomain> findById(Long id) {
        UserDomain userDomain = em.find(UserDomain.class, id);
        return Optional.ofNullable(userDomain);
    }

    @Override
    public Optional<UserDomain> findByName(String name) {
        List<UserDomain> result = em.createQuery("select u from user u where u.username = :name", UserDomain.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<UserDomain> findByNickname(String nickname) {
        List<UserDomain> result = em.createQuery("select u from user u where u.nickname = :nickname", UserDomain.class)
                .setParameter("nickname", nickname)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<UserDomain> findAll() {
        return em.createQuery("select u from user u", UserDomain.class)
                .getResultList();
    }

    @Override
    public void delete(UserDomain userDomain) {
        Assert.notNull(userDomain, "ID must not null");
        em.remove(userDomain);
    }

    @Override
    public void deleteByNickname(String nickname) {
        Assert.notNull(nickname, "ID must not null");
        delete(findByNickname(nickname).orElseThrow(() -> new EmptyResultDataAccessException(
                String.format("No user entity with nickname %s exists!", nickname), 1)));
    }


}
