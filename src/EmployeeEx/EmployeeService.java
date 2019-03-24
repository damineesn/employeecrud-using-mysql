package EmployeeEx;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
//import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

 

class EmployeeService {

static int display() throws IOException {
	@SuppressWarnings("resource")
	Scanner sc=new Scanner(System.in);

          System.out.println("\t\t Employee Database ");

          
          System.out.println("\t\t ~~~~~~~~~~~~~~~~~ ");

          System.out.println("\t\t 1. Insert. ");

          System.out.println("\t\t 2. Delete. ");

          System.out.println("\t\t 3. Update. ");

          System.out.println("\t\t 4. Display. ");
          
          System.out.println("\t\t 5. Display Employee. ");
          
          System.out.println("\t\t 6. Print Statistics. ");
          
          System.out.println("\t\t 7. Import. ");
          
          System.out.println("\t\t 8. Export. ");
          
          System.out.println("\t\t 9. Exit. ");

          System.out.print("\t Choose an option [1,2,3,4,5,6,7,8,9] : ");

          int ch =sc.nextInt();

          return ch;

          }        //       end of display

 

         

 


 

   public static void main(String args[]) throws IOException {
	   AbstractApplicationContext context=new ClassPathXmlApplicationContext("beans-config.xml");

          Employeedao db = (Employeedao)context.getBean("Employeedao", Employeedao.class);

          Scanner sc=new Scanner(System.in);

          while(true) {
        	  
          int ch = display();
          switch(ch) 
          {
         
                   case 1:

                             System.out.print(" Employee Name : ");

                             String name = sc.next();
                             boolean flag=false;
                             String str="[a-zA-Z]+";
                             while(!flag)
                             {
                            	 if(name.matches(str))
                            	 {
                            		 flag=true;
                            	 }
                            	 else
                            	 {
                            		 System.out.println("Error:Enter only alphabets");
                            		 name = sc.next();
                            	 }
                             }

                             System.out.print("       AGE : ");

                             int age = sc.nextInt();
                             boolean a=false;
                             String ab="[0-9]+";
                             String ac=String.valueOf(age);
                             while(!a)
                             {
                            	 if(ac.matches(ab))
                            	 {
                            		 if(age<15||age>80)
                            		 {
                            			 System.out.println("Error:Enter the age between 15 and 80");
                            			 age = sc.nextInt();
                            		 }
                            		 else
                            		 {
                            			 a=true;
                            		 }
                            	 }
                            	 else
                            	 {
                            		 System.out.println("Error:Enter the age between 15 and 80");
                        			 age = sc.nextInt();
                            	 }
                             }

                             System.out.print(" DEPARTMENT:");

                             String department = sc.next();

                             System.out.print(" Salary : ");

                             int salary = sc.nextInt();

                             db.insert( name, age, department, salary);

                             break;

                  

                   case 2://Scanner sc=new Scanner(System.in);

                     System.out.print("\nEnter Employee id to delete : ");

                            int id = sc.nextInt();

                             db.deleteX(id);

                             break;

 

                   case 3:
                	  // Scanner sc=new Scanner(System.in);
                	   System.out.print("\n Enter Employee Id : ");

                      id =sc.nextInt();

                       System.out.print(" Employee Name : ");
                       
                       name = sc.next();
                        flag=false;
                        str="[a-zA-Z]+";
                       while(!flag)
                       {
                      	 if(name.matches(str))
                      	 {
                      		 flag=true;
                      	 }
                      	 else
                      	 {
                      		 System.out.println("Error:Enter only alphabets");
                      		 name = sc.next();
                      	 }
                       }

                       System.out.print(" AGE : ");

                       age = sc.nextInt();
                       
                        a=false;
                        ab="[0-9]+";
                       ac=String.valueOf(age);
                       while(!a)
                       {
                      	 if(ac.matches(ab))
                      	 {
                      		 if(age<15||age>80)
                      		 {
                      			 System.out.println("Error:Enter the age between 15 and 80");
                      			 age = sc.nextInt();
                      		 }
                      		 else
                      		 {
                      			 a=true;
                      		 }
                      	 }
                      	 else
                      	 {
                      		 System.out.println("Error:Enter the age between 15 and 80");
                  			 age = sc.nextInt();
                      	 }
                       }


                       System.out.print(" DEPARTMENT ");

                      department = sc.next();

                       System.out.print(" Salary : ");

                      salary = sc.nextInt();
                        
                       db.updateX(id, name, age, department, salary);

                             break;

 

                   case 4:

                             db.display();

                             break;
                           
                             
                   
                   case 5:
                	  // Scanner sc=new Scanner(System.in);
                	   		System.out.print("\n Enter Employee Id : ");
                	   		id =sc.nextInt();
		                	  db.displayemployee(id);
		                       break;
 

                   case 6:
                	   
                	           db.statistics();
                	           break;
                	           
                   case 7://Scanner sc=new Scanner(System.in);
                	   Callable<Boolean> importsThread1=new Callable<Boolean>()
         	          {
         				@Override
         				public Boolean call() throws Exception {
         					System.out.println(Thread.currentThread()+"Waiting for 10s");
         					Thread.sleep(10000);
         				   BufferedReader bw=new BufferedReader(new FileReader("D:\\Training\\Employee.txt"));
                     	  String s;
                     	  int count=0;
                     			  while((s=bw.readLine())!=null)
                     			  {
                     				  count++;
                     				  String[] var=s.split(",");
                     				  db.importdetails(Integer.parseInt(var[0]), var[1],Integer.parseInt(var[2]) , var[3], Integer.parseInt(var[4]));
                     			  }
                     			  System.out.println("Imported successfully");
                     	          // bw.flush();
         					return null;
         				}
         	        	  
         	          };
         	          ExecutorService executor1=Executors.newFixedThreadPool(3);
         	          @SuppressWarnings("unused")
         	          Future<Boolean> importfuture1=executor1.submit(importsThread1);
         	          System.out.println(Thread.currentThread().getName()+"Import process Triggered");
         	          break;
                	            
                   case 8://Scanner sc=new Scanner(System.in);
                	   Callable<Boolean> exportsThread=new Callable<Boolean>()
          	          {
          				@Override
          				public Boolean call() throws Exception {
          					System.out.println(Thread.currentThread()+"Waiting for 10s");
          					Thread.sleep(10000);
          				   BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\Training\\Employee1.txt"));
                      	  Map<Integer, Employee> s=db.exportdetails();
                      	 for(Employee em:s.values())
                      	 {
                      		  bw.write(em.getId()+","+em.getName()+","+em.getAge()+","+em.getDept()+","+em.getSalary());
                      		 bw.newLine();
                      		 bw.flush();
                      		 
                      	 }
                      			  System.out.println("Exported successfully");
                      	          // 
          					return null;
          				}
          	        	  
          	          };
          	          ExecutorService executor=Executors.newFixedThreadPool(3);
          	          @SuppressWarnings("unused")
          	          Future<Boolean> exportfuture=executor.submit(exportsThread);
          	          System.out.println(Thread.currentThread().getName()+"Export process Triggered");
          	          break;
                   case 9: 

                               db.close();

                               System.exit(0);

                   }        //       end of switch
          
          }        //       end of while

       

          }

     }

