package com.team.ecommerce.service;

import com.team.ecommerce.entity.Product;
import com.team.ecommerce.repository.ProductRepository;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.engine.spi.FacetManager;
import org.hibernate.search.query.facet.Facet;
import org.hibernate.search.query.facet.FacetingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LuceneSearchService {
    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    private ProductRepository productRepository;

    public List<Product> search(String q, String ct, String fds) {
        try {
            return getFullTextQuery(q, ct, fds).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    private QueryBuilder getQueryBuilder() {
        return Search.getFullTextEntityManager(entityManager).getSearchFactory().buildQueryBuilder().forEntity(Product.class)
                .overridesForField("name", "without_edgeNgram")
                .overridesForField("description", "without_edgeNgram")
                .overridesForField("categoryFacet", "without_edgeNgram")
                .overridesForField("fieldDetailSearch", "without_edgeNgram")
                .get();
    }

    private FullTextQuery getFullTextQuery(String q, String ct, String fds) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder qb = getQueryBuilder();

        org.apache.lucene.search.Query query1 = q.equals("") ? qb.all().createQuery() : qb
                .simpleQueryString()
                .onFields("name", "description")
                .withAndAsDefaultOperator()
                .matching(q)
                .createQuery();

        org.apache.lucene.search.Query query2 = ct.equals("") ? qb.all().createQuery() : qb
                .simpleQueryString()
                .onFields("categorySearch")
                .matching(ct)
                .createQuery();

        org.apache.lucene.search.Query query3 = fds.equals("") ? qb.all().createQuery() : qb
                .simpleQueryString()
                .onFields("fieldDetailSearch")
                .withAndAsDefaultOperator()
                .matching(fds)
                .createQuery();

        org.apache.lucene.search.Query query = qb.bool().must(query1).must(query2).must(query3).createQuery();

        return fullTextEntityManager.createFullTextQuery(query, Product.class);
    }

    public LinkedHashMap<String, Integer> getCategory(String q, String ct, String fds) {
        FullTextQuery fullTextQuery = getFullTextQuery(q, ct, fds);
        FacetingRequest facetingRequest = getQueryBuilder().facet()
                .name("category")
                .onField("categoryFacet")
                .discrete()
                .createFacetingRequest();
        FacetManager facetManager = fullTextQuery.getFacetManager();
        facetManager.enableFaceting(facetingRequest);

        List<Facet> facets = facetManager.getFacets("category");

        return facets.stream().collect(Collectors.toMap(Facet::getValue, Facet::getCount, (e1, e2) -> e2,
                LinkedHashMap::new));
    }
}
