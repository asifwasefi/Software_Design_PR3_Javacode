package database;

import employee.Employee;
import register_entry.RegisterEntry;
import register_entry.RegisterEntryNull;

import java.util.HashMap;

public class RegistrationDB extends Database
{
    private final HashMap<Employee, RegisterEntry> db;

    private static RegistrationDB uniqueInstance;

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
    public void addEntry(Employee e, RegisterEntry re)
    {
        this.db.put(e, re);
        setChanged();//Mark the Observable object as changed
        notifyObservers();//Notify all observer objects of the Observable object. Upon notification the update() method of concenrning observer is executed
    }

    @Override
    public RegisterEntry getEntry(Employee e)
    {
        return this.db.getOrDefault(e, new RegisterEntryNull());
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
