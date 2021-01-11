package database;

import person.Person;
import register_entry.RegisterEntry;
import register_entry.RegisterEntryNull;
import ticket.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class RegistrationTicket extends DatabaseTicket
{
    private final HashMap<Ticket, Person> db;

    private static RegistrationTicket uniqueInstance;

    //    Private constructor (Only this class itself can make it by invoking getInstance method)
    private RegistrationTicket()
    {
        this.db = new HashMap<>();
    }

    public static RegistrationTicket getInstance()
    {
        if (uniqueInstance == null)//lazy instantiation
        {
            uniqueInstance = new RegistrationTicket();
        }
        return uniqueInstance;
    }


    @Override
    public void addEntry(Ticket ticket, Person person)
    {
        this.db.put(ticket,person);
        HashMap<Ticket,Person> databaseEntry = new HashMap<>();databaseEntry.put(ticket,person);
        setChanged();//Mark the Observable object as changed
        notifyObservers(databaseEntry);//Notify all observer objects of the Observable object. Upon notification the update() method of concerning observer is executed
        //We give as argument a hashMap which only contains the most recent person and register entry added to it
    }

    @Override
    public Person getEntry(Ticket t)
    {
        return this.db.getOrDefault(t, null);
    }

    public Ticket getTicket(double ticketNumber)
    {
        Ticket tempTicket = null;
        for (Ticket ticket : db.keySet())
        {
            if (ticket.getTicketNumber() == ticketNumber)
            {
                tempTicket = ticket;
            }
        }
        return tempTicket;

    }

    @Override
    public void calculateBills() {
        HashMap<  HashMap<Person,Person>, Double   > map = new HashMap<>();
        ArrayList<Person> allPeople = RegistrationDB.getInstance().getPeople();


        for (HashMap.Entry<Ticket, Person> entry : db.entrySet()) {
//            System.out.println(entry.getValue().getName() + " paid € " + entry.getKey().getTotalPrice()+ " for ticket number "+ entry.getKey().getTicketNumber());
            Iterator<Person> it = allPeople.iterator();
            while (it.hasNext())
            {
                Person nextPerson = it.next();
                if (!nextPerson.equals(entry.getValue()))//payer should not be the same as profiter
                {
                    HashMap<Person,Person> payerProfiter = new HashMap<>();
                    payerProfiter.put(entry.getValue(),(nextPerson));
                    map.put(payerProfiter,entry.getKey().getTotalPrice()/allPeople.size());
                }
            }

        }

        for (HashMap.Entry<HashMap<Person,Person>, Double> entry : map.entrySet()) {

            HashMap<Person,Person> payerProfiter = entry.getKey();
            for (HashMap.Entry<Person,Person> entryPayerProfiter : payerProfiter.entrySet())
            {
                System.out.println(entryPayerProfiter.getKey().getName() + " has paid € "+ entry.getValue()+" for " + entryPayerProfiter.getValue().getName());
            }

        }

        System.out.println(RegistrationDB.getInstance().getPeople().size());

    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }

    private int numberOfPayers()
    {

        ArrayList<String> names = new ArrayList<>();
        for (Person person : db.values())
        {
            if (!names.contains(person.getName()))
            {
                names.add(person.getName());
            }
        }
        return names.size();
    }
}
