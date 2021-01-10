package view.panels;

import Factory.EmployeeFactory;
import controller.RegistrationController;
import person.Person;

import javax.swing.*;

public class RegistrationButtonPanel extends JPanel {

    private JButton checkIn;
    private JButton checkOut;
    private JTextField personName;

    // Get your controller in this private field
    private RegistrationController controller;

    // For now, just make a new person in this class via your factory.
    // You can change this later on to a more unified way
    private Person person;

    // Get your controller in this class via the constructor
    public RegistrationButtonPanel(RegistrationController controller)
    {
        JLabel label = new JLabel("Registration buttons");
        this.checkIn = new JButton("Add Person");
        this.checkOut = new JButton("Remove Person");
        this.personName = new JTextField("Name of Person");

        // Create your temporary person here
//        this.person = new EmployeeFactory().getEmployee("TempEmployee");
        addCheckInButtonActionListener();
        addCheckOutButtonActionListener();


        this.controller = controller;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(this.checkIn);
        this.add(this.checkOut);
        this.add(this.personName);
    }

    public void addCheckInButtonActionListener()
    {
        this.checkIn.addActionListener(listener ->
        {
            // Insert here your controller functionality
            String input  = personName.getText();
            Person p = new Person(input);
            controller.checkIn(p);
        });
    }

    public void addCheckOutButtonActionListener()
    {
        this.checkOut.addActionListener(listener ->
        {
            // Insert here your controller functionality
            String input  = personName.getText();
            Person p = new Person(input);
            controller.checkOut(p);
        });
    }





}
