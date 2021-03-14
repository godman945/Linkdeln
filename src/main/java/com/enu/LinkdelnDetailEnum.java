package com.enu;

public enum LinkdelnDetailEnum {

	WEBSITE("網站","Website"), 
	PHONE("電話","Phone"),
	INDUSTRY("產業","Industry"),
	SCALE("規模","Company size"),
	HEADQUARTERS("總部","Headquarters"),
	TYPE("類型","Type"),
	SINCE("創立","Founded"),
	FIELD("領域","Specialties");

	private final String title;
	private final String enitle;
	
	LinkdelnDetailEnum(final String title ,String enitle) {
		this.title = title;
		this.enitle = enitle;
	}

	public String getTitle() {
		return title;
	}

	public String getEnitle() {
		return enitle;
	}


	

}
