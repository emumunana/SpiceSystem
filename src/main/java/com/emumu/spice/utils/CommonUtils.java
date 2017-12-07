package com.emumu.spice.utils;

import java.security.MessageDigest;

public class CommonUtils {
    public static String md5(String str){
        String data = "";
        try{
            byte[] buf = str.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(buf);
            byte[] tmp = md5.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b : tmp){
                int i = b;
                if(i < 0){
                    i += 256;
                }
                if(i < 16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(b & 0xff));
            }
            data = sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
