package com.emumu.spice.common;

import org.springframework.stereotype.Component;

@Component
public class ResultInfo {
    /**
     * 操作成功
     */
    public static String SUCCESS = "000000";
    public static String SUCCESS_DES = "操作成功";
    /**
     * 查询失败
     */
    public static String SELECT_FAIL = "000001";
    public static String SELECT_FAIL_DES = "查询操作失败";
    /**
     * 新增失败
     */
    public static String INSERT_FAIL = "000002";
    /**
     * 重复添加
     */
    public static String INSERT_FAIL_1 = "000002-1";
    public static String INSERT_FAIL_DES = "新增操作失败";
    /**
     * 更新失败
     */
    public static String UPDATE_FAIL = "000003";
    /**
     * 更新失败，密码错误
     */
    public static String UPDATE_FAIL_1 = "000003-1";
    public static String UPDATE_FAIL_DES = "更新操作失败";
    /**
     * 删除失败
     */
    public static String DELETE_FAIL = "000004";
    /**
     * 删除失败，密码错误
     */
    public static String DELETE_FAIL_1 = "000004-1";
    public static String DELETE_FAIL_DES = "删除操作失败";


    private String errCode;
    private String errMsg;
    private Object result;

    /**
     * 设置错误码
     * @return
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    /**
     * 获取错误码
     * @return
     */
    public String getErrCode() {
        return errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }
}
