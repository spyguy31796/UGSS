package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
        myPnlAdd.setLayout(new GridLayout(10, 0));
        final String[] labelNames = {"Enter Name:", "Enter Degree Track: ",
                                        "Enter Degree Level: ", "Enter Current Year: ",
                                        "Enter Term: ", "Enter GPA: ",
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
        panel.add(myBtnTransferCollege);
        panel.add(myBtnInternships);
        panel.add(btnJobs);
        panel.add(myBtnAddAlumni);
        myPnlAdd.add(panel);
        add(myPnlAdd, BorderLayout.CENTER);

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
            }
        } else if (theE.getSource() == myBtnInternships) {
            if (myInternships == null) {
                myInternships = new ArrayList();
            }
            final Object temp = performAddInternship();
            if (!STRCANCEL.equals(temp.toString())) {
                myInternships.add((Internship) temp);
            }
        } else if (theE.getSource() == btnJobs) {
            if (myJobs == null) {
                myJobs = new ArrayList();
            }
            final Object temp = performAddJob();
            if (!STRCANCEL.equals(temp.toString())) {
                myJobs.add((Job) temp);
            }
        }
    }

    /**
     * 
     * @return Job Object or Cancel String.
     */
    private Object performAddJob() {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(8,0));
        final String[] labelNames = {"Enter Company Name:", 
                                        "Enter Position: ", "Enter Required Skills: ",
                                        "Enter Description: ",
                                        "Enter Comments:", "Enter Salary: "};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
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
        }
        return new Job(myTxfField[0].getText(), myTxfField[1].getText(),
                myTxfField[2].getText(), myTxfField[3].getText(), myTxfField[4].getText(),
                Double.parseDouble(myTxfField[5].getText()), activeBox.isSelected());

    }

    /**
     * 
     * @return Internship object or Cancel String
     */
    private Object performAddInternship() {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(9, 0));
        final String[] labelNames = {"Enter Company Name:", "Enter Position: ", 
                                     "Enter Required Skills: ",
                                     "Enter Description: ",
                                     "Enter Comments:", "Enter Wage: ",
                                     "Enter Duration:"};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
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
        return new Internship(myTxfField[0].getText(), myTxfField[1].getText(),
                myTxfField[2].getText(), myTxfField[3].getText(),
                myTxfField[4].getText(), Double.parseDouble(myTxfField[5].getText()),
                Integer.parseInt(myTxfField[6].getText()));
    }

    /**
     * 
     * @return Transfer College object or Cancel String.
     */
    private Object performAddTransferCollege() {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(6, 0));
        final String[] labelNames = {"College Name:", "GPA:", "Degree:", "Year:", "Term:"};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
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
        return new TransferCollege(myTxfField[0].getText(),
                Double.parseDouble(myTxfField[1].getText()),myTxfField[2].getText(),
                myTxfField[3].getText(),myTxfField[4].getText());
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
            dTrack = null;
        }
        String dLevel = myTxfField[2].getText();
        if (dLevel.length() == 0) {
            dLevel = null;
        }
        String year = myTxfField[3].getText();
        if (year.length() == 0) {
            year = null;
        }
        String term = myTxfField[4].getText();
        if (term .length() == 0) {
            term = null;
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
        }
        String uEmail = myTxfField[6].getText();
        if (uEmail.length() == 0) {
            uEmail = null;
        }
        String pEmail = myTxfField[7].getText();
        if (pEmail.length() == 0) {
            pEmail = null;
        }
        Alumni al;
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

