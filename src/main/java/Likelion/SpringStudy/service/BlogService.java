package Likelion.SpringStudy.service;

import Likelion.SpringStudy.domain.Blog;
import Likelion.SpringStudy.dto.BlogForm;
import Likelion.SpringStudy.repository.BlogRepository;
import Likelion.SpringStudy.repository.BlogRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BlogService {
    private  final BlogRepositoryInterface blogRepositoryInterface;

    @Autowired
    public BlogService(BlogRepositoryInterface blogRepositoryInterface) {
        this.blogRepositoryInterface = blogRepositoryInterface;
    }

    private void validateDuplicateBlog(Blog blog) {
        blogRepositoryInterface.findByName(blog.getBlog_name())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 블로그명입니다.");
                });
    }

    public Long create(BlogForm form, Long user_id) {
        Blog blog = form.toEntity();
        if (blog.getId() == null) validateDuplicateBlog(blog);
        blogRepositoryInterface.save(blog, user_id);
        return blog.getId();
    }

    public Optional<Blog> findBlog(Long blog_id) {
        return blogRepositoryInterface.findById(blog_id);
    }

}
