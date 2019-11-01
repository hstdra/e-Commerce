package com.team.ecommerce.controller.web;

import com.team.ecommerce.entity.Product;
import com.team.ecommerce.service.LuceneSearchService;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
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
    private LuceneSearchService luceneSearchService;


    @GetMapping("product")
    public List<Product> searchProduct(@RequestParam(defaultValue = "") String q) {
        List<Product> products = luceneSearchService.search(q, "", "", "", "", "");
        return products.subList(0, Math.min(products.size(), 8));
    }

    @GetMapping(value = "/reindex")
    public ResponseEntity<String> insert() throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
        return ResponseEntity.ok("success");
    }
}
