/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.itexps.bankofsuburbs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author phalk
 */
public class BankOfSuburbs {
    public static void main(String[] args) {
        int choice;
        ArrayList<Employee> finalEmployees = new ArrayList();
        ArrayList<Customer> finalCustomers = new ArrayList();
        Bank myBank = new Bank("B001","Bank Of Suburbs","9990000111","Schaumburg, IL",finalEmployees,finalCustomers);
        do {
            System.out.println("|**********************************|");
            System.out.println("|*** Welcome to Bank Of Suburbs ***|");
            System.out.println("|**********************************|");         
            System.out.println("|        1. Admin Portal           |");
            System.out.println("|        2. Employee Portal        |");
            System.out.println("|        3. Customer Portal        |");
            System.out.println("|        4. Exit                   |");
            System.out.println("|**********************************|");
            
            System.out.println(" --> Please make your selection from above options : <--");
            Scanner sc1 = new Scanner(System.in);
            choice = sc1.nextInt();         
            switch(choice) {
                case 1 :
                        printAdminMenu();
                        break;
                case 2 :
                        String uname,password;
                        Scanner sc2= new Scanner(System.in);
                        System.out.println("Enter Username to Login Employee Portal:");
                        uname= sc2.next();
                        System.out.println("Enter Password to Login Employee Portal:");
                        password= sc2.next();
                        if(uname.equals("emp") && password.equals("emp@123")){
                          printEmployeeMenu(myBank,finalCustomers);  
                        }else{
                            System.out.println("Please check your Credentials and Try again..!");  
                        }  
                        break;
                case 3 :
                        ArrayList<Transaction> transactions = new ArrayList();
                        printCustomerMenu(finalCustomers,transactions);
                        break;
                case 4 :
                        System.out.println("Final Bank Details with all Information:");
                        System.out.println(myBank);
                        System.out.println("Thanks for visiting Bank Of Suburbs...See you soon...!");
                        break;
                default :
                        System.out.println("Try again...Unfortunately, you won't be to able visit any portal, as you have made the wrong selection..!");
                        break;
            }
        }while(choice!=4);
    }

    private static void printAdminMenu() {
        // System.out.println("I am in Admin Portal");
    }
    
    private static void printEmployeeMenu(Bank myBank,ArrayList<Customer> finalCustomers) {
        // System.out.println("I am in Employee Portal");
            int choice2;
            do {
                System.out.println("|****************************************|");
                System.out.println("|****** Welcome to Employee Portal ******|");
                System.out.println("|****************************************|");         
                System.out.println("|  1. Add Customer and Open Account/s    |");
                System.out.println("|  2. Update Customer Details            |");
                System.out.println("|  3. Find Customer                      |");
                System.out.println("|  4. Print All Customer's Information   |");
                System.out.println("|  5. Exit                               |");
                System.out.println("|****************************************|");

                System.out.println(" --> Please make your selection from above options : <--");
                Scanner sc1 = new Scanner(System.in);
                choice2 = sc1.nextInt();
                switch(choice2) {
                        case 1 :
                                Customer cust=new Customer();
                                addCustomer(cust);
                                System.out.println("Added Customer:");
                                System.out.println(cust);
                                finalCustomers.add(cust);
                                break;
                        case 2 :
                                updateCustomer(finalCustomers);
                                break;
                        case 3 :
                                findCustomer(finalCustomers);
                                break;
                        case 4 :
                                printAll(finalCustomers); 
                                break;
                        case 5 :
                                System.out.println("Thanks for visiting Employee Portal...!");
                                myBank.setCustomers(finalCustomers);
                                System.out.println(myBank);
                                break;
                        default :
                                System.out.println("Try again...you have made the wrong selection..!");
                                break;
                }
            }while(choice2!=5);
    }
    
