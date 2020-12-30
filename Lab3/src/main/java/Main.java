import Factory.EmployeeFactory;
import controller.Controller;
import controller.RegistrationController;
import database.Database;
import database.RegistrationDB;
import employee.CustomerService;
import employee.Employee;
import employee.Manager;
import employee.Programmer;
import observers.ObserverNotifier;
import observers.ObserverPrintEmployeeAndEntry;
import observers.ObserverPrintEntry;
import register_entry.RegisterEntry;

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
        Database timedb = RegistrationDB.getInstance();
        Controller register= new RegistrationController(timedb);

        //Add observer objects of the Observable database
        ObserverNotifier observerNotifier = new ObserverNotifier(timedb);
        ObserverPrintEmployeeAndEntry observerPrintEmployeeAndEntry = new ObserverPrintEmployeeAndEntry(timedb);
        ObserverPrintEntry observerPrintEntry = new ObserverPrintEntry(timedb);

//        Employee e1 = new Programmer("Alice");
//        Employee e2 = new CustomerService("Bob");
//        Employee e3 = new Manager("Charlie");


        //Make an EmployeeFactory
        EmployeeFactory employeeFactory = new EmployeeFactory();
        Employee e1 = employeeFactory.getEmployee("Alice","Programmer");
        Employee e2 = employeeFactory.getEmployee("Bob","CustomerService");
        Employee e3 = employeeFactory.getEmployee("Charlie","Manager");

        register.checkIn(e1);
        register.checkIn(e2);

//        print(e1, timedb.getEntry(e1));
//        print(e2, timedb.getEntry(e2));

        // We missed the print section of this checkin
        register.checkIn(e3);

        register.checkOut(e1);
        register.checkOut(e2);
        register.checkOut(e3);

//        print(e1, timedb.getEntry(e1));
//        print(e2, timedb.getEntry(e2));
//        print(e3, timedb.getEntry(e3));
    }

    public void print(Employee e, RegisterEntry re)
    {
        System.out.println(e.getName() +
                " (" + e.getFunction() + ")" +
                " " + re);
    }
}
