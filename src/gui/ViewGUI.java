package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Alumni;
import model.AlumniCollection;
import model.DataTypes;
import model.Internship;
import model.Job;
import model.TransferCollege;

public class ViewGUI extends JPanel implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = -1587871730380712674L;
    
    private JButton nameSearch, idSearch, editBtn, modifyBtn, addSelection, removeSelection, modifySelection,
    viewInternshipsBtn, viewJobsBtn, viewCollegesBtn;
    private JTextField searchTerm;
    private JPanel currentPanel, listPanel;
    
    private Object[][] mData;
    private List<Alumni> alumniList;
    Alumni currentAlumniSelected;
    
    private String[] mAlumniColumnNames = { "id", "name", "degreeTrack", "degreeLevel", "year", "term", "gpa", "uniEmail", "persEmail", "Internships", "Jobs", "TransferColleges"};
    private JTextField[] myModifyFields = new JTextField[8];
    private JTextField[] myJobFields = new JTextField[6];
    private JTextField[] myInternFields = new JTextField[7];
    private JTextField[] myCollegeFields = new JTextField[5];
    JLabel editLabel;
    
    JTable table, viewTable;
    
    JComboBox modifyTypeSelections, itemRemoveModifySelection;

    ViewGUI() {
        setLayout(new BorderLayout());

        alumniList = getData(null, null);
        createComponents();
        setVisible(true);
        setSize(500, 500);
    }
    
    private List<Alumni> getData(DataTypes searchType, String searchKey) {

        List<Alumni> searchData = new ArrayList<Alumni>();
        // If there is specific data to look for, load that data. Otherwise, display all alumni.
        if (searchKey != null && searchType != null) {
            // Search for names only if looking for name
            if (searchType == DataTypes.NAME) {
                searchData = AlumniCollection.searchName(searchKey);
            }
            else if (searchType == DataTypes.ID) {
                // Search for an ID
                try {
                    int id = Integer.parseInt(searchKey);
                    Alumni alum = AlumniCollection.searchID(id);
                    
                    if (alum != null) {
                        searchData.clear();
                        searchData.add(alum);
                    }
                    
                }
                catch (NumberFormatException e) {
                    searchData = AlumniCollection.getAlumni();
                }              
            }
        }
        else {
            // Otherwise, just display all alumni
            searchData = AlumniCollection.getAlumni();
        }

        if (searchData != null) {
            mData = new Object[searchData.size()][mAlumniColumnNames.length];
            for (int i = 0; i < searchData.size(); i++) {
                mData[i][0] = searchData.get(i).getMyID();
                mData[i][1] = searchData.get(i).getMyName();
                mData[i][2] = searchData.get(i).getMyDegreeTrack();
                mData[i][3] = searchData.get(i).getMyDegreeLevel();
                mData[i][4] = searchData.get(i).getMyYear();
                mData[i][5] = searchData.get(i).getMyTerm();
                mData[i][6] = searchData.get(i).getMyCurrentGPA();
                mData[i][7] = searchData.get(i).getMyUniEmail();
                mData[i][8] = searchData.get(i).getMyPersEmail();
                mData[i][9] = 0;
                if (searchData.get(i).getMyInternships() != null) {
                    mData[i][9] = searchData.get(i).getMyInternships().size();
                }
                mData[i][10] = 0;     
                if (searchData.get(i).getMyJobs() != null) {
                    mData[i][10] = searchData.get(i).getMyJobs().size();
                }
                mData[i][11] = 0; 
                if (searchData.get(i).getMyTransferColleges() != null) {
                    mData[i][11] = searchData.get(i).getMyTransferColleges().size(); 
                }
            }
        }

        return searchData;
    }

    private void createComponents() {
        add(createSearchPanel(), BorderLayout.NORTH);
        currentPanel = new JPanel();
        listPanel = createListPanel();
        
        currentPanel.add(listPanel);
        add(currentPanel, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel();
                
        editBtn = new JButton("Edit");
        editBtn.addActionListener(this);
        btnPanel.add(editBtn);
        
        viewInternshipsBtn = new JButton("View Internships");
        viewInternshipsBtn.addActionListener(this);
        btnPanel.add(viewInternshipsBtn);
        
        viewJobsBtn = new JButton("View Jobs");
        viewJobsBtn.addActionListener(this);
        btnPanel.add(viewJobsBtn);
        
        viewCollegesBtn = new JButton("View Colleges");
        viewCollegesBtn.addActionListener(this);
        btnPanel.add(viewCollegesBtn);
        
        add(btnPanel, BorderLayout.SOUTH);
   
    }
    
    private JPanel createSearchPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(3,2));
       
        searchTerm = new JTextField(25);
        JLabel searchLabel = new JLabel("Search For: ");
        
        nameSearch = new JButton("Search by Name");
        nameSearch.addActionListener(this);
        
        idSearch = new JButton("Search by ID");
        idSearch.addActionListener(this);
        
        mainPanel.add(searchLabel);
        mainPanel.add(searchTerm);
        mainPanel.add(nameSearch);
        mainPanel.add(idSearch);
        
        return mainPanel;
    }
    
    private JPanel createListPanel() {
        JPanel mainPanel = new JPanel();
        TableModel model = new DefaultTableModel(mData, mAlumniColumnNames)
        {
          public boolean isCellEditable(int row, int column)
          {
            return false;//This causes all cells to be not editable
          }
        };
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mainPanel.add(scrollPane);
               
        return mainPanel;        
    }
    
    
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nameSearch) {
            currentPanel.removeAll();
            alumniList = getData(DataTypes.NAME, searchTerm.getText());
            // Recreate the list to update it 
            listPanel = createListPanel();
            currentPanel.add(listPanel);
            currentPanel.revalidate();
            this.repaint();
        }
        else if (e.getSource() == idSearch) {
            currentPanel.removeAll();
            alumniList = getData(DataTypes.ID, searchTerm.getText());
            // Recreate the list to update it 
            listPanel = createListPanel();
            currentPanel.add(listPanel);
            currentPanel.revalidate();
            this.repaint();
        }
        else if (e.getSource() == editBtn) {
            int rowSelected = table.getSelectedRow();
            if (rowSelected == -1) {
                JOptionPane.showMessageDialog(null, "No Alumni Selected");
            }
            else {
                currentAlumniSelected = alumniList.get(rowSelected);
                performEdit();
                
            }
        }
        else if (e.getSource() == viewInternshipsBtn) {
            int rowSelected = table.getSelectedRow();
            if (rowSelected == -1) {
                JOptionPane.showMessageDialog(null, "No Alumni Selected");
            }
            else {
                currentAlumniSelected = alumniList.get(rowSelected);
                performView(DataTypes.INTNSHIP);


                
            }
        }
        else if (e.getSource() == viewJobsBtn) {
            int rowSelected = table.getSelectedRow();
            if (rowSelected == -1) {
                JOptionPane.showMessageDialog(null, "No Alumni Selected");
            }
            else {
                currentAlumniSelected = alumniList.get(rowSelected);
                performView(DataTypes.JOB);
                
            }
        }
        else if (e.getSource() == viewCollegesBtn) {
            int rowSelected = table.getSelectedRow();
            if (rowSelected == -1) {
                JOptionPane.showMessageDialog(null, "No Alumni Selected");
            }
            else {
                currentAlumniSelected = alumniList.get(rowSelected);
                performView(DataTypes.COLLEGES);
                
            }
        }
        else if (e.getSource() == modifyBtn) {
            // Edits the currently selected Alumni
            performModify();
            currentPanel.removeAll();
            alumniList = getData(DataTypes.NAME, searchTerm.getText());
            // Recreate the list to update it 
            listPanel = createListPanel();
            currentPanel.add(listPanel);
            currentPanel.revalidate();
            this.repaint();
            
        }
        else if (e.getSource() == addSelection) {
            int selection = modifyTypeSelections.getSelectedIndex();
            if (selection == 0) { // Internship is being added
                performAddInternship();
            }
            else if (selection == 1) { // Job is being added
                performAddJob();
//                currentPanel.removeAll();
//                alumniList = getData(DataTypes.NAME, searchTerm.getText());
//                // Recreate the list to update it 
//                listPanel = createListPanel();
//                currentPanel.add(listPanel);
//                currentPanel.revalidate();
//                this.repaint();
            }
            else if (selection == 2) { // College is being added
                performAddCollege();
            }
        }
        else if (e.getSource() == removeSelection) {
            int selection = modifyTypeSelections.getSelectedIndex();
            if (selection == 0) { // Internship is being removed
                performRemoveIntship();
            }
            else if (selection == 1) { // Job is being removed
                performRemoveJob();
            }
            else if (selection == 2) { // College is being removed
                performRemoveCollege();
            }
        }
        else if (e.getSource() == modifySelection) {
            int selection = modifyTypeSelections.getSelectedIndex();
            if (selection == 0) { // Internship is being modified
                performModifyInternshipSelection();
            }
            else if (selection == 1) { // Job is being modified
                performModifyJobSelection();
            }
            else if (selection == 2) { // College is being modified
                performModifyCollegeSelection();
            }
        }
    }
    
    /**
     * Modifies the selected college.
     */
    private void performModifyCollegeSelection() {
        List<TransferCollege> colleges = currentAlumniSelected.getMyTransferColleges();
        
        if (colleges != null) {
            List<String> revised = new ArrayList<String>();
            for (int i = 0; i < colleges.size(); i++) {
                revised.add("College: " + colleges.get(i).getMyName() + ", Year: " + colleges.get(i).getMyYear());
            }
            JPanel mainPanel = new JPanel();
            JPanel comboPanel = new JPanel();
            comboPanel.setLayout(new GridLayout(1, 1));
            Object[] options = revised.toArray();
            itemRemoveModifySelection = new JComboBox(options);
            if (options.length != 0) {
                modifyTypeSelections.setSelectedIndex(0);
            }
            comboPanel.add(new JLabel("Select College to modify: "));
            comboPanel.add(itemRemoveModifySelection);
            mainPanel.add(comboPanel, BorderLayout.CENTER);
            int choice = JOptionPane.showConfirmDialog(null, mainPanel, "Modify College", JOptionPane.OK_OPTION);
            if (choice == 0) { // User wants to go ahead with removal
                if (itemRemoveModifySelection.getSelectedIndex() >= 0) {
                        displayCollegeWindow();
                    
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "There are no College to remove!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displayCollegeWindow() {
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(6,0));
        String labelNames[] = { "College Name:", "GPA:", "Degree:","Year:","Term:"};
        for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            JLabel addLabel = new JLabel(labelNames[i]);
            myCollegeFields[i] = new JTextField(25);
            panel.add(addLabel);
            panel.add(myCollegeFields[i]);
            fields.add(panel);
        }

        int check = JOptionPane.showConfirmDialog(null, fields, "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        if(check==JOptionPane.CANCEL_OPTION){
            return;
        }

        List<TransferCollege> lsColleges = currentAlumniSelected.getMyTransferColleges();
        TransferCollege modifiedCollege = lsColleges.get(itemRemoveModifySelection.getSelectedIndex());
        if (myCollegeFields[0].getText().length() != 0) {
            modifiedCollege.setMyName(myCollegeFields[0].getText());
        }
        if (myCollegeFields[1].getText().length() != 0) {
            modifiedCollege.setMyGPA(Double.parseDouble(myCollegeFields[1].getText()));
        }
        if (myCollegeFields[2].getText().length() != 0) {
            modifiedCollege.setMyDegree(myCollegeFields[2].getText());
        }
        if (myCollegeFields[3].getText().length() != 0) {
            modifiedCollege.setMyYear(myCollegeFields[3].getText());
        }
        if (myCollegeFields[4].getText().length() != 0) {
            modifiedCollege.setMyTerm(myCollegeFields[4].getText());
        }

        lsColleges.remove(itemRemoveModifySelection.getSelectedIndex());
        lsColleges.add(modifiedCollege);
        
        
        boolean success = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.COLLEGES, lsColleges);

        if (success) {
            JOptionPane.showMessageDialog(null, "College was successfully modified");                 
        } 
        else {
            JOptionPane.showMessageDialog(null, "Error modifying College.", "Modify Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Modifies the selected job.
     */
    private void performModifyJobSelection() {       
    
   List<Job> jobs = currentAlumniSelected.getMyJobs();
        
        if (jobs != null) {
            List<String> revised = new ArrayList<String>();
            for (int i = 0; i < jobs.size(); i++) {
                revised.add("Company: " + jobs.get(i).getMyCompany() + ", Position: " + jobs.get(i).getMyPosition());
            }
            JPanel mainPanel = new JPanel();
            JPanel comboPanel = new JPanel();
            comboPanel.setLayout(new GridLayout(1, 1));
            Object[] options = revised.toArray();
            itemRemoveModifySelection = new JComboBox(options);
            if (options.length != 0) {
                modifyTypeSelections.setSelectedIndex(0);
            }
            comboPanel.add(new JLabel("Select Job to remove: "));
            comboPanel.add(itemRemoveModifySelection);
            mainPanel.add(comboPanel, BorderLayout.CENTER);
            int choice = JOptionPane.showConfirmDialog(null, mainPanel, "Modify Job", JOptionPane.OK_OPTION);
            if (choice == 0) { // User wants to go ahead with removal
                if (itemRemoveModifySelection.getSelectedIndex() >= 0) {
                    displayJobWindow();
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "There are no Jobs to remove!", "Error", JOptionPane.ERROR_MESSAGE);
        }
}

private void displayJobWindow() {
    JPanel fields = new JPanel();
    fields.setLayout(new GridLayout(8,0));
    String labelNames[] = { "Enter Company Name:", "Enter Position: ", "Enter Required Skills: ","Enter Description: ","Enter Comments:","Enter Salary: "};
    for (int i = 0; i < labelNames.length; i++) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));
        JLabel addLabel = new JLabel(labelNames[i]);
        myJobFields[i] = new JTextField(25);
        panel.add(addLabel);
        panel.add(myJobFields[i]);
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
    if(check==JOptionPane.CANCEL_OPTION){
        return;
    }
    // Check data
    boolean requiredEntered = true;
    for (int i = 0; i < myJobFields.length; i++) {
        String data = myJobFields[i].getText();
        System.out.println(i + ": " + data);
        if (data.length() != 0) {           
            if (requiredEntered & data.length() > 50) {
                JOptionPane.showMessageDialog(null, "Data entered must be less than 50 characters", "Entry is too Long", JOptionPane.ERROR_MESSAGE);
                requiredEntered = false;
            }             
        }
    }
    
    if (requiredEntered) {
      
          List<Job> lsJobs = currentAlumniSelected.getMyJobs();
          Job newJob = lsJobs.get(itemRemoveModifySelection.getSelectedIndex());
          if (myJobFields[0].getText().length() != 0) {
              newJob.setMyCompany(myJobFields[0].getText());
          }
          if (myJobFields[1].getText().length() != 0) {
              newJob.setMyPosition(myJobFields[1].getText());
          }
          if (myJobFields[2].getText().length() != 0) {
              newJob.setMySkillsReq(myJobFields[2].getText());
          }
          if (myJobFields[3].getText().length() != 0) {
              newJob.setMyDescription(myJobFields[3].getText());
          }
          if (myJobFields[4].getText().length() != 0) {
              newJob.setMyMiscComments(myJobFields[4].getText());
          }
          if (myJobFields[5].getText().length() != 0) {
              newJob.setSalary(Double.parseDouble(myJobFields[5].getText()));
          }     
          lsJobs.remove(itemRemoveModifySelection.getSelectedIndex());
          lsJobs.add(newJob);
          
          boolean success = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.JOB, lsJobs);
          
          if (success) {
              JOptionPane.showMessageDialog(null, "Job was successfully modified");                 
          } 
          else {
              JOptionPane.showMessageDialog(null, "Error modifiying Job.", "modify Error", JOptionPane.ERROR_MESSAGE);
          }
    }
}
    
    
    /**
     * Modifys the selected internship.
     */
    private void performModifyInternshipSelection() {
        List<Internship> internships = currentAlumniSelected.getMyInternships();
        
        if (internships != null) {
            List<String> revised = new ArrayList<String>();
            for (int i = 0; i < internships.size(); i++) {
                revised.add("Company: " + internships.get(i).getMyCompany() + ", Position: " + internships.get(i).getMyPosition());
            }
            JPanel mainPanel = new JPanel();
            JPanel comboPanel = new JPanel();
            comboPanel.setLayout(new GridLayout(1, 1));
            Object[] options = revised.toArray();
            itemRemoveModifySelection = new JComboBox(options);
            if (options.length != 0) {
                modifyTypeSelections.setSelectedIndex(0);
            }
            comboPanel.add(new JLabel("Select internship to modify: "));
            comboPanel.add(itemRemoveModifySelection);
            mainPanel.add(comboPanel, BorderLayout.CENTER);
            int choice = JOptionPane.showConfirmDialog(null, mainPanel, "Modify Internship", JOptionPane.OK_OPTION);
            if (choice == 0) { // User wants to go ahead with modification
                if (itemRemoveModifySelection.getSelectedIndex() >= 0) {
                    displayInternshipWindow();
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "There are no internships to modify!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displayInternshipWindow() {
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(9,0));
        String labelNames[] = { "Enter Company Name:", "Enter Position: ", "Enter Required Skills: ","Enter Description: ","Enter Comments:","Enter Wage: ","Enter Duration:"};
        for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            JLabel addLabel = new JLabel(labelNames[i]);
            myInternFields[i] = new JTextField(25);
            panel.add(addLabel);
            panel.add(myInternFields[i]);
            fields.add(panel);
        }

        int check = JOptionPane.showConfirmDialog(null, fields, "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        if(check==JOptionPane.CANCEL_OPTION){
            return;
        }

        List<Internship> lsIntShip = currentAlumniSelected.getMyInternships();
        Internship modifiedShip = lsIntShip.get(itemRemoveModifySelection.getSelectedIndex());
        if (myInternFields[0].getText().length() != 0) {
            modifiedShip.setMyCompany(myInternFields[0].getText());
        }
        if (myInternFields[1].getText().length() != 0) {
            modifiedShip.setMyPosition(myInternFields[1].getText());
        }
        if (myInternFields[2].getText().length() != 0) {
            modifiedShip.setMySkillsReq(myInternFields[2].getText());
        }
        if (myInternFields[3].getText().length() != 0) {
            modifiedShip.setMyDescription(myInternFields[3].getText());
        }
        if (myInternFields[4].getText().length() != 0) {
            modifiedShip.setMyMiscComments(myInternFields[4].getText());
        }
        if (myInternFields[5].getText().length() != 0) {
            modifiedShip.setMyWage(Double.parseDouble(myInternFields[5].getText()));
        }     
        if (myInternFields[6].getText().length() != 0) {
            modifiedShip.setMyDuration(Integer.parseInt(myInternFields[6].getText()));
        }
        lsIntShip.remove(itemRemoveModifySelection.getSelectedIndex());
        lsIntShip.add(modifiedShip);

        boolean success = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.INTNSHIP, lsIntShip);

        if (success) {
            JOptionPane.showMessageDialog(null, "Internship was successfully Modified");                 
        } 
        else {
            JOptionPane.showMessageDialog(null, "Error modifying Internship.", "Modify Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Removes an internship.
     */
    private void performRemoveIntship() {
        List<Internship> internships = currentAlumniSelected.getMyInternships();
        
        if (internships != null) {
            List<String> revised = new ArrayList<String>();
            for (int i = 0; i < internships.size(); i++) {
                revised.add("Company: " + internships.get(i).getMyCompany() + ", Position: " + internships.get(i).getMyPosition());
            }
            JPanel mainPanel = new JPanel();
            JPanel comboPanel = new JPanel();
            comboPanel.setLayout(new GridLayout(1, 1));
            Object[] options = revised.toArray();
            itemRemoveModifySelection = new JComboBox(options);
            if (options.length != 0) {
                modifyTypeSelections.setSelectedIndex(0);
            }
            comboPanel.add(new JLabel("Select internship to remove: "));
            comboPanel.add(itemRemoveModifySelection);
            mainPanel.add(comboPanel, BorderLayout.CENTER);
            int choice = JOptionPane.showConfirmDialog(null, mainPanel, "Remove Internship", JOptionPane.OK_OPTION);
            if (choice == 0) { // User wants to go ahead with removal
                if (itemRemoveModifySelection.getSelectedIndex() >= 0) {
                    Object item = internships.remove(itemRemoveModifySelection.getSelectedIndex());
                    if (item != null) {
                        boolean success = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.INTNSHIP, internships);
                        if (success) {
                             JOptionPane.showMessageDialog(null, "Internship successfully removed");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Error removing internship!", "Error", JOptionPane.ERROR_MESSAGE);
                        }                  
                    }
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "There are no internships to remove!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Removes a Job.
     */
    private void performRemoveJob() {
        List<Job> jobs = currentAlumniSelected.getMyJobs();
        
        if (jobs != null) {
            List<String> revised = new ArrayList<String>();
            for (int i = 0; i < jobs.size(); i++) {
                revised.add("Company: " + jobs.get(i).getMyCompany() + ", Position: " + jobs.get(i).getMyPosition());
            }
            JPanel mainPanel = new JPanel();
            JPanel comboPanel = new JPanel();
            comboPanel.setLayout(new GridLayout(1, 1));
            Object[] options = revised.toArray();
            itemRemoveModifySelection = new JComboBox(options);
            if (options.length != 0) {
                modifyTypeSelections.setSelectedIndex(0);
            }
            comboPanel.add(new JLabel("Select Job to remove: "));
            comboPanel.add(itemRemoveModifySelection);
            mainPanel.add(comboPanel, BorderLayout.CENTER);
            int choice = JOptionPane.showConfirmDialog(null, mainPanel, "Remove Job", JOptionPane.OK_OPTION);
            if (choice == 0) { // User wants to go ahead with removal
                if (itemRemoveModifySelection.getSelectedIndex() >= 0) {
                    Object item = jobs.remove(itemRemoveModifySelection.getSelectedIndex());
                    if (item != null) {
                        boolean success = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.JOB, jobs);
                        if (success) {
                             JOptionPane.showMessageDialog(null, "Job successfully removed");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Error removing Job!", "Error", JOptionPane.ERROR_MESSAGE);
                        }                  
                    }
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "There are no Jobs to remove!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Removes a College.
     */
    private void performRemoveCollege() {
        List<TransferCollege> colleges = currentAlumniSelected.getMyTransferColleges();
        
        if (colleges != null) {
            List<String> revised = new ArrayList<String>();
            for (int i = 0; i < colleges.size(); i++) {
                revised.add("College: " + colleges.get(i).getMyName() + ", Year: " + colleges.get(i).getMyYear());
            }
            JPanel mainPanel = new JPanel();
            JPanel comboPanel = new JPanel();
            comboPanel.setLayout(new GridLayout(1, 1));
            Object[] options = revised.toArray();
            itemRemoveModifySelection = new JComboBox(options);
            if (options.length != 0) {
                modifyTypeSelections.setSelectedIndex(0);
            }
            comboPanel.add(new JLabel("Select College to remove: "));
            comboPanel.add(itemRemoveModifySelection);
            mainPanel.add(comboPanel, BorderLayout.CENTER);
            int choice = JOptionPane.showConfirmDialog(null, mainPanel, "Remove College", JOptionPane.OK_OPTION);
            if (choice == 0) { // User wants to go ahead with removal
                if (itemRemoveModifySelection.getSelectedIndex() >= 0) {
                    Object item = colleges.remove(itemRemoveModifySelection.getSelectedIndex());
                    if (item != null) {
                        boolean success = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.COLLEGES, colleges);
                        if (success) {
                             JOptionPane.showMessageDialog(null, "College successfully removed");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Error removing College!", "Error", JOptionPane.ERROR_MESSAGE);
                        }                  
                    }
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "There are no College to remove!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void performView(DataTypes type) {
        JPanel mainPanel = null;
        if (type == DataTypes.INTNSHIP) {
            int size = 0;
            if (currentAlumniSelected.getMyInternships() != null) {
                size = currentAlumniSelected.getMyInternships().size();
            }
            String labelNames[] = { "Company Name", "Position ", "Required Skills","Description ","Comments","Wage: ","Duration"};
            Object[][] data = new Object[size][labelNames.length];
            
            
            
            for (int i = 0; i < data.length; i++) {
                data[i][0] = currentAlumniSelected.getMyInternships().get(i).getMyCompany();
                data[i][1] = currentAlumniSelected.getMyInternships().get(i).getMyPosition();
                data[i][2] = currentAlumniSelected.getMyInternships().get(i).getMySkillsReq();
                data[i][3] = currentAlumniSelected.getMyInternships().get(i).getMyDescription();
                data[i][4] = currentAlumniSelected.getMyInternships().get(i).getMyMiscComments();
                data[i][5] = currentAlumniSelected.getMyInternships().get(i).getMyWage();
                data[i][6] = currentAlumniSelected.getMyInternships().get(i).getMyDuration();
            }
            mainPanel = createTable(data, labelNames);          
        }
        else if (type == DataTypes.JOB) {
            
            String labelNames[] = { "Company Name:", "Position: ", "Required Skills: ","Description: ","Comments:","Salary: "};
            int size = 0;
            if (currentAlumniSelected.getMyJobs() != null) {
                size = currentAlumniSelected.getMyJobs().size();
            }
            Object[][] data = new Object[size][labelNames.length];
            
            for (int i = 0; i < data.length; i++) {
                data[i][0] = currentAlumniSelected.getMyJobs().get(i).getMyCompany();
                data[i][1] = currentAlumniSelected.getMyJobs().get(i).getMyPosition();
                data[i][2] = currentAlumniSelected.getMyJobs().get(i).getMySkillsReq();
                data[i][3] = currentAlumniSelected.getMyJobs().get(i).getMyDescription();
                data[i][4] = currentAlumniSelected.getMyJobs().get(i).getMyMiscComments();
                data[i][5] = currentAlumniSelected.getMyJobs().get(i).getSalary();
            }
            mainPanel = createTable(data, labelNames);          
        }
        else if (type == DataTypes.COLLEGES) {
            
            int size = 0;
            if (currentAlumniSelected.getMyTransferColleges() != null) {
                size = currentAlumniSelected.getMyTransferColleges().size();
            }
            String labelNames[] = { "College Name:", "GPA:", "Degree:","Year:","Term:"};
            Object[][] data = new Object[size][labelNames.length];
            
            for (int i = 0; i < data.length; i++) {
                data[i][0] = currentAlumniSelected.getMyTransferColleges().get(i).getMyName();
                data[i][1] = currentAlumniSelected.getMyTransferColleges().get(i).getMyGPA();
                data[i][2] = currentAlumniSelected.getMyTransferColleges().get(i).getMyDegree();
                data[i][3] = currentAlumniSelected.getMyTransferColleges().get(i).getMyYear();
                data[i][4] = currentAlumniSelected.getMyTransferColleges().get(i).getMyTerm();
            }
            mainPanel = createTable(data, labelNames);          
        }
        
        
        if (mainPanel != null) {
            JOptionPane.showMessageDialog(null, mainPanel, "View", JOptionPane.PLAIN_MESSAGE);
        }
          
    }
    
    private JPanel createTable(Object[][] data, String[] columnNames) {
        JPanel mainPanel = new JPanel();
        TableModel model = new DefaultTableModel(data, columnNames)
        {
          public boolean isCellEditable(int row, int column)
          {
            return false;//This causes all cells to be not editable
          }
        };
        JTable tempTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tempTable);
        tempTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mainPanel.add(scrollPane);
        
        return mainPanel;
    }
    
    /**
     * Adds a job to the Alumni.
     */
    private void performAddJob() {
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(8,0));
        String labelNames[] = { "Enter Company Name:", "Enter Position: ", "Enter Required Skills: ","Enter Description: ","Enter Comments:","Enter Salary: "};
        for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            JLabel addLabel = new JLabel(labelNames[i]);
            myJobFields[i] = new JTextField(25);
            panel.add(addLabel);
            panel.add(myJobFields[i]);
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
        if(check==JOptionPane.CANCEL_OPTION){
            return;
        }
        // Check data
        boolean requiredEntered = true;
        for (int i = 0; i < myJobFields.length; i++) {
            String data = myJobFields[i].getText();
            System.out.println(i + ": " + data);
            if (data.length() != 0) {           
                if (requiredEntered & data.length() > 50) {
                    JOptionPane.showMessageDialog(null, "Data entered must be less than 50 characters", "Entry is too Long", JOptionPane.ERROR_MESSAGE);
                    requiredEntered = false;
                }             
            }
            else if (requiredEntered & (i == 0 || i == 1 || i == 5)) {
                System.out.println("Failure at" + i + ": " + data);
                JOptionPane.showMessageDialog(null, "Error! Company Name, Position, and Salary are all required.", "Missing Required Entry", JOptionPane.ERROR_MESSAGE);
                requiredEntered = false;
            }
        }
        
        if (requiredEntered) {
              Job newJob = new Job(myJobFields[0].getText(), myJobFields[1].getText(), myJobFields[2].getText(), 
                      myJobFields[3].getText(), myJobFields[4].getText(), Double.parseDouble(myJobFields[5].getText()), activeBox.isSelected());
              List<Job> lsJobs = currentAlumniSelected.getMyJobs();
              if (lsJobs == null) {
                  lsJobs = new ArrayList<Job>();
              }
              lsJobs.add(newJob);
              
              boolean success = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.JOB, lsJobs);
              
              if (success) {
                  JOptionPane.showMessageDialog(null, "Job was successfully added");                 
              } 
              else {
                  JOptionPane.showMessageDialog(null, "Error adding Job.", "Add Error", JOptionPane.ERROR_MESSAGE);
              }
        }
      
    }
    
    /**
     * Adds a Internship to the Alumni.
     */
    private void performAddInternship() {
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(9,0));
        String labelNames[] = { "Enter Company Name:", "Enter Position: ", "Enter Required Skills: ","Enter Description: ","Enter Comments:","Enter Wage: ","Enter Duration:"};
        for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            JLabel addLabel = new JLabel(labelNames[i]);
            myInternFields[i] = new JTextField(25);
            panel.add(addLabel);
            panel.add(myInternFields[i]);
            fields.add(panel);
        }

        int check = JOptionPane.showConfirmDialog(null, fields, "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        if(check==JOptionPane.CANCEL_OPTION){
            return;
        }

        try {
            Double.parseDouble(myInternFields[5].getText());
            Integer.parseInt(myInternFields[6].getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Wage and duration are required and must be numbers");
        }
        Internship intnship = new Internship(myInternFields[0].getText(), myInternFields[1].getText(), myInternFields[2].getText(), 
                myInternFields[3].getText(), myInternFields[4].getText(),
                Double.parseDouble(myInternFields[5].getText()), Integer.parseInt(myInternFields[6].getText()));    
        
        List<Internship> lsIntShip = currentAlumniSelected.getMyInternships();
        if (lsIntShip == null) {
            lsIntShip = new ArrayList<Internship>();
        }

        lsIntShip.add(intnship);

        

        boolean success = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.INTNSHIP, lsIntShip);

        if (success) {
            JOptionPane.showMessageDialog(null, "Internship was successfully added");                 
        } 
        else {
            JOptionPane.showMessageDialog(null, "Error adding Internship.", "Add Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    /**
     * Adds a College to the Alumni.
     */
    private void performAddCollege() {
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(6,0));
        String labelNames[] = { "College Name:", "GPA:", "Degree:","Year:","Term:"};
        for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            JLabel addLabel = new JLabel(labelNames[i]);
            myCollegeFields[i] = new JTextField(25);
            panel.add(addLabel);
            panel.add(myCollegeFields[i]);
            fields.add(panel);
        }

        int check = JOptionPane.showConfirmDialog(null, fields, "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        if(check==JOptionPane.CANCEL_OPTION){
            return;
        }

       TransferCollege college = new TransferCollege(myCollegeFields[0].getText(), Double.parseDouble(myCollegeFields[1].getText()), myCollegeFields[2].getText(), 
               myCollegeFields[3].getText(), myCollegeFields[4].getText());
        
        List<TransferCollege> lsColleges = currentAlumniSelected.getMyTransferColleges();
        if (lsColleges == null) {
            lsColleges = new ArrayList<TransferCollege>();
        }

        lsColleges.add(college);
        
        boolean success = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.COLLEGES, lsColleges);

        if (success) {
            JOptionPane.showMessageDialog(null, "College was successfully added");                 
        } 
        else {
            JOptionPane.showMessageDialog(null, "Error adding College.", "Add Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    
    
    /**
     * Displays the selected Alumni.
     * @param currentAlumniSelected
     */
    private void performEdit() {
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        mainPanel.add(makeUpperPanel(), BorderLayout.NORTH);
        mainPanel.add(makeLowerPanel(), BorderLayout.SOUTH);
                   
        JOptionPane.showConfirmDialog(null, mainPanel, "Edit Alumni", JOptionPane.PLAIN_MESSAGE);
        
        
    }
    
     private JPanel makeUpperPanel() {
         JPanel mainPanel = new JPanel(new BorderLayout()); 
        JPanel labelPanel = new JPanel(new GridLayout(8, 2));   
        JPanel modifyPanel = new JPanel(new BorderLayout());
        
        // Create the labels and entry boxes for edits
        labelPanel.add(new JLabel("Name: "));
        labelPanel.add(myModifyFields[0] = new JTextField());
        labelPanel.add(new JLabel("Degree Track: " ));
        labelPanel.add(myModifyFields[1] = new JTextField());
        labelPanel.add(new JLabel("Degree Level: " ));
        labelPanel.add(myModifyFields[2] = new JTextField());
        labelPanel.add(new JLabel("Year: " ));
        labelPanel.add(myModifyFields[3] = new JTextField());
        labelPanel.add(new JLabel("Term: " ));
        labelPanel.add(myModifyFields[4] = new JTextField());
        labelPanel.add(new JLabel("GPA: " ));
        labelPanel.add(myModifyFields[5] = new JTextField());
        labelPanel.add(new JLabel("University Email: " ));
        labelPanel.add(myModifyFields[6] = new JTextField());
        labelPanel.add(new JLabel("Personal Email: " ));
        labelPanel.add(myModifyFields[7] = new JTextField());
        
        mainPanel.add(labelPanel, BorderLayout.CENTER);
    
        modifyBtn = new JButton("Modify");
        modifyBtn.addActionListener(this);
        modifyPanel.add(modifyBtn, BorderLayout.CENTER);
        mainPanel.add(modifyPanel, BorderLayout.EAST);
        
        return mainPanel;
                     
    }
     
     /**
      * Makes the lower panel of the edit panel.
      * @return a JPanel.
      */
    private JPanel makeLowerPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Get choices to display in the combo box
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new GridLayout(1, 1));
        Object[] options = {"Internship(s)", "Job(s)", "Transfer College(s)"};
        modifyTypeSelections = new JComboBox(options);
        if (options.length != 0) {
            modifyTypeSelections.setSelectedIndex(0);
        }
        comboPanel.add(new JLabel("Select item to modify:"));
        comboPanel.add(modifyTypeSelections);
        mainPanel.add(comboPanel, BorderLayout.NORTH);
        
        // To hold the buttons that will act upon the choice
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        addSelection = new JButton("Add to Selection");
        addSelection.addActionListener(this);;
        removeSelection = new JButton("Remove from Selection");
        removeSelection.addActionListener(this);
        modifySelection = new JButton("Modify Selection");
        modifySelection.addActionListener(this);

        buttonPanel.add(addSelection);
        buttonPanel.add(removeSelection);
        buttonPanel.add(modifySelection);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
               
        return mainPanel;
    }
    
   
    
    /**
     * Allows for the modification of Alumni.
     */
    private void performModify() {
        // Goes through the fields checking to see whether anything was modified, if so, it checks to see
        // whether the data is valid before updating the Alumni.
        boolean dataEntered = false;
        boolean modified = false;

        for (int i = 0; i < myModifyFields.length; i++) {
            if (myModifyFields[i].getText().length() != 0) {
                dataEntered = true;
                String textData = myModifyFields[i].getText();
                switch (i) {
                case 0:   // Name
                    // Make sure text will fit into SQL
                    if (textData.length() > 25) {
                        JOptionPane.showMessageDialog(null, "Name is too long.");
                    }
                    else {
                        modified = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.NAME, textData);
                    }
                    break;
                case 1: // Degree Track
                    // Make sure text will fit into SQL
                    if (textData.length() > 25) {
                        JOptionPane.showMessageDialog(null, "Degree Track is too long.");
                    }
                    else {
                        modified = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.TRACK, textData);
                    }
                    break;
                case 2: // Degree Level
                    // Make sure text will fit into SQL
                    if (textData.length() > 25) {
                        JOptionPane.showMessageDialog(null, "Degree Level is too long.");
                    }
                    else {
                        modified = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.LEVEL, textData);
                    }
                    break;
                case 3: // Year
                    try {
                        int integer = Integer.parseInt(textData);
                        if (textData.length() != 4 ) {
                            JOptionPane.showMessageDialog(null, "Year must be four digits long");
                        } else {
                            modified= AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.YEAR, textData);
                        }
                    }
                    catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Year is invalid");
                    }
                    break;
                case 4: // Term
                    if (textData.length() > 7 ) {
                        JOptionPane.showMessageDialog(null, "Invalid Term");
                    } else {
                        modified = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.TERM, textData);
                    }
                    break;
                case 5: // GPA       
                    try {
                        Double gpa = Double.parseDouble(textData);
                        if (textData.length() > 4 ) {
                            JOptionPane.showMessageDialog(null, "GPA can at most be four digits long");
                        } else {
                            modified = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.GPA, textData);
                        }
                    }
                    catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "GPA must be a number");
                    }
                    break;
                case 6: // University Email       
                    if (textData.length() > 25 ) {
                        JOptionPane.showMessageDialog(null, "University Email is too long");
                    } else {
                        modified = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.UNIEMAIL, textData);
                    }
                    break;
                case 7: // Personal Email       
                    if (textData.length() > 25 ) {
                        JOptionPane.showMessageDialog(null, "Personal Email is too long");
                    } else {
                        modified = AlumniCollection.updateAlumni(currentAlumniSelected, DataTypes.PERSEMAIL, textData);
                    }
                    break;
                }
                myModifyFields[i].setText(null);
            }
        }

        // Check if user entered data
        if (!dataEntered) {
            JOptionPane.showMessageDialog(null, "There's nothing to modify!");
        }
        else if (modified) {
            // Check whether edit was successful
            JOptionPane.showMessageDialog(null, "Alumni Successfully modified");

        } else {              
            JOptionPane.showMessageDialog(null, "Error modifying Alumni");
        }


    }

}
