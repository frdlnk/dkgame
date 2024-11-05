package utils;

public enum Tags {
	PLAYER("PLAYER"),
	ENEMY("ENEMIGO"),
	REHEN("REHEN"),
	FLOOR("FLOOR"),
	PLATFORM("PLATFORM"),
	DEADbOZ("DEADBOX"),
	STATIC_OBJECT("STATIC");

	private final String tag;
	Tags(String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return tag;
	}
}
