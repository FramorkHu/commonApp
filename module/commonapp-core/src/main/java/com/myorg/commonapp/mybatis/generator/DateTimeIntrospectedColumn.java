package com.myorg.commonapp.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

import java.sql.Types;

/**
 * Created by huyan on 2015/7/1.
 */
public class DateTimeIntrospectedColumn extends IntrospectedColumn {

    @Override
    public void setFullyQualifiedJavaType(FullyQualifiedJavaType fullyQualifiedJavaType) {
        super.setFullyQualifiedJavaType(fullyQualifiedJavaType);
        if (Types.DATE == this.getJdbcType() && "org.joda.time.DateTime".equals(fullyQualifiedJavaType.getFullyQualifiedName())) {
            this.typeHandler = "com.myorg.commonapp.mybatis.typehandler.JodaDateTime2DateTypeHandler";
        } else if (Types.TIMESTAMP == this.getJdbcType() && "org.joda.time.DateTime".equals(fullyQualifiedJavaType.getFullyQualifiedName())) {
            this.typeHandler = "com.myorg.commonapp.mybatis.typehandler.JodaDateTime2TimestampTypeHandler";
        } else if (Types.TIME == this.getJdbcType() && "org.joda.time.DateTime".equals(fullyQualifiedJavaType.getFullyQualifiedName())) {
            this.typeHandler = "com.myorg.commonapp.mybatis.typehandler.JodaDateTime2TimeTypeHandler";
        }
    }
}
