package com.aram.connect.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aram.connect.AramConstants;
import com.aram.connect.Exception.FileStorageException;
import com.aram.connect.dataObjects.CurrentAffairsJson;
import com.aram.connect.dataObjects.ErrorObject;
import com.aram.connect.dataObjects.FortnightCompliation;
import com.aram.connect.dataObjects.OnlineTest;
import com.aram.connect.dataObjects.mapper.AimCivilsMapper;
import com.aram.connect.persistence.AimCivilsService;
import com.aram.connect.persistence.dao.FortnightCompRepository;
import com.aram.connect.persistence.dao.LuTestName;
import com.aram.connect.persistence.dao.MainsCivilServiceRepository;
import com.aram.connect.persistence.dao.MainsCurrentAffairs;
import com.aram.connect.persistence.dao.MainsQuiz;
import com.aram.connect.persistence.dao.MainsQuizRepository;
import com.aram.connect.persistence.dao.OnlineTestRepository;
import com.aram.connect.persistence.dao.PrelimsCivilServiceRepository;
import com.aram.connect.persistence.dao.PrelimsCurrentAffairs;
import com.aram.connect.persistence.dao.PrelimsQuiz;
import com.aram.connect.persistence.dao.PrelimsQuizRepository;
import com.aram.connect.persistence.dao.TestAttemptDetail;
import com.aram.connect.service.CurrentAffairsService;
import com.aram.connect.util.AramUtil;

@Service
@Transactional
public class CurrentAffairsImpl implements CurrentAffairsService {

	Path fileStorageLocation = Paths.get("");

	String fileDate = "";

	@Autowired
	AimCivilsService aimCivilsService;

	@Autowired
	MainsCivilServiceRepository mainsCivilServiceRepository;

	@Autowired
	PrelimsCivilServiceRepository prelimsCivilServiceRepository;

	@Autowired
	MainsQuizRepository mainsQuizRepository;

	@Autowired
	PrelimsQuizRepository prelimsQuizRepository;

	@Autowired
	FortnightCompRepository fortnightRepo;

	@Autowired
	OnlineTestRepository onlineTestRepo;

	@PersistenceContext
	private EntityManager em;

