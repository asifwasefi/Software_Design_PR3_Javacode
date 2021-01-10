package database;

import person.Person;
import register_entry.RegisterEntry;
import java.util.Observable;

public abstract class Database extends Observable
{
    public Database()
    {

    }

    public abstract void addEntry(Person e, RegisterEntry re);
    public abstract RegisterEntry getEntry(Person e);
}
