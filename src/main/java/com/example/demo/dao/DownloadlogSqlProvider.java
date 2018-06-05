package com.example.demo.dao;

import com.example.demo.model.Downloadlog;
import org.apache.ibatis.jdbc.SQL;

public class DownloadlogSqlProvider {

    public String insertSelective(Downloadlog record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("DownloadLog");
        
        if (record.getSoftwareid() != null) {
            sql.VALUES("softwareId", "#{softwareid,jdbcType=INTEGER}");
        }
        
        if (record.getAccountid() != null) {
            sql.VALUES("accountId", "#{accountid,jdbcType=VARCHAR}");
        }
        
        if (record.getTime() != null) {
            sql.VALUES("time", "#{time,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Downloadlog record) {
        SQL sql = new SQL();
        sql.UPDATE("DownloadLog");
        
        if (record.getTime() != null) {
            sql.SET("time = #{time,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("softwareId = #{softwareid,jdbcType=INTEGER}");
        sql.WHERE("accountId = #{accountid,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}