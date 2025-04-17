package pl.edu.pg.hads.citiesdb;

public class CityModel {
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getProperName() {
        return properName;
    }

    public void setProperName(String properName) {
        this.properName = properName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    private String uuid;
    private String shortName;
    private String properName;
    private String service;
}
