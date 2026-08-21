import java.util.*;
/* This is the "BankingSystem" class this is the heart of the whole system
==========================================================================

    -:  METHODS AND THIER FUNCTIONS PRESENT IN THIS CLASS  :-
    1. start --> It shows welcome massage and ask the user if s/he has an account, And if user have an account they login otherwise they signup
    2. singup --> It ask User for informations(i.e. username, passward), creats an account and ask for initial balance And redirected to the main manu
    3. login  -->  It asks user's informations(i.e. username, passward) for verification, and if data match they are redirected to the main manu
    4. showMainMenu()  -->  It has mainly 4 features : Deposite, Withdraw, check BAlance, and logout 

*/
class BankingSystem{
    private Database database;
    private Scanner scan;
    private User currentUser;
    Valid valid = new Valid() ;

    public BankingSystem(){
        database=new Database();
        scan=new Scanner(System.in);
        
        currentUser=null;

    }
     public void start(){
           // WELCOME MASSAGE
        System.out.println("====================================");
        System.out.println("   Welcome to our Banking System!  ");
        System.out.println("====================================");

        System.out.println("Do you have an account?(yes/no)");
                   // CHACK DOES USER HAVE AN ACCOUNT 
       
       while (true) { 
         String answer = scan.nextLine().trim().toLowerCase();
            if(answer.equals("yes")){
                //  IF YES --> LOGIN
               login();
            break;
            }
               else if(answer.equals("no")){
                 //  IF NO --> SIGNUP
                 signup();
                break;
               }
                else{
                    System.out.println("Invalid input! Please enter yes or no");
                }
            } 
           
       }
        public void signup(){
        // step 1: Ask for user name
         System.out.println("\n=== Create New Account ===");
    System.out.print("Please entre your user name : ");
    String userName=scan.nextLine();
  
     while (!(valid.validAccountName(userName))) {
                        System.out.println("you have entered an invalid name!! please re-enter your name!! ");
                        userName=scan.nextLine();
                    }

    
    // Step 2: Get password
    System.out.print("Please entre your Password : "); 
    String pswd=scan.nextLine();

    while(!(valid.validPassword(pswd))){
         System.out.println("you have entered an invalid Password!! please re-enter your Password!! ");
        pswd=scan.nextLine();
    }
    // Step 3: Confirm password, 
    System.out.print("Please re-entre your Password : ");  
   
    while(true){
         String N_pswd=scan.nextLine();
        if(N_pswd.equals(pswd)){  
            break;
        }
        else{
            System.out.print("please re-enter correct Password : ");
        }
    }
    // Step 5: Create user ---> User newUser = database.createNewUser(...)   --->     Step 6: Check if null (username taken)
   
 User newUser = null;
while(newUser == null){
    newUser = database.createNewUser(userName, pswd);
    if(newUser == null){
        System.out.println("Username taken! Try another:");
        userName = scan.nextLine();  // Get new username!
    }
     while(!valid.validAccountName(userName)){
            System.out.println("Invalid name! Try again:");
            userName = scan.nextLine();
        }
}
     // Step 7: Deposit initial amount   

    System.out.print("Enter the initial amount you want to deposit(>500) : ");
    double deposit=scan.nextDouble();
    scan.nextLine();
  while(deposit < 500){
    System.out.println("Invalid amount, must enter mimimmum deposit of Rs.500");
     System.out.print("Enter the initial amount you want to deposit: ");
    deposit=scan.nextDouble();
    scan.nextLine();
}
if(newUser.deposit(deposit)){
    System.out.println("Successfully deposited ₹" + deposit);
}
else{
    System.out.println("Transaction failed");
}
    // Step 8: Set currentUser  ---> Step 9: Show success message and go to main menu
   this.currentUser = newUser;  // Set the CLASS field!
   System.out.println("=========================================");
   System.out.println("   Account Created Successfully! 🎉");
   System.out.println("=========================================");
   System.out.println("Welcome, "+currentUser.getUserName());
   System.out.println("Your user ID is : "+currentUser.getUserId()+ "!");
   System.out.println("Please save your Account ID for future reference!");
    System.out.println("=========================================");


    showMainMenu();              // Go straight to menu!

    
    }
      void login(){
      System.out.println("\n=== LOGIN ===");
      // ASKING USER NAME
    System.out.print("Please entre your user name : ");
    String userName=scan.nextLine();
    User newUser=database.findUser(userName);
   
        int attempts=3;
        while(attempts>0){
             System.out.print("Please entre your user Password : "); // ASKING FOR PASSWORD 
             String password=scan.nextLine();
              //  IF ENTER INVALID USER NAME / PASSWORD  MORE THAN 3 TIMES THE WHOLE PROCCESS WILL GET CANCLED AND USER WILL BE RE-DIRECTED TO THE START...
             if(newUser!=null && password.equals(newUser.getPassword())){
                 this.currentUser = newUser; 
                showMainMenu(); 
                break;
             }
             else{
                attempts--;
                if(attempts > 0)
                System.out.println("Invalid credentials! "+ attempts+ " attempts remaining..");
             }
        }   
 if(attempts==0){
        System.out.println("Too many failed attempts! Account locked.");
       start();
       return;
    }
    
    }
    public void showMainMenu(){
        System.out.println("=====================================");
        System.out.println("Welcome back! "+ currentUser.getUserName());
        System.out.println("=====================================");
        while(currentUser!=null){
        System.out.println("\n=== Main Menu ===");
        System.out.println("\t1. Check Balance");
        System.out.println("\t2. Deposit Money");
        System.out.println("\t3. Withdraw Money");
        System.out.println("\t4. Logout");
        System.out.print("Please, enter your choice : ");
        int choice =scan.nextInt();
        scan.nextLine();

        switch (choice) {
            case 1:  
            // TO SEE BALANCE USER MUST CONFERM PASSWORD 
            System.out.print("Please enter Password to continue : ");
            String password=scan.nextLine();
            if(password.equals(currentUser.getPassword())){
            System.out.println("Your current balance is: Rs." + currentUser.getBalance());
            }
             else {
             System.out.println("Wrong Password! Access denied.");
            }
              break;
            case 2:  
            System.out.print("Please enter the amount you want to deposit(In INR) : ");   // ASKING FOR THE AMOUNT 
            double amount =scan.nextDouble();
            scan.nextLine();
            System.out.print("Please enter Password to continue : ");  // TO DEPOSITE MONEY BALANCE USER MUST CONFERM PASSWORD 
            password=scan.nextLine();
            if(password.equals(currentUser.getPassword())){
                if( currentUser.deposit(amount)){
                    System.out.println("Amount has Successfully added to your balance!");
                }
                else{
                    System.out.println("Transaction failed");
                    break;
                }
            }
            else{
            System.out.println("Wrong Password! Transaction cancelled.");
            break;
            }
              break;
            case 3:  
            System.out.print("Please enter the amount you want to withdraw(In INR) : ");  // ASKING FOR THE AMOUNT 
            amount =scan.nextDouble();
            scan.nextLine();
            
            System.out.print("Please enter Password to continue : ");  // TO SEE WITHDRAW MONEY USER MUST CONFERM PASSWORD 
            password=scan.nextLine();
            if(password.equals(currentUser.getPassword())){
               
                while(!( currentUser.withdraw(amount))){
                   System.out.println("Your current balance is less then Rs."+amount+"\nplease enter a valid amount ");
                    amount=scan.nextDouble();
                   scan.nextLine();
                }
                
                    System.out.println("Amount Successfully withdrawed!");
                
            }
            else{
            System.out.println("Wrong Password! Transaction cancelled.");
            break;
            }
              break;
            case 4:  
            System.out.println("====================================");
            System.out.println("  Logged out successfully!");
            System.out.println("  Thank you for banking with us! ");
            System.out.println("====================================\n\n");
            currentUser=null;
          //  start();             //     <-- you can un-comment this  if you want to check "login" function 
          // Currently I don't have any database to store this data of the users (Cause I am still learning DBMS) but in next version it will be added surely :)
              break;
            
            default:
                System.out.println("Please enter a valid choice");
        
        }
    }
    }
    }
    