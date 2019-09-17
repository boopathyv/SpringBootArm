package com.aram.connect.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public class GenericCsvWriter {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		List<Vendor> dataList = new ArrayList<>();
//
//		List<VendorVO> vendorVOList = new ArrayList<>();
//		List<Vendor> vendorList = new ArrayList<>();
//		VendorMapper mapper = new VendorMapper();
//		VendorFacadeDAO dao = new VendorFacadeDAO();
//		try {
//			vendorVOList = dao.getAllVendorDetails();
//			for(VendorVO vo : vendorVOList) {
//				vendorList.add(mapper.mapToJson(vo));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//
//
//		csvFileOutput(Vendor.class , vendorList, "C:\\Users\\rvelappan\\Desktop\\CsvOutPut.csv" , ",");
//	}

	public static <T> void csvFileOutput(Class<T> type , List<T> dataList, String filePath , String seperator) {
		PrintWriter fw = null;
		File dir = new File(filePath); 

		
		StringBuilder builder = new StringBuilder();
		Map<String,String> csvMap = new LinkedHashMap<>();

		try {
			fw = new PrintWriter(new File(filePath));
			
			createCSVHeaders(type,builder,seperator,csvMap);
			//System.out.println(builder.toString());
			//Set<String> methodNames = getMethodNames(type);
			createCSVDataLines(builder,seperator,dataList,csvMap);
			//System.out.println(builder.toString());
			fw.write(builder.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			fw.close();
		}
		

	}

	private static <T> void createCSVHeaders(Class<T> type,StringBuilder builder,String seperator, Map<String,String> csvMap) {


		Field[] allFields = type.getDeclaredFields();
		if(allFields == null) return;
		int numberOfFields = allFields.length;
		int count = 1;
		for (Field field : allFields) {

			if (Modifier.isPrivate(field.getModifiers())) {
				builder.append(field.getName());
				csvMap.put(field.getName(), "");
				if(count != numberOfFields)
					builder.append(seperator);
			}
			count++;
		}
		builder.append("\n");
	}

	private static <T> Set<String> getMethodNames(Class<T> className ){
		Set<String> methodNames = new HashSet<>();

		Method[] allMethods = className.getDeclaredMethods();
		
		if(allMethods == null) return methodNames;
		for(Method method: allMethods) {

			method.setAccessible(true);
			String name = method.getName();
			if( !name.startsWith("get") ) continue;
			methodNames.add(name);

		}
		return methodNames;

	}

	private static <T> void createCSVDataLines( StringBuilder builder,String seperator, List<T> dataList , Map<String,String> csvMap ) {

		try {
			
			for(Object t:dataList) {
				Class<?> c = t.getClass();
				Method[] allMethods = c.getDeclaredMethods();
				if(allMethods == null) return;
				int numberOfFields = allMethods.length/2;
				int count = 1;
				for(Method method: allMethods) {

					method.setAccessible(true);
					String name = method.getName();
					if( !name.startsWith("get") ) continue;
					Optional<String> optionalKey = csvMap.keySet().stream().filter(x->x.equalsIgnoreCase(name.substring(3,name.length()))).findFirst();
					//System.out.println("Key :" + key);
					if(optionalKey.isPresent()) 						
						csvMap.put(optionalKey.get(), convertIntoString(method.invoke(t)));
					
				}
				
				appendCSVRow(csvMap,builder,seperator);
				csvMap.values().stream().forEach(x -> x = "");
				

			}

		}  catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	private static void appendCSVRow(Map<String,String> csvMap , StringBuilder builder, String seperator) {
		
		int length = csvMap.values().size();
		int count =1 ;
		for(String value : csvMap.values()) {
			builder.append(value);
			
			if(count != length)
				builder.append(seperator);

			count ++;
			
		}
		
		builder.append("\n");
	}
	
	private static String convertIntoString(Object obj) {
		String output ="";
		
		if(obj == null) return output;
		
		if(obj instanceof String) {
			output = (String) obj;
		}else if(obj instanceof Integer) {
			output = obj +"";
		}else if(obj instanceof Long) {
			output = obj +"";
		}else if(obj instanceof Double) {
			output = obj +"";
		}
		
		
		return output;
	}

}
