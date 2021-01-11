package database;

import person.Person;
import register_entry.RegisterEntry;
import register_entry.RegisterEntryNull;
import ticket.Ticket;

import java.util.ArrayList;
import java.util.HashMap;

public class RegistrationDB extends Database
{
    private final HashMap<Person, RegisterEntry> db;

    private static RegistrationDB uniqueInstance;
    private static int numberOfPeople=0;

//    original code
//    public RegistrationDB()
////    {
////        this.db = new HashMap<>();
////    }

//    Private constructor (Only this class itself can make it by invoking getInstance method)
        private RegistrationDB()
        {
            this.db = new HashMap<>();
        }

        public static RegistrationDB getInstance()
        {
            if (uniqueInstance == null)//lazy instantiation
            {
                uniqueInstance = new RegistrationDB();
            }
            return uniqueInstance;
        }


    @Override
    public void addEntry(Person e, RegisterEntry re)
    {
        this.db.put(e, re);
        HashMap<Person,RegisterEntry> databaseEntry = new HashMap<>();databaseEntry.put(e,re);
        setChanged();//Mark the Observable object as changed
        notifyObservers(databaseEntry);//Notify all observer objects of the Observable object. Upon notification the update() method of concerning observer is executed
        //We give as argument a hashMap which only contains the most recent person and register entry added to it
        if (re.isCheckedIn())//if person added to database
        {
            numberOfPeople++;
        }
        else
        {
            numberOfPeople--;
        }
    }

    @Override
    public RegisterEntry getEntry(Person e)
    {
        return this.db.getOrDefault(e, new RegisterEntryNull());
    }

    public Person getPerson(String name)
    {
        Person tempPerson = null;
        for (Person person : db.keySet())
        {
            if (person.getName().equals(name))
            {
                tempPerson = person;
            }
        }
        return tempPerson;

    }

    public ArrayList<Person> getPeople()
    {
        ArrayList<Person> uniquePeople = new ArrayList<Person>();
        for (HashMap.Entry<Person, RegisterEntry> entry : db.entrySet()) {
            if(!uniquePeople.contains(entry.getKey()) && entry.getValue().isCheckedIn())//if person checked in (= added to database) and not in uniquePeopleList
            {
                uniquePeople.add(entry.getKey());
            }
        }
        return uniquePeople;
    }

    public int getNumberOfPeople()
    {
        return numberOfPeople;
    }


    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }
}
