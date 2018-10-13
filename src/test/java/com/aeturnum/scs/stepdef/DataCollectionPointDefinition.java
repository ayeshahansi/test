package com.aeturnum.scs.stepdef;

//import com.aeturnum.scs.comparators.DataCollectionPointIdComparator;

import com.aeturnum.scs.model.*;
import com.aeturnum.scs.util.Configuration;
import com.aeturnum.scs.util.DbConnect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DataCollectionPointDefinition {

    Properties prop;

    private String token;

    @Before
    public void prepareSetup() {
        prop = Configuration.getProperties("config.properties");

    }

    @Given("^The the user has valid token$")
    public void the_the_user_has_valid_token() throws Throwable {
        System.out.println("start");
        Response response = given().contentType("application/x-www-form-urlencoded; charset")
                .formParam("grant_type", "password").formParam("username", "Peter").formParam("password", "Testing.123")
                .when().post("http://192.168.103.101:83/api/security/token").andReturn();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        if (response.getStatusCode() == 200) {
            JsonObject jsonObject = new JsonParser().parse(response.getBody().asString()).getAsJsonObject();
            token = jsonObject.get("access_token").getAsString();
            Assert.assertTrue(true, "Token generated");

        } else {
            Assert.assertFalse(true, "Failed to generate a token, statuscode is : " + response.getStatusCode());
        }
        System.out.println("app test end " + token);
        throw new PendingException();
    }

    @When("^user creates a DataCollectionPoint$")
    public void user_creates_a_DataCollectionPoint() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The DataCollectionPoint can be retrieved to the DataCollectionPoint list$")
    public void the_DataCollectionPoint_can_be_retrieved_to_the_DataCollectionPoint_list() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^User views the DataCollectionPoint list$")
    public void user_views_the_DataCollectionPoint_list() throws Throwable {
        System.out.println("start &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        DataCollectionPointListInputPayload payload = new DataCollectionPointListInputPayload();
        payload.setSiteID(4321);
        ObjectMapper objectMapper = new ObjectMapper();

//        Response response = given().when()
//                .get(prop.getProperty("environment") + "/api/DataCollectionPoint/getdatacollectionpoints/4321").then()
//                .contentType(ContentType.JSON).extract().response();

        Response response = given().contentType("application/json").body(objectMapper.writeValueAsString(payload))
                .when()
                .post(prop.getProperty("environment") + "/api/DataCollectionPoint/getdatacollectionpoints").then()
                .contentType(ContentType.JSON).extract().response();
        //System.out.println("YYYYYYYYYY" +response.toString());
        System.out.println("YYYYYYYYYY Test Comment " + response.getBody().asString());
        DataCollectionPoint[] restDataArray = response.as(DataCollectionPoint[].class);

        Statement stmt = DbConnect.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("select * from DataCollectionPoint d where d.SiteID=4321");

        List<DataCollectionPoint> list = new ArrayList<DataCollectionPoint>();
        while (rs.next()) {
            DataCollectionPoint dc = new DataCollectionPoint();
            dc.setDataCollectionPointID(rs.getInt(1));
            dc.setSiteID(rs.getInt(2));
            list.add(dc);

        }

        System.out.println("---------------------" + list.size());

        DataCollectionPoint[] dbDataArray = list.toArray(new DataCollectionPoint[list.size()]);

        Arrays.sort(restDataArray);
        Arrays.sort(dbDataArray);

        System.out.println("---------------------" + restDataArray);
        assertArrayEquals(restDataArray, dbDataArray);

        // rs.next();
        // String jsonStr = response.getBody().asString();
        // System.out.println("List************************" + jsonStr);
        // int sizeOfList = response.body().path("list.size()");
        // assertEquals(4, sizeOfList);

    }


    @Then("^User views the DataCollectionPoint description$")
    public void user_views_the_DataCollectionPoint_description() throws Throwable {

        if (prop == null) {
            System.out.println("==============================================");
        }

        DataCollectionPointDescriptionPayload payload = new DataCollectionPointDescriptionPayload();
        payload.setDcpID(401071);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("start $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + prop.getProperty("environment")
                + "/api/DataCollectionPoint/getdatacollectionpointdescription");
        Response response = given().contentType("application/json").body(objectMapper.writeValueAsString(payload)).when()
                .post(prop.getProperty("environment")
                        + "/api/DataCollectionPoint/getdatacollectionpointdescription")
                // .get("http://localhost:5000"+"/api/DataCollectionPoint/getdatacollectionpointdescription/410708")
                .then().contentType("application/json").extract().response();
        System.out.println("BBBBBBBBBBB");
        LFGDataCollectionPointDescription restDataArray = response.as(LFGDataCollectionPointDescription.class);

        System.out.println("CCCCCCCCCCCCCC");
        Statement stmt = DbConnect.getConnection().createStatement();
        ResultSet rs = stmt
                .executeQuery("SELECT * from LFGDataCollectionPointDescription where datacollectionpointid =401071");

        // List<LFGDataCollectionPointDescription> list = new
        // ArrayList<LFGDataCollectionPointDescription>();
        LFGDataCollectionPointDescription dbRecord = new LFGDataCollectionPointDescription();
        while (rs.next()) {
            dbRecord.setLFGDataCollectionPointDescritpionID(rs.getInt(1));
            dbRecord.setDataCollectionPointID(rs.getInt(2));
            // list.add(dc);

        }

        assertEquals(restDataArray, dbRecord);

    }

    //@Then("^User can view correct data sources$")
    public void Xuser_can_view_correct_data_sources() throws Throwable {

        System.out.println("DDDDDDDDDDD");
        System.out.println(
                prop.getProperty("environment") + "/api/DataCollectionPoint/getdcpconstructiontabdatasources/4015");


        Response response = given().when()
                .get(prop.getProperty("environment") + "/api/DataCollectionPoint/getdcpconstructiontabdatasources/4015")
                // .get("http://localhost:5000"+"/api/DataCollectionPoint/getdatacollectionpointdescription/410708")
                .then().extract().response();
        System.out.println("EEEEEEEEEEEE");
        System.out.println(response.getBody().asString());

        String key = "SEMGrids";// array key (as it mentioned in your Json)
        // List returnedArtworks = response.getBody().as(List.class)
        List<DataCollectionPoint> restData = response.jsonPath().getList(key);
        Statement stmt = DbConnect.getConnection().createStatement();
        ResultSet rs = stmt
                .executeQuery("Select * from DataCollectionPoint d inner join DataCollectionPointDataCollectionType dt \r\n" +
                        "on d.DataCollectionPointID= dt.DataCollectionPointID\r\n" +
                        "Where d.SiteID=4015 and dt.DataCollectionTypeID=35");

        //LFGDataCollectionPointDescription dbRecord = new LFGDataCollectionPointDescription();

        List<DataCollectionPoint> list = new ArrayList<DataCollectionPoint>();
        while (rs.next()) {
            DataCollectionPoint dc = new DataCollectionPoint();
            dc.setDataCollectionPointID(rs.getInt(1));
            dc.setSiteID(rs.getInt(2));
            list.add(dc);
            System.out.println("###############################");

        }

        DataCollectionPoint[] dbDataArray = list.toArray(new DataCollectionPoint[list.size()]);
        System.out.println("GGGGGGGGGGGGGGGG");
        assertArrayEquals(dbDataArray, restData.toArray());
    }


    @Then("^User can view correct data sources$")
    public void user_can_view_correct_data_sources() throws Throwable {

        System.out.println("DDDDDDDDDDD");
        System.out.println(
                prop.getProperty("environment") + "/api/DataCollectionPoint/getdcpconstructiondatasources");

        DcpconstructiondatasourcesPayload payload = new DcpconstructiondatasourcesPayload();
        payload.setSiteID(4015);
        ObjectMapper objectMapper = new ObjectMapper();

        Response response = given().contentType("application/json").body(objectMapper.writeValueAsString(payload) ).when()
                .post(prop.getProperty("environment") + "/api/DataCollectionPoint/getdcpconstructiondatasources")
                // .get("http://localhost:5000"+"/api/DataCollectionPoint/getdatacollectionpointdescription/410708")
                .then().extract().response();
        System.out.println("EEEEEEEEEEEE");
        System.out.println(response.getBody().asString());

        String key = "semGrids";// array key (as it mentioned in your Json)
        // List returnedArtworks = response.getBody().as(List.class)
        List<DataCollectionPoint> restData = response.jsonPath().getList(key, DataCollectionPoint.class);
        Statement stmt = DbConnect.getConnection().createStatement();
        ResultSet rs = stmt
                .executeQuery("Select * from DataCollectionPoint d inner join DataCollectionPointDataCollectionType dt \r\n" +
                        "on d.DataCollectionPointID= dt.DataCollectionPointID\r\n" +
                        "Where d.SiteID=4015 and dt.DataCollectionTypeID=35");

        //LFGDataCollectionPointDescription dbRecord = new LFGDataCollectionPointDescription();

        List<DataCollectionPoint> list = new ArrayList<DataCollectionPoint>();
        while (rs.next()) {
            DataCollectionPoint dc = new DataCollectionPoint();
            dc.setDataCollectionPointID(rs.getInt(1));
            dc.setSiteID(rs.getInt(2));
            list.add(dc);
            System.out.println("###############################");

        }

        DataCollectionPoint[] dbDataArray = list.toArray(new DataCollectionPoint[list.size()]);
        DataCollectionPoint[] restDaraArray = restData.toArray(new DataCollectionPoint[restData.size()]);
        System.out.println("GGGGGGGGGGGGGGGG");

        Arrays.sort(restDaraArray);
        Arrays.sort(dbDataArray);

        assertArrayEquals(dbDataArray, restDaraArray);
    }
}