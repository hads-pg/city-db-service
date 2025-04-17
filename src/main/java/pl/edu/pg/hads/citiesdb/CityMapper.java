package pl.edu.pg.hads.citiesdb;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityMapper {

    @Select("SELECT * FROM cities")
    List<CityModel> getAllCities();

    @Select("SELECT * FROM cities WHERE uuid = #{uuid}")
    CityModel getCityByUuid(String uuid);

    @Select("SELECT * FROM cities WHERE shortName = #{shortName}")
    CityModel getCityByShortName(String shortName);

    @Select("SELECT * FROM cities WHERE properName = #{properName}")
    CityModel getCityByProperName(String properName);
}
