package Likelion.SpringStudy.repository;

import Likelion.SpringStudy.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {
}
