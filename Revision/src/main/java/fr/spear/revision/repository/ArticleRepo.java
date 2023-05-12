package fr.spear.revision.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.spear.revision.Bean.Article;

public interface ArticleRepo extends JpaRepository<Article, Integer> {


}
