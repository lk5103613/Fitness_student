package com.honestwalker.androidutils.IO;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.honestwalker.androidutils.exception.ExceptionUtil;

public class FileIO {
	
	/**
	 * 保存文件
	 * @param buf
	 * @param filePath
	 * @param fileName
	 */
	public static void saveFile(byte[] buf, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {
				dir.mkdirs();
			}
			file = new File(filePath + File.separator + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(buf);
		} catch (Exception e) {
			ExceptionUtil.showException(e);
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					ExceptionUtil.showException(e);
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					ExceptionUtil.showException(e);
				}
			}
		}
	}
	
	/**
	 * 保存文件，
	 * @param file 包含文件名的file对象
	 */
	public static void saveFile(File file) {
		saveFile(fileToBytes(file) , file.getParent().toString() , file.getName());
	}
	
	/** 
     * 获得指定文件的byte数组 
     */  
    public static byte[] fileToBytes(File filePath){  
        byte[] buffer = null;  
        try {  
            FileInputStream fis = new FileInputStream(filePath);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);  
            byte[] b = new byte[1024];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }  
	
}
