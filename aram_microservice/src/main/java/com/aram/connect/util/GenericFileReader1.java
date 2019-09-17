package com.aram.connect.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class GenericFileReader1 {

	public static void main(String[] args) {
		List l = readFileInList("C:\\Users\\rvelappan\\Desktop\\IM_ITEM.txt"); 

//		Iterator<String> itr = l.iterator(); 
//		while (itr.hasNext()) 
//			System.out.println(itr.next());

		Map<String,Integer> settersMap = new LinkedHashMap<>();
		List<String> errorList = new ArrayList<>();
		
//		@SuppressWarnings("unchecked")
//		List<ItemCounterPointVO> online = (List<ItemCounterPointVO>)convertStreamToList(l,"\\t",settersMap,ItemCounterPointVO.class,errorList);
//		System.out.println(online.size());
//		for(ItemCounterPointVO o : online) {
//			System.out.println(o.toString());
//		}

	}
	
	public static <T> List<T> fileReader(String fileName,Class<?> type, String separator,List<String> errorList){
		
		List l = readFileInList(fileName);
		
		Map<String,Integer> settersMap = new LinkedHashMap<>();

		
		@SuppressWarnings("unchecked")
		List<T> online = convertStreamToList(l,separator,settersMap,type,errorList);
		
		return online;

		
	}

	public static List<String> readFileInList(String fileName) 
	{ 

		List<String> lines = new LinkedList<>(); 
		try
		{ 
			lines = 	Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
		} 

		catch (IOException e) 
		{ 
			e.printStackTrace(); 
		} 
		return lines; 
	} 


	public static <T> List<T> convertStreamToList(List<String> fileLines,String separator,Map<String,Integer> settersMap,Class<T> type,List<String> errorList ){

		List<T> testList = new LinkedList<>();
		int skip = 0;
		try {
			for(String s : fileLines) {
				//skip header row
				
				String[] fields = s.split(separator);
				int size = fields.length;
				if(size != 192) {
					errorList.add(s);
					continue;
				}
				if(skip == 0) {
					createSetterMap(type,settersMap,fields);
					skip ++;
					continue;
				}
				
				System.out.println("Total number of Columns " +settersMap.size());

				T test = type.newInstance();

				List<Field> classFields = Arrays.asList(type.getDeclaredFields());
				int count = 0;
				for(String str : settersMap.keySet()) {
					//if( !m.getName().startsWith("set")) continue;
					if(count >= size) continue;
					if(AramUtil.checkIfStringIsEmpty(fields[count])) {
						System.out.println("Missing value for Column :" + str );
						count++;
						continue;
					}
					String methodName = str.substring(3, str.length());
					Optional<Field> f = classFields.stream().filter(x->x.getName().equalsIgnoreCase(methodName)).findFirst();
					if(!f.isPresent()) {
						System.out.println("Missing value for Column :" + str );
						count++;
						continue;
					}
					String fieldType = f.get().getType().getSimpleName().toLowerCase();
					
					Class<?> classType = fieldType.equalsIgnoreCase("long") ? Long.class : String.class;
					Method m = type.getDeclaredMethod(str,  classType);
					m.setAccessible(true);
					
					switch(fieldType){
					case "long" : m.invoke(test, Long.parseLong(fields[count] ));
					break;
					case "string" :m.invoke(test, fields[count].trim());
					break;
					}
					count ++;
				}

				testList.add((T) test);

			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return testList;


	}

	private static <T> List<String> createSetterMap(Class<T> type, Map<String,Integer> headerMap, String[] fields) {

		List<String> settersList = new LinkedList<>();
		int count =0;
		for(String s :fields) {
			String methodName = null;
			if(AramUtil.checkIfStringIsEmpty(s))  continue;
			String name = getProperCase(s.trim());
//			if(name.trim().equalsIgnoreCase("item_no")) {
//				name = "Item_no";
//			}
				methodName = "set" + name;
			headerMap.put(methodName, count);
			count++;
		}

		return settersList;
	}
	
	private static String getProperCase(String s) {
		
		if(Character.isLetter(s.charAt(0))) {
			return s.charAt(0) + s.substring(1).toLowerCase();
		}
		
		return s.charAt(1)+s.substring(2).toLowerCase();
		
	}
	
	private static String removeDoubleQuotes(String name) {
		return name.replaceAll("^\"|\"$", "");
	}

}
