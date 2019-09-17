package com.aram.connect.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aram.connect.dataObjects.StudentResponse;
import com.aram.connect.persistence.dao.Student;
import com.aram.connect.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student/*")
public class StudentController  {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    @CrossOrigin
    public ResponseEntity<Map> getStudents() {

        Map<String, List<Student>> response = new HashMap<String, List<Student>>();
        response.put("students", userService.getStudents());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/list/active")
    @CrossOrigin
    public ResponseEntity<StudentResponse> getActiveStudents(@RequestParam String from, @RequestParam String count, 
        @RequestParam String searchTxt, @RequestParam Integer reqCount, @RequestParam Boolean registeredWithUPSC){

        StudentResponse response = new StudentResponse();
        response.reqCount = reqCount;
        if(searchTxt == "") {
            response.students = userService.activeOffsetList(Integer.parseInt(from), Integer.parseInt(count), registeredWithUPSC);
            response.count = userService.countActiveList(registeredWithUPSC);
        } else {
            response.students = userService.searchWithTxtActiveOffsetList(searchTxt, Integer.parseInt(from), Integer.parseInt(count), registeredWithUPSC);
            response.count = userService.countOfSearchWithTxtActive(searchTxt, registeredWithUPSC);

        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/list/inActive")
    @CrossOrigin
    public ResponseEntity<StudentResponse> getInActiveStudents(@RequestParam String from, @RequestParam String count, 
        @RequestParam String searchTxt, @RequestParam Integer reqCount, @RequestParam Boolean registeredWithUPSC) {

        StudentResponse response = new StudentResponse();
        response.reqCount = reqCount;
        if(searchTxt == "") {
            response.students = userService.inActiveOffsetList(Integer.parseInt(from), Integer.parseInt(count), registeredWithUPSC);
            response.count = userService.countInActiveList(registeredWithUPSC);
        } else {
            response.students = userService.searchWithTxtInActiveOffsetList(searchTxt, Integer.parseInt(from), Integer.parseInt(count), registeredWithUPSC);
            response.count = userService.countOfSearchWithTxtInActive(searchTxt, registeredWithUPSC);

        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{studentId}/activate")
    @CrossOrigin
    public ResponseEntity<Map> activateStudent(@PathVariable String studentId) {

        Map<String, String> response = new HashMap<String, String>();

        Student student = userService.getStudent(Integer.parseInt(studentId));

        if(student == null) {
            response.put("error", "student is not found");
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
        }

        student.setActiveStatus(true);
        userService.createStudent(student);

        response.put("message", "student activated successfully");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{studentId}/inActivate")
    @CrossOrigin
    public ResponseEntity<Map> inActivateStudent(@PathVariable String studentId) {

        Map<String, String> response = new HashMap<String, String>();

        Student student = userService.getStudent(Integer.parseInt(studentId));

        if(student == null) {
            response.put("error", "student is not found");
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
        }

        student.setActiveStatus(false);
        userService.createStudent(student);

        response.put("message", "student inactivated successfully");

        return ResponseEntity.ok(response);
    }

}