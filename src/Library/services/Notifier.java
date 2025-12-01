package Library.services;

import java.time.LocalDate;

public class Notifier {
    
    public void sendEmail(LocalDate message, String user){
        System.out.println("Sending email to user: " + user + ", your lend finished at: " + message);
    }

}
