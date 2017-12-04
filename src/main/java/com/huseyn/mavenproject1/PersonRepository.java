
package com.huseyn.mavenproject1;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import oracle.jdbc.OracleDriver;

public class PersonRepository {
    
   // List<Person> persons;
    Connection conn=null;
    
    public PersonRepository(){
        
        try {
            DriverManager.registerDriver(new OracleDriver());
            
            conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","hr","hr");
               } catch (SQLException ex) {
            System.out.println("exception is "+ex);
        }
            //persons=new ArrayList<>();
            
//    Person p1=new Person();
//    p1.setName("Huseyn");
//    p1.setAge(25);
//    p1.setId(100);
//    
//    Person p2=new Person();
//    p2.setName("Huseynov");
//    p2.setAge(26);
//    p2.setId(102);
//    
//    persons.add(p1);
//    persons.add(p2);

// con=DriverManager.getConnection(url, user, password)
     
    
    }
    
    public List<Person> getPersons(){
       
        List<Person> persons=new ArrayList<>();
        String sql="select id,name,age from persons";
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
            
            Person p=new Person();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setAge(rs.getInt("age"));
            
            persons.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("exceptin is a "+ex);
        }
             
      return persons;
    }
    
    public Person getPerson(int id){
    
//        for (Person p : persons){
//        
//            if(p.getId()==id){
//            return p;
//            }
//        }

      String sql="select id,name,age from persons where id=?"+id;
      Person person=new Person();

        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
            
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setAge(rs.getInt("age"));
            }

        } catch (SQLException ex) {
            System.out.println("exceptin is a "+ex);
        }
        return person;
    }

    public void create(Person person) {

        String sql="insert into persons (id,name,age) values(?,?,?)";
        
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, person.getId());
            ps.setString(2, person.getName());
            ps.setInt(3, person.getAge());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("exception is "+ex);
        }
        
    }
    
    public void update(Person person){
        
    String sql="update persons set id=?,name=?,age=?";
    
    
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, person.getId());
            ps.setString(2, person.getName());
            ps.setInt(3, person.getAge());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }    
    }
    
    public void delete (int id){
    
            String sql="delete from persons where id=?";
    
    
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
    
    }
    
}
