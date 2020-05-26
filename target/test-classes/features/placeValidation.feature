Feature: Validating Place API's


@AddPlace
Scenario Outline: 	Verify if Place is successfully added using AddPlaceAPI
   Given Add Place Payload with "<name>" "<language>" "<address>"
   When user calls "addPlaceAPI" with "Post" http request
   Then the API call got success with status code 200
   And "status" in response body is "OK"
   And "scope" in response body is "APP"
   And verify place_Id created maps to "<name>" using "getPlaceAPI"
  # And verify that existing place deleted for placeID that maps to "<name>" using "deletePlaceAPI""
   
   
  Examples:
    |name   |language  |address|
    |Shubham|English   |483,Chla Vsta,91910|
#     |Leoanrd|Hebrew    |402 park wy,chla vsta,91910|
   
   
   @DeletePlace
   Scenario: Verify if Place is successfully added using DeletePlaceAPI
   Given Delete Place Payload 
   When user calls "deletePlaceAPI" with "Delete" http request
   Then the API call got success with status code 200
   And "status" in response body is "OK"
   
   