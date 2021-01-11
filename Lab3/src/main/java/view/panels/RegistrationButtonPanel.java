package view.panels;

import Factory.EmployeeFactory;
import controller.RegistrationController;
import controller.TicketController;
import person.Person;
import ticket.Flight;
import ticket.Ticket;

import javax.swing.*;

public class RegistrationButtonPanel extends JPanel {

    private JButton addPerson;
    private JButton removePerson;
    private JTextField personName;
    private JTextField ticketAmount;
    private JTextField payerName;
    private JButton addTicket;
    private JButton calculateBills;

    // Get your controller in this private field
    private RegistrationController controller;
    private TicketController ticketController;

    // For now, just make a new person in this class via your factory.
    // You can change this later on to a more unified way
//    private Person person;

    // Get your controller in this class via the constructor
    public RegistrationButtonPanel(RegistrationController controller, TicketController ticketController)
    {
        JLabel label = new JLabel("Registration buttons");
        this.addPerson = new JButton("Add Person");
        this.removePerson= new JButton("Remove Person");
        this.personName = new JTextField("Name of Person");
        this.ticketAmount = new JTextField("Ticket Amount");
        this.payerName = new JTextField("Who paid ticket?");
        this.addTicket = new JButton("Add Ticket");
        this.calculateBills = new JButton("Calculate Bills");

        // Create your temporary person here
//        this.person = new EmployeeFactory().getEmployee("TempEmployee");
        addPersonButtonActionListener();
        removePersonButtonActionListener();
        addTicketButtonActionListener();
        calculateBillsActionListener();


        this.controller = controller;
        this.ticketController = ticketController;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(this.personName);
        this.add(this.addPerson);
        this.add(this.removePerson);
        this.add(this.ticketAmount);
        this.add(this.payerName);
        this.add(this.addTicket);
        this.add(this.calculateBills);
    }

    public void addPersonButtonActionListener()
    {
        this.addPerson.addActionListener(listener ->
        {
            // Insert here your controller functionality
            String input  = personName.getText();
            Person p = new Person(input);
            controller.checkIn(p);
        });
    }

    public void removePersonButtonActionListener()
    {
        this.removePerson.addActionListener(listener ->
        {
            // Insert here your controller functionality
            String input  = personName.getText();
            Person p = new Person(input);
            controller.checkOut(p);
        });
    }

    public void addTicketButtonActionListener()
    {
        this.addTicket.addActionListener(listener ->
        {
            // Insert here your controller functionality
            String input  = payerName.getText();
            Person p = new Person(input);
            Double price = Double.parseDouble(ticketAmount.getText());
            Ticket t = new Flight(price);
            ticketController.addTicket(t,p);
        });
    }

    public void calculateBillsActionListener()
    {
        this.calculateBills.addActionListener(listener ->
        {
            // Insert here your controller functionality
            ticketController.calculateTotalBill();
        });
    }





}
