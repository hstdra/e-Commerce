package com.team.ecommerce.service;

import com.team.ecommerce.entity.Product;
import com.team.ecommerce.repository.ProductRepository;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class LuceneSearchService {
    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    private ProductRepository productRepository;

    public List<Product> search(String q, String ct, String rds) {
        try {
            return getFullTextQuery(q, ct, rds).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    private FullTextQuery getFullTextQuery(String q, String ct, String fds) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Product.class)
                .overridesForField("name", "without_edgeNgram")
                .overridesForField("description", "without_edgeNgram")
                .overridesForField("categoryFacet", "without_edgeNgram")
                .overridesForField("fieldDetailSearch", "without_edgeNgram")
                .get();

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
}
