package controller;

import database.Database;
import database.RegistrationDB;
import person.Person;
import register_entry.RegisterEntry;

public class RegistrationController implements Controller
{
    private Database db;

    public RegistrationController(Database db)
    {
        this.db = db;
    }

    @Override
    public void checkIn(Person e)
    {
        Person p = RegistrationDB.getInstance().getPerson(e.getName());
        if (p != null)//person already in database
        {
            RegisterEntry entry = db.getEntry(p);
            if(!entry.isCheckedIn())//If person removed previously and want to add again
            {
                db.addEntry(e, new RegisterEntry(true));
            }
            else
                System.out.println(p.getName() + " already in database " + entry.toString());
        }
        else//person not at all in database => new entry
        {
            RegisterEntry entry = new RegisterEntry(true);
            db.addEntry(e, entry);
        }

    }

    @Override
    public void checkOut(Person e)
    {
        Person p = RegistrationDB.getInstance().getPerson(e.getName());
        if (p != null)
        {
            RegisterEntry entry = db.getEntry(p);
            if(entry.isCheckedIn())
            {
                db.addEntry(p, new RegisterEntry(false));
            }
            else
            {
                System.out.println(p.getName() + " already removed from database " + entry.toString());
            }

        }
        else
        {
            System.out.println(e.getName() + " not in database");
        }

    }
}
