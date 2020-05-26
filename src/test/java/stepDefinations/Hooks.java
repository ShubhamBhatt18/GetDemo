package stepDefinations;

import cucumber.api.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforScenario() throws Throwable {

		stepDefination m = new stepDefination();

		if(stepDefination.placeId == null) {
			m.add_Place_Payload_with("Shetty", "French", "Asia");
			m.user_calls_with_http_request("addPlaceAPI","Post");

			m.verify_place_Id_created_maps_to_using("Shetty", "getPlaceAPI");

		}
		
		else {System.out.println("Pace id not null");}
	}
}
