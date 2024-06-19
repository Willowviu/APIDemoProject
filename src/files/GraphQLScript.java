package files;
import static io.restassured.RestAssured.*;

public class GraphQLScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		given().log().all().header("Content-Type","application/json")
		.body("")
		.when().post()
		.then();
	}

}
