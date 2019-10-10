package com.team.ecommerce.controller.web;

import com.team.ecommerce.entity.Product;
import com.team.ecommerce.repository.ProductRepository;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class APIController {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductRepository repository;

    @GetMapping("product")
    public List<Product> fTS(@RequestParam(defaultValue = "") String q) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Product.class).get();
        org.apache.lucene.search.Query query = qb
                .keyword()
                .onField("name")
                .matching(q + "*")
                .createQuery();

        javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Product.class);
        return persistenceQuery.getResultList();
    }

    @GetMapping(value = "/reindex")
    public ResponseEntity<String> insert() throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
        return ResponseEntity.ok("success");
    }
}
