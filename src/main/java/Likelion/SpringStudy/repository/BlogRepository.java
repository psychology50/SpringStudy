package Likelion.SpringStudy.repository;

import Likelion.SpringStudy.domain.Blog;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.Optional;

public class BlogRepository implements BlogRepositoryInterface{
    private final EntityManager em;
    public BlogRepository(EntityManager em) {this.em = em;}

    @Override
    public Blog save(Blog blog, Long user_id) {
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
        Blog blog = em.find(Blog.class, name);
        return Optional.ofNullable(blog);
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
}
