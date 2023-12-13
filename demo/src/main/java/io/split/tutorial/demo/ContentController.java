package io.split.tutorial.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
public class ContentController {

	@Autowired
	ContentService contentService;

	@GetMapping("/healthCheck")
	public Content healthCheck() {
		return contentService.healthCheck();
	}	
	
	@GetMapping("/fetchVehicleInfo")
	public Content fetchVehInfo() {
		return contentService.fetchVehicleData();
	}	
	
	@GetMapping("/vehMakeMatch")
	public Content checkVehicleMakeMatch(@RequestParam String make) {
		return contentService.checkVehicleMakeMatch(make);
	}	
	
	@GetMapping("/checkWhatsNew")
	public Content checkWhatsNew(@RequestParam String orgId) {
		return contentService.showWhatsNew(orgId);
	}	
	
	@GetMapping("/manage1on1s")
	public Content manage1on1s(@RequestParam String orgId, String userId  ) {
		return contentService.reArrange1on1sBasedOnUser(orgId,userId);
	}	
	
	@GetMapping("/checkPromotionalBanner")
	public PromotionalBannerContent checkPromotionalBanner(@RequestParam String userId) {
		return contentService.showPromotionalBanner(userId);
	}
	
	
}
