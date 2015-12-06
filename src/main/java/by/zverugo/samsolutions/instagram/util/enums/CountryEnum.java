package by.zverugo.samsolutions.instagram.util.enums;

public enum CountryEnum {
    BELARUS("Belarus"),
    RUSSIA("Russia"),
    UK("UK"),
    CHINA("China"),
    LITHUANIA("Lithuania"),
    GERMANY("Germany"),
    UKRAINE("Ukraine"),
    BRAZIL("Brazil"),
    USA("USA"),
    KAZAKHSTAN("Kazakhstan"),
    GEORGIA("Georgia"),
    LATVIA("Latvia"),
    ESTONIA("Estonia");

    String name;
    CountryEnum(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }
}
