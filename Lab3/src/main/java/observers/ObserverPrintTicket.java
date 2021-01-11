package observers;

import person.Person;
import register_entry.RegisterEntry;
import ticket.Ticket;

import java.util.Observable;
import java.util.Observer;
import java.util.HashMap;

public class ObserverPrintTicket implements Observer {
    Observable observable;

    public ObserverPrintTicket(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        HashMap<Ticket, Person> databaseEntry = (HashMap<Ticket,Person>) arg;

        for(HashMap.Entry<Ticket, Person> entry : databaseEntry.entrySet())
        {
            System.out.println(entry.getValue().getName() +" paid €"
                    + entry.getKey().getTotalPrice() + " for ticket number "
                    + entry.getKey().getTicketNumber());
        }
    }
}
