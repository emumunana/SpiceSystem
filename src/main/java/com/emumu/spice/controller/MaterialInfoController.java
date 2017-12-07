package com.emumu.spice.controller;

import com.emumu.spice.common.ResultInfo;
import com.emumu.spice.entity.MaterialInfo;
import com.emumu.spice.service.MaterialInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialInfoController {

    @Autowired
    private MaterialInfoService materialInfoService;

    @RequestMapping(value="/search",method = RequestMethod.GET)
    public ResultInfo searchMaterialInfoByKeyword(String keyword) {
        return materialInfoService.searchMaterialInfoByKeyword(keyword);
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public ResultInfo addMaterialInfo(@RequestBody MaterialInfo materialInfo) {
        return materialInfoService.addMatreialInfo(materialInfo);
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public ResultInfo updateMaterialInfo(@RequestBody MaterialInfo materialInfo) {
        return materialInfoService.updateMaterialInfo(materialInfo);
    }

    @RequestMapping(value="/delete",method = RequestMethod.GET)
    public ResultInfo deleteMaterialInfo(String materialId) {
        return materialInfoService.deleteMaterialInfo(materialId);
    }
}
