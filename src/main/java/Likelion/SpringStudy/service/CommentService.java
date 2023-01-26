package Likelion.SpringStudy.service;

import Likelion.SpringStudy.domain.Blog;
import Likelion.SpringStudy.domain.Comment;
import Likelion.SpringStudy.domain.Post;
import Likelion.SpringStudy.domain.UserDomain;
import Likelion.SpringStudy.dto.CommentForm;
import Likelion.SpringStudy.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final UserRepositoryInterface userRepositoryInterface;
    private final BlogRepositoryInterface blogRepositoryInterface;
    private final PostRepoInterface postRepoInterface;
    private final CommentRepo commentRepo;

    public Comment create(CommentForm form, Long user_id, Long post_id) {
        Comment comment = form.toEntity();
        Post post = postRepoInterface.findById(post_id).orElseGet(Post::new);
        UserDomain user = userRepositoryInterface.findById(user_id).orElseGet(UserDomain::new);
        comment.setUser(user);
        comment.setPost(post);

        return (Comment) commentRepo.save(comment);
    }

    public void delete(Long comment_id) {
        commentRepo.deleteById(comment_id);
    }
}
