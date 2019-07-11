package com.trimind.restdemo1.entities;

import com.trimind.restdemo1.exception.DuplicateDataException;
import com.trimind.restdemo1.exception.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;




    public List<Employee> getAllEmployees(){
        List<Employee>employeeList=new ArrayList<>();
        employeeList=jdbcTemplate.query("select * from employee",
                new BeanPropertyRowMapper<>(Employee.class));
        return employeeList;
    }

    public Employee getOneEmployee(int id){

        List<Employee>employeeList=new ArrayList<>();
        employeeList=jdbcTemplate.query("select * from employee where id=?",
                new Object[]{id},new BeanPropertyRowMapper(Employee.class));

        if( !employeeList.isEmpty()){

            Employee employee=employeeList.get(0) ;
            return employee;

        }else{
             throw new UserNotFound("data with this " + id + " id  is not found");
        }
    }

    public int createEmployee(Employee employee) {
        try {
            int k = jdbcTemplate.update("insert into employee(firstname,lastname,emailid,phonenumber) values('"+employee.getFirstName()+"','"+employee.getLastName()+"','"+employee.getEmailId()+"','"+employee.getPhoneNumber()+"')");


            System.out.println(k);
            System.out.println("employee details are added to the database..");
            if (k == 1) {
                return k;
            }
        } catch (Exception e) {
            throw new DuplicateDataException("Record already exist with this Email Id.Please try with another One");

        }
        return 0;
    }

    public Employee updateEmployee(Employee employee){

        int k=jdbcTemplate.update("update employee set firstname='"+employee.getFirstName()+"',lastname='"+employee.getLastName()+"',emailid='"+employee.getEmailId()+"',phonenumber='"+employee.getPhoneNumber()+"' where id='"+employee.getId()+"'");
        if(k==1){
            return employee;
        }
        return null;
    }

    public int  deleteEmployee(int id){

        int k =jdbcTemplate.update("delete from  employee where id='"+id+"'");

        if(k==1){
            return k;
        }

        return 0;

    }



}
