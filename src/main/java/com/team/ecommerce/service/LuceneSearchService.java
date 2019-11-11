package com.team.ecommerce.service;

import com.team.ecommerce.entity.Product;
import com.team.ecommerce.repository.ProductRepository;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
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

    public List<Product> search(String q, String ct, String fds, String from, String to, String sort) {
        try {
            return getFullTextQuery(q, ct, fds, from, to, sort).getResultList();
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
                .overridesForField("fieldDetailSearchBar", "without_edgeNgram")
                .get();
    }

    private FullTextQuery getFullTextQuery(String q, String ct, String fds, String from, String to, String sort) {
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

        org.apache.lucene.search.Query query4 = from.equals("") ? qb.all().createQuery() : qb
                .range()
                .onField("finalPrice")
                .above(Long.valueOf(from))
                .createQuery();

        org.apache.lucene.search.Query query5 = to.equals("") ? qb.all().createQuery() : qb
                .range()
                .onField("finalPrice")
                .below(Long.valueOf(to))
                .createQuery();

        org.apache.lucene.search.Query query = qb.bool().must(query1).must(query2).must(query3).must(query4).must(query5).createQuery();

        return fullTextEntityManager.createFullTextQuery(query, Product.class).setSort(getSort(sort));
    }

    private Sort getSort(String sort) {
        switch (sort) {
            case "ID_ASC":
                return new Sort(SortField.FIELD_SCORE,
                        new SortField("idSort", SortField.Type.INT, false));
            case "PRICE_ASC":
                return new Sort(SortField.FIELD_SCORE,
                        new SortField("finalPrice", SortField.Type.LONG, false));
            case "PRICE_DESC":
                return new Sort(SortField.FIELD_SCORE,
                        new SortField("finalPrice", SortField.Type.LONG, true));
            default:
                return new Sort(SortField.FIELD_SCORE,
                        new SortField("idSort", SortField.Type.INT, true));
        }
    }

    public LinkedHashMap<String, Integer> getCategory(String q, String ct, String fds, String from, String to) {
        FullTextQuery fullTextQuery = getFullTextQuery(q, ct, fds, from, to, "");
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

    public LinkedHashMap<String, LinkedHashMap<String, Integer>> getFieldDetails(String q, String ct, String fds, String from, String to) {
        FullTextQuery fullTextQuery = getFullTextQuery(q, ct, fds, from, to, "");
        FacetingRequest facetingRequest = getQueryBuilder().facet()
                .name("fieldDetails")
                .onField("fieldDetailsFacet")
                .discrete()
                .createFacetingRequest();
        FacetManager facetManager = fullTextQuery.getFacetManager();
        facetManager.enableFaceting(facetingRequest);

        List<Facet> facets = facetManager.getFacets("fieldDetails");

        LinkedHashMap<String, LinkedHashMap<String, Integer>> map = new LinkedHashMap<>();
        for (Facet f : facets) {
            String[] s = f.getValue().split("::");
            if (!map.containsKey(s[0]))
                map.put(s[0], new LinkedHashMap<>());
            map.get(s[0]).put(s[1], f.getCount());
        }
        return map;
    }
}
