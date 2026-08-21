
   //    user class contain various attributes of the user like user name, user id, etc. 
   /* Methods  :
      User --> Constructor 
      deposite --> it only give access to user to change or update there balance only if amount is valid 
      withdraw -->  it only give access to user to change or update there balance only if amount is valid 
      GETTER methods...
      */
    
class User{
private String userName;
private String userId;
 private String password;    
 private double balance=0.0;    

public User(String userName,String password,String userId){
this.userName=userName;
this.password=password;
this.userId=userId;
}
public boolean deposit(double amount){   // Returns whether deposit is sucessful or not  
  
    if(amount>=0){
        this.balance+=amount;
        return true;
    }
    return false;
}
public boolean withdraw(double amount){   // Returns wheather withdral is sucessful or not  
   
    if(amount>0 && amount<=this.balance){
         this.balance-=amount;
         return true;
    }
    

      return false;
}
public String getUserName(){return userName;}
public String getPassword(){return password;}
public String getUserId(){return userId;}
public double getBalance(){return balance;}
@Override
    public String toString() {
        return "UserID: " + userId + " | Name: " + userName;
    }
    
}