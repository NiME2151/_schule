package de.szut.springboot_db02_service_demo.mapper;

import de.szut.springboot_db02_service_demo.dto.ArticleRequest;
import de.szut.springboot_db02_service_demo.dto.ArticleResponse;
import de.szut.springboot_db02_service_demo.model.Article;
import de.szut.springboot_db02_service_demo.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleMapper {

    @Autowired
    private SupplierService supplierService;

    public Article mapRequestToModel(ArticleRequest articleRequest) {
        Article article = new Article();
        article.setDesignation(articleRequest.getDesignation());
        article.setPrice(articleRequest.getPrice());
        if (articleRequest.getSupplierId() > 0) {
            article.setSupplier(supplierService.readById(articleRequest.getSupplierId()));
        }
        return article;
    }

    public Article mapRequestToModel(long id, ArticleRequest articleRequest) {
        Article article = new Article();
        article.setId(id);
        article.setDesignation(articleRequest.getDesignation());
        article.setPrice(articleRequest.getPrice());
        if (articleRequest.getSupplierId() > 0) {
            article.setSupplier(supplierService.readById(articleRequest.getSupplierId()));
        }
        return article;
    }

    public ArticleResponse mapModelToResponse(Article article) {
        ArticleResponse response = new ArticleResponse();
        response.setId(article.getId());
        response.setDesignation(article.getDesignation());
        response.setPrice(article.getPrice());
        return response;
    }

    public List<ArticleResponse> mapAllModelsToResponses(List<Article> articles) {
        List<ArticleResponse> response = new ArrayList<>();
        for (Article article : articles ) {
            ArticleResponse toBeAdded = new ArticleResponse();
            toBeAdded.setId(article.getId());
            toBeAdded.setDesignation(article.getDesignation());
            toBeAdded.setPrice(article.getPrice());
            response.add(toBeAdded);
        }
        return response;
    }
}
