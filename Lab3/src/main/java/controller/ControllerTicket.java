package controller;

import person.Person;
import ticket.Ticket;

public interface ControllerTicket
{
    void addTicket(Ticket t, Person p);
    void calculateTotalBill();
}
