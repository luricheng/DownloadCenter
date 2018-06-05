package com.example.demo.dao;

import com.example.demo.model.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AccountMapper {
    @Delete({
        "delete from Account",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into Account (id, password, ",
        "type, nick, age, ",
        "mail, question, ",
        "answer)",
        "values (#{id,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER}, #{nick,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, ",
        "#{mail,jdbcType=VARCHAR}, #{question,jdbcType=VARCHAR}, ",
        "#{answer,jdbcType=VARCHAR})"
    })
    int insert(Account record);

    @InsertProvider(type=AccountSqlProvider.class, method="insertSelective")
    int insertSelective(Account record);

    @Select({
        "select",
        "id, password, type, nick, age, mail, question, answer",
        "from Account",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="nick", property="nick", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR),
        @Result(column="question", property="question", jdbcType=JdbcType.VARCHAR),
        @Result(column="answer", property="answer", jdbcType=JdbcType.VARCHAR)
    })
    Account selectByPrimaryKey(String id);

    @UpdateProvider(type=AccountSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Account record);

    @Update({
        "update Account",
        "set password = #{password,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "nick = #{nick,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "mail = #{mail,jdbcType=VARCHAR},",
          "question = #{question,jdbcType=VARCHAR},",
          "answer = #{answer,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Account record);
}