package com.team.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team.ecommerce.other.CollectionToCSVBridge;
import com.team.ecommerce.other.CustomNumericBridge;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Indexed
@AnalyzerDef(name = "edgeNgram",
        tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = ASCIIFoldingFilterFactory.class),
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(
                        factory = EdgeNGramFilterFactory.class,
                        params = {
                                @Parameter(name = "minGramSize", value = "1"),
                                @Parameter(name = "maxGramSize", value = "10")
                        }
                )
        })
@AnalyzerDef(name = "without_edgeNgram",
        tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = ASCIIFoldingFilterFactory.class),
                @TokenFilterDef(factory = LowerCaseFilterFactory.class)
        })
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private Category category;

    @Field(termVector = TermVector.YES, analyze = Analyze.YES, store = Store.NO, analyzer = @Analyzer(definition = "edgeNgram"))
    private String name;

    private String image;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer quantity;

    private Long price;

    private Long discount;

    @Field(termVector = TermVector.YES, analyze = Analyze.YES, store = Store.NO, analyzer = @Analyzer(definition = "edgeNgram"))
    @Column(columnDefinition = "TEXT DEFAULT NULL")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<FieldDetail> fieldDetails;

    @Field(name = "idSort", bridge = @FieldBridge(impl = CustomNumericBridge.class))
    @NumericField(forField = "idSort")
    @SortableField(forField = "idSort")
    public Integer getIdSort() {
        return id;
    }

    @Field(name = "finalPrice", bridge = @FieldBridge(impl = CustomNumericBridge.class))
    @NumericField(forField = "finalPrice")
    @SortableField(forField = "finalPrice")
    public Long getFinalPrice() {
        return discount == null ? price : discount;
    }

    @Field(name = "categorySearch", analyzer = @Analyzer(definition = "without_edgeNgram"))
    @Field(name = "category", analyze = Analyze.NO)
    @Facet(forField = "category", name = "categoryFacet")
    private String getCategoryFacet() {
        return category.getCategory();
    }

    @Field(name = "fieldDetailSearch", analyzer = @Analyzer(definition = "without_edgeNgram"))
    public String getFieldDetailSearch() {
        try {
            return fieldDetails.stream().map(fd -> fd.getField().getField() + "::" + fd.getDetail() + "  ").collect(Collectors.joining());
        } catch (Exception e) {
            return null;
        }
    }

    @Field(name = "fieldDetailSearchBar", analyzer = @Analyzer(definition = "without_edgeNgram"))
    public String getFieldDetailSearchBar() {
        try {
            return fieldDetails.stream().map(fd -> fd.getDetail() + " ").collect(Collectors.joining());
        } catch (Exception e) {
            return null;
        }
    }

    @Field(name = "fieldDetails", analyze = Analyze.NO, bridge = @FieldBridge(impl = CollectionToCSVBridge.class))
    @Facet(forField = "fieldDetails", name = "fieldDetailsFacet")
    public List<String> getFieldDetailsFacet() {
        try {
            return new ArrayList<String>() {{
                fieldDetails.stream().map(fd -> fd.getField().getField() + "::" + fd.getDetail()).forEach(this::add);
            }};
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<History> histories;

}
