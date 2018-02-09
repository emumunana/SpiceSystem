package com.emumu.spice.entity;

import java.io.*;
import java.util.Date;
import java.util.List;

public class RecipeInfo implements Serializable{
    private String recipeId;
    private String recipeName;
    private List<RecipeDetail> mainRecipe;
    private byte[] mainRecipeStream;
    private List<RecipeDetail> secondRecipe;
    private byte[] secondRecipeStream;
    private Date created;
    private Date updated;

    private VerifyInfo verifyInfo;

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
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

    public void setMainRecipe(List<RecipeDetail> mainRecipe) {
        this.mainRecipe = mainRecipe;
    }

    public List<RecipeDetail> getMainRecipe() {
        List<RecipeDetail> list = null;
        if(this.mainRecipeStream != null){
            try {
                ByteArrayInputStream in = new ByteArrayInputStream(this.mainRecipeStream);
                ObjectInputStream sIn = new ObjectInputStream(in);
                list = (List<RecipeDetail>) sIn.readObject();
            }catch (Exception e){

            }
        }
        return list != null ? list : this.mainRecipe;
    }

    public void setSecondRecipe(List<RecipeDetail> secondRecipe) {
        this.secondRecipe = secondRecipe;
    }

    public List<RecipeDetail> getSecondRecipe() {
        List<RecipeDetail> list = null;
        if(this.mainRecipeStream != null){
            try {
                ByteArrayInputStream in = new ByteArrayInputStream(this.secondRecipeStream);
                ObjectInputStream sIn = new ObjectInputStream(in);
                list = (List<RecipeDetail>) sIn.readObject();
            }catch (Exception e){

            }
        }
        return list != null ? list : this.secondRecipe;
    }

    public void setMainRecipeStream(byte[] mainRecipeStream) {
        this.mainRecipeStream = mainRecipeStream;
    }

    public byte[] getMainRecipeStream() {
        byte[] bytes = null;
        if(this.mainRecipe != null){
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream Out = new ObjectOutputStream(out);
                Out.writeObject(this.mainRecipe);
                Out.flush();
                bytes = out.toByteArray();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return bytes;
    }

    public void setSecondRecipeStream(byte[] secondRecipeStream) {
        this.secondRecipeStream = secondRecipeStream;
    }

    public byte[] getSecondRecipeStream() {
        byte[] bytes = null;
        if(this.secondRecipe != null){
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream Out = new ObjectOutputStream(out);
                Out.writeObject(this.secondRecipe);
                Out.flush();
                bytes = out.toByteArray();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return bytes;
    }

    public void setVerifyInfo(VerifyInfo verifyInfo) {
        this.verifyInfo = verifyInfo;
    }

    public VerifyInfo getVerifyInfo() {
        return verifyInfo;
    }
}

