package com.apps.wave.news.enums;

public enum Role {
	 NORMAL(1),
	    CONTENT_WRITER(2),
	    ADMIN(3);

	    private final int value;

	    Role(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
}
