package view;

import controller.RegistrationController;
import controller.TicketController;
import database.Database;
import database.DatabaseTicket;
import database.RegistrationDB;
import database.RegistrationTicket;
import person.Person;
import register_entry.RegisterEntry;
import ticket.Ticket;
import view.panels.ListPanel;
import view.panels.RegistrationButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class ViewFrame extends JFrame implements Observer
{
    // Get your controller in this private field
    RegistrationController registrationController;
    TicketController ticketController;
    ListPanel panel;
    RegistrationButtonPanel buttons;

    public ViewFrame(RegistrationController controller, TicketController ticketController)
    {
        super("Registration");
        this.registrationController = controller;
        this.ticketController = ticketController;
        RegistrationDB.getInstance().addObserver(this);//take observable object e.g. Database and add viewFrame as its observer
        RegistrationTicket.getInstance().addObserver(this);////take observable object e.g. DatabaseTicket and add viewFrame as its observer
    }

    public void initialize()
    {
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Pass the controller to the ButtonPanel
        buttons = new RegistrationButtonPanel(registrationController, ticketController);
        panel = new ListPanel();

        this.add(panel);
        this.add(buttons);
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

        if(o instanceof Database)
        {
            HashMap<Person, RegisterEntry> databaseEntry = (HashMap<Person,RegisterEntry>) arg;
            for(RegisterEntry re : databaseEntry.values())
            {
                panel.addEntry(re);
            }
        }

        
    }
}
