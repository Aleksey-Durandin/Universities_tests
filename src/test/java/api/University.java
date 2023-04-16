package api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class University implements Comparable<University>{
    private String country;
    @JsonAlias({"web_pages"})
    private List<String> webPages;
    @JsonAlias({"state-province"})
    private String stateProvince;
    private String name;
    @JsonAlias({"alpha_two_code"})
    private String alphaTwoCode;
    private List<String> domains;

    public University(@JsonProperty("country")String country,
                       @JsonProperty("web_pages")List<String> webPages,
                       @JsonProperty("state-province")String stateProvince,
                       @JsonProperty("name")String name,
                       @JsonProperty("alpha_two_code")String alphaTwoCode,
                       @JsonProperty("domains")List<String> domains) {
        this.country = country;
        this.webPages = webPages;
        this.stateProvince = stateProvince;
        this.name = name;
        this.alphaTwoCode = alphaTwoCode;
        this.domains = domains;
    }

    //    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    //    @JsonProperty("web_pages")
    public List<String> getWebPages() {
        return webPages;
    }

    //    @JsonProperty("state-province")
    public String getStateProvince() {
        return stateProvince;
    }

    //    @JsonProperty("name")
    public String getName() {
        return name;
    }

    //    @JsonProperty("alpha_two_code")
    public String getAlphaTwoCode() {
        return alphaTwoCode;
    }

    //    @JsonProperty("domains")
    public List<String> getDomains() {
        return domains;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        University university = (University) obj;
        return ((university.getCountry() == null) ? (this.getCountry() == null) : university.getCountry().equals(this.getCountry())) &&
                ((university.getWebPages() == null) ? (this.getWebPages() == null) : university.getWebPages().equals(this.getWebPages())) &&
                ((university.getStateProvince() == null) ? (this.getStateProvince() == null) : university.getStateProvince().equals(this.getStateProvince())) &&
                ((university.getName() == null) ? (this.getName() == null) : university.getName().equals(this.getName())) &&
                ((university.getAlphaTwoCode() == null) ? (this.getAlphaTwoCode() ==null) : university.getAlphaTwoCode().equals(this.getAlphaTwoCode())) &&
                ((university.getDomains() == null) ? (this.getDomains() == null) : university.getDomains().equals(this.getDomains()));
    }

    @Override
    public int hashCode() {
        final int a = 28;
        int result = 3;
        result = a * result +
                ((country == null) ? 0 : country.hashCode()) +
                ((webPages == null) ? 0 : webPages.hashCode()) +
                ((stateProvince == null) ? 0 : stateProvince.hashCode()) +
                ((name == null) ? 0 : name.hashCode()) +
                ((alphaTwoCode == null) ? 0 : alphaTwoCode.hashCode()) +
                ((domains == null) ? 0 : domains.hashCode());
        return result;
    }

    @Override
    public int compareTo(University o) {
        return o.getName().compareTo(this.name);
    }
}
