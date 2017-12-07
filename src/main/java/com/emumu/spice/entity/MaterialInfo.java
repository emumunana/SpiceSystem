package com.emumu.spice.entity;

import java.io.Serializable;
import java.util.Date;

public class MaterialInfo implements Serializable {
    private String materialId;
    private String materialName;
    private String materialPrice;
    private Date created;
    private Date updated;

    public void setMaterialId(String materialId){
        this.materialId = materialId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialPrice(String materialPrice) {
        this.materialPrice = materialPrice;
    }

    public String getMaterialPrice() {
        return materialPrice;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCreated() {
        return created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getUpdated() {
        return updated;
    }
}
