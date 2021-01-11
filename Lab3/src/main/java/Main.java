import controller.RegistrationController;
import controller.TicketController;
import database.Database;
import database.DatabaseTicket;
import database.RegistrationDB;
import database.RegistrationTicket;
import observers.ObserverPrintTicket;
import person.Person;
import Factory.EmployeeFactory;
import ticket.Flight;
import ticket.Ticket;
import view.ViewFrame;
import observers.ObserverNotifier;
import observers.ObserverPrintEmployeeAndEntry;
import observers.ObserverPrintEntry;

public class Main
{
    public static void main(String[] args)
    {
        Main main = new Main();
        main.run();
    }

    public Main()
    {

    }

    public void run()
    {
        // Replace with your own objects
        Database timedb = RegistrationDB.getInstance();
        DatabaseTicket ticketdb = RegistrationTicket.getInstance();
        RegistrationController register= new RegistrationController(timedb);
        TicketController ticketController = new TicketController(ticketdb);
        EmployeeFactory factory = new EmployeeFactory();

        ViewFrame view = new ViewFrame(register, ticketController);
        view.initialize();

//        // Replace with your own observers
//        PrintEntry printEntry = new PrintEntry();
//        PrintUpdated printUpdated = new PrintUpdated();
//        timedb.addObserver(printEntry);
//        timedb.addObserver(printUpdated);


        //Add observer objects of the Observable database
        ObserverNotifier observerNotifier = new ObserverNotifier(timedb);
        ObserverPrintEmployeeAndEntry observerPrintEmployeeAndEntry = new ObserverPrintEmployeeAndEntry(timedb);
        ObserverPrintEntry observerPrintEntry = new ObserverPrintEntry(timedb);
        ObserverPrintTicket observerPrintTicket = new ObserverPrintTicket(ticketdb);

//        // Replace with your own person creation methods
//        Person e1 = factory.getEmployee("Alice");
//        Person e2 = factory.getEmployee("Bob");
//        Person e3 = factory.getEmployee("Charlie");
//
//        sleep(3000);
//
//        register.checkIn(e1);
//        register.checkIn(e2);
//        register.checkIn(e3);
//
//        sleep(1000);
//        register.checkOut(e1);
//        sleep(1000);
//        register.checkOut(e2);
//        sleep(1000);
//        register.checkOut(e3);




    }




    public void sleep(int millis)
    {
        try
        {
            System.out.print("Sleeping [    ]\r");
            Thread.sleep(millis);
            System.out.println("Sleeping [ OK ]");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
