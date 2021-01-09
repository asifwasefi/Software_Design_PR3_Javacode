package view;

import view.panels.ListPanel;
import view.panels.RegistrationButtonPanel;

import javax.swing.*;
import java.awt.*;

public class ViewFrame extends JFrame
{
    // Get your controller in this private field
//    RegistrationController registrationController;
    ListPanel panel;
    RegistrationButtonPanel buttons;

    public ViewFrame()
    {
        super("Registration");
    }

    public void initialize()
    {
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Pass the controller to the ButtonPanel
        buttons = new RegistrationButtonPanel();
        panel = new ListPanel();

        this.add(panel);
        this.add(buttons);
        this.setVisible(true);
    }
}
