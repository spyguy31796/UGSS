package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import model.Alumni;
import model.AlumniCollection;


public class AlumniAddGUI extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1779520078061383929L;

    private JPanel pnlAdd =new JPanel();
    private JLabel[] txfLabel = new JLabel[8];
    private JTextField[] txfField = new JTextField[8];
    private JButton btnAddItem;

    /**
     * Use this for Item administration. Add components that contain the list,
     * search and add to this.
     */
    public AlumniAddGUI() {
        setLayout(new BorderLayout());
        createComponents();
        setVisible(true);
        setSize(500, 500);
    }

    /**
     * Create the three panels to add to this GUI. One for list, one for search,
     * one for add.
     * TODO Add internships, jobs, and transferColleges
     */
    private void createComponents() {
        pnlAdd = new JPanel();
        pnlAdd.setLayout(new GridLayout(10, 0));
        String labelNames[] = { "Enter Name:", "Enter Degree Track: ", "Enter Degree Level: ","Enter Current Year: ",
                "Enter Term: ","Enter GPA: ","Enter University Email: ","Enter Personal Email: "};
        for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            txfLabel[i] = new JLabel(labelNames[i]);
            txfField[i] = new JTextField(25);
            panel.add(txfLabel[i]);
            panel.add(txfField[i]);
            pnlAdd.add(panel);
        }
        JPanel panel = new JPanel();
        btnAddItem = new JButton("Add");
        btnAddItem.addActionListener(this);
        panel.add(btnAddItem);
        pnlAdd.add(panel);
        add(pnlAdd, BorderLayout.CENTER);

    }

    /**
     * Make the buttons work!
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddItem) {
            performAddAlumni();
        }
    }

    /**
     * Allows to add an Alumni.
     * TODO Change Item Stuff and Actually check things
     */
    private void performAddAlumni() {

        String name = txfField[0].getText();
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter an item name");
            txfField[0].setFocusable(true);
            return;
        }
        String desc = txfField[1].getText();
        if (desc.length() == 0) {
            desc = null;
        }
        String priceStr = txfField[2].getText();
        double price = 0.0;
        if (priceStr.length() != 0) {
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter price as decimal");
                txfField[2].setText("");
                txfField[2].setFocusable(true);
                return;
            }
        }
        String condition = txfField[3].getText();
        if (condition.length() == 0) {
            condition = null;
        }
        Alumni al;
        if (desc == null) {
            al = null;
        } else {
            al = null;
        }
        String message = "Alumni add failed";
        if (AlumniCollection.add(al)) {
            message = "Alumni added";
        }
        JOptionPane.showMessageDialog(null, message);

        // Clear all text fields.
        for (int i = 0; i < txfField.length; i++) {
            if (txfField[i].getText().length() != 0) {
                txfField[i].setText("");
            }
        }
    }
}

