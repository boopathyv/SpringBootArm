package com.aram.connect.controller;



import java.io.File;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.Region;

@RestController
@RequestMapping("/content/*")
public class ContentManagementController {

	@CrossOrigin
	@PostMapping("/uploadImage")
	public boolean storeImageSlides(
			//@RequestParam("image") MultipartFile mfile
			) {
		
		AWSCredentials credentials = new BasicAWSCredentials(
				  "", 
				  ""
				);
		
		AmazonS3 s3client = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(Regions.AP_SOUTH_1)
				  .build();
		
		List<Bucket> buckets = s3client.listBuckets();
		for(Bucket bucket : buckets) {
		    System.out.println(bucket.getName());
		}
		
		s3client.putObject(
				  "aram-images", 
				  "images/SimlingStudent.jpg", 
				  new File("C:\\Users\\rvelappan\\Downloads\\smilingStudent.jpg")
				);
		
		return true;
	}
	
	public void sendSms() {
		
		
	}
	
	public static void main(String[] arg) {
		ContentManagementController contr = new ContentManagementController();
		
		contr.storeImageSlides();
	}
}
