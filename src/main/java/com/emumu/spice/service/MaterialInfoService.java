package com.emumu.spice.service;

import com.emumu.spice.common.ResultInfo;
import com.emumu.spice.entity.MaterialInfo;
import com.emumu.spice.mapper.MaterialInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaterialInfoService {

    @Autowired
    private MaterialInfoMapper materialInfoMapper;

    private ResultInfo resultInfo;

    /**
     * 根据关键之查询原材料信息
     * @param keyword 关键字
     * @return ResultInfo
     */
    public ResultInfo searchMaterialInfoByKeyword(String keyword){
        resultInfo = new ResultInfo();
        keyword = "%" +keyword + "%";
        try{
            List<MaterialInfo> result = materialInfoMapper.findMaterialInfoByKeyword(keyword);
            resultInfo.setErrCode(ResultInfo.SUCCESS);
            resultInfo.setResult(result);
        }catch(Exception e){
            resultInfo.setErrCode(ResultInfo.SELECT_FAIL);
        }
        return resultInfo;
    }

    /**
     * 添加原材料信息
     * @param materialInfo 原材料信息
     * @return ResultInfo
     */
    public ResultInfo addMatreialInfo(MaterialInfo materialInfo){
        resultInfo = new ResultInfo();
        MaterialInfo mi = materialInfoMapper.findMaterialInfoById(materialInfo.getMaterialId());
        if(mi != null){
            resultInfo.setErrCode(ResultInfo.INSERT_FAIL_1);
            return resultInfo;
        }
        Date date = new Date();
        materialInfo.setCreated(date);
        materialInfo.setUpdated(date);
        int result =  materialInfoMapper.insertMaterialInfo(materialInfo);
        resultInfo.setErrCode(result == 1 ? ResultInfo.SUCCESS : ResultInfo.INSERT_FAIL);
        return resultInfo;
    }

    /**
     * 修改原材料信息
     * @param materialInfo 原材料信息
     * @return
     */
    public ResultInfo updateMaterialInfo(MaterialInfo materialInfo){
        resultInfo = new ResultInfo();
        materialInfo.setUpdated(new Date());
        int result =  materialInfoMapper.updateMaterianInfoById(materialInfo);
        resultInfo.setErrCode(result == 1 ? ResultInfo.SUCCESS : ResultInfo.UPDATE_FAIL);
        return resultInfo;
    }

    /**
     * 根据ID删除原材料信息
     * @param materialId 原材料ID
     * @return ResultInfo
     */
    public ResultInfo deleteMaterialInfo(String materialId){
        resultInfo = new ResultInfo();
        int result = materialInfoMapper.deleteMaterialInfo(materialId);
        resultInfo.setErrCode(result == 1 ? ResultInfo.SUCCESS : ResultInfo.DELETE_FAIL);
        return resultInfo;
    }
}
