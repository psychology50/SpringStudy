package Likelion.SpringStudy.service;

import Likelion.SpringStudy.domain.Blog;
import Likelion.SpringStudy.domain.UserDomain;
import Likelion.SpringStudy.dto.BlogForm;
import Likelion.SpringStudy.repository.BlogRepositoryInterface;
import Likelion.SpringStudy.repository.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepositoryInterface blogRepositoryInterface;
    private final UserRepositoryInterface userRepositoryInterface;

    private void validateDuplicateBlog(Blog blog) {
        blogRepositoryInterface.findByName(blog.getBlog_name())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 블로그명입니다.");
                });
    }

    private void isPresent(UserDomain userDomain) {
        Blog blog = userDomain.getBlog();
        if (blog != null) {
            throw new IllegalStateException("이미 블로그를 소유하고 있습니다.");
        }
    }

    public Long create(BlogForm form, UserDomain userDomain) {
        Blog blog = form.toEntity();
        if (blog.getId() == null) {
            validateDuplicateBlog(blog);
            isPresent(userDomain);
            blog.setOwner(userDomain);
        }
        blogRepositoryInterface.save(blog);
        return blog.getId();
    }

    public Optional<Blog> findBlog(Long blog_id) {
        return blogRepositoryInterface.findById(blog_id);
    }

    public Optional<Blog> findBlogByUserId(Long user_id) {
        return blogRepositoryInterface.findByUserId(user_id);
    }

}