	@Override
	public String storeCurrentAffairsFile(MultipartFile file, CurrentAffairsJson cAffairs) throws FileStorageException {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		System.out.println("original fileName = " + fileName);
		String directory = createDirectories(fileName);

		if ("FileName Doesn't Match".equalsIgnoreCase(directory))
			return "FileName Doesn't Match";
		cAffairs.setFileDate(getFileDate(fileName));
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			System.out.println("directory = " + directory);

			// File fileIns = new File(AramConstants.serverPath + directory + "/" + fileName);
			// if (fileIns.exists()) {
				System.out.println("exist");
				this.fileStorageLocation = Paths.get(AramConstants.serverPath + directory);
				// Copy file to the target location (Replacing existing file with the same name)

				Path targetLocation = this.fileStorageLocation.resolve(fileName);

				// Files.copy(file.getInputStream(), this.fileStorageLocation,
				// StandardCopyOption.REPLACE_EXISTING);
				Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
				File fileloc = targetLocation.toFile();
				fileloc.setExecutable(true, false);
				fileloc.setReadable(true, false);
				fileloc.setWritable(true, false);
				return directory + "/" + fileName;
			// }

			// fileIns.setWritable(true);
			// File direc = new File(AramConstants.serverPath + "/" + directory);
			// System.out.println("ab = " + direc.getAbsolutePath());
			// Boolean isDirec = direc.exists();
			// if (!isDirec) {
			// 	System.out.println("No directory");
			// 	try {
			// 		direc.mkdirs();
			// 		isDirec = true;
			// 	}

			// 	catch(SecurityException e) {
			// 		throw new SecurityException("Security exception " + fileName + ". Please try again!", e);
			// 		// System.out.println("Security exception = " + e);
			// 	}
			// }

			// if(isDirec) {
			// 	File fileToSaveInFolder = new File(direc, fileName);
			// 	fileToSaveInFolder.createNewFile();
			// 	FileOutputStream fo = new FileOutputStream(fileToSaveInFolder);
			// 	fo.write(file.getBytes());
			// 	fo.close();	
			// }
			// return targetLocation.toAbsolutePath().toString();

			// Boolean success = fileIns.createNewFile();
			// if(success) {
			// System.out.println("created new file");
			// }

		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}

	}

	public String createDirectories(String fileName) {

		String directory = "";
		if (fileName.startsWith(AramConstants.PRELIMS) || fileName.startsWith(AramConstants.PRELIMS_QUIZ)
				|| fileName.startsWith(AramConstants.MAINS) || fileName.startsWith(AramConstants.MAINS_QUIZ)
				|| fileName.startsWith(AramConstants.FORTNIGHT)) {
			directory = getDirectory(fileName);

		}

		if (!("".equals(directory) || "FileName doesn't Match".equals(directory))) {
			File dirPublic = new File(AramConstants.serverPath + "/" + directory);
			dirPublic.setWritable(true);
			Boolean success = dirPublic.mkdirs();
			if (success) {
				System.out.println("directory created");
				dirPublic.getParentFile().setWritable(true, false);
				dirPublic.setReadable(true, false);
				dirPublic.setExecutable(true, false);
			}
		}

		return directory;
	}

	@Override
	public Path getCurrentAffairs(String fileName) {

		String directory = getDirectory(fileName);

		Path filePath = null;

		if (!("".equals(directory) || "FileName doesn't Match".equals(directory))) {
			filePath = Paths.get(directory, fileName);
		}

		return filePath;
	}

	private String getDirectory(String fileName) {

		String directory = "";

		String[] fileArray = fileName.split("-");

		if (!(fileArray.length == 4)) {
			directory = "FileName doesn't Match";
		} else {

			int year = Integer.parseInt(fileArray[1]);
			int month = Integer.parseInt(fileArray[2]);
			int date = Integer.parseInt(fileArray[3].replaceAll("[^0-9]", ""));

			if (date <= 31 && month <= 12 && year > 2017) {
				directory = "/" + fileArray[0] + "/" + fileArray[1] + "/" + fileArray[2];
			} else {
				directory = "FileName doesn't Match";
			}
		}

		return directory;

	}

	private String getFileDate(String fileName) {

		String directory = "";

		String[] fileArray = fileName.split("-");

		if (!(fileArray.length == 4)) {
			directory = "FileName doesn't Match";
		} else {

			int year = Integer.parseInt(fileArray[1]);
			int month = Integer.parseInt(fileArray[2]);
			int date = Integer.parseInt(fileArray[3].replaceAll("[^0-9]", ""));

			if (date <= 31 && month <= 12 && year > 2017) {
				directory = month + "/" + date + "/" + year;
			} else {
				directory = "FileName doesn't Match";
			}
		}

		return directory;

	}

	@Override
	public String storeCurrentAffairs(CurrentAffairsJson cAffairs) {
		// TODO Auto-generated method stub

		String fileName = storeCurrentAffairsFile(cAffairs.getFile(), cAffairs);
		AimCivilsMapper mapper = new AimCivilsMapper();
		String type = cAffairs.getType();
		if (!fileName.startsWith("/"))
			return "Failure";

		if (AramConstants.PRELIMS.equalsIgnoreCase(type)) {
			PrelimsCurrentAffairs pcAffairs = mapper.convertJsonToPrelimsCA(cAffairs, fileName);
			aimCivilsService.mergePrelimsCurrentAffairs(pcAffairs);
		} else if (AramConstants.MAINS.equalsIgnoreCase(type)) {
			MainsCurrentAffairs mcAffairs = mapper.convertJsonToMainsCA(cAffairs, fileName);
			aimCivilsService.mergeMainsCurrentAffairs(mcAffairs);
		} else if ((AramConstants.MAINS_QUIZ.equalsIgnoreCase(type))) {
			MainsQuiz mQuiz = mapper.convertJsonToMainsQuiz(cAffairs, fileName);
			aimCivilsService.mergeMainsQuiz(mQuiz);

		} else if ((AramConstants.FORTNIGHT.equalsIgnoreCase(type))) {
			FortnightCompliation forNight = mapper.convertFortnightIntoJson(cAffairs, fileName);
			aimCivilsService.mergeFortnight(forNight);

		} else {
			return "Failure";
		}

		return null;
	}

	@Override
	public List<MainsCurrentAffairs> getAllMainsCurrentAffairs(String strMinDate, String strMaxDate) throws Exception {
		// TODO Auto-generated method stub
		List<MainsCurrentAffairs> mainsCurrentAffairs = null;
		try {
			if (null != strMinDate || null != strMaxDate) {

				mainsCurrentAffairs = mainsCivilServiceRepository.findMainsCurrentAffaris(strMinDate, strMaxDate);
			} else {

				LocalDate minDate = LocalDate.now().minusDays(3);
				LocalDate maxDate = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				String minString = minDate.format(formatter);
				String maxString = maxDate.format(formatter);

				System.out.println("minDate--------" + minString);
				System.out.println("maxDate--------" + maxString);

				mainsCurrentAffairs = mainsCivilServiceRepository.findMainsCurrentAffaris(minString, maxString);
			}
		} catch (Exception e) {
			throw new Exception("Exception in getAllMainsCurrentAffairs service" + e);
		}
		return mainsCurrentAffairs;
	}

	@Override
	public List<PrelimsCurrentAffairs> getAllPrelimsCurrentAffairs(String strMinDate, String strMaxDate)
			throws Exception {
		// TODO Auto-generated method stub
		List<PrelimsCurrentAffairs> prelimsCurrentAffairs = null;
		try {
			if (null != strMinDate || null != strMaxDate) {

				prelimsCurrentAffairs = prelimsCivilServiceRepository.findPerlimsCurrentAffaris(strMinDate, strMaxDate);
			} else {

				LocalDate minDate = LocalDate.now().minusDays(3);
				LocalDate maxDate = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				String minString = minDate.format(formatter);
				String maxString = maxDate.format(formatter);

				System.out.println("minDate--------" + minString);
				System.out.println("maxDate--------" + maxString);

				prelimsCurrentAffairs = prelimsCivilServiceRepository.findPerlimsCurrentAffaris(minString, maxString);
			}
		} catch (Exception e) {
			throw new Exception("Exception in getAllPrelimsCurrentAffairs service" + e);
		}
		return prelimsCurrentAffairs;
	}

	@Override
	public List<MainsQuiz> getAllMainsQuiz(String strMinDate, String strMaxDate) throws Exception {
		// TODO Auto-generated method stub
		List<MainsQuiz> mainsQuiz = null;
		try {
			if (null != strMinDate || null != strMaxDate) {
				mainsQuiz = mainsQuizRepository.findMainsQuiz(strMinDate, strMaxDate);

			} else {

				LocalDate minDate = LocalDate.now().minusDays(3);
				LocalDate maxDate = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				String minString = minDate.format(formatter);
				String maxString = maxDate.format(formatter);

				System.out.println("minDate--------" + minString);
				System.out.println("maxDate--------" + maxString);

				mainsQuiz = mainsQuizRepository.findMainsQuiz(minString, maxString);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception in mainsQuiz service" + e);
		}
		return mainsQuiz;
	}

	@Override
	public List<PrelimsQuiz> getPrelimsQuiz(String strMaxDate) throws Exception {
		// TODO Auto-generated method stub
		List<PrelimsQuiz> prelimsQuiz = new ArrayList<>();
		try {
			if (null != strMaxDate) {
				prelimsQuiz = prelimsQuizRepository.findPerlimsQuiz(strMaxDate);

			} else {

				LocalDate minDate = LocalDate.now().minusDays(3);
				LocalDate maxDate = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				String minString = minDate.format(formatter);
				String maxString = maxDate.format(formatter);

				System.out.println("minDate--------" + minString);
				System.out.println("maxDate--------" + maxString);

				while (prelimsQuiz.isEmpty()) {
					prelimsQuiz = prelimsQuizRepository.findPerlimsQuiz(maxString);
					maxDate = maxDate.minusDays(1);
					maxString = maxDate.format(formatter);
				}

			}
		} catch (Exception e) {
			throw new Exception("Exception in getPrelims service" + e);
		}
		return prelimsQuiz;
	}

	@Override
	public List<ErrorObject> dataUploadQues(InputStream inputStream, String strFileName, Date prelimsDate)
			throws Exception {
		// TODO Auto-generated method stub

		String headers = getHeaders(strFileName);
		ErrorObject errObj = new ErrorObject();
		List<ErrorObject> errorList = null;

		// Not an empty call will use in next sprint

		List<PrelimsQuiz> commonModel = readAddExam(inputStream, strFileName, headers);

		if (!commonModel.isEmpty()) {
			for (PrelimsQuiz tdwQuestion : commonModel) {
				if (null == tdwQuestion.getPrelimsQuestion() || "".equals(tdwQuestion.getPrelimsQuestion()))
					continue;
				tdwQuestion.setPrelimsQuizDate(prelimsDate);
				tdwQuestion.setPrelimsQuestion(addNewLineToString(tdwQuestion.getPrelimsQuestion()));
				tdwQuestion.setExplanation(addNewLineToString(tdwQuestion.getExplanation()));
				saveObjectPrelims(tdwQuestion);
			}

		} else {
			System.out.println(" Common Model is empty");
		}

		return errorList;

	}

	public String addNewLineToString(String ques) {
		if (null == ques)
			return null;

		String newLine = ques.replaceAll("\n", "\n\r");

		return newLine;
	}

	/**
	 * DAO IMPL method to read File
	 * 
	 * @return ArrayList<CommonModel>
	 * @throws Exception
	 */

	public List<PrelimsQuiz> readAddExam(InputStream myInput, String fileName, String headers) throws Exception {

		String headerMissMatch = "";
		String strEmptyRow = "";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		ArrayList<PrelimsQuiz> lstQuestion = new ArrayList<PrelimsQuiz>();

		ArrayList<ArrayList<String>> finalArrayListString = new ArrayList<ArrayList<String>>();

		if (fileName.equalsIgnoreCase("prelims_quiz")) {

			XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
			boolean headersMatch = false;
			ArrayList<String> tempList = new ArrayList<String>();
			String[] headersarray = headers.toUpperCase().split(",");
			int length = headersarray.length;
			XSSFSheet mySheet = null;
			mySheet = myWorkBook.getSheetAt(0);
			int emptyCheck = mySheet.getLastRowNum();
			if (emptyCheck > 12) {
				emptyCheck = 11;
			}
			if (mySheet != null) {
				for (int row = 0; row <= emptyCheck; row++) {
					if (null == mySheet.getRow(row))
						continue;
					XSSFRow myRow = (XSSFRow) mySheet.getRow(row);
					if (row == 0) {
						for (int col = 0; col < length; col++) {
							XSSFCell cell = myRow.getCell((short) col);
							if (cell != null) {
								String temprow = cell.getStringCellValue().toUpperCase();
								tempList.add(temprow);
							} else {
								tempList.add(null);
							}
						}
						String[] stockArr = new String[tempList.size()];
						stockArr = tempList.toArray(stockArr);
						if (Arrays.equals(headersarray, stockArr)) {
							headersMatch = true;
						}
					}
					if (headersMatch != false) {
						if (emptyCheck == 0) {
							strEmptyRow = AramConstants.EMPTY_FILE_ERROR;
						} else {
							if (row != 0) {
								if (null == myRow.getCell(0))
									break;
								ArrayList<String> listBuffer = new ArrayList<String>();
								for (int col = 0; col < length; col++) {
									Cell cell = myRow.getCell(col, XSSFRow.CREATE_NULL_AS_BLANK);
									if (cell != null) {

										String temp = "";
										cell.setCellType(Cell.CELL_TYPE_STRING);

										temp = cell.getRichStringCellValue().getString();

										if (temp.equalsIgnoreCase("")) {
											temp = "";
										} else {

											temp = temp.replaceAll("&nbsp", "");

										}

										listBuffer.add(temp);

									} else {
										listBuffer.add("");
									}
								}
								finalArrayListString.add(listBuffer);
								PrelimsQuiz objQuestion = new PrelimsQuiz();
								objQuestion.setPrelimsQuestion(listBuffer.get(0));
								objQuestion.setAnswer(listBuffer.get(6));
								objQuestion.setExplanation(listBuffer.get(7));
								objQuestion.setCreatedBy("Admin");
								objQuestion.setCreatedDate(timestamp);

								objQuestion.setOptionA(listBuffer.get(1));
								objQuestion.setOptionB(listBuffer.get(2));
								objQuestion.setOptionC(listBuffer.get(3));
								objQuestion.setOptionD(listBuffer.get(4));
								objQuestion.setOptionE(listBuffer.get(5));
								objQuestion.setCreatedBy("Admin");
								objQuestion.setCreatedDate(timestamp);
								lstQuestion.add(objQuestion);

							}
						}
					} else {
						headerMissMatch = AramConstants.HEADER_ERROR;
					}

				}
			}

		}

		return lstQuestion;
	}

	/**
	 * DAO IMPL method to save
	 * 
	 * @return String
	 * @throws Exception
	 */

	@Transactional(propagation = Propagation.REQUIRED)
	public String saveObjectPrelims(PrelimsQuiz obj) {
		String retVal = "Data uploaded successfully";
		Session objSession = (Session) em.getDelegate();
		objSession.setCacheMode(CacheMode.IGNORE);
		objSession.setFlushMode(FlushMode.COMMIT);
		try {

			try {
				objSession.persist(obj);
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Please contact support team";

			}
			objSession.flush();
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "Please contact support team";
		}
		return retVal;
	}

	/**
	 * DAO IMPL method to get Header
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String getHeaders(String fileName) throws Exception {

		String headers = "";
		Session objSession = (Session) em.getDelegate();
		Query headerQuery = objSession
				.createSQLQuery("Select cast(headers as char(20000)) from aram_file_header where fileName= :fileName");
		headerQuery.setParameter("fileName", fileName);
		ArrayList<String> retVal = (ArrayList<String>) headerQuery.list();
		for (String string : retVal) {
			headers = string;
		}
		return headers;

	}

	@Override
	public boolean prelimsQuizIfExist(Date prelimsQuizDate) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<PrelimsQuiz> query = em.createNamedQuery("PrelimsQuiz.getUsingDate", PrelimsQuiz.class);
		query.setParameter("prelimsQuizDate", prelimsQuizDate);
		long count = query.getResultList().stream().count();

		if (count == 0)
			return false;

		for (PrelimsQuiz pq : query.getResultList()) {
			em.remove(pq);
		}

		em.flush();

		return true;
	}

	@Transactional
	@Override
	public boolean deletePrelimsQuizUsingDate(Date prelimsQuizDate) throws Exception {

		String quizDate = AramUtil.dateToString(prelimsQuizDate, "yyyy-MM-dd");

		javax.persistence.Query query1 = em
				.createNativeQuery("DELETE from prelims_quiz  where prelims_quiz_date =" + quizDate);
		int update = query1.executeUpdate();

		System.out.println(" Delete existing prelims quiz :" + update);

		return true;

	}

	@Override
	public List<FortnightCompliation> getFornightCompilations(String strMinDate, String strMaxDate) throws Exception {
		// TODO Auto-generated method stub

		List<FortnightCompliation> fortNightList = fortnightRepo.getSomeFortnightCompliations(strMinDate, strMaxDate);

		List<FortnightCompliation> threeFortList = new ArrayList<>();

		if (null != fortNightList && fortNightList.size() > 3) {

			threeFortList = fortNightList.stream().limit(3).collect(Collectors.toList());
		} else {
			threeFortList = fortNightList;
		}

		return threeFortList;
	}

	@Override
	public List<OnlineTest> getOnlineTest(long testId) throws Exception {

		List<OnlineTest> testList = onlineTestRepo.getTestById(testId);

		return testList;
	}

	@Override
	public String getLuTestName(long testId) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<LuTestName> query = em.createNamedQuery("LuTestName.findTestid", LuTestName.class);
		query.setParameter("tId", testId);
		List<LuTestName> luTestNameList = query.getResultList();
		String testName = "";
		for (LuTestName luTestName : luTestNameList) {
			testName = luTestName.getTestName();

		}
		return testName;
	}

}
