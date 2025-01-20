package com.green.jobdone.config.handler;
//import com.green.jobdone.config.jwt.UserRole;
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UserRoleTypeHandler extends BaseTypeHandler<UserRole> {
//
//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i, UserRole parameter, JdbcType jdbcType) throws SQLException {
//        ps.setInt(i, parameter.getCode());
//    }
//
//    @Override
//    public UserRole getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        int code = rs.getInt(columnName);
//        return UserRole.fromCode(code);
//    }
//
//    @Override
//    public UserRole getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        int code = rs.getInt(columnIndex);
//        return UserRole.fromCode(code);
//    }
//
//    @Override
//    public UserRole getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//        int code = cs.getInt(columnIndex);
//        return UserRole.fromCode(code);
//    }


import com.green.jobdone.config.jwt.UserRole;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserRoleTypeHandler extends BaseTypeHandler<List<UserRole>> {

    @Override
    public void setNonNullParameter(java.sql.PreparedStatement ps, int i, List<UserRole> parameter, JdbcType jdbcType) throws SQLException {
        String roles = parameter.stream()
                .map(role -> String.valueOf(role.getCode()))
                .collect(Collectors.joining(","));
        ps.setString(i, roles);
    }

    @Override
    public List<UserRole> getNullableResult(java.sql.ResultSet rs, String columnName) throws SQLException {
        String roles = rs.getString(columnName);
        return mapToUserRoleList(roles);
    }

    @Override
    public List<UserRole> getNullableResult(java.sql.ResultSet rs, int columnIndex) throws SQLException {
        String roles = rs.getString(columnIndex);
        return mapToUserRoleList(roles);
    }

    @Override
    public List<UserRole> getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        String roles = cs.getString(columnIndex);
        return mapToUserRoleList(roles);
    }

    private List<UserRole> mapToUserRoleList(String roles) {
        if (roles == null || roles.isEmpty()) {
            return Arrays.asList();
        }
        return Arrays.stream(roles.split(","))
                .map(Integer::parseInt)
                .map(UserRole::fromCode)
                .collect(Collectors.toList());
    }
}
