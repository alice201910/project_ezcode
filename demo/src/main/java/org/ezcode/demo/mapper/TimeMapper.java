package org.ezcode.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * TimeMapper
 */
@Mapper
public interface TimeMapper {

    @Select("select now()")
    public String getTime1();

    public String getTime2();
}