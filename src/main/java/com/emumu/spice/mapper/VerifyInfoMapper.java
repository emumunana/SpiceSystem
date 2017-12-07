package com.emumu.spice.mapper;

import com.emumu.spice.entity.VerifyInfo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface VerifyInfoMapper {
    /**
     * 查询配方列表
     * @return
     */
    @Select("SELECT * FROM verify_info WHERE password = #{password}")
    VerifyInfo checkPassword(String password);

    @Update("UPDATE verify_info SET password = #{password}")
    int updatePassword(String password);
}
