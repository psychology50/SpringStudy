package Likelion.SpringStudy.service;

import Likelion.SpringStudy.repository.CategoryRepo;
import Likelion.SpringStudy.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final PostRepo postRepo;
    private final CategoryRepo categoryRepo;
}
