package Likelion.SpringStudy.service;

import Likelion.SpringStudy.domain.Blog;
import Likelion.SpringStudy.domain.UserDomain;
import Likelion.SpringStudy.dto.BlogForm;
import Likelion.SpringStudy.repository.BlogRepo;
import Likelion.SpringStudy.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepo blogRepo;
    private final UserRepo userRepo;

    private void validateDuplicateBlog(Blog blog) {
        blogRepo.findByName(blog.getBlog_name())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 블로그명입니다.");
                });
    }

    private void isPresent(UserDomain userDomain) {
        Blog blog = userDomain.getBlog();
        System.out.println(blog);
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
        blogRepo.save(blog);
        return blog.getId();
    }

    public Optional<Blog> findBlog(Long blog_id) {
        return blogRepo.findById(blog_id);
    }

    public Optional<Blog> findBlogByUserId(Long user_id) {
        return blogRepo.findByUserId(user_id);
    }

    public void deleteBlog(Long id) {
        blogRepo.deleteById(id);
    }
}
