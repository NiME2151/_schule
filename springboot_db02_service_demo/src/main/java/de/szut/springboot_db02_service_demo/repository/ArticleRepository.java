package de.szut.springboot_db02_service_demo.repository;

import de.szut.springboot_db02_service_demo.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
