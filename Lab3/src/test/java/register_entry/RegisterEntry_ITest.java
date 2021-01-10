package register_entry;


import database.RegistrationDB;
import person.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class RegisterEntry_ITest {

    int hours;int minutes;int seconds;

    public RegisterEntry_ITest() {
    }

    @Before
    public void initialize()
    {
        System.out.println("Initializing integration test");
        RegistrationDB db = RegistrationDB.getInstance();
        Person e = new Person("Adam");
        db.addEntry(e,new RegisterEntry(true));
        RegisterEntry databaseEntry = db.getEntry(e);
        this.hours = databaseEntry.getHours();
        this.minutes = databaseEntry.getMinutes();
        this.seconds = databaseEntry.getSeconds();

    }

    @Test
    public void t_getEntryHours()
    {
        RegisterEntry entry = new RegisterEntry(true);
        int hours2 = entry.getHours();
        assertEquals("Testing hours at which the entry for a certain person is added to database",hours2,hours);
    }

    @Test
    public void t_getEntryMinutes()
    {
        RegisterEntry entry = new RegisterEntry(true);
        int minutes2 = entry.getMinutes();
        assertEquals("Testing minutes at which the entry for a certain person is added to database",minutes2,minutes);
    }

    @Test
    public void t_getEntrySeconds()
    {
        RegisterEntry entry = new RegisterEntry(true);
        int seconds2 = entry.getSeconds();
        assertEquals("Testing hours at which the entry for a certain person is added to database",seconds2,seconds);
    }

}
