package com.emumu.spice.mapper;

import com.emumu.spice.entity.RecipeInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RecipeInfoMapper {

    /**
     * 查询配方列表
     * @return
     */
    @Select("SELECT recipe_id, recipe_name FROM recipe_info ORDER BY created desc")
    @Results({
            @Result(property = "recipeId",  column = "recipe_id"),
            @Result(property = "recipeName", column = "recipe_name")
    })
    List<RecipeInfo> listRecipeInfo();

    /**
     * 新增配方信息
     * @param recipeInfo
     * @return
     */
    @Insert("INSERT INTO recipe_info (recipe_id, recipe_name, main_recipe, second_recipe, created, updated) VALUES (#{recipeId}, #{recipeName}, #{mainRecipeStream}, #{secondRecipeStream}, #{created}, #{updated})")
    int addRecipeInfo(RecipeInfo recipeInfo);

    /**
     * 根据ID更新配方信息
     * @param recipeInfo
     * @return
     */
    @Update("UPDATE recipe_info SET recipe_name = #{recipeName}, main_recipe = #{mainRecipeStream}, second_recipe = #{secondRecipeStream}, updated = #{updated} WHERE recipe_id = #{recipeId}")
    int updateRecipeInfoById(RecipeInfo recipeInfo);

    /**
     * 根据ID查询配方信息
     * @param recipeId
     * @return
     */
    @Select("SELECT * FROM recipe_info WHERE recipe_id = #{recipeId}")
    @Results({
            @Result(property = "recipeId",  column = "recipe_id"),
            @Result(property = "recipeName", column = "recipe_name"),
            @Result(property = "mainRecipeStream", column = "main_recipe"),
            @Result(property = "secondRecipeStream", column = "second_recipe"),
            @Result(property = "created", column = "created"),
            @Result(property = "updated", column = "updated")
    })
    RecipeInfo findRecipeInfoById(String recipeId);

    /**
     * 根据ID删除配方信息
     * @param recipeId
     * @return
     */
    @Delete("DELETE FROM recipe_info WHERE recipe_id = #{recipeId}")
    int deleteRecipeInfoById(String recipeId);
}
