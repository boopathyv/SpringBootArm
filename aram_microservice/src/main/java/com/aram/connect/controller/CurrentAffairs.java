package com.aram.connect.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aram.connect.AramConstants;
import com.aram.connect.Exception.FileStorageException;
import com.aram.connect.dataObjects.CurrentAffairsJson;
import com.aram.connect.dataObjects.ErrorObject;
import com.aram.connect.dataObjects.FortnightCompliation;
import com.aram.connect.dataObjects.OnlineMobileAppTestFinalJson;
import com.aram.connect.dataObjects.OnlineMobileAppTestResponseJson;
import com.aram.connect.dataObjects.OnlineTest;
import com.aram.connect.dataObjects.OnlineTestJson;
import com.aram.connect.dataObjects.OnlineTestMbAppJson;
import com.aram.connect.dataObjects.PrelimsQuizJson;
import com.aram.connect.dataObjects.mapper.AimCivilsMapper;
import com.aram.connect.persistence.dao.MainsCurrentAffairs;
import com.aram.connect.persistence.dao.MainsQuiz;
import com.aram.connect.persistence.dao.PrelimsCurrentAffairs;
import com.aram.connect.persistence.dao.PrelimsQuiz;
import com.aram.connect.service.CurrentAffairsService;
import com.aram.connect.util.AramUtil;

@RestController
public class CurrentAffairs {
	
	@Autowired
	private CurrentAffairsService currentAffairsService;

	@Autowired
	private ServletContext servletContext;
	
	@CrossOrigin
	@GetMapping("/welcome")
	public String index() {
		return "Aram IAS Academy";
	}
	
