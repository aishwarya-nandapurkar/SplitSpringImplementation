package io.split.tutorial.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import io.split.client.SplitClient;
import io.split.client.api.SplitResult;
import split.com.google.gson.Gson;
	
@Service
public class ContentService {
	String configValues="";
	SplitClient client = SplitInstance.getInstance().splitFactory.client();
	//client.blockUntilReady();
	
	List<String> splitNames = SplitInstance.getInstance().splitFactory.manager().splitNames();
	
	
	public void readSplits()
	{
		if (splitNames!=null ) {
			 for (int i = 0; i < splitNames.size(); i++) {
			    	
			        System.out.print("SplitName : "+splitNames.get(i));
		        	
		        	
	              
	         }
		}
	   
	}
	
	
	public Content healthCheck() {

		
		try {
			client.blockUntilReady();
		} catch (TimeoutException | InterruptedException e) {
			// log & handle
			System.out.print("Split had an issue. NOT loaded correctly!");
		}

		String treatment = client.getTreatment("ash123", "js_test");

		if (treatment.equals("on")) {
			boolean track_event1 = client.track("CUSTOMER_ID", "user", "app_loaded");
			System.out.println("Split event tracked for health Check " + track_event1);
			return new Content("status : UP");
		} else if (treatment.equals("off")) {
			// insert code here to show off treatment
			boolean track_event1 = client.track("CUSTOMER_ID", "user", "app_down");
			System.out.println("Treatment is off");
			return new Content("status : DOWN");

		} else {
			// insert your control treatment code here
			return new Content("ONGOING");

		}
	}

	public Content showWhatsNew(String orgId) {

	//	SplitClient client = SplitInstance.getInstance().splitFactory.client();
		HashMap<String, String> config = null;
		try {
			client.blockUntilReady();
		} catch (TimeoutException | InterruptedException e) {
			// log & handle
			System.out.print("Split had an issue. NOT loaded correctly!");
		}

		//String treatment = client.getTreatment(orgId, "enable_whatsnew");
		SplitResult result=client.getTreatmentWithConfig(orgId, "enable_whatsnew");
		String treatment = result.treatment();
	    if (result.config() != null) {
	        Gson gson = new Gson();
	       config= gson.fromJson(result.config(), HashMap.class);
	    }

		if (treatment.equals("ExperienceA")) {
			boolean track_event1 = client.track(orgId, "org", "app_loaded");
			System.out.println("Split event tracked for whatsnew " + track_event1);
			if(config!=null)
			{
				System.out.print(config.toString());
				System.out.print("************ Text from config : "+config.get("text"));
				return new Content(config.get("text"));
			}else
				return new Content("Not found");
			
		} else if (treatment.equals("ExperienceB")) {
			// insert code here to show off treatment
			boolean track_event1 = client.track(orgId, "org", "app_down");
			System.out.println("Treatment is Experience B");
			if(config!=null)
			{
				System.out.print(config.toString());
				System.out.print("************ Text from config : "+config.get("text"));
				return new Content(config.get("text"));
			}else
				return new Content("Not found");

		} else {
			// insert your control treatment code here
			return new Content("NA");

		}
	}
	
	public Content fetchVehicleData() {

		try {
	//		SplitClient client = SplitInstance.getInstance().splitFactory.client();
			client.blockUntilReady();
			String uuid_user = UUID.randomUUID().toString();
			String treatment = client.getTreatment(uuid_user, // unique identifier for your user
					"getVehicle_split");

			if (treatment.equals("on")) {
				// insert on code here
				VehicleInfo v1 = new VehicleInfo("VIN1234", "Honda", "CRV", "2022", "03-02-2022", "VZW");
				boolean track_event1 = client.track(uuid_user, "user", "Get_VehicleInfo");
				System.out.print(track_event1);
				return new Content("Vehicle make is : " + v1.getMake() + " and VIN is :" + v1.getVin()
						+ " and model is : " + v1.getModel());

			} else if (treatment.equals("off")) {
				// insert off code here
				boolean track_event1 = client.track(uuid_user, "user", "VehicleInfo_NA");
				System.out.print(track_event1);
				return new Content("Sorry this functionality is not currently available");
			} else {
				// insert control code here
				boolean track_event1 = client.track(uuid_user, "user", "Not_interacted");
				System.out.print(track_event1);
				System.out.println("Split treatment not found!!");
			}
		} catch (TimeoutException | InterruptedException e) {
			// log & handle
			System.out.print("Split had an issue");
		}

		return new Content("Work in progress!!");
	}

