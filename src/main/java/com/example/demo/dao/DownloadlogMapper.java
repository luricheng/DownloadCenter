package com.example.demo.dao;

import com.example.demo.model.Downloadlog;
import com.example.demo.model.DownloadlogKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface DownloadlogMapper {
    @Delete({
        "delete from DownloadLog",
        "where softwareId = #{softwareid,jdbcType=INTEGER}",
          "and accountId = #{accountid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(DownloadlogKey key);

    @Insert({
        "insert into DownloadLog (softwareId, accountId, ",
        "time)",
        "values (#{softwareid,jdbcType=INTEGER}, #{accountid,jdbcType=VARCHAR}, ",
        "#{time,jdbcType=TIMESTAMP})"
    })
    int insert(Downloadlog record);

    @InsertProvider(type=DownloadlogSqlProvider.class, method="insertSelective")
    int insertSelective(Downloadlog record);

    @Select({
        "select",
        "softwareId, accountId, time",
        "from DownloadLog",
        "where softwareId = #{softwareid,jdbcType=INTEGER}",
          "and accountId = #{accountid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="softwareId", property="softwareid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="accountId", property="accountid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP)
    })
    Downloadlog selectByPrimaryKey(DownloadlogKey key);

    @UpdateProvider(type=DownloadlogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Downloadlog record);

    @Update({
        "update DownloadLog",
        "set time = #{time,jdbcType=TIMESTAMP}",
        "where softwareId = #{softwareid,jdbcType=INTEGER}",
          "and accountId = #{accountid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Downloadlog record);

    @Select({
        "select",
        "softwareId, accountId, time",
        "from DownloadLog"
    })
    @Results({
        @Result(column="softwareId", property="softwareid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="accountId", property="accountid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Downloadlog> selectAll();

    @Select({
        "select",
        "softwareId, accountId, time",
        "from DownloadLog",
        "where accountId = #{accountid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="softwareId", property="softwareid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="accountId", property="accountid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Downloadlog> selectByAccountId(Downloadlog key);

    @Delete({
        "delete from DownloadLog",
        "where accountId = #{accountid,jdbcType=VARCHAR}"
    })
    int deleteByAccountId(DownloadlogKey key);
}