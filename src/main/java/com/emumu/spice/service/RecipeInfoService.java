package com.emumu.spice.service;

import com.emumu.spice.common.ResultInfo;
import com.emumu.spice.entity.MaterialInfo;
import com.emumu.spice.entity.RecipeDetail;
import com.emumu.spice.entity.RecipeInfo;
import com.emumu.spice.entity.VerifyInfo;
import com.emumu.spice.mapper.RecipeInfoMapper;
import com.emumu.spice.mapper.VerifyInfoMapper;
import com.emumu.spice.utils.CommonUtils;
import com.emumu.spice.utils.ExcelUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecipeInfoService {

    @Autowired
    private RecipeInfoMapper recipeInfoMapper;

    @Autowired
    private VerifyInfoMapper verifyInfoMapper;

    private ResultInfo resultInfo;
    
    public ResultInfo importRecipeInfo(MultipartFile file) {
    		resultInfo = new ResultInfo();
    		if(file.isEmpty()) {
    			resultInfo.setErrCode(ResultInfo.INSERT_FAIL_1);
            return resultInfo;
    		}
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if(!"xls".equals(fileType) && !"xlsx".equals(fileType)) {
        		resultInfo.setErrCode(ResultInfo.INSERT_FAIL_1);
            return resultInfo;
        }
        InputStream in;
    		try {
    			in = file.getInputStream();
    			ArrayList<ArrayList<String>> list = ExcelUtil.exportExcel(in, fileType); // 导入配方excel
    			RecipeInfo recipeInfo = new RecipeInfo();
    			String recipeId = list.get(0).get(0); //取excel第一行，解析配方ID及名字
    			// 解析配方ID及名字
    			if(recipeId.length() > 0) {
    				int index = recipeId.indexOf(" ");
    				if(index != -1) {
    					recipeInfo.setRecipeId(recipeId.substring(0, index));
    					recipeInfo.setRecipeName(recipeId.substring(index + 1, recipeId.length()));
    				}else {
    					recipeInfo.setRecipeId(recipeId);
    					recipeInfo.setRecipeName(recipeId);
    				}
    			}
    			int mid = -1;
    			int mn = -1;
    			int mc = -1;
    			int startIndex = 0;
    			// 解析编码、名称、数量对应的列
    			for(int i = 1; i < list.size(); i++) {
    				ArrayList<String> row = list.get(i);
    				for(int j = 0; j < row.size(); j++) {
    					if("编码".equals(row.get(j).trim())) {
    						mid = j;
    					}
    					if("名称".equals(row.get(j).trim())) {
    						mn = j;
    					}
    					if("数量".equals(row.get(j).trim())) {
    						mc = j;
    					}
    				}
    				if(mid + mn + mc > 1) {
    					startIndex = i;
    					break;
    				}
    			}
    			// 解析配方原料数据
    			List<RecipeDetail> recipeDetailList = new ArrayList<RecipeDetail>();
    			for(int i = startIndex + 1; i < list.size(); i++) {
    				ArrayList<String> row = list.get(i);
    				RecipeDetail recipeDetail = new RecipeDetail();
    				MaterialInfo materialInfo = new MaterialInfo();
    				boolean breakFlag = false;
    				for(int j = 0; j < row.size(); j++) {
    					String value = row.get(j);
    					if("合计".equals(value.trim())) {
    						breakFlag = true;
    						break;
    					}
    					// 根据解析的列进行设值
    					if(j == mid) {
    						materialInfo.setMaterialId(value.trim());
    					}
    					if(j == mn) {
    						materialInfo.setMaterialName(value.trim());
    					}
    					if(j == mc) {
    						recipeDetail.setScale((Math.round(Float.parseFloat(value.trim()) * 100000) / 100000.000) + "");
    					}
    				}
    				if(breakFlag) {
    					break;
    				}
    				recipeDetail.setMaterialInfo(materialInfo);
    				recipeDetailList.add(recipeDetail);
    			}
    			recipeInfo.setMainRecipe(recipeDetailList);
    			recipeInfo.setSecondRecipe(new ArrayList<RecipeDetail>());
    			resultInfo.setErrCode(ResultInfo.SUCCESS);
            resultInfo.setResult(recipeInfo);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		return resultInfo;
    }

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
        if(result == 1) {
        		resultInfo = this.listRecipeInfo();
        }
        resultInfo.setErrCode(result == 1 ? ResultInfo.SUCCESS : ResultInfo.DELETE_FAIL);
        return resultInfo;
    }
}