	public Content checkVehicleMakeMatch(String make) {
		try {
		//	SplitClient client = SplitInstance.getInstance().splitFactory.client();
			client.blockUntilReady();
			String uuid_make = "MAKE" + Math.random();
			String treatment = client.getTreatment(uuid_make, // unique identifier for your user
					"checkMake_Split");

			if (treatment.equals("on")) {
				// insert on code here
				if (make.equalsIgnoreCase("Honda")) {
					boolean make_event = client.track(uuid_make, "user", "make_honda");
					System.out.print(make_event);
					return new Content("Make MATCHED with System!");
				} else {
					boolean make_event = client.track(uuid_make, "user", "make_NOThonda");
					System.out.print(make_event);
					return new Content("Make did not match");
				}
			} else if (treatment.equals("off")) {
				// insert off code here
				boolean track_event1 = client.track(uuid_make, "user", "make_off");
				System.out.print(track_event1);
				return new Content("Sorry this functionality is not currently available");
			} else {
				// insert control code here
				boolean track_event1 = client.track(uuid_make, "user", "Not_interactedMake");
				System.out.print(track_event1);
				System.out.println("Split treatment not found!!");
			}
		} catch (TimeoutException | InterruptedException e) {
			// log & handle
			System.out.print("Split had an issue");
		}

		return new Content("Work in progress!!");
	}
	
	public Content reArrange1on1sBasedOnUser(String orgId, String userId) {

	//	SplitClient client = SplitInstance.getInstance().splitFactory.client();
		
		try {
			client.blockUntilReady();
		} catch (TimeoutException | InterruptedException e) {
			// log & handle
			System.out.print("Split had an issue. NOT loaded correctly!");
		}

		//String treatment = client.getTreatment(orgId, "Manage1on1s");
		//SplitResult result=client.getTreatmentWithConfig(orgId, "Manage1on1s");
		
	    
	    Map<String, Object> attributes = new HashMap<String, Object>();
	    attributes.put("user_id", userId);
	    attributes.put("location", "California");
	    
	    String treatment=client.getTreatment(orgId, "Manage1on1s", attributes);
	    
		if (treatment.equals("on")) {
			boolean track_event1 = client.track(orgId, "org", "enabing1on1");
			System.out.println("Split event tracked for manage1on1s " + track_event1);
			
				return new Content("Manage1on1s enabled for this user");
			
		} else if (treatment.equals("off")) {
			// insert code here to show off treatment
			boolean track_event1 = client.track(orgId, "org", "notenabing1on1");
			System.out.println("Treatment is Experience B");
			
				return new Content("Manage1on1s NOT enabled for this user");

		} else {
			// insert your control treatment code here
			return new Content("NA");

		}
	}
	
	
	public PromotionalBannerContent showPromotionalBanner(String userid) {
		readSplits();
	//	SplitClient client = SplitInstance.getInstance().splitFactory.client();
		//client = SplitInstance.getInstance().splitFactory.client();
		HashMap<String, String> config = null;
		try {
			client.blockUntilReady();
		} catch (TimeoutException | InterruptedException e) {
			// log & handle
			System.out.print("Split had an issue. NOT loaded correctly!");
		}

		SplitResult result=client.getTreatmentWithConfig(userid, "showPromotionBanner");
		String treatment = result.treatment();
		System.out.print("++++++++++++ Printing getTreatment call's result : "+treatment);
		
	    if (result.config() != null) {
	        Gson gson = new Gson();
	       config= gson.fromJson(result.config(), HashMap.class);
	    }

		if (treatment.equals("on")) {
			boolean track_event1 = client.track(userid, "user", "banneron");
			System.out.println("Split event tracked for promotionalbanner " + track_event1);
			if(config!=null)
			{
				System.out.print(config.toString());
				System.out.print("************ Text from config : "+config.get("bannerText"));
				return new PromotionalBannerContent(config.get("bannerText"), config.get("url") );
			}else
				return new PromotionalBannerContent("NA","NA");
			
		} else if (treatment.equals("off")) {
			// insert code here to show off treatment
			boolean track_event1 = client.track(userid, "user", "bannerOff");
			System.out.println("bannerOff tracked :"+track_event1);
			if(config!=null)
			{
				System.out.print(config.toString());
				System.out.print("************ Text from config : "+config.get("bannerText"));
				return new PromotionalBannerContent(config.get("bannerText"), config.get("url") );
			}else
				return new PromotionalBannerContent("NA","NA");

		} else if (treatment.equals("maintenance")) {
			boolean track_event1 = client.track(userid, "user", "maintenance");
			System.out.println("maintenance tracked :"+track_event1);
			if(config!=null)
			{
				System.out.print(config.toString());
				System.out.print("************ Text from config : "+config.get("bannerText"));
				return new PromotionalBannerContent(config.get("bannerText"), config.get("url") );
			}else
				return new PromotionalBannerContent("NA","NA");

		}else {
			// insert your control treatment code here
			return new PromotionalBannerContent("NA","NA");

		}
	}
	
}
