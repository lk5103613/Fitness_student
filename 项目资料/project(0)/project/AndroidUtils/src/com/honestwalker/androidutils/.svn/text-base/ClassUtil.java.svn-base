package com.honestwalker.androidutils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.honestwalker.androidutils.IO.LogCat;

/**
 * Class对象工具类，主要用于反射等相关辅助
 * @author honestwalker
 *
 */
public class ClassUtil {
	
	/**
	 * 输出对象所有字段的值
	 * @param obj          目标对象
	 * @param split        如何分割属性， 如 \r\n就是没个属性后换行显示，  ";" 就是用分号分割显示
	 * @param showNull     是否显示null值的属性
	 * @return
	 */
	public static String getFieldNameAndValue(Object obj , String split , boolean showNull) {
		
		StringBuffer valueSB = new StringBuffer();
		
		Field[] fs = obj.getClass().getDeclaredFields();
		for(Field f : fs) {
			
			// 设置Accessible为true才能直接访问private属性
			f.setAccessible(true); 
			try {
				if(f.get(obj) == null) {
					if(showNull) {
						valueSB.append(f.getName() + "=null" + split);
					}
				} else {
					valueSB.append(f.getName() + "=" + f.get(obj).toString() + split);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valueSB.toString();
	}
	
	/**
	 * 获取对象全部属性，也包括private
	 * @return
	 */
	public static Field[] getAllFields(Class clazz) {
		Field[] fs = clazz.getDeclaredFields();
		return fs;
	}
	
	/**
	 * 两个对象同名字段对拷
	 * @param srcObj
	 * @param desObj
	 */
	public static void copyObjValueForSameName(Object srcObj , Object desObj , boolean copySupper) {
		if(srcObj == null) {
			desObj = null;
		} else {
			Field[] srcObjFields = getAllFields(srcObj.getClass());
			Field[] srcSupperObjFields = null;
			
			int srcObjFieldCount = srcObjFields.length;
			int srcSuperObjFieldCount = 0;
			
			if(copySupper && srcObj.getClass().getSuperclass() != null) {
				srcSupperObjFields = getAllFields(srcObj.getClass().getSuperclass());
				srcSuperObjFieldCount = srcSupperObjFields.length;
			}
			
			LogCat.d("CLASS", "srcObj =" + srcObj + "     desObj=" + desObj);
			if(srcSupperObjFields != null) {
				LogCat.d("CLASS", "srcSupperObjFields len =" + srcSupperObjFields.length);
			} else {
				LogCat.d("CLASS", "srcSupperObjFields len =" + 0 + " 没有父类");
			}
			
			Field[] allSrcFields = new Field[srcObjFieldCount + srcSuperObjFieldCount];
			
			int index = 0;
			for(Field f : srcObjFields) {
				allSrcFields[index] = f;
				index++;
			}
			if(copySupper) {
				for(Field f : srcSupperObjFields) {
					allSrcFields[index] = f;
					index++;
				}
			}
			
			Map<String, Field> desObjFieldsMap = getAllFieldsMap(desObj.getClass() , copySupper);
			for(Field field : allSrcFields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				if(desObjFieldsMap.containsKey(fieldName)) {
					try {
						LogCat.d("CLASS", field.getName() + "=" + field.get(srcObj));
						desObjFieldsMap.get(field.getName()).set(desObj, field.get(srcObj));
						LogCat.d("CLASS", "SUCCESS");
					} catch (IllegalArgumentException e) {
					} catch (IllegalAccessException e) {
					}
				}
			}
			
		}
	}
	/**
	 * 获取一个类的属性map key是属性名 value是field
	 * @param clazz
	 * @return
	 */
	public static Map<String , Field> getAllFieldsMap(Class clazz , boolean copySupper) {
		Field[] fs = clazz.getDeclaredFields();
		Map<String , Field> map = new HashMap<String, Field>();
		for(Field field : fs) {
			field.setAccessible(true); 
			map.put(field.getName(), field);
		}
		if(copySupper && clazz.getSuperclass() != null) {
			Field[] fsSuper = clazz.getSuperclass().getDeclaredFields();
			for(Field field : fsSuper) {
				field.setAccessible(true); 
				map.put(field.getName(), field);
			}
		}
		return map;
	}
	
}
