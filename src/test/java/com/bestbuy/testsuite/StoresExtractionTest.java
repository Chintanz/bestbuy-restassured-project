package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }
//  1. Extract the limit
@Test
public void test001() {
    int limit = response.extract().path("limit");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of limit is : " + limit);
    System.out.println("------------------End of Test---------------------------");

}
//2. Extract the total
@Test
public void test002() {
    int total = response.extract().path("total");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of limit is : " + total);
    System.out.println("------------------End of Test---------------------------");
}
//3. Extract the name of 5th store
@Test
public void test003(){
   String storeName = response.extract().path("data[4].name");
    System.out.println(storeName);
    }
//4. Extract the names of all the store
@Test
    public void test004(){
    List<HashMap<String, ?>> allStoreNames = response.extract().path("data.findAll{it.name == 'name'}");
    response.body("data.findAll{it.name == 'name'}", hasItem(hasEntry("services", "storeId")));

    System.out.println(allStoreNames);
}

//5. Extract the storeId of all the store
    @Test
    public void test005(){
        List<HashMap<Double, ?>> id = response.extract().path("data.findAll{it.name == 'storeId'}");
        System.out.println(id);
    }
//6. Print the size of the data list

//7. Get all the value of the store where store name = St Cloud
    @Test
    public void test006(){
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println(values);
    }

//8. Get the address of the store where store name = Rochester
    @Test
    public void test007(){
        String address = response.extract().path("data[8].address");
        System.out.println(address);
    }

//9. Get all the services of 8th store
    @Test
        public void test008() {
        List<HashMap<String, ?>> services  = response.extract().path("data[7].services");
        System.out.println(services);
   }


//10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010(){
//        List<HashMap<String, ?>> storeservices  = response.extract().path("data[7].services.[5].storeservice");
//        System.out.println(storeservices);

    }

//11. Get all the storeId of all the store
@Test
    public void test011(){
//    List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'storeId'}");
    response.body("data.findAll{it.name == 'services'}", hasItem(hasEntry("storeservices","storeId")));

}

//12. Get id of all the store
    @Test
    public void test012(){
        List<HashMap<String, ?>> services  = response.extract().path("data.[id]");
        System.out.println(services);
    }

//13. Find the store names Where state = ND
    @Test
    public void test013(){
        response.body("data[7].state", equalTo("ND"));
    }

//14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014(){
        List<HashMap<String, ?>> services  = response.extract().path("data[8].services");
        System.out.println(services);
    }

//15. Find the createdAt for all services whose name = “ Windows Store”
    @Test
    public void test0015(){
        response.body("data.findAll{it.name == 'Windows Store'}",hasItem(hasEntry("services","createdAt")));

    }
// 16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016(){
        List<?> services = response.extract().path("data.findAll{it.name == 'Fargo'}.services");
        System.out.println(services);
    }

// 17. Find the zip of all the store
    @Test
    public void test017(){
        List<Double> zip = response.extract().path("data.findAll{it.name == 'Minnetonka'}.zip");
        System.out.println(zip);
    }


//18. Find the zip of store name = Roseville
@Test
    public void test018(){
    List<HashMap<String, ?>> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
    System.out.println(zip);
}

//19. Find the storeservices details of the service name = Magnolia Home Theater
@Test
public void test019(){
//    List<HashMap<String, ?>> storeServices = response.extract().path("data.findAll{it.name == 'Magnolia Home Theater'}.services");
//    System.out.println(storeServices);

            response.body("data.name", hasItem("Roseville"))
            .body("data[]2.services[]7", hasKey("storeservices"));
}

//20. Find the lat of all the stores
























}
