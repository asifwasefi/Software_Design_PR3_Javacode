package controller;

import database.Database;
import database.DatabaseTicket;
import database.RegistrationDB;
import database.RegistrationTicket;
import person.Person;
import register_entry.RegisterEntry;
import ticket.Ticket;

import java.util.HashMap;

public class TicketController implements ControllerTicket
{
    private DatabaseTicket db;

    public TicketController(DatabaseTicket db)
    {
        this.db = db;
    }

    @Override
    public void addTicket(Ticket ticket, Person person)
    {
        Person p = RegistrationDB.getInstance().getPerson(person.getName());
        if (p != null)//person already in database
        {
            db.addEntry(ticket,p);
        }
        else
        {
            System.out.println("Please add " + person.getName()+ " to the database first");
        }


    }

    @Override
    public void calculateTotalBill() {
        db.calculateBills();
    }
}
