package Likelion.SpringStudy.repository;

import Likelion.SpringStudy.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
