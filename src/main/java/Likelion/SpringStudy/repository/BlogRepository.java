package Likelion.SpringStudy.repository;

import Likelion.SpringStudy.domain.Blog;
import Likelion.SpringStudy.domain.UserDomain;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BlogRepository implements BlogRepositoryInterface{
    private final EntityManager em;
    public BlogRepository(EntityManager em) {this.em = em;}

    @Override
    public Blog save(Blog blog) {
        if (blog.getId() == null) {
            em.persist(blog);
            return blog;
        } else {
            return em.merge(blog);
        }
    }

    @Override
    public Optional<Blog> findById(Long id) {
        Blog blog = em.find(Blog.class, id);
        return Optional.ofNullable(blog);
    }

    @Override
    public Optional<Blog> findByName(String name) {
        List<Blog> result = em.createQuery("select b from blog b where b.blog_name = :name", Blog.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public void delete(Blog blog) {
        Assert.notNull(blog, "블로그가 존재해야 합니다.");
        em.remove(blog);
    }

    @Override
    public void deleteById(Long id) {
        Assert.notNull(id, "ID must not null");
        delete(findById(id).orElseThrow(() -> new EmptyResultDataAccessException(
                String.format("No Blog entity exists!"), 1)));
    }

    @Override
    public Optional<Blog> findByUserId(Long user_id) {
        Blog blog = em.find(Blog.class, user_id);
        return Optional.ofNullable(blog);
    }
}
