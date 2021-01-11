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

        //GENERATE complex hashmap where   Hashmap< Hashmap<Person,Person>,Double> first person is payer, 2nd person is profiter and Double is amount paid
        for (HashMap.Entry<Ticket, Person> entry : db.entrySet())
        {
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


        //Make a last standing with HashMap<Person,Double> Person in key has to either pay or receive amount in Value
        HashMap<Person,Double> standings = new HashMap<>();
        Iterator<Person> it = allPeople.iterator();
        while (it.hasNext())
        {
            Person nextPerson = it.next();
            double amountPaid=0;
            double amountToPay=0;

            for (HashMap.Entry<HashMap<Person,Person>, Double> entry : map.entrySet())
            {

                HashMap<Person,Person> payerProfiter = entry.getKey();
                for (HashMap.Entry<Person,Person> entryPayerProfiter : payerProfiter.entrySet())
                {
                    if(nextPerson.equals(entryPayerProfiter.getKey()))//The amount person has paid in total
                    {
                        amountPaid = amountPaid+entry.getValue();
                    }
                    if(nextPerson.equals(entryPayerProfiter.getValue()))//The amount person has profited in total
                    {
                        amountToPay = amountToPay+entry.getValue();
                    }
                }

            }
            System.out.println(nextPerson.getName()+ " has paid € " + amountPaid + " and profited € "+ amountToPay);
            amountToPay = amountToPay -amountPaid;
            standings.put(nextPerson,amountToPay);

        }


        //last standing. Here we determine who has to pay how much to whom
        for (HashMap.Entry<Person, Double> outsideEntry : standings.entrySet())
        {
            double toPayAmount = outsideEntry.getValue();
            while (toPayAmount>0)//the person has to pay (positive amount)
            {
                for (HashMap.Entry<Person, Double> insideEntry : standings.entrySet())
                {
                    double profit = insideEntry.getValue();
                    if(profit <0)//the person has to be paid (negative amount)
                    {

                        if (Math.abs(toPayAmount) > Math.abs(profit))
                        {

                            System.out.println(outsideEntry.getKey().getName()+ " has to pay € "+ Math.abs(profit) +" to " + insideEntry.getKey().getName() );
                            toPayAmount = toPayAmount + profit;
                            profit = 0;
                        }
                        else
                        {

                            System.out.println(outsideEntry.getKey().getName()+ " has to pay € "+ Math.abs(toPayAmount) +" to " + insideEntry.getKey().getName() );
                            toPayAmount = 0;
                            profit = toPayAmount + profit;
                        }

                    }
                    standings.put(insideEntry.getKey(),profit);
                }
                standings.put(outsideEntry.getKey(),toPayAmount);
            }
        }




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
