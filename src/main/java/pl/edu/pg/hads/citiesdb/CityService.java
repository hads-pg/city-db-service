package pl.edu.pg.hads.citiesdb;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityMapper cityMapper;

    public CityService(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    public List<CityModel> getAllCities() {
        return this.cityMapper.getAllCities();
    }

    public CityModel getCityByShortName(String shortName) {
        return cityMapper.getCityByShortName(shortName);
    }

    public CityModel getCityByProperName(String properName) {
        return cityMapper.getCityByProperName(properName);
    }

    public CityModel getCityByUuid(String uuid) {
        return cityMapper.getCityByUuid(uuid);
    }
}
