package com.myorg.commonapp.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

import java.sql.*;

/**
 * Created by huyan on 2015/7/1.
 */
public class JodaDateTime2TimestampTypeHandler extends BaseTypeHandler<DateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, new Timestamp(parameter.getMillis()));
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        DateTime dateTime = null;
        Timestamp timestamp = rs.getTimestamp(columnName);
        if (timestamp != null) {
            dateTime = new DateTime(timestamp.getTime());
        }
        return dateTime;
    }

    @Override
    public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Timestamp timestamp = cs.getTimestamp(columnIndex);
        if (timestamp != null) {
            dateTime = new DateTime(timestamp.getTime());
        }
        return dateTime;
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Timestamp timestamp = rs.getTimestamp(columnIndex);
        if (timestamp != null) {
            dateTime = new DateTime(timestamp.getTime());
        }
        return dateTime;
    }
}
