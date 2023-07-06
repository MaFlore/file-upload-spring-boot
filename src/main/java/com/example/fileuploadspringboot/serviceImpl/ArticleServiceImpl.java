package com.example.fileuploadspringboot.serviceImpl;

import com.example.fileuploadspringboot.model.Article;
import com.example.fileuploadspringboot.repository.ArticleRepository;
import com.example.fileuploadspringboot.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public Article add(Article article) {
        article.setDesignation(article.getDesignation());
        article.setDescription(article.getDescription());
        article.setPrixUnitaire(article.getPrixUnitaire());
        return articleRepository.save(article);
    }

    @Override
    public Article update(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}
