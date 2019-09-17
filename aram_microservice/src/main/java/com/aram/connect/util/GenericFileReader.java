package com.aram.connect.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.aram.connect.dataObjects.OnlineTest;

public class GenericFileReader {

//	public static void main(String[] args) {
//		List l = readFileInList("C:\\Users\\rvelappan\\Desktop\\OnlineTestCsat.csv"); 
//
//		Iterator<String> itr = l.iterator(); 
//		while (itr.hasNext()) 
//			System.out.println(itr.next());
//
//		Map<String,Integer> settersMap = new LinkedHashMap<>();
//
//		createSetterMap(OnlineTest.class,settersMap);
//		
//		@SuppressWarnings("unchecked")
//		List<OnlineTest> online = (List<OnlineTest>)convertStreamToList(l,",",settersMap,OnlineTest.class);
//		for(OnlineTest o : online) {
//			System.out.println(o.toString());
//		}
//
//	}
//	
	public static <T> List<T> fileReader(Path fileName,Class<?> type, String separator,List<String> errorList){
		
		List l = readFileInList(fileName);
		
		Map<String,Integer> settersMap = new LinkedHashMap<>();

		createSetterMap(type,settersMap);
		
		
		
		@SuppressWarnings("unchecked")
		List<T> online = convertStreamToList(l,separator,settersMap,type,errorList);
//		for(OnlineTest o : online) {
//			System.out.println(o.toString());
//		}
		
		return online;

		
	}

	public static List<String> readFileInList(Path fileName) 
	{ 
		
		Charset set = Charset.forName("utf-8");

		List<String> lines = new LinkedList<>(); 
		try
		{ 
			lines = 	Files.readAllLines(fileName,StandardCharsets.UTF_8); 
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
				if(skip == 0) {
					skip ++;
					continue;
				}
				String[] fields = s.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				if( !(fields.length <= 12)) {
					errorList.add(s);
					continue;
				}
				int size = fields.length;

				T test = type.newInstance();

				List<Field> classFields = Arrays.asList(type.getDeclaredFields());
				int count = 0;
				for(String str : settersMap.keySet()) {
					//if( !m.getName().startsWith("set")) continue;
					if(count >= size) continue;
					if(AramUtil.checkIfStringIsEmpty(fields[count])) {
						count++;
						continue;
					}
					String methodName = str.substring(3, str.length());
					Optional<Field> f = classFields.stream().filter(x->x.getName().equalsIgnoreCase(methodName)).findFirst();
					if(!f.isPresent()) continue;
					String fieldType = f.get().getType().getSimpleName().toLowerCase();
					
					Class<?> classType = fieldType.equalsIgnoreCase("long") ? Long.class : String.class;
					Method m = type.getDeclaredMethod(str,  classType);
					m.setAccessible(true);
					
					switch(fieldType){
					case "long" : m.invoke(test, Long.parseLong(removeDoubleQuotes(fields[count] )));
					break;
					case "string" :m.invoke(test, removeDoubleQuotes(fields[count].trim()));
					break;
					}
					count ++;
				}

				testList.add((T) test);

			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return testList;		


	}

	private static <T> List<String> createSetterMap(Class<T> type, Map<String,Integer> headerMap) {

		List<String> settersList = new LinkedList<>();

//		Method[] allMethods = type.getDeclaredMethods();
//		if(allMethods == null) return null;
//		int numberOfFields = allMethods.length;
//		int count = 0;
//		for (Method med : allMethods) {
//
//			if (Modifier.isPublic(med.getModifiers())) {
//				if(med.getName().startsWith("set")) {
//					headerMap.put(med.getName(),count);
//					count++;
//				}
//					
//			}
//		}
		
		headerMap.put("setQuestionId", 0);
		headerMap.put("setTestId", 1);
		headerMap.put("setOnlineTestQuestion", 2);
		headerMap.put("setOptionA", 3);
		headerMap.put("setOptionB", 4);
		headerMap.put("setOptionC", 5);
		headerMap.put("setOptionD", 6);
		headerMap.put("setAnswer", 7);
		headerMap.put("setExplanation", 8);
		headerMap.put("setParagraph", 9);
		headerMap.put("setImagePath", 10);
		headerMap.put("setExplanationImage", 11);

		return settersList;
	}
	
	private static String removeDoubleQuotes(String name) {
		return name.replaceAll("^\"|\"$", "");
	}

}
