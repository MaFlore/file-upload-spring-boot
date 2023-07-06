package com.example.fileuploadspringboot.service;

import com.example.fileuploadspringboot.model.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAll();
    Article findById(Long id);
    Article add(Article article);
    Article update(Article article);
    void deleteById(Long id);
}
