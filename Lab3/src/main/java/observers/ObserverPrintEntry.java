package observers;

import person.Person;
import register_entry.RegisterEntry;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class ObserverPrintEntry implements Observer {
    Observable observable;

    public ObserverPrintEntry(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        HashMap<Person, RegisterEntry> databaseEntry = (HashMap<Person,RegisterEntry>) arg;

        for(RegisterEntry re : databaseEntry.values())
        {
            System.out.println(re);
        }
    }
}
