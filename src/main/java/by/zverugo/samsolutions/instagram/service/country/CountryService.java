package by.zverugo.samsolutions.instagram.service.country;

import by.zverugo.samsolutions.instagram.dto.CountryDTO;

import java.util.Map;

public interface CountryService {
    CountryDTO getCountry(long countryId);
    Map<Long,String> getCountriesByLocale();
}
