package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import model.Alumni;
import model.AlumniCollection;

/**
 * Report Tab for GUI will genrate report based on user input.
 * @author Bui
 * @version November 29,2016
 */
public class ReportGUI extends JPanel implements ActionListener, TableModelListener {
    
    /**
     * auto generated ID.
     */
    private static final long serialVersionUID = 1L;
    private JComboBox cmbCategories;
    private JPanel pnlReport, pnlContent;
    private JButton btnGenerate;
    private JTable table;
    private JScrollPane scrollPane;
    private String[] mItemColumnNames = {"Name", "Degree Track"};;
    private Object [][] mData;
    private List<Alumni> mList;
    
    /**
     * Constructor for report GUI.
     */
    public ReportGUI() {
        setLayout(new BorderLayout());
        createComponents();
        setVisible(true);
        setSize(500, 500);
    }
    
    /*
     * Returns the data (2d) to use in the list.
     * 
     * @param title
     * 
     * @return
     */
    private List<Alumni> getData(String searchKey) {

        if (searchKey != null)
            mList = AlumniCollection.search(searchKey);
        else
            mList = AlumniCollection.getItems();

        if (mList != null) {
            mData = new Object[mList.size()][mItemColumnNames.length];
            for (int i = 0; i < mList.size(); i++) {
                mData[i][0] = mList.get(i).getName();
                mData[i][1] = mList.get(i).getDescription();
                mData[i][2] = mList.get(i).getPrice();
                mData[i][3] = mList.get(i).getCondition();
                mData[i][4] = mList.get(i).getItemCategory().getCategory();

            }
        }

        return mList;
    }
    
    /**
     * Create components for tab.
     */
    private void createComponents() {
        
        pnlReport = new JPanel();
        pnlReport.setLayout(new GridLayout(6,0));
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new GridLayout(1, 1));
        Object[] categories = {"GPA", "Degree Track", "Degree Term"};
        if (categories != null) {
            cmbCategories = new JComboBox(categories);
            cmbCategories.setSelectedIndex(0);
            comboPanel.add(new JLabel("Select Report Type: "));
            comboPanel.add(cmbCategories);
            pnlReport.add(comboPanel);
        }
        add(pnlReport, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        btnGenerate = new JButton("Generate Report");
        btnGenerate.addActionListener(this);
        panel.add(btnGenerate);
        pnlReport.add(panel);


        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        // TODO Auto-generated method stub
        
    }
}
