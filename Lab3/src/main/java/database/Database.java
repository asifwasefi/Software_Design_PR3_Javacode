package database;

import employee.Employee;
import register_entry.RegisterEntry;
import java.util.Observable;

public abstract class Database extends Observable
{
    public Database()
    {

    }

    public abstract void addEntry(Employee e, RegisterEntry re);
    public abstract RegisterEntry getEntry(Employee e);
}
