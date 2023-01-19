package Likelion.SpringStudy.service;

import Likelion.SpringStudy.domain.Blog;
import Likelion.SpringStudy.domain.Post;
import Likelion.SpringStudy.dto.PostForm;
import Likelion.SpringStudy.repository.BlogRepositoryInterface;
import Likelion.SpringStudy.repository.PostRepoInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepoInterface postRepoInterface;
    private final BlogRepositoryInterface blogRepositoryInterface;

    public Post create(PostForm form) {
        Post post = form.toEntity();
        if (post.getId() == null) {
            Blog blog = blogRepositoryInterface.findById(form.getBlog_id())
                    .orElseGet(Blog::new);
            post.setBlog(blog);
        }
        return postRepoInterface.save(post);
    }

    public List<Post> findPosts() {return postRepoInterface.findAll();}
}
