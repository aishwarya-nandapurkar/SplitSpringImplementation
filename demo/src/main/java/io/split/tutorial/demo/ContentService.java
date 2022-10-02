package io.split.tutorial.demo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import io.split.client.SplitClient;
import io.split.client.SplitClientConfig;
import io.split.client.SplitFactory;
import io.split.client.SplitFactoryBuilder;

@Service
public class ContentService {

	public Content healthCheck() {

		SplitClient client = SplitInstance.getInstance().splitFactory.client();
		try {
			client.blockUntilReady();
		} catch (TimeoutException | InterruptedException e) {
			// log & handle
			System.out.print("Split had an issue. NOT loaded correctly!");
		}

		String treatment = client.getTreatment("CUSTOMER_ID", "first_split");

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

	public Content fetchVehicleData() {

		try {
			SplitClient client = SplitInstance.getInstance().splitFactory.client();
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
			SplitClient client = SplitInstance.getInstance().splitFactory.client();
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
}
