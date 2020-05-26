package resources;

import java.util.ArrayList;
import java.util.List;

import pojo_classes.AddPlace;
import pojo_classes.Location;

public class TestDataBuild {

	
	public AddPlace addPlacePayLoad(String name,String language,String address) {
		
		AddPlace addP = new AddPlace();
		addP.setAccuracy(50);
		addP.setName(name);
		addP.setPhone_number("(+91) 983 893 3937");
		addP.setAddress(address);
		addP.setWebsite("ttps://rahulshettyacademy.com");
		addP.setLanguage(language);

		List<String> typeList = new ArrayList<String>();
		typeList.add("shoe park");
		typeList.add("shop");
		addP.setTypes(typeList);

		Location loc = new Location();
		addP.setLocation(loc);
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		return addP;

	}
	
	public String deletePlacePayLoad(String placeId) {
		return("{\n" + 
		  		"\"place_id\":\""+placeId+"\"\n" + 
		  		"\n" + 
	  		"}");
		
		
	}
}
