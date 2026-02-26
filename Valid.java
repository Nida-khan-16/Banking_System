public class Valid 
// This class have function to validate account name and acount password
{
    public  Boolean validAccountName( String accountName ){//void makingAccount( String accountName,int[] accountNo, double balence)
        
        if(accountName==null || accountName.isEmpty()){
            return false;
        }
        if(accountName.contains("@") || accountName.contains(".") || accountName.length()>15){
           return false;
        }               
        return true;
    }
     public Boolean validPassword( String pswd){
        
        if(pswd.contains(" ")){
            return false;
        }
        if(pswd.length()<6){
            return false;
        }
        return true;
     }
      
}
   