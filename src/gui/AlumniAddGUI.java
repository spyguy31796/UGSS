package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Alumni;
import model.AlumniCollection;
import model.Internship;
import model.Job;
import model.TransferCollege;

/**
 * 
 * @author GROUP8
 * @version 12/3/2016
 *
 */
public class AlumniAddGUI extends JPanel implements ActionListener {
    /**
     * Required serialization #.
     */
    private static final long serialVersionUID = 1779520078061383929L;
    /**
     * String representing the word Cancelled.
     */
    private static final String STRCANCEL = "cancelled";
    /**
     * ArrayList containing jobs to add to the alumni.
     */
    private ArrayList myJobs;
    /**
     * ArrayList containing colleges to add to the alumni.
     */
    private ArrayList myColleges;
    /**
     * ArrayList containing internships to add to the alumni.
     */
    private ArrayList myInternships;
    /**
     * Add Panel.
     */
    private JPanel myPnlAdd = new JPanel();
    /**
     * Labels for Fields.
     */
    private JLabel[] myTxfLabel = new JLabel[8];
    /**
     * Fields for input jobs.
     */
    private JTextField[] myTxfFieldJobs = new JTextField[6];
    /**
     * Fields for input internships.
     */
    private JTextField[] myTxfFieldInternships = new JTextField[7];
    /**
     * Fields for input schools.
     */
    private JTextField[] myTxfFieldSchools = new JTextField[5];
    /**
     * Fields for input.
     */
    private JTextField[] myTxfField = new JTextField[8];
    /**
     * Button to add alumni.
     */
    private JButton myBtnAddAlumni; 
    /**
     * Button to add college.
     */
    private JButton myBtnTransferCollege; 
    /**
     * Button to add internship.
     */
    private JButton myBtnInternships;
    /**
     * Button to add job.
     */
    private JButton btnJobs;
    /**
     * Button to remove transferCollege, internship, or job info.
     */
    private JButton btnRemove;

    JComboBox comboCollege;

    JComboBox comboJob;

    JComboBox comboInternships;

    JButton removeCollege;

    JButton removeJob;

    JButton removeInternship;

    /**
     * Used for Alumni administration.
     */
    public AlumniAddGUI() {
        setLayout(new BorderLayout());
        createComponents();
        setVisible(true);
        setSize(500, 500);
    }

