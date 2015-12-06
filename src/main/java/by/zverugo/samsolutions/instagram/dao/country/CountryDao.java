package by.zverugo.samsolutions.instagram.dao.country;


import by.zverugo.samsolutions.instagram.entity.Country;

import java.util.List;

public interface CountryDao {
    Country getCountry(long country);
    List<Country> getCountriesByLocale();
}
