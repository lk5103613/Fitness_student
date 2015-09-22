package com.honestwalker.kancart.core.IO;

/**
 * 对象流类型
 * @author langel
 *
 */
public class ObjectStreamType {
	
	// country对象流
	public static final ObjectStreamType COUNTRY = new ObjectStreamType("countries");
	// currency对象流
	public static final ObjectStreamType CURRENCY = new ObjectStreamType("currency");
	
	public static final ObjectStreamType KML = new ObjectStreamType("kml");
	
	public static final ObjectStreamType CATEGORIES = new ObjectStreamType("categories");
	
	private String name;
	public ObjectStreamType(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}