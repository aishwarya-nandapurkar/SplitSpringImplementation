package io.split.tutorial.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