    private static void printCustomerMenu(ArrayList<Customer> finalCustomers,ArrayList<Transaction> transactions) {
        System.out.println("I am in Customer Portal");
        int choice3;
        int count=0;
        String uname,pass;
        String accountId;
        float amount;
        
        System.out.println("Please Enter Your Username to Perform any Transaction / View Account Balance:");
        Scanner sc5 = new Scanner(System.in);
        uname=sc5.next();
        
        System.out.println("Please Enter Your Password:");
        pass=sc5.next();
        
        for(Customer cust:finalCustomers) {
            if(uname.equals(cust.getUname()) && pass.equals(cust.getPass())) {
               count++;
               int cnt=0;
               Scanner sc6 = new Scanner(System.in);
               System.out.println("Please Enter Your Acccount ID to Perform any Transaction / View Account Balance:");
               accountId=sc6.next();
               for(Account acct:cust.getAccounts()) {
                   if(accountId.equals(acct.getId())) {
                       cnt++;
                       do {
                             System.out.println("|****************************************|");
                             System.out.println("|      Welcome "+cust.getFirstname()+" "+cust.getLastname());
                             System.out.println("|****************************************|");         
                             System.out.println("|       1. Deposit                       |");
                             System.out.println("|       2. Withdraw                      |");
                             System.out.println("|       3. Check Account Balance         |");
                             System.out.println("|       4. Print Account Information     |");
                             System.out.println("|       5. Exit                          |");
                             System.out.println("|****************************************|");
                             System.out.println(" --> Please make your selection from above options : <--");
                             Scanner sc1 = new Scanner(System.in);
                             choice3 = sc1.nextInt();
                             switch(choice3) {
                                     case 1 :
                                             System.out.println("Your Current Balance is : "+acct.getBalance());
                                             System.out.println("Please Enter Amount to Deposit:");
                                             amount=sc6.nextFloat();
                                             acct.setBalance(acct.getBalance()+amount);
                                             System.out.println("Entered Amount Has Been Deposited Successfully...Your Balance is : "+acct.getBalance());
                                             Random r = new Random(System.currentTimeMillis());
                                             int result = 10000 + r.nextInt(20000);
                                             Transaction trn = new Transaction("T"+result,TransactionType.Credit,amount,LocalDateTime.now());
                                             transactions.add(trn);
                                             break;
                                     case 2 :
                                             System.out.println("Your Current Balance is : "+acct.getBalance());
                                             System.out.println("Please Enter Amount to Withdraw:");
                                             amount=sc6.nextFloat();
                                             acct.setBalance(acct.getBalance()-amount);
                                             System.out.println("Entered Amount Has Been Withdrawn Successfully...Your Balance is : "+acct.getBalance());
                                             Random r1 = new Random(System.currentTimeMillis());
                                             int result1 = 10000 + r1.nextInt(20000);
                                             Transaction trn1 = new Transaction("T"+result1,TransactionType.Debit,amount,LocalDateTime.now());
                                             transactions.add(trn1);
                                             break;
                                     case 3 :
                                             System.out.println("Your Account Balance is : "+acct.getBalance());
                                             break;
                                     case 4 :
                                             acct.setTransactions(transactions);
                                             System.out.println("|-------------------------------------------------|");
                                             System.out.println("|      Hello "+cust.getFirstname()+" "+cust.getLastname());
                                             System.out.println("|-------------------------------------------------|");         
                                             System.out.println("|   Account No.  :  "+acct.getId());
                                             System.out.println("|   Type         :  "+acct.getAccType());
                                             System.out.println("|   Balance      :  "+acct.getBalance());
                                             System.out.println("|-------------------------------------------------|");
                                             System.out.println("|ID|Transaction Type|Amount|Transaction DateTime");
                                             System.out.println("|-------------------------------------------------|");
                                             for(Transaction t:acct.getTransactions()) {
                                                 System.out.println("|"+t.getTid()+"|"+t.getTransactionType()+"|"+t.getTamount()+"|"+t.getTdatetime());
                                             }
                                             System.out.println("|-------------------------------------------------|");
                                             break;
                                     case 5 :
                                             acct.setTransactions(transactions);
                                             System.out.println("Recent Transactions -->");
                                             System.out.println("Account : "+acct);
                                             System.out.println("Transaction Details : "+acct.getTransactions());
                                             System.out.println("Thanks for visiting Customer Portal...!");
                                             break;
                                     default :
                                             System.out.println("Try again...you have made the wrong selection..!");
                                             break;
                             }
                         }while(choice3!=5);
                       break;
                   }
               }
               
               if(cnt==0) {
                  System.out.println("Please Enter Correct Account Number and Try Again..!");  
               }
               break;
            }
        }
        
        if(count==0) {
            System.out.println("Either Your Username or Password is Incorrect. Please Try Again..!"); 
        }
    }

