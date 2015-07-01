package com.myorg.commonapp.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

import java.sql.*;

/**
 * Created by huyan on 2015/7/1.
 */
public class JodaDateTime2TimeTypeHandler extends BaseTypeHandler<DateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setTime(i, new Time(parameter.getMillis()));
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        DateTime dateTime = null;
        Time time = rs.getTime(columnName);
        if (time != null) {
            dateTime = new DateTime(time.getTime());
        }
        return dateTime;
    }

    @Override
    public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Time time = cs.getTime(columnIndex);
        if (time != null) {
            dateTime = new DateTime(time.getTime());
        }
        return dateTime;
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Time time = rs.getTime(columnIndex);
        if (time != null) {
            dateTime = new DateTime(time.getTime());
        }
        return dateTime;
    }
}
