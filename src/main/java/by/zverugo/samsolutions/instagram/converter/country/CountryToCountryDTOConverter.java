package by.zverugo.samsolutions.instagram.converter.country;

import by.zverugo.samsolutions.instagram.dto.CountryDTO;
import by.zverugo.samsolutions.instagram.entity.Country;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CountryToCountryDTOConverter implements Converter<Country, CountryDTO>{
    @Override
    public CountryDTO convert(Country country) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setCountryId(country.getCountryId());
        countryDTO.setCountryEn(country.getCountryEn());
        countryDTO.setCountryRu(country.getCountryRu());

        return countryDTO;
    }
}
