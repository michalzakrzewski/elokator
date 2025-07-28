package com.elokator.enums;

import com.elokator.exceptions.AppCoreException;

public enum CountryEnum {
    AFGHANISTAN("Afghanistan", "AF", "AFG"),
    ALBANIA("Albania", "AL", "ALB"),
    ALGERIA("Algeria", "DZ", "DZA"),
    ANDORRA("Andorra", "AD", "AND"),
    ANGOLA("Angola", "AO", "AGO"),
    ARGENTINA("Argentina", "AR", "ARG"),
    ARMENIA("Armenia", "AM", "ARM"),
    AUSTRALIA("Australia", "AU", "AUS"),
    AUSTRIA("Austria", "AT", "AUT"),
    AZERBAIJAN("Azerbaijan", "AZ", "AZE"),
    BAHAMAS("Bahamas", "BS", "BHS"),
    BAHRAIN("Bahrain", "BH", "BHR"),
    BANGLADESH("Bangladesh", "BD", "BGD"),
    BARBADOS("Barbados", "BB", "BRB"),
    BELARUS("Belarus", "BY", "BLR"),
    BELGIUM("Belgium", "BE", "BEL"),
    BELIZE("Belize", "BZ", "BLZ"),
    BENIN("Benin", "BJ", "BEN"),
    BHUTAN("Bhutan", "BT", "BTN"),
    BOLIVIA("Bolivia", "BO", "BOL"),
    BOSNIA_AND_HERZEGOVINA("Bosnia and Herzegovina", "BA", "BIH"),
    BOTSWANA("Botswana", "BW", "BWA"),
    BRAZIL("Brazil", "BR", "BRA"),
    BULGARIA("Bulgaria", "BG", "BGR"),
    BURKINA_FASO("Burkina Faso", "BF", "BFA"),
    BURUNDI("Burundi", "BI", "BDI"),
    CAMBODIA("Cambodia", "KH", "KHM"),
    CAMEROON("Cameroon", "CM", "CMR"),
    CANADA("Canada", "CA", "CAN"),
    CAPE_VERDE("Cape Verde", "CV", "CPV"),
    CHAD("Chad", "TD", "TCD"),
    CHILE("Chile", "CL", "CHL"),
    CHINA("China", "CN", "CHN"),
    COLOMBIA("Colombia", "CO", "COL"),
    COSTA_RICA("Costa Rica", "CR", "CRI"),
    CROATIA("Croatia", "HR", "HRV"),
    CUBA("Cuba", "CU", "CUB"),
    CYPRUS("Cyprus", "CY", "CYP"),
    CZECHIA("Czechia", "CZ", "CZE"),
    DENMARK("Denmark", "DK", "DNK"),
    DJIBOUTI("Djibouti", "DJ", "DJI"),
    DOMINICAN_REPUBLIC("Dominican Republic", "DO", "DOM"),
    ECUADOR("Ecuador", "EC", "ECU"),
    EGYPT("Egypt", "EG", "EGY"),
    EL_SALVADOR("El Salvador", "SV", "SLV"),
    ESTONIA("Estonia", "EE", "EST"),
    ETHIOPIA("Ethiopia", "ET", "ETH"),
    FINLAND("Finland", "FI", "FIN"),
    FRANCE("France", "FR", "FRA"),
    GERMANY("Germany", "DE", "DEU"),
    GHANA("Ghana", "GH", "GHA"),
    GREECE("Greece", "GR", "GRC"),
    GUATEMALA("Guatemala", "GT", "GTM"),
    HONDURAS("Honduras", "HN", "HND"),
    HUNGARY("Hungary", "HU", "HUN"),
    ICELAND("Iceland", "IS", "ISL"),
    INDIA("India", "IN", "IND"),
    INDONESIA("Indonesia", "ID", "IDN"),
    IRAN("Iran", "IR", "IRN"),
    IRAQ("Iraq", "IQ", "IRQ"),
    IRELAND("Ireland", "IE", "IRL"),
    ISRAEL("Israel", "IL", "ISR"),
    ITALY("Italy", "IT", "ITA"),
    JAMAICA("Jamaica", "JM", "JAM"),
    JAPAN("Japan", "JP", "JPN"),
    JORDAN("Jordan", "JO", "JOR"),
    KAZAKHSTAN("Kazakhstan", "KZ", "KAZ"),
    KENYA("Kenya", "KE", "KEN"),
    KUWAIT("Kuwait", "KW", "KWT"),
    LATVIA("Latvia", "LV", "LVA"),
    LEBANON("Lebanon", "LB", "LBN"),
    LITHUANIA("Lithuania", "LT", "LTU"),
    LUXEMBOURG("Luxembourg", "LU", "LUX"),
    MALAYSIA("Malaysia", "MY", "MYS"),
    MALTA("Malta", "MT", "MLT"),
    MEXICO("Mexico", "MX", "MEX"),
    MOLDOVA("Moldova", "MD", "MDA"),
    MONACO("Monaco", "MC", "MCO"),
    MONGOLIA("Mongolia", "MN", "MNG"),
    MONTENEGRO("Montenegro", "ME", "MNE"),
    MOROCCO("Morocco", "MA", "MAR"),
    MOZAMBIQUE("Mozambique", "MZ", "MOZ"),
    NAMIBIA("Namibia", "NA", "NAM"),
    NEPAL("Nepal", "NP", "NPL"),
    NETHERLANDS("Netherlands", "NL", "NLD"),
    NEW_ZEALAND("New Zealand", "NZ", "NZL"),
    NIGERIA("Nigeria", "NG", "NGA"),
    NORWAY("Norway", "NO", "NOR"),
    PAKISTAN("Pakistan", "PK", "PAK"),
    PANAMA("Panama", "PA", "PAN"),
    PARAGUAY("Paraguay", "PY", "PRY"),
    PERU("Peru", "PE", "PER"),
    PHILIPPINES("Philippines", "PH", "PHL"),
    POLAND("Poland", "PL", "POL"),
    PORTUGAL("Portugal", "PT", "PRT"),
    QATAR("Qatar", "QA", "QAT"),
    ROMANIA("Romania", "RO", "ROU"),
    RUSSIA("Russia", "RU", "RUS"),
    SAUDI_ARABIA("Saudi Arabia", "SA", "SAU"),
    SERBIA("Serbia", "RS", "SRB"),
    SINGAPORE("Singapore", "SG", "SGP"),
    SLOVAKIA("Slovakia", "SK", "SVK"),
    SLOVENIA("Slovenia", "SI", "SVN"),
    SOUTH_AFRICA("South Africa", "ZA", "ZAF"),
    SOUTH_KOREA("South Korea", "KR", "KOR"),
    SPAIN("Spain", "ES", "ESP"),
    SRI_LANKA("Sri Lanka", "LK", "LKA"),
    SWEDEN("Sweden", "SE", "SWE"),
    SWITZERLAND("Switzerland", "CH", "CHE"),
    SYRIA("Syria", "SY", "SYR"),
    TAIWAN("Taiwan", "TW", "TWN"),
    TANZANIA("Tanzania", "TZ", "TZA"),
    THAILAND("Thailand", "TH", "THA"),
    TUNISIA("Tunisia", "TN", "TUN"),
    TURKEY("Turkey", "TR", "TUR"),
    UKRAINE("Ukraine", "UA", "UKR"),
    UNITED_ARAB_EMIRATES("United Arab Emirates", "AE", "ARE"),
    UNITED_KINGDOM("United Kingdom", "GB", "GBR"),
    UNITED_STATES("United States", "US", "USA"),
    URUGUAY("Uruguay", "UY", "URY"),
    VENEZUELA("Venezuela", "VE", "VEN"),
    VIETNAM("Vietnam", "VN", "VNM"),
    YEMEN("Yemen", "YE", "YEM"),
    ZIMBABWE("Zimbabwe", "ZW", "ZWE");

    private final String fullName;
    private final String alpha2Code;
    private final String alpha3Code;

    CountryEnum(String fullName, String alpha2Code, String alpha3Code) {
        this.fullName = fullName;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public static CountryEnum getCountryByAlpha2Code(String alpha2Code) {
        for (CountryEnum country : values()) {
            if (country.getAlpha2Code().equalsIgnoreCase(alpha2Code)) {
                return country;
            }
        }
        return null;
    }

    public static CountryEnum getCountryByAlpha3Code(String alpha3Code) {
        for (CountryEnum country : values()) {
            if (country.getAlpha3Code().equalsIgnoreCase(alpha3Code)) {
                return country;
            }
        }
        return null;
    }
}

