package Factory;

import database.RegistrationDB;
import person.Person;
import register_entry.RegisterEntry;
import register_entry.RegisterEntryNull;

import java.util.HashMap;

public class EmployeeFactory {

    public Person getEmployee(String name)
    {
        Person tempPerson = RegistrationDB.getInstance().getPerson(name);
        if (tempPerson != null)
        {
            return tempPerson;
        }
        else
        {
            return new Person(name);
        }
    }
}
