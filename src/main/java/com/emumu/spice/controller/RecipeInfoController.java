package com.emumu.spice.controller;

import com.emumu.spice.common.ResultInfo;
import com.emumu.spice.entity.RecipeInfo;
import com.emumu.spice.service.RecipeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/recipe")
public class RecipeInfoController {

    @Autowired
    private RecipeInfoService recipeInfoService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public ResultInfo listRecipeInfo(){
        return recipeInfoService.listRecipeInfo();
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public ResultInfo addRecipeInfo(@RequestBody RecipeInfo recipeInfo){
        return recipeInfoService.addRecipeInfo(recipeInfo);
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public ResultInfo updateReciptInfo(@RequestBody RecipeInfo recipeInfo){
        return recipeInfoService.updateRecipeInfo(recipeInfo);
    }

    @RequestMapping(value="/find",method = RequestMethod.GET)
    public ResultInfo findRecipeInfo(String recipeId){
        return recipeInfoService.findRecipeInfoById(recipeId);
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public ResultInfo deleteRecipeInfo(@RequestBody RecipeInfo recipeInfo){
        return recipeInfoService.deleteRecipeInfoById(recipeInfo);
    }

}
