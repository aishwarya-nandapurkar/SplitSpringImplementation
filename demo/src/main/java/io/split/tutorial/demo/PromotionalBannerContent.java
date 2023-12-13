package io.split.tutorial.demo;

public class PromotionalBannerContent {
	private String banner;
	private String url;
	
	public PromotionalBannerContent(String banner, String url) {
		super();
		this.banner = banner;
		this.url = url;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
