package Likelion.SpringStudy.service;

import Likelion.SpringStudy.domain.Blog;
import Likelion.SpringStudy.domain.Post;
import Likelion.SpringStudy.dto.PostForm;
import Likelion.SpringStudy.repository.BlogRepo;
import Likelion.SpringStudy.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepo postRepo;
    private final BlogRepo blogRepo;

    public Post create(PostForm form) {
        Post post = form.toEntity();
        Blog blog = blogRepo.findById(form.getBlog_id())
                .orElseGet(Blog::new);
        post.setBlog(blog);
        return postRepo.save(post);
    }

    public List<Post> findAllByBlogId(Long blog_id) {return postRepo.findAllByBlogId(blog_id);}

    public Post findById(Long post_id) {return postRepo.findById(post_id).orElseGet(Post::new);}
    public void deleteById(Long post_id) {
        postRepo.deleteById(post_id);}
}
