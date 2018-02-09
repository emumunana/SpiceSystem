package com.emumu.spice.controller;

import com.emumu.spice.common.ResultInfo;
import com.emumu.spice.entity.RecipeInfo;
import com.emumu.spice.service.RecipeInfoService;
import com.emumu.spice.utils.ExcelUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/recipe")
public class RecipeInfoController {

    @Autowired
    private RecipeInfoService recipeInfoService;
    
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody 
    public ResultInfo importRecipeInfo(@RequestParam("file") MultipartFile file) {
    		return recipeInfoService.importRecipeInfo(file);
    }

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