    /**
     * Creates the components that allow data to be inputted into the system.
     */
    private void createComponents() {
        myPnlAdd = new JPanel();
        myPnlAdd.setLayout(new GridLayout(9, 0));
        final String[] labelNames = {"Enter Name:", "Enter Degree Track: ",
                "Enter Degree Level: ", "Enter Graduated Year: ",
                "Enter Graduated Term: ", "Enter GPA: ",
                "Enter University Email: ", 
        "Enter Personal Email: "};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            myPnlAdd.add(panel);
        }
        final JPanel panel = new JPanel();
        myBtnTransferCollege = new JButton("New Transfer College");
        myBtnTransferCollege.addActionListener(this);
        myBtnInternships = new JButton("New Internships");
        myBtnInternships.addActionListener(this);
        btnJobs = new JButton("New Jobs");
        btnJobs.addActionListener(this);
        myBtnAddAlumni = new JButton("Add");
        myBtnAddAlumni.addActionListener(this);
        btnRemove = new JButton("Remove Records");
        btnRemove.addActionListener(this);
        JPanel bPanel = new JPanel();
        JPanel superPanel = new JPanel();
        superPanel.setLayout(new GridLayout(2,0));
        bPanel.add(myBtnTransferCollege);
        bPanel.add(myBtnInternships);
        bPanel.add(btnJobs);
        bPanel.add(btnRemove);
        superPanel.add(bPanel);
        superPanel.add(myBtnAddAlumni);
        //panel.add(superPanel);
        myPnlAdd.add(panel);
        add(myPnlAdd, BorderLayout.CENTER);
        add(superPanel, BorderLayout.PAGE_END);
    }

    /**
     * Make the buttons work!
     */
    @Override
    public void actionPerformed(final ActionEvent theE) {
        if (theE.getSource() == myBtnAddAlumni) {
            performAddAlumni();
        } else if (theE.getSource() == myBtnTransferCollege) {
            if (myColleges == null) {
                myColleges = new ArrayList();
            }
            final Object temp = performAddTransferCollege();
            if (!STRCANCEL.equals(temp.toString())) {
                myColleges.add((TransferCollege) temp);
                JOptionPane.showMessageDialog(null, "Transfer College "+temp+" added!");
            }
        } else if (theE.getSource() == myBtnInternships) {
            if (myInternships == null) {
                myInternships = new ArrayList();
            }
            final Object temp = performAddInternship();
            if (!STRCANCEL.equals(temp.toString())) {
                myInternships.add((Internship) temp);
                JOptionPane.showMessageDialog(null, "Internship at "+temp+" added!");
            }
        } else if (theE.getSource() == btnJobs) {
            if (myJobs == null) {
                myJobs = new ArrayList();
            }
            final Object temp = performAddJob();
            if (!STRCANCEL.equals(temp.toString())) {
                myJobs.add((Job) temp);
                JOptionPane.showMessageDialog(null, "Job at "+temp+" added!");
            }
        } else if (theE.getSource() == btnRemove) {
            if(myJobs == null){
                myJobs = new ArrayList();
            }
            if(myColleges == null){
                myColleges = new ArrayList();
            }
            if(myInternships == null){
                myInternships = new ArrayList();
            }
            performRemove();
        } else if(theE.getSource() == removeCollege) {
            if(myColleges!=null&&myColleges.size()!=0){
                myColleges.remove(comboCollege.getSelectedIndex());
                refreshCombo(comboCollege,myColleges);
            }
        } else if(theE.getSource() == removeJob) {
            if(myJobs!=null&&myJobs.size()!=0){
                myJobs.remove(comboJob.getSelectedIndex());
                refreshCombo(comboJob,myJobs);
            }
        } else if(theE.getSource() == removeInternship) {
            if(myInternships!=null&&myInternships.size()!=0){
                myInternships.remove(comboInternships.getSelectedIndex());
                refreshCombo(comboInternships,myInternships);
            }
        }
    }
    private void refreshCombo(final JComboBox theJC, final ArrayList theAR){
        theJC.removeAllItems();
        for(int i = 0;i < theAR.size(); i++){
            theJC.addItem(theAR.get(i));
        }
    }

    /**
     * Removes selected Job, Internship, or College Records
     */
    private void performRemove() {
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(4,0));
        comboCollege = new JComboBox(myColleges.toArray());
        comboJob = new JComboBox(myJobs.toArray());
        comboInternships = new JComboBox(myInternships.toArray());
        removeCollege = new JButton("Remove");
        removeCollege.addActionListener(this);
        removeJob = new JButton("Remove");
        removeJob.addActionListener(this);
        removeInternship = new JButton("Remove");
        removeInternship.addActionListener(this);
        fields.add(comboCollege);
        fields.add(removeCollege);
        fields.add(comboJob);
        fields.add(removeJob);
        fields.add(comboInternships);
        fields.add(removeInternship);
        final int check = JOptionPane.showConfirmDialog(null, fields, 
                "Data Entry", JOptionPane.OK_CANCEL_OPTION);
    }

    /**
     * 
     * @return Job Object or Cancel String.
     */
    private Object performAddJob() {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(8,0));
        final String[] labelNames = {"*Enter Company Name:", 
                "*Enter Position: ", "Enter Required Skills: ",
                "Enter Description: ",
                "Enter Comments:", "*Enter Salary: "};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfFieldJobs[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfFieldJobs[i]);
            fields.add(panel);
        }
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));
        final JCheckBox activeBox = new JCheckBox();
        activeBox.setSelected(false);
        panel.add(new JLabel("Active?"));
        panel.add(activeBox);
        fields.add(panel);
        final int check = JOptionPane.showConfirmDialog(null, fields, 
                "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.CANCEL_OPTION) {
            return STRCANCEL;
        }if(myTxfFieldJobs[0].getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Enter a Company Name");
            return STRCANCEL;
        }else if(myTxfFieldJobs[1].getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Enter a Position");
            return STRCANCEL;
        }else if(myTxfFieldJobs[2].getText().length() == 0){
            myTxfFieldJobs[2].setText("");
        }else if(myTxfFieldJobs[3].getText().length() == 0){
            myTxfFieldJobs[3].setText("");
        }else if(myTxfFieldJobs[4].getText().length() == 0){
            myTxfFieldJobs[4].setText("");
        }else if(myTxfFieldJobs[5].getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Enter Salary");
            return STRCANCEL;
        }        

        if(myTxfFieldJobs[5].getText().length() == 0){
            try{Double.parseDouble(myTxfFieldJobs[5].getText());}catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Salary Must be a Decimal");
                return STRCANCEL;
            }
        }
        return new Job(myTxfFieldJobs[0].getText(), myTxfFieldJobs[1].getText(),
                myTxfFieldJobs[2].getText(), myTxfFieldJobs[3].getText(), myTxfFieldJobs[4].getText(),
                Double.parseDouble(myTxfFieldJobs[5].getText()), activeBox.isSelected());

    }

    /**
     * 
     * @return Internship object or Cancel String
     */
    private Object performAddInternship() {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(9, 0));
        final String[] labelNames = {"*Enter Company Name:", "*Enter Position: ", 
                "Enter Required Skills: ",
                "Enter Description: ",
                "Enter Comments:", "*Enter Wage: ",
        "*Enter Duration (in years):"};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfFieldInternships[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfFieldInternships[i]);
            fields.add(panel);
        }
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));
        fields.add(panel);
        final int check = JOptionPane.showConfirmDialog(null, fields,
                "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.CANCEL_OPTION) {
            return STRCANCEL;
        }if(myTxfFieldInternships[0].getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Enter a Company Name");
            return STRCANCEL;
        }else if(myTxfFieldInternships[1].getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Enter a Position");
            return STRCANCEL;
        }else if(myTxfFieldInternships[2].getText().length() == 0){
            myTxfFieldInternships[2].setText("");
        }else if(myTxfFieldInternships[3].getText().length() == 0){
            myTxfFieldInternships[3].setText("");
        }else if(myTxfFieldInternships[4].getText().length() == 0){
            myTxfFieldInternships[4].setText("");
        }else if(myTxfFieldInternships[5].getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Enter Wage");
            return STRCANCEL;
        }else if(myTxfFieldInternships[6].getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Enter Duration");
            return STRCANCEL;
        }
        if(myTxfFieldInternships[5].getText().length() == 0){
            try{Double.parseDouble(myTxfFieldInternships[5].getText());}catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Wage Must be a Decimal");
                return STRCANCEL;
            }
        }
        if(myTxfFieldInternships[6].getText().length() == 0){
            try{Integer.parseInt(myTxfFieldInternships[6].getText());}catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Duration Must be an Integer");
                return STRCANCEL;
            }
        }
        return new Internship(myTxfFieldInternships[0].getText(), myTxfFieldInternships[1].getText(),
                myTxfFieldInternships[2].getText(), myTxfFieldInternships[3].getText(),
                myTxfFieldInternships[4].getText(), Double.parseDouble(myTxfFieldInternships[5].getText()),
                Integer.parseInt(myTxfFieldInternships[6].getText()));
    }

    /**
     * 
     * @return Transfer College object or Cancel String.
     */
    private Object performAddTransferCollege() {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(6, 0));
        final String[] labelNames = {"*College Name:", "*GPA:", "*Degree:", "*Year:", "*Term:"};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfFieldSchools[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfFieldSchools[i]);
            fields.add(panel);
        }
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));
        fields.add(panel);
        final int check = JOptionPane.showConfirmDialog(null, fields, 
                "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.CANCEL_OPTION) {
            return STRCANCEL;
        }
        if(myTxfFieldSchools[0].getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Enter a College Name");
            return STRCANCEL;
        }else if(myTxfFieldSchools[1].getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Enter a GPA");
            return STRCANCEL; 
        }else if(myTxfFieldSchools[2].getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Enter a Degree");
            return STRCANCEL;
        }else if(myTxfFieldSchools[3].getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Enter a Year");
            return STRCANCEL;
        }else if(myTxfFieldSchools[4].getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Enter a Term");
            return STRCANCEL;
        }
        if(myTxfFieldSchools[1].getText().length() != 0){
            try{Double.parseDouble(myTxfFieldSchools[1].getText());}catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "GPA must be formatted as a decimal");
                return STRCANCEL;
            }
        }
        return new TransferCollege(myTxfFieldSchools[0].getText(), 
                Double.parseDouble(myTxfFieldSchools[1].getText()), myTxfFieldSchools[2].getText(), 
                myTxfFieldSchools[3].getText(), myTxfFieldSchools[4].getText());

    }

    /**
     * Allows to add an Alumni.
     * TODO Needs to check the various possible constructions and null cases.
     * TODO Needs to use correct ids
     * TODO Change Item Stuff and Actually check things
     */
    private void performAddAlumni() {

        final String name = myTxfField[0].getText();
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter an Alumni name");
            myTxfField[0].setFocusable(true);
            return;
        }
        String dTrack = myTxfField[1].getText();
        if (dTrack.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Degree Track");
            myTxfField[1].setFocusable(true);
            return;
        }
        String dLevel = myTxfField[2].getText();
        if (dLevel.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter an Degree Level");
            myTxfField[2].setFocusable(true);
            return;
        }
        String year = myTxfField[3].getText();
        if (year.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Graduated Year");
            myTxfField[3].setFocusable(true);
            return;
        }
        String term = myTxfField[4].getText();
        if (term .length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Graduated Term");
            myTxfField[4].setFocusable(true);
            return;
        }
        String strGpa = myTxfField[5].getText();
        double gpa = 0.0;
        if (strGpa.length() != 0) {
            try {
                gpa = Double.parseDouble(strGpa);
            } catch (final NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter GPA as decimal");
                myTxfField[5].setText("");
                myTxfField[5].setFocusable(true);
                return;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Enter a GPA");
            myTxfField[5].setFocusable(true);
            return;
        }
        String uEmail = myTxfField[6].getText();
        if (uEmail.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter an University Email");
            myTxfField[6].setFocusable(true);
            return;
        }
        String pEmail = myTxfField[7].getText();
        if (pEmail.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Personal Email");
            myTxfField[7].setFocusable(true);
            return;
        }
        Alumni al;
        if(myInternships==null||myInternships.size() == 0){
            myInternships = null;
        }
        if(myJobs==null||myJobs.size() == 0){
            myJobs = null;
        }
        if(myColleges==null||myColleges.size() == 0){
            myColleges = null;
        }
        al = new Alumni(name, dTrack, dLevel,
                year, term, gpa, uEmail, pEmail, myInternships, myJobs, myColleges);
        String message = "Alumni add failed";
        if (AlumniCollection.add(al)) {
            message = "Alumni added";
        }
        JOptionPane.showMessageDialog(null, message);
        // Clear all text fields.
        for (int i = 0; i < myTxfField.length; i++) {
            if (myTxfField[i].getText().length() != 0) {
                myTxfField[i].setText("");
            }
        }

    }
}

