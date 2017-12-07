package com.emumu.spice.service;

import com.emumu.spice.common.ResultInfo;
import com.emumu.spice.entity.RecipeInfo;
import com.emumu.spice.entity.VerifyInfo;
import com.emumu.spice.mapper.RecipeInfoMapper;
import com.emumu.spice.mapper.VerifyInfoMapper;
import com.emumu.spice.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RecipeInfoService {

    @Autowired
    private RecipeInfoMapper recipeInfoMapper;

    @Autowired
    private VerifyInfoMapper verifyInfoMapper;

    private ResultInfo resultInfo;

    /**
     * 查询所有配方信息
     * @return
     */
    public ResultInfo listRecipeInfo(){
        resultInfo = new ResultInfo();
        try {
            List<RecipeInfo> result = recipeInfoMapper.listRecipeInfo();
            resultInfo.setErrCode(ResultInfo.SUCCESS);
            resultInfo.setResult(result);
        }catch (Exception e){
            resultInfo.setErrCode(ResultInfo.SELECT_FAIL);
        }
        return resultInfo;
    }

    /**
     * 新增配方信息
     * @param recipeInfo
     * @return
     */
    public ResultInfo addRecipeInfo(RecipeInfo recipeInfo){
        resultInfo = new ResultInfo();
        RecipeInfo ri = recipeInfoMapper.findRecipeInfoById(recipeInfo.getRecipeId());
        if(ri != null){
            resultInfo.setErrCode(ResultInfo.INSERT_FAIL_1);
            return resultInfo;
        }
        Date date = new Date();
        recipeInfo.setCreated(date);
        recipeInfo.setUpdated(date);
        int result = recipeInfoMapper.addRecipeInfo(recipeInfo);
        resultInfo.setErrCode(result == 1 ? ResultInfo.SUCCESS : ResultInfo.INSERT_FAIL);
        return resultInfo;
    }

    /**
     * 更新配方信息
     * @param recipeInfo
     * @return
     */
    public ResultInfo updateRecipeInfo(RecipeInfo recipeInfo){
        resultInfo = new ResultInfo();
        String password = CommonUtils.md5(recipeInfo.getVerifyInfo().getPassword()).toUpperCase();
        VerifyInfo verifyInfo = verifyInfoMapper.checkPassword(password);
        if(verifyInfo == null){
            resultInfo.setErrCode(ResultInfo.UPDATE_FAIL_1);
            return resultInfo;
        }
        recipeInfo.setUpdated(new Date());
        int result =  recipeInfoMapper.updateRecipeInfoById(recipeInfo);
        resultInfo.setErrCode(result == 1 ? ResultInfo.SUCCESS : ResultInfo.UPDATE_FAIL);
        return resultInfo;
    }

    /**
     * 根据ID查询配方详情
     * @param recipeId
     * @return
     */
    public ResultInfo findRecipeInfoById(String recipeId){
        resultInfo = new ResultInfo();
        try {
            RecipeInfo result = recipeInfoMapper.findRecipeInfoById(recipeId);
            resultInfo.setErrCode(ResultInfo.SUCCESS);
            resultInfo.setResult(result);
        }catch (Exception e){
            resultInfo.setErrCode(ResultInfo.SELECT_FAIL);
        }
        return resultInfo;
    }

    public ResultInfo deleteRecipeInfoById(RecipeInfo recipeInfo){
        resultInfo = new ResultInfo();
        String password = CommonUtils.md5(recipeInfo.getVerifyInfo().getPassword()).toUpperCase();
        VerifyInfo verifyInfo = verifyInfoMapper.checkPassword(password);
        if(verifyInfo == null){
            resultInfo.setErrCode(ResultInfo.DELETE_FAIL_1);
            return resultInfo;
        }
        int result = recipeInfoMapper.deleteRecipeInfoById(recipeInfo.getRecipeId());
        resultInfo.setErrCode(result == 1 ? ResultInfo.SUCCESS : ResultInfo.DELETE_FAIL);
        return resultInfo;
    }
}
