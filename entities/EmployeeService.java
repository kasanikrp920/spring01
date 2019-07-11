package com.trimind.restdemo1.entities;

import com.trimind.restdemo1.exception.DuplicateDataException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeService {



    Connection con = DBconnection.getCon();
    @GetMapping("employee/get")
    public  List<Employee> allEmployeesDetails(){


        List<Employee>employeeList=new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("select * from employee");
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                Employee emp=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                if(!(employeeList.contains(emp))){
                    employeeList.add(emp);
                }
            }
        }catch(Exception ep){
            ep.printStackTrace();
        }

        return employeeList;
    }

    public Employee getOneEmployee(int id){
        Employee emp=null;
        try{

            PreparedStatement ps = con.prepareStatement("select * from employee where id=?");
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                emp=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
        }catch(Exception ep){
            ep.printStackTrace();
        }


        return emp;
    }

    public Employee createEmployee(Employee employee){



        try {

            PreparedStatement ps = con.prepareStatement("insert into employee(firstname,lastname,emailid,phonenumber) values(?,?,?,?)");

            ps.setString(1,employee.getFirstName());
            ps.setString(2,employee.getLastName());
            ps.setString(3,employee.getEmailId());
            ps.setString(4,employee.getPhoneNumber());
            int a = ps.executeUpdate();
            if (a == 1) {
                System.out.println("record inserted successfully");
                return employee;
            } else {
                System.out.println("record inserted failed");
                return null;
            }
        } catch (Exception e) {
            throw new DuplicateDataException("Record already exist with this Email Id.Please try with another One");
        }

    }

    public Employee updateEmployee(Employee employee){

        int id=employee.getId();


        try{
            PreparedStatement ps = con.prepareStatement("update employee set firstname=?,lastname=?,emailid=?,phonenumber=? where id=?");
            ps.setString(1,employee.getFirstName());
            ps.setString(2,employee.getLastName());
            ps.setString(3,employee.getEmailId());
            ps.setString(4,employee.getPhoneNumber());
            ps.setInt(5,id);
            int a = ps.executeUpdate();
            if (a == 1) {
                System.out.println( "record updated  suscessfully..");
                return employee;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public int deleteEmployee(int id){
        int a;
        try {
            PreparedStatement ps = con.prepareStatement("delete from employee where id=?");
            ps.setInt(1, id);
            a = ps.executeUpdate();
            if (a == 1) {
                System.out.println("record deleted successfully..");
                return a;
            } else {
                System.out.println(" deleting record failed");
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }

    public boolean numberOrNot(String number){
        int id1=0;
        boolean isNumber=true;
        try{
            id1=Integer.parseInt(number);
        }catch (NumberFormatException e){
            isNumber=false;
        }

        return isNumber;
    }
}




