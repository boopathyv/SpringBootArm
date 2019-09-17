package com.aram.connect.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aram.connect.dataObjects.LatestNews;
import com.aram.connect.dataObjects.UserLogin;
import com.aram.connect.service.LatestNewsService;
import com.aram.connect.service.UserService;

@RestController
@RequestMapping("/latestNews/*")
public class LastestNewsController {
	
	@Autowired
	LatestNewsService latestNewsService;

	@SuppressWarnings("unchecked")
	@GetMapping("/getAll")
	@CrossOrigin
	public List<LatestNews> getAllLatestNews() {
		
		
		return latestNewsService.getAllNews();
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getActive")
	@CrossOrigin
	public List<LatestNews> getActiveLatestNews() {
		return latestNewsService.getActiveNews();
	}

	@CrossOrigin
	@PostMapping("/save")
	public ResponseEntity<Map> save(@RequestBody LatestNews latestNews) {

		System.out.println(latestNews);

		latestNewsService.saveLatestNews(latestNews);
		ResponseEntity.status(200);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Latest News saved successfully");
		return ResponseEntity.ok(response);
	}

	@CrossOrigin
	@PutMapping("/update")
	public ResponseEntity<Map> update(@RequestBody LatestNews latestNews) {
		latestNewsService.updateLatestNews(latestNews);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Latest News updated successfully");
		return ResponseEntity.ok(response);
	}

	@CrossOrigin
	@PutMapping("/activateLatestNews/{id}")
	public ResponseEntity<Map> activateLatestNews(@PathVariable String id) {
		latestNewsService.activateLatestNews(Long.parseLong(id));
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Latest News activates successfully");
		return ResponseEntity.ok(response);
	}

	@CrossOrigin
	@PutMapping("/inActivateLatestNews/{id}")
	public ResponseEntity<Map> inActivateLatestNews(@PathVariable String id) {
		latestNewsService.inActivateLatestNews(Long.parseLong(id));
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Latest News insactivates successfully");
		return ResponseEntity.ok(response);
	}
}
