
public enum LinkdelnDetailEnum {

	WEBSITE("網站"), 
	PHONE("電話"),
	INDUSTRY("產業"),
	SCALE("規模"),
	HEADQUARTERS("總部"),
	TYPE("類型"),
	SINCE("創立"),
	FIELD("領域");

	private final String title;
	
	LinkdelnDetailEnum(final String title ) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}


	

}
