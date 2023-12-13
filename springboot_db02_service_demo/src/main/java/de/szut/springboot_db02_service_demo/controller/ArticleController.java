package de.szut.springboot_db02_service_demo.controller;

import de.szut.springboot_db02_service_demo.dto.ArticleRequest;
import de.szut.springboot_db02_service_demo.dto.ArticleResponse;
import de.szut.springboot_db02_service_demo.dto.SupplierResponse;
import de.szut.springboot_db02_service_demo.mapper.ArticleMapper;
import de.szut.springboot_db02_service_demo.mapper.SupplierMapper;
import de.szut.springboot_db02_service_demo.model.Article;
import de.szut.springboot_db02_service_demo.model.Supplier;
import de.szut.springboot_db02_service_demo.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/articles")
@RequiredArgsConstructor
public class ArticleController {

    @Autowired
    private final ArticleService articleService;
    @Autowired
    private final ArticleMapper articleMapper;
    @Autowired
    private final SupplierMapper supplierMapper;

    @PostMapping
    public ArticleResponse addArticle(@Valid @RequestBody ArticleRequest articleRequest) {
        Article article = articleMapper.mapRequestToModel(articleRequest);
        Article articleResponse = articleService.create(article);
        return articleMapper.mapModelToResponse(articleResponse);
    }

    @GetMapping
    public List<ArticleResponse> getAllArticles() {
        List<Article> articles = articleService.readAll();
        return articleMapper.mapAllModelsToResponses(articles);
    }

    @GetMapping("/{id}")
    public ArticleResponse getArticle(@PathVariable long id) {
        Article article = articleService.readById(id);
        return articleMapper.mapModelToResponse(article);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable long id) {
        articleService.delete(id);
    }

    @PutMapping("/{id}")
    public ArticleResponse updateArticle(@PathVariable long id, @Valid @RequestBody ArticleRequest articleRequest) {
        Article article = articleMapper.mapRequestToModel(id, articleRequest);
        Article articleResponse = articleService.update(article);
        return articleMapper.mapModelToResponse(articleResponse);
    }

    @GetMapping("/{id}/suppliers")
    public SupplierResponse getForOneArticleItsSupplier(@PathVariable long id) {
        Supplier supplier = articleService.readForOneArticleItsSupplier(id);
        return supplierMapper.mapModelToResponse(supplier);
    }
}
