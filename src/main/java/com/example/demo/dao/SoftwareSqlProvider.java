package com.example.demo.dao;

import com.example.demo.model.Software;
import org.apache.ibatis.jdbc.SQL;

public class SoftwareSqlProvider {

    public String insertSelective(Software record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("Software");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getSource() != null) {
            sql.VALUES("source", "#{source,jdbcType=VARCHAR}");
        }
        
        if (record.getIntroduce() != null) {
            sql.VALUES("introduce", "#{introduce,jdbcType=VARCHAR}");
        }
        
        if (record.getDownloadlink() != null) {
            sql.VALUES("downloadLink", "#{downloadlink,jdbcType=VARCHAR}");
        }
        
        if (record.getImglink() != null) {
            sql.VALUES("imgLink", "#{imglink,jdbcType=VARCHAR}");
        }
        
        if (record.getDownloadcount() != null) {
            sql.VALUES("downloadCount", "#{downloadcount,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Software record) {
        SQL sql = new SQL();
        sql.UPDATE("Software");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getSource() != null) {
            sql.SET("source = #{source,jdbcType=VARCHAR}");
        }
        
        if (record.getIntroduce() != null) {
            sql.SET("introduce = #{introduce,jdbcType=VARCHAR}");
        }
        
        if (record.getDownloadlink() != null) {
            sql.SET("downloadLink = #{downloadlink,jdbcType=VARCHAR}");
        }
        
        if (record.getImglink() != null) {
            sql.SET("imgLink = #{imglink,jdbcType=VARCHAR}");
        }
        
        if (record.getDownloadcount() != null) {
            sql.SET("downloadCount = #{downloadcount,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}