package test;

import org.junit.Test;

import Library.services.Notifier;
import java.time.LocalDate;

public class NotifierTest {

    @Test
    public void testNotifierDoesNotCrash() {
        Notifier n = new Notifier();
        n.sendEmail(LocalDate.now(), "Ana");
    }
}
