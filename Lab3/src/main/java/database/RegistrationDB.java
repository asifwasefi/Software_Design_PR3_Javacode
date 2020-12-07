package database;

import employee.Employee;
import register_entry.RegisterEntry;
import register_entry.RegisterEntryNull;

import java.util.HashMap;

public class RegistrationDB extends Database
{
    private final HashMap<Employee, RegisterEntry> db;

    public RegistrationDB()
    {
        this.db = new HashMap<>();
    }

    @Override
    public void addEntry(Employee e, RegisterEntry re)
    {
        this.db.put(e, re);
    }

    @Override
    public RegisterEntry getEntry(Employee e)
    {
        return this.db.getOrDefault(e, new RegisterEntryNull());
    }
}
