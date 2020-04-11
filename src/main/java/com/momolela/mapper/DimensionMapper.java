package com.momolela.mapper;

import com.momolela.entity.Dimension;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface DimensionMapper {

    public int insert(Dimension dimension);

    @Select("select * from sys_dimension where dimid = #{dimid}")
    public Dimension getDimensionByDimId();

    /**
     * useGeneratedKeys用于自动生成主键, keyProperty主键标识
     *
     * @param dimension
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "dimid")
    @Insert("insert into sys_dimension(catalogid,dimname,dimdictype,dimdic,dimtablename,dimtype) values(#{catalogid},#{dimname},#{dimdictype},#{dimdic},#{dimtablename},#{dimtype})")
    public int insertDimension(Dimension dimension);
}
