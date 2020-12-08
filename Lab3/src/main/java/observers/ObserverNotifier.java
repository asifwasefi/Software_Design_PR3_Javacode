package observers;

import database.Database;

import java.util.Observer;
import java.util.Observable;

public class ObserverNotifier implements Observer {
    Observable observable;

    public ObserverNotifier(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);//take observable object e.g. Database and add ObserverNotifier as its observer
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Database)
        {
            System.out.println("Database got updated");
        }

    }
}
