package com.emumu.spice.entity;

import java.io.Serializable;
import java.util.Date;

public class RecipeDetail implements Serializable{

    private MaterialInfo materialInfo;
    private String scale;

    public void setMaterialInfo(MaterialInfo materialInfo) {
        this.materialInfo = materialInfo;
    }

    public MaterialInfo getMaterialInfo() {
        return materialInfo;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getScale() {
        return scale;
    }

}
