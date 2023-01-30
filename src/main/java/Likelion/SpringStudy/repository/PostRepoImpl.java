package Likelion.SpringStudy.repository;

import Likelion.SpringStudy.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PostRepoImpl implements PostRepo {
    private final EntityManager em;

    @Override
    public Post save(Post post) {
        if (post.getId() == null) {
            em.persist(post);
            return post;
        } else {
            return em.merge(post);
        }
    }

    @Override
    public Optional<Post> findById(Long id) {
        Post post = em.find(Post.class, id);
        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> findByName(String name) {
        return em.createQuery("SELECT p FROM Post p where p.title = :name", Post.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("SELECT p from Post p", Post.class)
                .getResultList();
    }

    @Override
    public List<Post> findAllByBlogId(Long blog_id) {
        return em.createQuery("select p from Post p where p.blog.id = :blog_id", Post.class)
                .setParameter("blog_id", blog_id)
                .getResultList();
    }

    @Override
    public void delete(Post post) {
        em.remove(post);
    }

    @Override
    public void deleteById(Long id) {
        Assert.notNull(id, "ID must not null");
        delete(findById(id).orElseThrow(() -> new EmptyResultDataAccessException(
                String.format("No post entity with id exists!"), 1)));
    }
}
