package com.example.demo.dao;

import com.example.demo.model.Software;
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

public interface SoftwareMapper {
    @Delete({
        "delete from Software",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into Software (id, name, ",
        "source, introduce, ",
        "downloadLink, imgLink, ",
        "downloadCount)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{source,jdbcType=VARCHAR}, #{introduce,jdbcType=VARCHAR}, ",
        "#{downloadlink,jdbcType=VARCHAR}, #{imglink,jdbcType=VARCHAR}, ",
        "#{downloadcount,jdbcType=INTEGER})"
    })
    int insert(Software record);

    @InsertProvider(type=SoftwareSqlProvider.class, method="insertSelective")
    int insertSelective(Software record);

    @Select({
        "select",
        "id, name, source, introduce, downloadLink, imgLink, downloadCount",
        "from Software",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
        @Result(column="introduce", property="introduce", jdbcType=JdbcType.VARCHAR),
        @Result(column="downloadLink", property="downloadlink", jdbcType=JdbcType.VARCHAR),
        @Result(column="imgLink", property="imglink", jdbcType=JdbcType.VARCHAR),
        @Result(column="downloadCount", property="downloadcount", jdbcType=JdbcType.INTEGER)
    })
    Software selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SoftwareSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Software record);

    @Update({
        "update Software",
        "set name = #{name,jdbcType=VARCHAR},",
          "source = #{source,jdbcType=VARCHAR},",
          "introduce = #{introduce,jdbcType=VARCHAR},",
          "downloadLink = #{downloadlink,jdbcType=VARCHAR},",
          "imgLink = #{imglink,jdbcType=VARCHAR},",
          "downloadCount = #{downloadcount,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Software record);

    @Select({
        "select",
        "*",
        "from Software"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
        @Result(column="introduce", property="introduce", jdbcType=JdbcType.VARCHAR),
        @Result(column="downloadLink", property="downloadlink", jdbcType=JdbcType.VARCHAR),
        @Result(column="imgLink", property="imglink", jdbcType=JdbcType.VARCHAR),
        @Result(column="downloadCount", property="downloadcount", jdbcType=JdbcType.INTEGER)
    })
    List<Software> selectAll();
}