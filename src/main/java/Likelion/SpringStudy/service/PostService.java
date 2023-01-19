package Likelion.SpringStudy.service;

import Likelion.SpringStudy.domain.Blog;
import Likelion.SpringStudy.domain.Post;
import Likelion.SpringStudy.dto.PostForm;
import Likelion.SpringStudy.repository.BlogRepositoryInterface;
import Likelion.SpringStudy.repository.PostRepoInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepoInterface postRepoInterface;
    private final BlogRepositoryInterface blogRepositoryInterface;

    public Post create(PostForm form) {
        Post post = form.toEntity();
        Blog blog = blogRepositoryInterface.findById(form.getBlog_id())
                .orElseGet(Blog::new);
        post.setBlog(blog);
        log.info("is valid");
        return postRepoInterface.save(post);
    }

    public List<Post> findAllByBlogId(Long blog_id) {return postRepoInterface.findAllByBlogId(blog_id);}

    public Post findById(Long post_id) {return postRepoInterface.findById(post_id).orElseGet(Post::new);}
    public void deleteById(Long post_id) {postRepoInterface.deleteById(post_id);}
}
