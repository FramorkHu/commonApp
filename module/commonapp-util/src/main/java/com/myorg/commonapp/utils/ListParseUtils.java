package com.myorg.commonapp.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by huyan on 15/7/26.
 */
public class ListParseUtils<T> {

    /**
     * 将List转化成 *,*,* String 类型
     */
    public String parseListToString(List<T> originDataList){

        StringBuilder builder = new StringBuilder();

        if (CollectionUtils.isEmpty(originDataList)){
            return "";
        }
        for (int i=0; i<originDataList.size(); i++){
            String value = String.valueOf(originDataList.get(i));
            if (!"null".equals(value) && !"".equals(value)) {
                if (i< originDataList.size() -1){
                    builder.append(originDataList.get(i)).append(",");
                }else {
                    builder.append(originDataList.get(i));
                }
            }
        }
        return builder.toString();

    }

    /**
     * 将List转化成 '*','*','*' String 类型
     */
    public String parseListToStringWithQuote(List<T> originDataList){

        StringBuilder builder = new StringBuilder();
        if (CollectionUtils.isEmpty(originDataList)){
            return "";
        }
        for (int i=0; i<originDataList.size(); i++){
            String value = String.valueOf(originDataList.get(i));
            if (!"null".equals(value) && !"".equals(value)) {
                if (i< originDataList.size() -1){
                    builder.append("'")
                            .append(originDataList.get(i))
                            .append("',");
                }else {
                    builder.append("'")
                            .append(originDataList.get(i))
                            .append("'");
                }
            }

        }
        return builder.toString();
    }



}
