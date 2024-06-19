import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

//Rahul is asking us to remember this package (import org.hamcrest.Matchers.*) is helping us
//to resolve the equalTo method, as it is static method and Eclipse not always provide us
//suggestion to all static packages.

public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
/* Validate if Add Place is working as expected
REST Assured works in 3 principles: Given, When & Then

Given - all input details
When - Submit the API - resource, http method		
Then - validate the response
Convert content of the file to String -> convert content of file into Byte -> Byte data to String
*/
	RestAssured.baseURI="https://rahulshettyacademy.com";	
	String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
	.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Fran\\Documents\\addPlace.json")))).when().post("maps/api/place/add/json")
	.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
	.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
	System.out.println(response);
	JsonPath js = new JsonPath(response); // for parsing Json (it will take input as a String)
	String placeId = js.getString("place_id");
	
	System.out.println(placeId);
	
//Add place -> Update Place with New Address -> Get Place to validate if New address is present in the response	
	
	//Update Place
	String newAddress = "Summer Walk, Africa";
	
	given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
	.body("{\r\n"
			+ "\"place_id\":\""+placeId+"\",\r\n"
			+ "\"address\":\""+newAddress+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}\r\n"
			+ "")
	.when().put("maps/api/place/update/json")
	.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	//Get Place
	
	String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
	.queryParam("place_id", placeId)
	.when().get("maps/api/place/get/json")
	.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
	String actualAddress = js1.getString("address");
	System.out.println(actualAddress);
	Assert.assertEquals(actualAddress, newAddress);
	//Cucumber Junit, Testng
	
	}

}
