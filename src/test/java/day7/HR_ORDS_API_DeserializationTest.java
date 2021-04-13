package day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Country;
import pojo.Department;
import pojo.Employee;
import pojo.Region;
import utils.HR_ORDS_API_BaseTest;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HR_ORDS_API_DeserializationTest extends HR_ORDS_API_BaseTest {

    // Send request to /regions and save the result into List<Region>
    // assert the list has size 4
    @DisplayName("GET/regions")
    @Test
    public void testGetAllRegionAndSaveToListOfPOJO() {

        List<Region> allRegions =
                get("/regions")
                .jsonPath()
                .getList("items", Region.class);

        System.out.println("allRegions = " + allRegions);
    }

    @DisplayName("GET/Countries")
    @Test
    public void testAllCountries() {

        //Country c1 = new Country("AR","Argentina",1);
        //System.out.println("c1 = " + c1);

        // Save 3rd country as Country POJO

        Country thirdCountry = get("/countries")
                .jsonPath()
                .getObject("items[2]",Country.class)
                ;
        System.out.println("thirdCountry = " + thirdCountry);

        // Save all countries as List<POJO>
        List<Country> allCountries = get("/countries")
                .jsonPath()
                .getList("items",Country.class);

        //allCountries.forEach(System.out::println);
        allCountries.forEach(System.out::println);

    }

    // We want to create pojo that represent Employee data
    // We only care about employee_id , first_name , last_name , salary, department_id
    // we want to save the json data as pojo and we want to ignore any other fields other than specified above
    @DisplayName("GET/Employees")
    @Test
    public void testAllEmployees() {

        // get the first employee and save it into Employee POJO
        Employee employee = get("/employees")
                .jsonPath()
                .getObject("items[0]",Employee.class)
                ;
        System.out.println("employee = " + employee);
    }

    // Till this moment we have been naming our pojo class field names
    //   to match exact name from json field
    //  and yet there will be situations that the json field name
    //    does not really work for java naming convention
    // we want to be able to name the POJO field anything we want

    @DisplayName("GET/departments")
    @Test
    public void testAllDepartments() {

        Department department = get("/departments")
                .jsonPath()
                .getObject("items[0]",Department.class)
                ;
        System.out.println("department = " + department);   }






}
