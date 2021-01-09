package view;

import controller.RegistrationController;
import database.RegistrationDB;
import employee.Employee;
import register_entry.RegisterEntry;
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
    ListPanel panel;
    RegistrationButtonPanel buttons;

    public ViewFrame(RegistrationController controller)
    {
        super("Registration");
        this.registrationController = controller;
        RegistrationDB.getInstance().addObserver(this);//take observable object e.g. Database and add ObserverNotifier as its observer
    }

    public void initialize()
    {
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Pass the controller to the ButtonPanel
        buttons = new RegistrationButtonPanel(registrationController);
        panel = new ListPanel();

        this.add(panel);
        this.add(buttons);
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

        HashMap<Employee, RegisterEntry> databaseEntry = (HashMap<Employee,RegisterEntry>) arg;
        for(RegisterEntry re : databaseEntry.values())
        {
            panel.addEntry(re);
        }
        
    }
}
