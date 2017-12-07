package com.emumu.spice.service;

import com.emumu.spice.common.ResultInfo;
import com.emumu.spice.entity.VerifyInfo;
import com.emumu.spice.mapper.VerifyInfoMapper;
import com.emumu.spice.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserInfoService {

    @Autowired
    private VerifyInfoMapper verifyInfoMapper;

    private ResultInfo resultInfo;

    public ResultInfo changePassword(Map<String, String> params){
        resultInfo = new ResultInfo();
        String password = CommonUtils.md5(params.get("oldPassword")).toUpperCase();
        VerifyInfo verifyInfo = verifyInfoMapper.checkPassword(password);
        if(verifyInfo == null){
            resultInfo.setErrCode(ResultInfo.UPDATE_FAIL_1);
            return resultInfo;
        }
        int result = verifyInfoMapper.updatePassword(CommonUtils.md5(params.get("newPassword")));
        resultInfo.setErrCode(result == 1 ? ResultInfo.SUCCESS : ResultInfo.UPDATE_FAIL);
        return resultInfo;
    }
}
