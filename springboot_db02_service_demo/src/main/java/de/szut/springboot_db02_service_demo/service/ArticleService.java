package de.szut.springboot_db02_service_demo.service;

import de.szut.springboot_db02_service_demo.model.Article;
import de.szut.springboot_db02_service_demo.model.Supplier;
import de.szut.springboot_db02_service_demo.repository.ArticleRepository;
import de.szut.springboot_db02_service_demo.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;

    private String articleNotFoundExceptionMessage = "Article not found.";
    private String noArticleFoundExceptionMessage = "No articles found.";
    private String supplierNotFoundExceptionMessage = "Supplier not found.";
    @Autowired
    private SupplierRepository supplierRepository;


    public Article create(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> readAll() {
        List<Article> optionalArticle = articleRepository.findAll();
        if (optionalArticle.isEmpty()) {
            throw new EntityNotFoundException(noArticleFoundExceptionMessage);
        }
        return optionalArticle;
    }

    public Article readById(long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty()) {
            throw new EntityNotFoundException(articleNotFoundExceptionMessage + " id = " + id);
        }
        return optionalArticle.get();
    }

    public void delete(long id) {
        if (!articleRepository.existsById(id)) {
            throw new EntityNotFoundException(articleNotFoundExceptionMessage + " id = " + id);
        }
        articleRepository.deleteById(id);
    }

    public Article update(Article article) {
        Article toBeUpdated = readById(article.getId());
        toBeUpdated.setDesignation(article.getDesignation());
        toBeUpdated.setPrice(article.getPrice());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(article.getSupplier().getId());
        if (optionalSupplier.isEmpty()) {
            throw new EntityNotFoundException(supplierNotFoundExceptionMessage + " id = " + article.getId());
        }
        toBeUpdated.setSupplier(optionalSupplier.get());
        return articleRepository.save(toBeUpdated);
    }

    public Supplier readForOneArticleItsSupplier(long id) {
        Article article = readById(id);
        return article.getSupplier();
    }
}