	@CrossOrigin
	@PostMapping("/uploadCurrentAffairs")
    public String uploadFile(@RequestParam("file") MultipartFile mfile,@RequestParam("type")String type,
    		@RequestParam("heading") String heading,@RequestParam("shortDescription") String shortDesc ) {
        String fileName = "";
        String reason = "";
        
        CurrentAffairsJson currentAffairs = new CurrentAffairsJson(mfile,heading,shortDesc,type);
        
		try {
			MultipartFile file =currentAffairs.getFile();
			if(null == file) return "Please Attach the File";
			
			 String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
			 if( !(originalFileName.endsWith(".pdf"))) return "Please Attach the File in correct format";
			
			 reason =  currentAffairsService.storeCurrentAffairs(currentAffairs);
			
			
			System.out.println("Filename " + fileName);
		} catch (FileStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        

        return reason;
    }
	
	@CrossOrigin
	@GetMapping(value="/downloadCurrentAffairs" , produces="application/pdf")
	public void downloadCurrentAffairs(HttpServletResponse response,
			                           HttpServletRequest request,
			                           @RequestParam("fileName") String fileName) {
		
		if(null == fileName || "".equals(fileName)) return ;
		
		Path filePath = currentAffairsService.getCurrentAffairs(fileName);
		
		if(Files.exists(filePath)) {
			response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
                Files.copy(filePath, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
		}
		
		
	}
	
	@CrossOrigin
	@GetMapping(value="/showCurrentAffairs" , produces="application/pdf")
	public void showCurrentAffairs(HttpServletResponse response,
			                           HttpServletRequest request,
			                           @RequestParam("fileName") String fileName) {
		
		if(null == fileName || "".equals(fileName)) return ;
		
		Path filePath = currentAffairsService.getCurrentAffairs(fileName);
		
		if(Files.exists(filePath)) {
			response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "filename="+fileName);
            try
            {
                Files.copy(filePath, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
		}
		
		
	}
	
	@CrossOrigin
	@GetMapping("/getMainsCurrentAffairs")
	public ResponseEntity<List<MainsCurrentAffairs>> getAllMainsCurrentAffairs(

			@RequestParam(value = "maxDate",required = false) String strMaxDate,
			@RequestParam(value = "minDate",required = false) String strMinDate) throws Exception{
		List<MainsCurrentAffairs> mainsCurrentAffairs = null;
		System.out.println("getMainsCurrentAffairs");
		try{
			mainsCurrentAffairs = currentAffairsService.getAllMainsCurrentAffairs(strMinDate,strMaxDate);	
			System.out.println("Account size is " + mainsCurrentAffairs.size() );
			return ResponseEntity.ok(mainsCurrentAffairs);	
		}catch(Exception e){
			System.out.println("Exception in fetching all Main Currenct Affairs"+e);
			return ResponseEntity.notFound().build();
		}	
	}
	
	@CrossOrigin
	@GetMapping("/getPrelimsCurrentAffairs")
	public ResponseEntity<List<PrelimsCurrentAffairs>> getAllPrelimsCurrentAffairs(

			@RequestParam(value = "maxDate",required = false) String strMaxDate,
			@RequestParam(value = "minDate",required = false) String strMinDate) throws Exception{
		List<PrelimsCurrentAffairs> prelimsCurrentAffairs = null;
		System.out.println("getMainsCurrentAffairs");
		try{
			prelimsCurrentAffairs = currentAffairsService.getAllPrelimsCurrentAffairs(strMinDate,strMaxDate);	
			System.out.println("Account size is " + prelimsCurrentAffairs.size() );
			
			if(prelimsCurrentAffairs.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
			return ResponseEntity.ok(prelimsCurrentAffairs);	
		}catch(Exception e){
			System.out.println("Exception in fetching all Prelims Current Affairs"+e);
			return ResponseEntity.notFound().build();
		}	
	}
	
	@CrossOrigin
	@GetMapping("/getMainsQuiz")
	public ResponseEntity<List<MainsQuiz>> getAllMainsQuiz(
			@RequestParam(value = "maxDate",required = false) String strMaxDate,
			@RequestParam(value = "minDate",required = false) String strMinDate) 
			throws Exception{
		List<MainsQuiz> mainsQuiz = null;
		System.out.println("getMainsCurrentAffairs");
		try{
			mainsQuiz = currentAffairsService.getAllMainsQuiz(strMinDate,strMaxDate);	
			System.out.println("getMainsCurrentAffairs size is " + mainsQuiz.size() );
			
			if(mainsQuiz.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
			return ResponseEntity.ok(mainsQuiz);	
		}catch(Exception e){
			System.out.println("Exception in fetching all Mains Quiz"+e);
			return ResponseEntity.notFound().build();
		}	
	}
	
	@CrossOrigin
	@GetMapping("/getPrelimsQuiz")
	public ResponseEntity<List<PrelimsQuizJson>> getPrelimsQuiz(
			@RequestParam(value = "maxDate",required = false) String strMaxDate) 
			throws Exception{
		JSONArray strJson = new JSONArray();
		String strJtoS ="";
		List<PrelimsQuiz> prelimsQuiz = null;
		List<PrelimsQuizJson> prelimsQuizList = new ArrayList<>();
		AimCivilsMapper mapper = new AimCivilsMapper();
		System.out.println("getMainsCurrentAffairs");
		try{
			prelimsQuiz = currentAffairsService.getPrelimsQuiz(strMaxDate);	
			System.out.println("getMainsCurrentAffairs size is " + prelimsQuiz.size() );
			for(PrelimsQuiz pq : prelimsQuiz) {
				PrelimsQuizJson json = new PrelimsQuizJson();
				mapper.convertPrelimsQuizIntoJson(pq, json);
				prelimsQuizList.add(json);
			}
			if(prelimsQuizList.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			return ResponseEntity.ok(prelimsQuizList);	
		}catch(Exception e){
			System.out.println("Exception in fetching all Prelims Quiz"+e);
			return ResponseEntity.notFound().build();
		}	
	}
	
	@CrossOrigin
	@PostMapping("/getOnlineTest")
	public ResponseEntity<List<OnlineTestJson>> getOnlineTest(
			@RequestParam(value = "testId",required = false) long testId){
		
		List<OnlineTestJson> testList = new ArrayList<>();
		AimCivilsMapper mapper = new AimCivilsMapper();
		
		if( 0L == testId) return ResponseEntity.noContent().build();
		try {
			List<OnlineTest> onlineList = currentAffairsService.getOnlineTest(testId);
			
			testList = onlineList.stream().map(x->mapper.convertOnlineTestToJson(x)).collect(Collectors.toList());
			 
			 if(null == testList && testList.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			System.out.println("Exception in fetching all Online Test"+e);
			e.printStackTrace();
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(testList);	
		
		
	}
	
	
	@CrossOrigin
	@PostMapping("/aramMobileApp/getOnlineTest")
	public ResponseEntity<OnlineMobileAppTestFinalJson> getMobileOnlineTest(
			@RequestParam(value = "testId",required = false) long testId){
		
		List<OnlineTestMbAppJson> testList = new ArrayList<>();
		AimCivilsMapper mapper = new AimCivilsMapper();
		OnlineMobileAppTestFinalJson finalTestJson = new OnlineMobileAppTestFinalJson();
		
		if( 0L == testId) return ResponseEntity.noContent().build();
		try {
			List<OnlineTest> onlineList = currentAffairsService.getOnlineTest(testId);
			
			testList = onlineList.stream().map(x->mapper.convertOnlineMobileAppTestToJson(x)).collect(Collectors.toList());
			OnlineMobileAppTestResponseJson responseJson = new OnlineMobileAppTestResponseJson();

			responseJson.setDescription("");
			responseJson.setName(currentAffairsService.getLuTestName(testId));
			responseJson.setNo_of_question(testList.size());
			responseJson.setQuestions(testList);
			responseJson.setIs_answerd(0);
			finalTestJson.setStatus("200");
			finalTestJson.setResponse(responseJson);
			
			 if(null == testList && testList.isEmpty()) 
				 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			System.out.println("Exception in fetching all Online Test"+e);
			e.printStackTrace();
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(finalTestJson);	
		
		
	}
	
	
	@CrossOrigin
	@GetMapping("/getFortnight")
	public ResponseEntity<List<FortnightCompliation>> getFortnightCompilations(@RequestParam(value = "maxDate",required = false) String strMaxDate) 
			throws Exception{
		String strMinDate = null;
		List<FortnightCompliation> compilationList = new ArrayList<>();
		try {
			if(null != strMaxDate ) {
				
				LocalDate maxDate = AramUtil.stringToLocalDate(strMaxDate);
				LocalDate minDate = maxDate.minusMonths(2);
				strMinDate = AramUtil.localDateToString(minDate);
			}else {
				LocalDate maxDate = LocalDate.now();
				LocalDate minDate = maxDate.minusMonths(2);
				strMinDate = AramUtil.localDateToString(minDate);
				strMaxDate = AramUtil.localDateToString(maxDate);
			}
			
			System.out.println("Min Date :" + strMinDate + " and Max Date :" + strMaxDate);
			
			compilationList = currentAffairsService.getFornightCompilations(strMinDate, strMaxDate);
			
			if(null== compilationList || compilationList.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		}catch(Exception e) {
			
			System.out.println("Exception in fetching all FortNight Compilations "+ e);
			return ResponseEntity.notFound().build();
			
		}
		
		return ResponseEntity.ok(compilationList);
		
		
	}
	
	
	@CrossOrigin
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/uploadPrelimsQuiz")
	public ResponseEntity dataUploadQuesOp(
	        @RequestParam(value="file") MultipartFile quesExcel,
	        @RequestParam(value = "type") String strFileName,
	        @RequestParam(value = "quizDate") String date){

		String strStatus ="dataUploadQuesOp";
		String fileName = "No file" ;
		ErrorObject errObj = new ErrorObject();
		boolean isError = false;
		Date prelimsDate = null;
		List<ErrorObject> errorList = new ArrayList<>();
		
		if(null == date || date.isEmpty()) {
			errorList.add(setError("Date is Empty" , "Quiz Date"));
			isError = true;
		}
		if ( null == strFileName || strFileName.isEmpty()) {
			strFileName="prelims_quiz";
		}
		if( null == quesExcel) {
			errorList.add(setError("File is Missing" , "File"));
			isError = true;
		}
		
		
		 if(isError) {
			 new ResponseEntity(errorList, HttpStatus.OK);
		 }else {
			 errorList = null;
		 }
			try{
				
			prelimsDate = AramUtil.toDate(date);
			
			boolean isExist = currentAffairsService.prelimsQuizIfExist(prelimsDate);
			
			//if(isExist) currentAffairsService.deletePrelimsQuizUsingDate(prelimsDate);
			
			System.out.println("is Prelims Quiz exist :" + isExist);
			
			InputStream inputStream =	quesExcel.getInputStream();
			errorList = currentAffairsService.dataUploadQues(inputStream,strFileName,prelimsDate);
				
			}catch(Exception e){
				errObj.setCode("NS4001");
				errObj.setField(strStatus);
				errObj.setMessage("Failure in controller");
				errorList = new ArrayList<ErrorObject>();
				errorList.add(errObj);
				e.printStackTrace();
				
			}
			return new ResponseEntity(errorList, HttpStatus.OK);
	}
	
	private ErrorObject setError(String message , String field) {
		ErrorObject errObj = new ErrorObject();
		errObj.setCode("NS4001");
		errObj.setField(field);
		errObj.setMessage("Failure in controller");
		
		return errObj;
	}

	@CrossOrigin
	@GetMapping("/getPdf")
	@RequestMapping(method = RequestMethod.GET,
	produces = MediaType.APPLICATION_PDF_VALUE)
	public void getPdf(@RequestParam("file") String fileName, HttpServletResponse response) throws IOException {

		File fileIns = new File(AramConstants.serverPath + "/" + fileName);

		response.setContentType(MediaType.ALL_VALUE);

		InputStream in = new FileInputStream(fileIns);
		
		StreamUtils.copy(in, response.getOutputStream());
	}

}
