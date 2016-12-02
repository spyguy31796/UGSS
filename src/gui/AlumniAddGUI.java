package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


import model.Alumni;
import model.AlumniCollection;
import model.Internship;
import model.Job;
import model.TransferCollege;


public class AlumniAddGUI extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1779520078061383929L;
    ArrayList jobs, colleges, internships;
    private JPanel pnlAdd =new JPanel();
    private JLabel[] txfLabel = new JLabel[8];
    private JTextField[] txfField = new JTextField[8];
    private JButton btnAddItem, btnTransferCollege, btnInternships, btnJobs;

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
        btnTransferCollege = new JButton("New Transfer College");
        btnTransferCollege.addActionListener(this);
        btnInternships = new JButton("New Internships");
        btnInternships.addActionListener(this);
        btnJobs = new JButton("New Jobs");
        btnJobs.addActionListener(this);
        btnAddItem = new JButton("Add");
        btnAddItem.addActionListener(this);
        panel.add(btnTransferCollege);
        panel.add(btnInternships);
        panel.add(btnJobs);
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
        }else if(e.getSource() == btnTransferCollege){
            if(colleges==null){
                colleges = new ArrayList();
            }
            Object temp = performAddTransferCollege();
            if(temp.toString()!="Cancelled"){
                colleges.add((TransferCollege)temp);
            }
        }else if(e.getSource() == btnInternships){
            if(internships==null){
                internships = new ArrayList();
            }
            Object temp = performAddInternship();
            if(temp.toString()!="Cancelled"){
                internships.add((Internship)temp);
            }
        }else if(e.getSource() == btnJobs){
            if(jobs==null){
                jobs = new ArrayList();
            }
            Object temp = performAddJob();
            if(temp.toString()!="Cancelled"){
                jobs.add((Job)temp);
            }
        }
    }

    private Object performAddJob() {
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(8,0));
        String labelNames[] = { "Enter Company Name:", "Enter Position: ", "Enter Required Skills: ","Enter Description: ","Enter Comments:","Enter Salary: "};
        for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            txfLabel[i] = new JLabel(labelNames[i]);
            txfField[i] = new JTextField(25);
            panel.add(txfLabel[i]);
            panel.add(txfField[i]);
            fields.add(panel);
        }
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));
        JCheckBox activeBox = new JCheckBox();
        activeBox.setSelected(false);
        panel.add(new JLabel("Active?"));
        panel.add(activeBox);
        fields.add(panel);
        int check = JOptionPane.showConfirmDialog(null, fields, "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        //System.out.println(txfField[0].getText()+" "+txfField[1].getText()+" "+txfField[2].getText()+" "+txfField[3].getText()+" "+txfField[4].getText()+" "+Double.parseDouble(txfField[5].getText())+" "+activeBox.isSelected());
        if(check==JOptionPane.CANCEL_OPTION){
            return "Cancelled";
        }
        return new Job(txfField[0].getText(),txfField[1].getText(),txfField[2].getText(),txfField[3].getText(),txfField[4].getText(),Double.parseDouble(txfField[5].getText()),activeBox.isSelected());

    }

    private Object performAddInternship() {
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(9,0));
        String labelNames[] = { "Enter Company Name:", "Enter Position: ", "Enter Required Skills: ","Enter Description: ","Enter Comments:","Enter Wage: ","Enter Duration:"};
        for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            txfLabel[i] = new JLabel(labelNames[i]);
            txfField[i] = new JTextField(25);
            panel.add(txfLabel[i]);
            panel.add(txfField[i]);
            fields.add(panel);
        }
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));
        fields.add(panel);
        int check = JOptionPane.showConfirmDialog(null, fields, "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        //System.out.println(txfField[0].getText()+" "+txfField[1].getText()+" "+txfField[2].getText()+" "+txfField[3].getText()+" "+txfField[4].getText()+" "+Double.parseDouble(txfField[5].getText())+" "+activeBox.isSelected());
        if(check==JOptionPane.CANCEL_OPTION){
            return "Cancelled";
        }
        return new Internship(txfField[0].getText(),txfField[1].getText(),txfField[2].getText(),txfField[3].getText(),txfField[4].getText(),Double.parseDouble(txfField[5].getText()),Integer.parseInt(txfField[6].getText()));
    }

    private Object performAddTransferCollege() {
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(6,0));
        String labelNames[] = { "College Name:", "GPA:", "Degree:","Year:","Term:"};
        for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            txfLabel[i] = new JLabel(labelNames[i]);
            txfField[i] = new JTextField(25);
            panel.add(txfLabel[i]);
            panel.add(txfField[i]);
            fields.add(panel);
        }
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));
        fields.add(panel);
        int check = JOptionPane.showConfirmDialog(null, fields, "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        //System.out.println(txfField[0].getText()+" "+txfField[1].getText()+" "+txfField[2].getText()+" "+txfField[3].getText()+" "+txfField[4].getText()+" "+Double.parseDouble(txfField[5].getText())+" "+activeBox.isSelected());
        if(check==JOptionPane.CANCEL_OPTION){
            return "Cancelled";
        }
        return new TransferCollege(txfField[0].getText(),Double.parseDouble(txfField[1].getText()),txfField[2].getText(),txfField[3].getText(),txfField[4].getText());
    }

    /**
     * Allows to add an Alumni.
     * TODO Needs to check the various possible constructions and null cases.
     * TODO Needs to use correct ids
     * TODO Change Item Stuff and Actually check things
     */
    private void performAddAlumni() {

        String name = txfField[0].getText();
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter an Alumni name");
            txfField[0].setFocusable(true);
            return;
        }
        String dTrack = txfField[1].getText();
        if (dTrack.length() == 0) {
            dTrack = null;
        }
        String dLevel = txfField[2].getText();
        if (dLevel.length() == 0) {
            dLevel = null;
        }
        String Year = txfField[3].getText();
        if (Year.length() == 0) {
            Year = null;
        }
        String Term = txfField[4].getText();
        if (Term .length() == 0) {
            Term = null;
        }
        String GPA = txfField[5].getText();
        double gpa = 0.0;
        if (GPA.length() != 0) {
            try {
                gpa = Double.parseDouble(GPA);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter GPA as decimal");
                txfField[5].setText("");
                txfField[5].setFocusable(true);
                return;
            }
        }
        String uEmail = txfField[6].getText();
        if (uEmail.length() == 0) {
            uEmail = null;
        }
        String pEmail = txfField[7].getText();
        if (pEmail.length() == 0) {
            pEmail = null;
        }
        Alumni al;
        al = new Alumni(name, 1, dTrack, dLevel, Year, Term, gpa, uEmail, pEmail,null,null,null);
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

