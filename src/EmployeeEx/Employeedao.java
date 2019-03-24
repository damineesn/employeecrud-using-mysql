package EmployeeEx;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class Employeedao implements Interface{

          Connection cn;

          PreparedStatement ps,cd,nm,th;

          ResultSet rs;                                

          public Employeedao() {

             try {

                     Class.forName("com.mysql.jdbc.Driver");

                     cn = DriverManager.getConnection ("jdbc:mysql://localhost/EmployeeDetails?autoReconnect=true&useSSL=false", "root", "pass@word1");

                   System.out.println("Database Connected..");

                   }catch(Exception e) {

                   System.out.println("Database not Connected.." + e);

                   }

          }        //       End of Constuctor

          @Override
       public synchronized void importdetails( int id,String name, int age, String department, int salary)
       {
    	   try {

               ps = cn.prepareStatement("insert into employeedetails values(?,?,?,?,?)");

               ps.setInt(1, id);

               ps.setString(2, name);

               ps.setInt(3, age);

               ps.setString(4, department);

               ps.setInt(5, salary);

               ps.executeUpdate();

      }catch(Exception e) {

               System.out.print("\n Unable to import..\n" + e);

      }
       }


@Override
public void insert(String name, int age, String department, int salary) {

          try {

                   ps = cn.prepareStatement("insert into employeedetails(name,age,department,salary) values(?,?,?,?)");

                  

                   ps.setString(1, name);

                   ps.setInt(2, age);

                   ps.setString(3, department);

                   ps.setInt(4, salary);

                   ps.executeUpdate();

                   System.out.println("1 Record inserted...");

          }catch(Exception e) {

                   System.out.print("\n Unable to insert..\n" + e);

          }

          }        // end of insert

@Override
public void displayemployee(int id)
{
	try
	{     
		 ps=cn.prepareStatement("select * from employeedetails where id=?");
		 ps.setInt(1, id);
		 rs = ps.executeQuery();
		  while(rs.next()) {
			  	
              System.out.println( rs.getInt(1) + "\t " + rs.getString(2) + "\t " + rs.getInt(3) + "\t " + rs.getString(4) +  "\t " + rs.getInt(5));

    }   
	}
	catch(Exception e)
	{
		System.out.println("no results found");
	}
}
@Override
 public void statistics()
 {
	 try
	 {
		 int count=0,depn=0;
	 ps=cn.prepareStatement("select * from employeedetails where age>20");
	 rs = ps.executeQuery();
     System.out.println("The number of employees whose age is greater than 20:");
	 while(rs.next()) {
            count++;
      }
	 System.out.println(count);
	 ps=cn.prepareStatement("select id from employeedetails where age>20");
	 rs = ps.executeQuery();
	   System.out.println("employees id whose age is greater than 20:");
	 while(rs.next()) {

         System.out.println( rs.getInt(1)+"\t");

      }
	 
	 System.out.println("Number of employees in a specific department:");
	 cd=cn.prepareStatement("SELECT department, COUNT(*) FROM employeedetails  GROUP BY  department ");
	 rs = cd.executeQuery();
	 while(rs.next())
	 {
		 System.out.println( rs.getString(1)+"\t"+rs.getInt(2));
	 }
	 
	 //groupby department and orderby department
	 System.out.println("Number of employees in a specific department in order:");
	 nm=cn.prepareStatement("SELECT department, COUNT(*) FROM employeedetails  GROUP BY  department  ORDER BY department");
	 rs = nm.executeQuery();
	 while(rs.next())
	 {
		 System.out.println( rs.getString(1)+"\t"+rs.getInt(2));
	 }
	 
	 System.out.println("the average salary in specific department");
	 
	 ps=cn.prepareStatement("SELECT department,avg(salary) FROM employeedetails GROUP BY department");
	 rs = ps.executeQuery();
	 while(rs.next())
	 {
		 System.out.println( rs.getString(1)+"\t"+rs.getInt(2));
	 }
	 
	 
 System.out.println("the Total salary in specific department");
	 
	 ps=cn.prepareStatement("SELECT department,sum(salary) FROM employeedetails GROUP BY department");
	 rs = ps.executeQuery();
	 while(rs.next())
	 {
		 System.out.println( rs.getString(1)+"\t"+rs.getInt(2));
	 }
	 
	 System.out.println("the employees whose name start with D");
	 ps=cn.prepareStatement("SELECT id, name FROM employeedetails WHERE name LIKE 'D%'");
	 rs = ps.executeQuery();
	 while(rs.next())
	 {
		 System.out.println(rs.getInt(1)+"\t"+ rs.getString(2));
	 }
	 
	 System.out.println("Department more than three employees");
	 
	 ps=cn.prepareStatement("SELECT department FROM employeedetails GROUP BY department HAVING count(department)>3");
	 rs = ps.executeQuery();
	 while(rs.next())
	 {
		 System.out.println( rs.getString(1)+""+rs.getString(2));
	 }
	 
	 
	 
	 
	 //System.out.println(depn);
	 }
	 catch(Exception e)
	 {
		 System.out.println("No details found");
	 }
 }
@Override
public void updateX(int id, String name, int age,  String department, int salary) {

          try {

                   ps = cn.prepareStatement("update employeedetails set name=?, age=?, department=?, salary=? WHERE id=?");

                   ps.setString(1, name);

                   ps.setInt(2, age);

                   ps.setString(3, department);

                   ps.setInt(4, salary);

                   ps.setInt(5, id);

                   ps.executeUpdate();

                   System.out.println("Record Update...");

          }catch(Exception e) {

                   System.out.print("\n Unable to Update..\n" + e);

          }

          }        // end of insert
Employee emp=new Employee(0, null, 0, null, 0);
Map<Integer,Employee> empp=new TreeMap<>();
 public synchronized Map<Integer, Employee> exportdetails()
 {  
	
	try {

         ps = cn.prepareStatement("select * from employeedetails");

         rs = ps.executeQuery();

         while(rs.next()) {

                int id=  rs.getInt(1) ; 
                String name=rs.getString(2) ; 
                int age=rs.getInt(3);
                String department=rs.getString(4) ;
               int salary= rs.getInt(5);
               Employee emp=new Employee(id,name,age,department,salary);
               
               	empp.put(emp.getId(), emp);
         }  

}catch(Exception e) {System.out.println("export not working");}


	 return empp;
 }
 @Override
public void display() {

          try {

                   ps = cn.prepareStatement("select * from employeedetails");

                   rs = ps.executeQuery();

                             System.out.println("  ID    |    NAME     |    Age   |   DEPARTMENT  |   SALARY");

                             System.out.println("--------------------------------------------------------------------------");

                   while(rs.next()) {

                             System.out.println( rs.getInt(1) + "\t " + rs.getString(2) + "\t\t " + rs.getInt(3) + "\t\t " + rs.getString(4) + "\t\t" + rs.getInt(5));

                   }        //       end of while

          }catch(Exception e) {}

          }                 //       end of dispay

 
 @Override
public void deleteX(int id) {

          try {

                   ps = cn.prepareStatement("delete from employeedetails where id=?");

                   ps.setInt(1, id);

                   ps.executeUpdate();

                   System.out.println("Record Deleted..");

          }catch(Exception e) {

                   System.out.println(e);

                   }

          }                 //       end of deleteX

 

public void close()  {   

          try {

                   rs.close();

                   ps.close();

                   cn.close();
                   cd.close();
                   nm.close();
                   th.close();

          }catch(Exception e) {}

          }        //       end of close



    }