    private static void addCustomer(Customer cust) {
        String answer;
        ArrayList<Account> accountsArray = new ArrayList();
        System.out.println("Enter Customer ID:");
        Scanner sc3=new Scanner(System.in);
        answer=sc3.next();
        cust.setId(answer);
        
        System.out.println("Enter Customer's First Name:");       
        answer=sc3.next();
        cust.setFirstname(answer);
        
        System.out.println("Enter Customer's Last Name:");      
        answer=sc3.next();
        cust.setLastname(answer);
        
        System.out.println("Enter Customer's Email:");       
        answer=sc3.next();
        cust.setEmail(answer);
        
        System.out.println("Enter Customer's Phone:");
        answer=sc3.next();
        cust.setPhone(answer);
        
        System.out.println("Enter Customer Type: Enter 'I'/'i' For Individual and 'B'/'b' For Business:");
        answer=sc3.next();
        if(answer.equalsIgnoreCase("i")) {
           cust.setCustType(CustomerType.Individual);
        }else if(answer.equalsIgnoreCase("b")) {
           cust.setCustType(CustomerType.Business); 
        }else {
           cust.setCustType(CustomerType.Individual); 
        }
        
        System.out.println("Set Username For Customer:");
        answer=sc3.next();
        cust.setUname(answer);
        
        System.out.println("Set Password For Customer:");
        answer=sc3.next();
        cust.setPass(answer);
        
        System.out.println("Customer wants to open Checking Account: Y/N ?");
        answer=sc3.next();
        if(answer.equalsIgnoreCase("y")){
           Account checkAcc = new Account();
           System.out.println("Enter Checking Account Number:");      
            answer=sc3.next();
            checkAcc.setId(answer);
            checkAcc.setAccType(AccountType.Checking);
            System.out.println("Enter Balance For Checking Account:");
            float balance = sc3.nextFloat();
            checkAcc.setBalance(balance);
            accountsArray.add(checkAcc);
        }
        
        System.out.println("Customer wants to open Saving Account: Y/N ?");
        answer=sc3.next();
        if(answer.equalsIgnoreCase("y")){
           Account savingAcc = new Account();
           System.out.println("Enter Saving Account Number:");      
            answer=sc3.next();
            savingAcc.setId(answer);
            savingAcc.setAccType(AccountType.Saving);
            System.out.println("Enter Balance For Saving Account:");
            float balance = sc3.nextFloat();
            savingAcc.setBalance(balance);
            accountsArray.add(savingAcc);
        }
        
        System.out.println("Customer wants to open FD Account: Y/N ?");
        answer=sc3.next();
        if(answer.equalsIgnoreCase("y")){
           Account fdAcc = new Account();
           System.out.println("Enter FD Account Number:");      
            answer=sc3.next();
            fdAcc.setId(answer);
            fdAcc.setAccType(AccountType.FixedDeposit);
            System.out.println("Enter Balance For FD Account:");
            float balance = sc3.nextFloat();
            fdAcc.setBalance(balance);
            accountsArray.add(fdAcc);
        }
        
        cust.setAccounts(accountsArray);
    }

    private static void updateCustomer(ArrayList<Customer> finalCustomers) {
        int count=0;
        String answer;
        System.out.println("Please Enter Customer ID to Update Information For:");
        Scanner sc4 = new Scanner(System.in);
        String custId = sc4.next();
        for(Customer cust:finalCustomers){
            if(custId.equals(cust.getId())) {
               count++;
               System.out.println("Existing First Name for Customer is : "+cust.getFirstname());
               System.out.println("Enter New First Name for Customer :");
               answer=sc4.next();
               cust.setFirstname(answer);

               System.out.println("Existing Last Name for Customer is : "+cust.getLastname());
               System.out.println("Enter New Last Name for Customer :");
               answer=sc4.next();
               cust.setLastname(answer);

               System.out.println("Existing Email for Customer is : "+cust.getEmail());
               System.out.println("Enter New Email for Customer :");
               answer=sc4.next();
               cust.setEmail(answer);

               System.out.println("Existing Phone Number for Customer is : "+cust.getPhone());
               System.out.println("Enter New Phone Number for Customer :");
               answer=sc4.next();
               cust.setPhone(answer);
            }
        }
        if(count==0) {
            System.out.println("Customer doesn't exist in Bank. Please Try Again with Correct Customer ID...!");
        }
    }

    private static void findCustomer(ArrayList<Customer> finalCustomers) {
        int count=0;
        System.out.println("Please Enter Customer ID to Find :");
        Scanner sc4 = new Scanner(System.in);
        String custId = sc4.next();
        for(Customer cust:finalCustomers){
            if(custId.equals(cust.getId())) {
                count++;
                System.out.println("Customer Found and Below are the Customer Details:");
                System.out.println("|-------------------------------------------------|");         
                System.out.println("|   ID      :    "+cust.getId());
                System.out.println("|   Name    :    "+cust.getFirstname()+" "+cust.getLastname());
                System.out.println("|   Email   :    "+cust.getEmail());
                System.out.println("|   Phone   :    "+cust.getPhone());
                for(Account acct:cust.getAccounts()){
                   System.out.println("|");
                   System.out.println("|   Account Number   :    "+acct.getId());
                   System.out.println("|   Account Type     :    "+acct.getAccType());
                   System.out.println("|   Account Balance  :    "+acct.getBalance());
                }
                System.out.println("|-------------------------------------------------|");
                break;
            }
        }
        
        if(count==0) {
            System.out.println("Customer doesn't exist in Bank. Please Try Again with Correct Customer ID...!");
        }
    }

    private static void printAll(ArrayList<Customer> finalCustomers) {
        System.out.println("Total Number Of Registered Customers with us : "+finalCustomers.size());
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|ID|Name|Email|Phone|Accounts(Number-Type-Balance)");
        for(Customer cust:finalCustomers){ 
            String str="";
            for(Account acct:cust.getAccounts()){
                   str = str+"|"+acct.getId()+"-"+acct.getAccType()+"-"+acct.getBalance();
                }
            System.out.println("|"+cust.getId()+"|"+cust.getFirstname()+" "+cust.getLastname()+"|"+cust.getEmail()+"|"+cust.getPhone()+str);
        }
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
    }
}