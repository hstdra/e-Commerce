package com.team.ecommerce.controller.web;

import com.team.ecommerce.entity.Product;
import com.team.ecommerce.service.LuceneSearchService;
import com.team.ecommerce.service.ProductService;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.engine.spi.FacetManager;
import org.hibernate.search.query.facet.Facet;
import org.hibernate.search.query.facet.FacetingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class APIController {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductService productService;

    @Autowired
    private LuceneSearchService luceneSearchService;


    @GetMapping("product")
    public List<Product> searchProduct(@RequestParam(defaultValue = "") String q) {
        return luceneSearchService.search(q, "", "");
    }

    @GetMapping("facet")
    public List<Facet> facet(@RequestParam(defaultValue = "") String q) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder builder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Product.class)
                .get();

        FacetingRequest priceFacetingRequest = builder.facet()
                .name("test")
                .onField("fieldDetailsFacet")
                .discrete()
                .createFacetingRequest();

        Query luceneQuery = builder.simpleQueryString().onField("fieldDetailSearch").matching("  + HANG::g skill + Bus::2133").createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Product.class);

        FacetManager facetManager = fullTextQuery.getFacetManager();
        facetManager.enableFaceting(priceFacetingRequest);

        List<Facet> facets = facetManager.getFacets("test");
        System.out.println(new ArrayList<>(facets));
        return new ArrayList<>(facets);
    }

    @GetMapping(value = "/reindex")
    public ResponseEntity<String> insert() throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
        return ResponseEntity.ok("success");
    }
}
