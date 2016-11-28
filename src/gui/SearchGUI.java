package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import model.Alumni;
import model.AlumniCollection;

public class SearchGUI extends JPanel implements ActionListener, TableModelListener {

    /**
     * 
     */
    private static final long serialVersionUID = -1587871730380712674L;
    
    private JButton nameSearch, idSearch;
    private JTextField searchTerm;
    private JPanel currentPanel, listPanel;
    
    private Object[][] mData;
    
    private String[] mAlumniColumnNames = { "id", "name", "degreeTrack", "degreeLevel", "year", "term", "gpa", "uniEmail", "persEmail"};

    SearchGUI() {
        setLayout(new BorderLayout());

        //getData(null);
        createComponents();
        setVisible(true);
        setSize(500, 500);
    }
    
    private List<Alumni> getData(String column, String searchKey) {

        List<Alumni> searchData = null;
        if (searchKey != null)
            searchData = AlumniCollection.search(column, searchKey);

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

            }
        }

        return searchData;
    }

    private void createComponents() {
        add(createSearchPanel(), BorderLayout.NORTH);
        currentPanel = new JPanel();
        //listPanel = createListPanel();
        //currentPanel.add(listPanel);
        add(currentPanel, BorderLayout.CENTER);
   
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
        JTable table = new JTable(mData, mAlumniColumnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane);
        table.getModel().addTableModelListener(this);
               
        return mainPanel;        
    }
    
    
   
    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == nameSearch) {
//            currentPanel.removeAll();
//            getData("name", searchTerm.getText());
//            // Recreate the list to update it 
//            listPanel = createListPanel();
//            currentPanel.add(listPanel);
//            currentPanel.revalidate();
//            this.repaint();
//        }
//        else if (e.getSource() == idSearch) {
//            currentPanel.removeAll();
//            getData("id", searchTerm.getText());
//            // Recreate the list to update it 
//            listPanel = createListPanel();
//            currentPanel.add(listPanel);
//            currentPanel.revalidate();
//            this.repaint();
//        }
        
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        // TODO Auto-generated method stub
        
    }
}
