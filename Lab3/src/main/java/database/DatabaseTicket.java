package database;

import person.Person;
import register_entry.RegisterEntry;
import ticket.Ticket;

import java.util.Observable;

public abstract class DatabaseTicket extends Observable
{
    public DatabaseTicket()
    {

    }

    public abstract void addEntry(Ticket t, Person p);
    public abstract Person getEntry(Ticket t);
    public abstract void calculateBills();
}
