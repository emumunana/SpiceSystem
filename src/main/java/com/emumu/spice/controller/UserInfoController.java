package com.emumu.spice.controller;

import com.emumu.spice.common.ResultInfo;
import com.emumu.spice.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value="/change",method = RequestMethod.POST)
    public ResultInfo changePassword(@RequestBody Map<String, String> params){
        return userInfoService.changePassword(params);
    }
}
