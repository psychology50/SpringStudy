package Likelion.SpringStudy.service;

import Likelion.SpringStudy.domain.Post;
import Likelion.SpringStudy.dto.PostForm;
import Likelion.SpringStudy.repository.PostRepoInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepoInterface postRepoInterface;

    public Post create(PostForm form) {
        Post post = form.toEntity();
        return postRepoInterface.save(post);
    }
}
