package com.myorg.commonapp.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

import java.sql.*;

/**
 * Created by huyan on 2015/7/1.
 */
public class JodaDateTime2DateTypeHandler extends BaseTypeHandler<DateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setDate(i, new Date(parameter.getMillis()));
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        DateTime dateTime = null;
        Date date = rs.getDate(columnName);
        if (date != null) {
            dateTime = new DateTime(date.getTime());
        }
        return dateTime;
    }

    @Override
    public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Date date = cs.getDate(columnIndex);
        if (date != null) {
            dateTime = new DateTime(date.getTime());
        }
        return dateTime;
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Date date = rs.getDate(columnIndex);
        if (date != null) {
            dateTime = new DateTime(date.getTime());
        }
        return dateTime;
    }
}
