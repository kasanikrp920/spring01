package com.trimind.restdemo1.controller;

import com.trimind.restdemo1.entities.Employee;
import com.trimind.restdemo1.entities.EmployeeRepository;
import com.trimind.restdemo1.entities.EmployeeService;
import com.trimind.restdemo1.exception.InvalidFormatException;
import com.trimind.restdemo1.exception.UserNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController()
public class EmployeeRestController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public EmployeeService employeeService;

    @Autowired
    public EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getEmployee() {
        List<Employee> employeeList = new ArrayList<>();

        log.info("getting all Employees values");
        employeeList = employeeRepository.getAllEmployees();
        if (!(employeeList.isEmpty() == true)) {

            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.add("Description","You have gotten all employees details");

            return new ResponseEntity<>(employeeList,httpHeaders, HttpStatus.OK);
        }


        return null;
    }

    @GetMapping("employee/{id}")
    public Employee getEmployee(@PathVariable String id) {


        boolean isNumber = employeeService.numberOrNot(id);

        if (isNumber == true) {
            Employee emp = employeeRepository.getOneEmployee(Integer.parseInt(id));

            if (emp == null) {
                log.warn("data with this id " + id + " is not found");
                throw new UserNotFound("data with this " + id + " id  is not found");
            }
            return emp;
        } else {
            throw new InvalidFormatException("you have given wrong format for id " + id);
        }

    }


    @PostMapping("employee")
    public ResponseEntity<Employee> createResource(@Valid @RequestBody Employee employee) {

       /*log.debug("start of creating a resource method");
        System.out.println(employee.toString());
        Employee emp=employeeService.createEmployee(employee);
        if(emp!=null){
            return new ResponseEntity<>(emp,HttpStatus.CREATED);
        }

        log.debug("End  of creating a resource method");
        return null;*/

        int status = employeeRepository.createEmployee(employee);
        if (status == 1) {
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        }
        return null;
    }

    @PutMapping("/employee")
    public Employee updateResource(@Valid @RequestBody Employee employee) {
        int id = employee.getId();
        Employee emp = employeeRepository.updateEmployee(employee);
        if (emp != null) {
            return emp;
        } else {
            throw new UserNotFound("data with this " + id + " id  is not found");

        }

    }


    @DeleteMapping("/employee/{id}")
    public String deleteResource(@PathVariable String id) {


        boolean isNumber = employeeService.numberOrNot(id);

        if (isNumber == true) {
            int status = employeeService.deleteEmployee(Integer.parseInt(id));
            if (status == 0) {
                throw new UserNotFound("data with this " + id + " id  is not found");
            } else {
                return "record deleted successfully";
            }

        } else {
            throw new InvalidFormatException("you have given wrong format for id " + id);
        }

    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("id")String id,@RequestParam("file")  MultipartFile file)throws IOException {
        long size=file.getSize();
           if (id.equals("")&&size==0) {
               return "please give file and id";
           }else{
               BufferedOutputStream outputStream = new BufferedOutputStream(
                       new FileOutputStream(
                               new File("C:\\Users\\raghu\\Desktop\\uploads\\images\\"+id+".jpg")));
               outputStream.write(file.getBytes());
               outputStream.flush();
               outputStream.close();

           }
           return "file uploaded successfully..";
    }

    @PostMapping("/upload/files")
    public String uploadFiles(@RequestParam("id") String id,@RequestParam("files")MultipartFile[] files)throws IOException
    {
        if (files.length!= 0 &&!id.equals("")) {
        for(int i=0;i<files.length;i++){
            MultipartFile file=files[i];
                File getFolder= new File("C:\\Users\\raghu\\Desktop\\uploads\\"+id);
                boolean isCreated=getFolder.mkdir();
                if(isCreated==true){
                    System.out.println(getFolder.getAbsolutePath());
                }else{
                    System.out.println("file not created");
                }

                BufferedOutputStream outputStream = new BufferedOutputStream(
                        new FileOutputStream(getFolder.getAbsolutePath()+"\\"+file.getOriginalFilename()));
                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();
            }
            return "file uploaded successfully....";
        }else {
            System.out.println("give id");

            return "please give file and id";
        }
    }

    @GetMapping("/headers")
    public ResponseEntity<String> usingResponseEntityBuilderAndHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Baeldung-Example-Header",
                "Value-ResponseEntityBuilderWithHttpHeaders");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("Response with header using ResponseEntity");
    }

}

