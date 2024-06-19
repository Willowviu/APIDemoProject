import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	JsonPath js = new JsonPath(payload.CoursePrice());	
		
		//Print No of courses returned by API
	int count = js.getInt("courses.size()");
	System.out.println(count);
		//Print purchase amount
	int totalAmount = js.getInt("dashboard.purchaseAmount");
	System.out.println(totalAmount);
		//Print title of the first course
	String firstTitle = js.getString("courses[0].title");
	System.out.println(firstTitle);
		//Print All courses titles and their respective Prices
	
	for(int i=0;i<count;i++)
	{
		String courseTitles = js.get("courses["+i+"].title");
		System.out.println(js.get("courses["+i+"].price").toString());
		
		System.out.println(courseTitles);
		
	}
		//Print no of copies sold by RPA Course
		//as "tomorrow" RPA index could have changed we won't hardcode the code 
		System.out.println("Print no of copies sold by RPA Course");
		
		for(int i=0;i<count;i++)
		{
			String courseTitles = js.get("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA"))
			{
				//copies sold
				int copies = js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}	
			 
		}
	
		
	}

}
