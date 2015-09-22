package com.honestwalker.kancart.core.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;

import com.honestwalker.androidutils.IO.LogCat;

/**
 * 对储存卡对象流的IO操作
 * @author langel
 *
 */
public class ObjectStreamIO {
	
	public static Object output(String dir,Object obj,ObjectStreamType objectStreamType) throws IOException {
		dir = fixDir(dir);
		File file = new File(dir + objectStreamType.getName());
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.flush();
		oos.close();
		return obj;
	}
	public static Object output(String dir,Object obj,String name) throws IOException {
		LogCat.d("Object output " + dir + name);
		dir = fixDir(dir);
		File file = new File(dir + name);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.flush();
		oos.close();
		return obj;
	}
	
	public static Object input(String dir,ObjectStreamType objectStreamType) throws OptionalDataException, ClassNotFoundException, IOException {
		dir = fixDir(dir);
		File file = new File(dir + objectStreamType.getName());
		if(!file.exists()) {
			return null;
		}
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj =  ois.readObject();
		ois.close();
		return obj;
	}
	public static Object input(String dir,String name) throws OptionalDataException, ClassNotFoundException, IOException {
		dir = fixDir(dir);
		File file = new File(dir + name);
		if(!file.exists()) {
			return null;
		}
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj =  ois.readObject();
		ois.close();
		return obj;
	}
	
	/**
	 * 判断本地是否存在某个个对象流
	 * @return
	 */
	public static Boolean existsObjectStream(String dir,String name) {
		dir = fixDir(dir);
		File file = new File(dir + name);
		return file.exists();
	}
	
	/**
	 * 判断本地是否存在某个个对象流
	 * @return
	 */
	public static Boolean existsObjectStream(String dir,ObjectStreamType objectStreamType) {
		dir = fixDir(dir);
		File file = new File(dir + objectStreamType.getName());
		return file.exists();
	}
	
	private static String fixDir(String dir) {
		if(dir == null) {
			dir = "";
		}
		if(!dir.endsWith("/")) {
			dir += "/";
		}
		return dir;
	}
	
}
