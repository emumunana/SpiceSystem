package com.emumu.spice.mapper;

import com.emumu.spice.entity.MaterialInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MaterialInfoMapper {
    /**
     * 根据关键字查询原材料信息
     * @param keyword 关键字
     * @return
     */
    @Select("SELECT * FROM material_info WHERE material_id LIKE #{keyword} OR material_name LIKE #{keyword}")
    @Results({
            @Result(property = "materialId",  column = "material_id"),
            @Result(property = "materialName", column = "material_name"),
            @Result(property = "materialPrice", column = "material_price"),
            @Result(property = "created", column = "created"),
            @Result(property = "updated", column = "updated")
    })
    List<MaterialInfo> findMaterialInfoByKeyword(String keyword);

    /**
     * 根据ID查找原材料信息
     * @param materialId 原材料ID
     * @return
     */
    @Select("SELECT * FROM material_info WHERE material_id = #{materialId}")
    @Results({
            @Result(property = "materialId",  column = "material_id"),
            @Result(property = "materialName", column = "material_name"),
            @Result(property = "materialPrice", column = "material_price"),
            @Result(property = "created", column = "created"),
            @Result(property = "updated", column = "updated")
    })
    MaterialInfo findMaterialInfoById(String materialId);

    /**
     * 新增原材料信息
     * @param materialInfo 原材料信息
     * @return
     */
    @Insert("INSERT INTO material_info(material_id, material_name, material_price, created, updated) VALUES(#{materialId}, #{materialName}, #{materialPrice}, #{created}, #{updated})")
    int insertMaterialInfo(MaterialInfo materialInfo);

    /**
     * 根据ID删除原材料信息
     * @param materialId 原材料ID
     * @return
     */
    @Delete("DELETE FROM material_info WHERE material_id = #{materialId}")
    int deleteMaterialInfo(String materialId);

    /**
     * 根据ID修改原材料信息
     * @param materialInfo 原材料信息
     * @return
     */
    @Update("UPDATE material_info SET material_name = #{materialName}, material_price = #{materialPrice}, updated = #{updated} WHERE material_id = #{materialId}")
    int updateMaterianInfoById(MaterialInfo materialInfo);
}
