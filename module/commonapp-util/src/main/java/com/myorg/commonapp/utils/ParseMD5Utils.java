package com.myorg.commonapp.utils;

import java.security.MessageDigest;

/**
 * Created by huyan on 2015/7/2.
 */
public class ParseMD5Utils {

    /**
     * 返回32位MD5值
     * @param str
     * @return
     */
    public static String parseStrToMd5L32(String str){
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());

            StringBuilder builder = new StringBuilder();
            for (byte b : bytes){
                int bt = b&0xff;
                if (bt < 16){
                    builder.append(0);
                }
                builder.append(Integer.toHexString(bt));
            }
            reStr = builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reStr;
    }

    public static void main(String[] args){
        //e6e061838856bf47e1de730719fb2609
        //e6e061838856bf47e1de730719fb2609
        System.out.println(parseStrToMd5L32("admin@123"));
    }

}
