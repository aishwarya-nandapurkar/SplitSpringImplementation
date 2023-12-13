package io.split.tutorial.demo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.segment.analytics.Analytics;
import com.segment.analytics.messages.TrackMessage;

import io.split.client.SplitClient;
import io.split.client.SplitClientConfig;
import io.split.client.SplitFactory;
import io.split.client.SplitFactoryBuilder;
import io.split.client.SplitManager;
import io.split.client.impressions.Impression;
import io.split.client.impressions.ImpressionListener;
import io.split.integrations.IntegrationsConfig;

/**
 * 
 *
 */
public class OrgImpressionRecorderClass {
	public class MyImpressionListener implements ImpressionListener {

		public void log(Impression impression) {

			Map<String, Object> props = new HashMap<String, Object>();
			props.put("trafficType", manager.split(impression.split()).trafficType);
			System.out.println("################ trafficType " + manager.split(impression.split()).trafficType);
			props.put("treatment", impression.treatment());
			props.put("key", impression.key());
			props.put("time", impression.time());
			props.put("split", impression.split());
			props.put("value", "23");

			analytics.enqueue(TrackMessage.builder("Get Treatment").userId("ash1234").properties(props));
			analytics.enqueue(TrackMessage.builder("creditcard payment complete").userId("ash1234").properties(props));
		}

		public void close() {
			// do nothing
		}

	}

	Analytics analytics;
	SplitManager manager;
	
	public void execute() throws Exception {
		analytics = Analytics.builder("FfR1ukF9MYemmh3bEaMTedewdDYbqejT").build();

		SplitClientConfig config = SplitClientConfig.builder().setBlockUntilReadyTimeout(5000)
				.integrations(IntegrationsConfig.builder().impressionsListener(new MyImpressionListener(), 500).build())
				.build();

		SplitFactory splitFactory = SplitFactoryBuilder.build("cf7kbi6b5slr3qr7lvr4mlh9kg1mkoql4f7f", config);

		manager = splitFactory.manager();
		manager.blockUntilReady();

		final SplitClient split = splitFactory.client();
		split.blockUntilReady();

		// the split client is thread safe and should be reused for the life of the
		// container

		final String orgId = "ash1234";
		String treatment = split.getTreatment(orgId, "Manage1on1s");

		if (treatment.equals("on")) {
			boolean track_event1 = split.track(orgId, "org", "on_received");
			System.out.println("Split event tracked: " + track_event1);
		} else if (treatment.equals("off")) {
			boolean track_event2 = split.track(orgId, "org", "off_received");
			System.out.println("Split event tracked :" + track_event2);
		} else {
			boolean track_event3 = split.track(orgId, "org", "control_received");
			System.out.println("Split event tracked :" + track_event3);
		}

		System.out.println("treatment: " + treatment);

	}

	public static void main(String[] args) throws Exception {
		new OrgImpressionRecorderClass().execute();
	}
}