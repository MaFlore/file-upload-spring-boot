package com.example.fileuploadspringboot.repository;

import com.example.fileuploadspringboot.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
