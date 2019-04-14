package com.mall.model;

public class TAttributePropertyMapping {
    private Integer id;

    private Integer attributeId;

    private Integer propertyId;

    public TAttributePropertyMapping() {
    }

    public TAttributePropertyMapping(Integer attributeId, Integer propertyId) {
        this.attributeId = attributeId;
        this.propertyId = propertyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }
}