package Likelion.SpringStudy.service;

import Likelion.SpringStudy.domain.Like;
import Likelion.SpringStudy.domain.Post;
import Likelion.SpringStudy.domain.UserDomain;
import Likelion.SpringStudy.repository.LikeRepo;
import Likelion.SpringStudy.repository.PostRepo;
import Likelion.SpringStudy.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {
    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final LikeRepo likeRepo;

    public Like create(Long user_id, Long post_id) {
        UserDomain user = userRepo.findById(user_id).orElseGet(UserDomain::new);
        Post post = postRepo.findById(post_id).orElseGet(Post::new);
        post.setLike_cnt(1);

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);

        return likeRepo.save(like);
    }

    public void delete(Long user_id, Long post_id) {
        UserDomain user = userRepo.findById(user_id).orElseGet(UserDomain::new);
        Post post = postRepo.findById(post_id).orElseGet(Post::new);
        post.setLike_cnt(-1);

        Like like = likeRepo.findLikeByUserAndBlog(user, post);

        like.setUser(null); user.getLikes().remove(like);
        like.setPost(null); post.getLikes().remove(like);

        likeRepo.delete(like);
    }
}
