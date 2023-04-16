package api;

import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;
import utils.FileHelper;
import java.util.List;
import java.util.stream.Collectors;

public class UniversitiesTests {
    private final static String URL = "http://universities.hipolabs.com/";

//    Positive

    @Test
    public void checkNameFull() {
        api.Specifications.installSpecification(api.Specifications.requestSpec(URL), api.Specifications.responseSpecOK200());
        List<University> universities = RestAssured.given().when()
                .get("/search?name=Nizhny Novgorod State Technical University")
                .then().log().all()
                .extract().body().jsonPath().getList("", University.class)
                .stream().sorted().collect(Collectors.toList());

        List<University> listFromFile = FileHelper.getUniversitiesListFromFile("world_universities_and_domains.json")
                .stream().filter(u -> u.getName().contains("Nizhny Novgorod State Technical University"))
                .sorted().collect(Collectors.toList());
        Assert.assertEquals(universities, listFromFile);
    }

    @Test
    public void checkCountryFull() {
        api.Specifications.installSpecification(api.Specifications.requestSpec(URL), api.Specifications.responseSpecOK200());
        List<University> universities = RestAssured.given().when()
                .get("/search?country=Russian Federation")
                .then().log().all()
                .extract().body().jsonPath().getList("", University.class)
                .stream().sorted().collect(Collectors.toList());

        List<University> listFromFile = FileHelper.getUniversitiesListFromFile("world_universities_and_domains.json")
                .stream().filter(u -> u.getCountry().equals("Russian Federation"))
                .sorted().collect(Collectors.toList());
        Assert.assertEquals(universities, listFromFile);
    }

    @Test
    public void checkNamePartAndCountry() {
        api.Specifications.installSpecification(api.Specifications.requestSpec(URL), api.Specifications.responseSpecOK200());
        List<University> universities = RestAssured.given().when()
                .get("/search?name=Medical&country=Russian Federation")
                .then().log().all()
                .extract().body().jsonPath().getList("", University.class)
                .stream().sorted().collect(Collectors.toList());

        List<University> listFromFile = FileHelper.getUniversitiesListFromFile("world_universities_and_domains.json")
                .stream().filter(u -> u.getCountry().equals("Russian Federation") && u.getName().contains("Medical"))
                .sorted().collect(Collectors.toList());
        Assert.assertEquals(universities, listFromFile);
    }

//    Negative

    @Test
    public void checkCountryPart() {
        api.Specifications.installSpecification(api.Specifications.requestSpec(URL), api.Specifications.responseSpecOK200());
        List<University> universities = RestAssured.given().when()
                .get("/search?country=Russian")
                .then().log().all()
                .extract().body().jsonPath().getList("", University.class)
                .stream().sorted().collect(Collectors.toList());

        List<University> listFromFile = FileHelper.getUniversitiesListFromFile("world_universities_and_domains.json")
                .stream().filter(u -> u.getCountry().equals("Russian"))
                .sorted().collect(Collectors.toList());
        Assert.assertEquals(universities, listFromFile);
    }

    @Test
    public void checkWrongName() {
        api.Specifications.installSpecification(api.Specifications.requestSpec(URL), api.Specifications.responseSpecOK200());
        List<University> universities = RestAssured.given().when()
                .get("/search?name=dddd")
                .then().log().all()
                .extract().body().jsonPath().getList("", University.class)
                .stream().sorted().collect(Collectors.toList());

        List<University> listFromFile = FileHelper.getUniversitiesListFromFile("world_universities_and_domains.json")
                .stream().filter(u -> u.getName().contains("dddd"))
                .sorted().collect(Collectors.toList());
        Assert.assertEquals(universities, listFromFile);
    }
}