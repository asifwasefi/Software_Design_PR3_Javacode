package database;

import employee.Employee;
import register_entry.RegisterEntry;

public abstract class Database
{
    public Database()
    {

    }

    public abstract void addEntry(Employee e, RegisterEntry re);
    public abstract RegisterEntry getEntry(Employee e);
}
