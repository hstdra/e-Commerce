package com.team.ecommerce.other;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.TwoWayFieldBridge;

public class CustomNumericBridge implements TwoWayFieldBridge {


    @Override
    public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
        if (value != null) {
            luceneOptions.addNumericFieldToDocument(name, value, document);
        }
    }

    @Override
    public Object get(String name, Document document) {
        return document.get(name);
    }

    @Override
    public String objectToString(Object object) {
        return object.toString();
    }
}