
package za.ac.cput.readstakeholders;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * ReadStakeholders.java;
 * This class is reading or de-serializing all stakeholders ser file and writing the data to two 
 * different text files. 
 * @author Anele Aneal Tose - 216079292
 * Date: 03-June-2021
 */
public class ReadStakeholders {
    //Declaring variables
    ObjectInputStream input;
    int counterCustomer;
    int counterSupplier;
    ArrayList<Customer> cust;
    ArrayList<Supplier> supp;
    
    public ReadStakeholders(){
        cust = new ArrayList<>();
        supp = new ArrayList<>();
        counterCustomer = 0;
        counterSupplier = 0;
    }
    public void readFile() throws IOException{
        //Reading the ser file of de-serialising the ser file
        try{
            input = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
            while(true){
                cust.add((Customer) input.readObject());
                counterCustomer ++;
                supp.add((Supplier) input.readObject());
                counterSupplier ++;  
            }
        }
        catch(ClassNotFoundException cnfe){
            System.out.println(cnfe.getMessage());
        }
        catch(EOFException e){
            System.out.println("Reached EOF!");
            input.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void displayCustomers(){
        //Displayind all customer that are stored in the text file 
        System.out.println("**************ALL CUSTOMERS**************");
        for (Customer c: cust){ 
            System.out.println(c);
        }  
    }
    public void sortCustomers(){
        //Sorting customers by stakeholder id in ascending order
        System.out.println("*****SORT ALL CUSTOMER BY STAKEHOLDER ID IN ASCENDING ORDER*****");
        for(int i = 0; i < counterCustomer - 1; i++){
            for(int j = i + 1; j < counterCustomer; j++){
                if(cust.get(i).getStHolderId().compareTo(cust.get(j).getStHolderId()) > 0){
                    Customer temp = cust.get(i);
                    cust.set(i, cust.get(j));
                    cust.set(j, temp);   
                }
            } 
        }
    }
    public void countCustomerAge(){
        //Counting customer age
        System.out.println("*********COUNT CUSTOMER AGE*********");
        for(Customer cus: cust){
            String dob[]=cus.getDateOfBirth().split("-");
            int year=Integer.parseInt(dob[0]);
            int age = 2021-year;
            System.out.println("ID: "+cus.getStHolderId()+"\tAge: "+age);
        }
    }
    public void reformatCustomerDod(){
        //Re-formating the date of birth of each customer from 1993-01-24 to 24 Jan 1993
        System.out.println("*********RE-FORMAT CUSTOMER DOB**********");
        for(Customer cus: cust){
            String dob[]=cus.getDateOfBirth().split("-");
            String month;
            switch(dob[1]){
                case "01":
                    month = "Jan";
                break;
                case "02":
                    month = "Feb";
                break;
                case "03":
                    month = "Mar";
                break;
                case "04":
                    month = "Apr";
                break;
                case "05":
                    month = "May";
                break;
                case "06":
                    month = "Jun";
                break;
                case "07":
                    month = "Jul";
                break;
                case "08":
                    month = "Aug";
                break;
                case "09":
                    month = "Sep";
                break;
                case "10":
                    month = "Oct";
                break;
                case "11":
                    month = "Nov";
                break;
                case "12":
                    month = "Dec";
                break;
                default:
                    month = "invalid";    
            }
            String newDob = dob[2]+" "+month+" "+dob[0];
            cus.setDateOfBirth(newDob);  
        }
    }
    public void customerData(){
            //Displaying custome data and saving it in a textfile called (customerOutFile.txt)
            System.out.println("********DIPLAY CUSTOMER DATA ON A TEXT FILE*******");
            String data = "";
            data +="========================CUSTOMERS========================\n";
            data +="ID\tName\tSurname\t\tDate of birth\tAge\n";
            data +="=========================================================\n";
        for (Customer c: cust){
            if(c.getSurName().length()<8)
            data += c.getStHolderId()+"\t"+c.getFirstName()+"\t"+c.getSurName()+"\t\t"+c.getDateOfBirth()+"\t"+getCustomerAge(c)+"\n";
            else{
                data +=c.getStHolderId()+"\t"+c.getFirstName()+"\t"+c.getSurName()+"\t"+c.getDateOfBirth()+"\t"+getCustomerAge(c)+"\n";
            }   
        }
        int index = 0;
        for(Customer c: cust){
            if(c.getCanRent()==true){          
                index++;
            }
        }
        data +="Number of customers who can rent: "+index+"\n";
        data +="Number of customers who cannot rent: "+(cust.size()-index)+"\n";
        try{
            FileWriter fw = new FileWriter("customerOutFile.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.write(data);
            pw.close();
            fw.close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }  
    }
    private int getCustomerAge(Customer cus){
            //Getting the customer age and add it to the txtfile
            String dob[]=cus.getDateOfBirth().split(" ");
            int year=Integer.parseInt(dob[2]);
            int age = 2021-year;
            return age;
    }
   public void displaySuppliers(){
        //Getting all suppliers from ser file and display them 
        System.out.println("***************ALL SUPPLIERS***************");
        for (Supplier s: supp){ 
            System.out.println(s);
            }
    }
   public void sortSuppliers(){
       //Sorting all suppliers by name in ascending order
        System.out.println("*******SORT ALL SUPPLIERS ALPHABETICALLY IN ASCENDING ORDER*******");
        for(int i = 0; i < counterSupplier - 1; i++){
            for(int j = i + 1; j < counterSupplier; j++){
                if(supp.get(i).getName().compareTo(supp.get(j).getName()) > 0){
                    Supplier supplier = supp.get(i);
                    supp.set(i, supp.get(j));
                    supp.set(j, supplier);  
                }
            }   
        }
    }
   public void supplierData(){
            //Save all the required supplier data the text file called (supplierOutFile.txt)
            String data = "";
            String out = String.format("%-15s %-20s %-15s %-15s", "ID","Name","Product Type","Description");
            data +="===============================SUPPLIERS===============================\n";
            data +=out+"\n";
            data +="=======================================================================\n";
        for (Supplier supplier: supp){
            out = String.format("%-15s %-20s %-15s %-15s", supplier.getStHolderId(),supplier.getName(),supplier.getProductType(),supplier.getProductDescription());
                
            data +=out+"\n"; 
            
        }
        try{
            FileWriter fw = new FileWriter("supplierOutFile.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.write(data);
            pw.close();
            fw.close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        } 
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Calling all the required methods 
        ReadStakeholders read = new ReadStakeholders();
        read.readFile();
        read.displayCustomers();
        read.displaySuppliers();
        read.sortCustomers();
        read.displayCustomers();
        read.countCustomerAge();
        read.reformatCustomerDod();
        read.displayCustomers();
        read.customerData();
        read.sortSuppliers();
        read.displaySuppliers();
        read.supplierData();
    }      
}
