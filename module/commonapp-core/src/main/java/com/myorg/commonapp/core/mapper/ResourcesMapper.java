package com.myorg.commonapp.core.mapper;

import com.myorg.commonapp.bean.po.Resources;
import com.myorg.commonapp.bean.po.ResourcesExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ResourcesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Thu Jul 16 22:22:20 CST 2015
     */
    int countByExample(ResourcesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Thu Jul 16 22:22:20 CST 2015
     */
    int deleteByExample(ResourcesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Thu Jul 16 22:22:20 CST 2015
     */
    @Delete({
        "delete from resources",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Thu Jul 16 22:22:20 CST 2015
     */
    @Insert({
        "insert into resources (resource_name, resource_type, ",
        "priority, resource_url, ",
        "resource_desc, enable)",
        "values (#{resourceName,jdbcType=VARCHAR}, #{resourceType,jdbcType=VARCHAR}, ",
        "#{priority,jdbcType=INTEGER}, #{resourceUrl,jdbcType=VARCHAR}, ",
        "#{resourceDesc,jdbcType=VARCHAR}, #{enable,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Resources record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Thu Jul 16 22:22:20 CST 2015
     */
    int insertSelective(Resources record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Thu Jul 16 22:22:20 CST 2015
     */
    List<Resources> selectByExample(ResourcesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Thu Jul 16 22:22:20 CST 2015
     */
    @Select({
        "select",
        "id, resource_name, resource_type, priority, resource_url, resource_desc, enable",
        "from resources",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Resources selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Thu Jul 16 22:22:20 CST 2015
     */
    int updateByExampleSelective(@Param("record") Resources record, @Param("example") ResourcesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Thu Jul 16 22:22:20 CST 2015
     */
    int updateByExample(@Param("record") Resources record, @Param("example") ResourcesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Thu Jul 16 22:22:20 CST 2015
     */
    int updateByPrimaryKeySelective(Resources record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Thu Jul 16 22:22:20 CST 2015
     */
    @Update({
        "update resources",
        "set resource_name = #{resourceName,jdbcType=VARCHAR},",
          "resource_type = #{resourceType,jdbcType=VARCHAR},",
          "priority = #{priority,jdbcType=INTEGER},",
          "resource_url = #{resourceUrl,jdbcType=VARCHAR},",
          "resource_desc = #{resourceDesc,jdbcType=VARCHAR},",
          "enable = #{enable,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Resources record);
}