package demo;
import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class oAuthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		/*System.setProperty("webdriver.gecko.driver", "C:\\Users\\Fran\\Desktop\\Selenium Automation Testing\\Selenium\\GRID\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://accounts.google.com/v3/signin/identifier?opparams=%253Fauth_url%253Dhttps%25253A%25252F%25252Faccounts.google.com%25252Fo%25252Foauth2%25252Fv2%25252Fauth&dsh=S721972642%3A1708331636439977&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&o2v=2&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&response_type=code&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&service=lso&state=verifyfjdss&theme=glif&flowName=GeneralOAuthFlow&continue=https%3A%2F%2Faccounts.google.com%2Fsignin%2Foauth%2Fconsent%3Fauthuser%3Dunknown%26part%3DAJi8hAO21BEqvC_-tX6Uj1ov8vebhFr3nkcjl1UhIgvMjVtx-kA7LhXmsJxYyKNiZ-MLWAyJJBsCugciuKmjAipA7njN2A4W0G4lAAbwSNb96Cc3Jr1EDtg9abHjVnRWlyWze5L0lm_9lvm3sE_L9oi8JCRWZXG4rx90VgWmHq6PFSIuuA9_v1XLwIGPSmmEFIwoyydGNtjVNcXyiUqQma-yIBHyDqe_Fzm4uzmN26RIflCRm20ZHvI_3w2iaMecU6plF43xvSEP1OnfmP1k8hJQE5iVkfhMy-I20JPYJLw2mSA31BCn8spxMGi_lNuCAHTCnMBnkgAlg4Qw30lB72FtKHniDS8cKEauyNMOj0tkO7N7QXhnjbJm5m-cXbGQNEy15qXdtJBhwk2jBOIF9V_WiK8GN7qu1xWk0MwhibCOgUegv-F9mdiHTP8tpX0qrXWWAMY-nEAPosXHPKLKuOvJMbP8SVekAw%26as%3DS721972642%253A1708331636439977%26client_id%3D692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com%26theme%3Dglif%23&app_domain=https%3A%2F%2Frahulshettyacademy.com&rart=ANgoxcc-3K2eaJ36SoX93kDnQkghh2VfbFuz3Z5HWbXz5gn3u30ICB8_L7SSQUDwiSOH6wKjx_dJC9XIRfBQSbQnEDeOljwY2Usu4-FrW9gjyNJzcjLENWE");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("willowviu3@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("password");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		//String url = driver.getCurrentUrl();*/
		String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";
		
		String partialcode = url.split("code=")[1];
		String code = partialcode.split("&scope")[0];
		System.out.println(code);
		
		//tagname[attribute='value']
		
		String partialcode1=url.split("code=")[1];

		String code1=partialcode1.split("&scope")[0];

		System.out.println(code1);

		String response = given() 
				.urlEncodingEnabled(false)
				.queryParams("code",code1)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("grant_type", "authorization_code")
				.queryParams("state", "verifyfjdss")
				.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

		                     // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		// System.out.println(response);

		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");
		System.out.println(accessToken);

		String r2=    given().contentType("application/json").
		queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php")
		.asString();
		System.out.println(r2);
		
	}

}
