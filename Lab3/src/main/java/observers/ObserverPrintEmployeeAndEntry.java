package observers;

import person.Person;
import register_entry.RegisterEntry;

import java.util.Observable;
import java.util.Observer;
import java.util.HashMap;

public class ObserverPrintEmployeeAndEntry implements Observer {
    Observable observable;

    public ObserverPrintEmployeeAndEntry(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        HashMap<Person, RegisterEntry> databaseEntry = (HashMap<Person,RegisterEntry>) arg;

        for(HashMap.Entry<Person, RegisterEntry> entry : databaseEntry.entrySet())
        {
            System.out.println(entry.getKey().getName() +

                    " " + entry.getValue());
        }
    }
}
