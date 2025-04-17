package pl.edu.pg.hads.citiesdb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/api/cities")
    public List<CityModel> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/api/city")
    public CityModel getCity(
            @RequestParam(required = false) String uuid,
            @RequestParam(required = false) String shortName,
            @RequestParam(required = false) String properName
    ) {
        if (uuid != null) {
            return cityService.getCityByUuid(uuid);
        } else if (shortName != null) {
            return cityService.getCityByShortName(shortName);
        } else if (properName != null) {
            return cityService.getCityByProperName(properName);
        } else {
            throw new IllegalArgumentException("At least one parameter must be provided");
        }
    }
}
