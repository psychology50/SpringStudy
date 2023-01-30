package Likelion.SpringStudy.service;

import Likelion.SpringStudy.domain.Post;
import Likelion.SpringStudy.domain.Tag;
import Likelion.SpringStudy.dto.TagForm;
import Likelion.SpringStudy.repository.PostRepo;
import Likelion.SpringStudy.repository.TagRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class TagService {
    private final PostRepo postRepo;
    private final TagRepo tagRepo;

    private void validateDuplicateTag(String tag_name, Post post) {
        for (Tag tag : post.getTags()) {
            if (tag.getTag_name().equals(tag_name))
                throw new IllegalStateException("중복되는 태그 이름입니다.");
        }
    }

    public Tag create(TagForm form, Long post_id) {
        Tag tag = form.toEntity();
        Post post = postRepo.findById(post_id).orElseGet(Post::new);

        if (tag.getId() == null) {
            validateDuplicateTag(tag.getTag_name(), post);
        }
        tag.setPost(post);
        tagRepo.save(tag);

        return tag;
    }

    public void delete(Long post_id, Long tag_id) { // 쿼리로 받자
        Post post = postRepo.findById(post_id).orElseGet(Post::new);
        Tag tag = tagRepo.findById(tag_id).orElseGet(Tag::new);

        post.getTags().remove(tag);
    }

    public void deleteAll(Long post_id) {
        Post post = postRepo.findById(post_id).orElseGet(Post::new);
        post.getTags().clear();
    }
}
