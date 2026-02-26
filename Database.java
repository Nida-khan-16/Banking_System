 import java.util.*;
/* This class have following functions : 
1. createNewUser --> creates new user 
   [ To create a new user we need user ID which system provides so to ganerate a new user ID No. generateAccountNo() is used ]
2.  userExists  --> to see, if user exist in the data??      
3.  findUser --> to find a user 
4.  viewData  --> to print the whole data ( information about all the users)      */

class Database{

    private ArrayList<User> userdata;
    private long nextUserId=1000000000000L;
   
    public Database(){
        userdata=new ArrayList<>();
    }
    public String generateAccountNo(){
        // This is the very basic way to ganerate account no.( Upgrated vertion will be added after 3rd stage...)
        String userId=Long.toString(nextUserId);
        nextUserId++;
        return userId;
    }
    public boolean userExists(String userName) {
    for (User user : userdata) {
        if (user.getUserName().equals(userName)) {
            return true;  // Found!
        }
    }
    return false;  // Not found
}
   /*    ALTERBATE FOR THE "userExists" METHOD 
         public boolean userExists(String userName){
          int i=0;
        while (i < userdata.size()) { 
        if (userdata.get(i).getUserName().equals(userName)) {  
            return true;  
        }
        i++;
        }
        return false;
       }
      */
    public User createNewUser(String userName,String password ){
       if(userExists(userName)){
        return null;
       }
       String userId=generateAccountNo();
       User newUser=new User(userName, password, userId);
       userdata.add(newUser);
       return newUser;
     
    }
    public User findUser(String userName) {
    for(User user : userdata) {
        if(user.getUserName().equals(userName)){
            return user;
        }
    }
    return null;
}
    public void viewData(){
        for (User newUser : userdata) {
                System.out.println(newUser);
            }
    }
    
}