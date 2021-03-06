package Other_Services;


import SQL_Connector.Save_mailLog;

import Sql_Lite.Load_Settings;

import com.email.durgesh.Email;

import java.io.UnsupportedEncodingException;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.mail.MessagingException;

public class email extends Thread
{
    String reciver_name = null;
    
    String reciver_mail = null;
    
    String subject_ = null;
    
    String message_ = null; 
    
    int  index_ = 0;
    
    
    public email(String reaciver,String name,String subject,String msg,int index)
    {
        reciver_name = name;
        
        reciver_mail = reaciver;
        
        subject_ = subject;
        
        message_ = msg;
        
        index_ = index;
    }
    
     public void run() 
     {
        try 
        {
            String message =  main_template(message_,subject_);
            
            String user_name = Load_Settings.load_settings("EMAIL");
            
            String password  = Load_Settings.load_settings("PASSWORD");
            
            Email email = new Email(user_name,password);
            
            email.setFrom(user_name,Load_Settings.load_settings("SENDER_NAME"));
            
            email.setSubject("SLTC Book Bucket Notification");
            
            email.setContent(message,"text/html");
            
            email.addRecipient(reciver_mail);
                        
            try
            {
                email.send();
                
                System.out.println("SEND MAIL");
                            
                Save_mailLog.save_log(subject_,index_);
            
            }
            catch (MessagingException ex)
            {
                System.out.println(ex);
            }
        } 
        catch (MessagingException | UnsupportedEncodingException ex) 
        {
            Logger.getLogger(email.class.getName()).log(Level.SEVERE, null,ex);
        }
     }
     
    public static String main_template(String data,String subject)
    {
        String mail = ""
                + ""
                + "<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"  <head>\n" +
"    <meta charset=\"UTF-8\" />\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
"    <title>Document</title>\n" +
"    <style>\n" +
"     \n" +
"\n" +
"      * {\n" +
"        margin: 0;\n" +
"        padding: 0;\n" +
"        border: 0;\n" +
"      }\n" +
"\n" +
"      body {\n" +
"        font-family: 'Courier New', monospace;\n" +
"        background-color: #d8dada;\n" +
"        font-size: 19px;\n" +
"        max-width: 800px;\n" +
"        margin: 0 auto;\n" +
"        padding: 3%;\n" +
"      }\n" +
"\n" +
"      img {\n" +
"        max-width: 100%;\n" +
"      }\n" +
"\n" +
"      header {\n" +
"        width: 98%;\n" +
"      }\n" +
"\n" +
"      #logo {\n" +
"        max-width: 500px;\n" +
"        margin: 3% 0 3% 3%;\n" +
"        float: left;\n" +
"      }\n" +
"\n" +
"      #wrapper {\n" +
"        background-color: #f0f6fb;\n" +
"      }\n" +
"\n" +
"      #social {\n" +
"        float: right;\n" +
"        margin: 3% 2% 4% 3%;\n" +
"        list-style-type: none;\n" +
"      }\n" +
"\n" +
"      #social > li {\n" +
"        display: inline;\n" +
"      }\n" +
"\n" +
"      #social > li > a > img {\n" +
"        max-width: 35px;\n" +
"      }\n" +
"\n" +
"      h1,\n" +
"      p {\n" +
"        margin: 3%;\n" +
"      }\n" +
"    \n" +
"\n" +
"      hr {\n" +
"        height: 1px;\n" +
"        background-color: #000000;\n" +
"        clear: both;\n" +
"        width: 96%;\n" +
"        margin: auto;\n" +
"      }\n" +
"\n" +
"      #contact {\n" +
"        text-align: center;\n" +
"        padding-bottom: 3%;\n" +
"        line-height: 16px;\n" +
"        font-size: 12px;\n" +
"        color: #000000;\n" +
"      }\n" +
"    </style>\n" +
"  </head>\n" +
"  <body>\n" +
"    <div id=\"wrapper\">\n" +
"      <header>\n" +
"        <div id=\"logo\">\n" +
"          <img\n" +
"            src=\"https://sltc.ac.lk/assets/images/logo-lg.png\"\n" +
"            alt=\"\"\n" +
"          />\n" +
"        </div>\n" +
"      </header>\n" +
"      <div id=\"banner\">\n" +
"        <img\n" +
"          src=\"https://i.ibb.co/g3LJ62r/Banner.png\" \n" +
"          alt=\"\"\n" +
"        />\n" +
"      </div>\n" +
"      <div class=\"one-col\">\n" +
"        <h1>"+subject+"</h1>\n" +
"\n" +
"        <p>\n" + data +
"        </p>\n" +
"\n" +
"        <hr />\n" +
"\n" +
"        <footer>\n" +
"          <p id=\"contact\">\n" +
"            +94 11 2100 500 / +94 71 1100 500 <br/>\n" +
"            info@sltc.ac.lk<br/>\n" +
"            Main Campus : Ingiriya Road, Padukka, Sri Lanka.<br/>\n" +
"            Colombo Campus : No 07, Hector Kobbakaduwa Mawatha, Colombo 07.<br/>\n" +
"            City Campus : Trace Expert City, Colombo 10 <br/>\n" +
"          </p>\n" +
"        </footer>\n" +
"      </div>\n" +
"    </div>\n" +
"  </body>\n" +
"</html>";
        
        return mail;
    }
}
    

