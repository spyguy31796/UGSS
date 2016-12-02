package gui;

import java.awt.BorderLayout;
import java.awt.Container;
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
 * Report Tab for GUI will generate report based on user input.
 * @author Bui
 * @version November 29,2016
 */
public class ReportGUI extends JPanel implements ActionListener, TableModelListener {
    
    /**
     * auto generated ID.
     */
    private static final long serialVersionUID = 1L;
    private JComboBox cmbCategories, cmbDegreeTrack;
    private JPanel pnlReport, pnlContent;
    private JButton btnGenerate;
    private JTable table;
    private JScrollPane scrollPane;
    private String[] mItemColumnNames = {"Name", "Student ID", "Degree Track", "Degree Level"};;
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
    
   /**
    * Get data for report.
    * @param theLevel degree Level
    * @param theTrack degree Track
    * @return data to display
    */
    private List<Alumni> getData(final String theLevel,
            final String theTrack) {

        if (theLevel != null) {
            mList = AlumniCollection.search(theLevel, theTrack);
        } else {
            mList = AlumniCollection.getAlumni();
        }
        if (mList != null) {
            mData = new Object[mList.size()][mItemColumnNames.length];
            for (int i = 0; i < mList.size(); i++) {
                mData[i][0] = mList.get(i).getMyName();
                mData[i][1] = mList.get(i).getMyID();
                mData[i][2] = mList.get(i).getMyDegreeTrack();
                mData[i][3] = mList.get(i).getMyDegreeLevel();
            }
        }

        return mList;
    }
    
    /**
     * Create components for tab.
     */
    private void createComponents() {
        pnlContent = new JPanel();
        table = new JTable(mData, mItemColumnNames);
        scrollPane = new JScrollPane(table);
        pnlContent.add(scrollPane);
        
        pnlReport = new JPanel();
        pnlReport.setLayout(new GridLayout(6,0));
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new GridLayout(1, 1));
        final Object [] degreeLevel = AlumniCollection.getDegreeLevel();
        if (degreeLevel != null) {
            cmbCategories = new JComboBox(degreeLevel);
            comboPanel.add(new JLabel("Select Degree Level: "));
            comboPanel.add(cmbCategories);
            pnlReport.add(comboPanel);
        }
        add(pnlReport, BorderLayout.NORTH);
        
        // Get DegreeTrack for ComboBox.
        JPanel comboDegreeTrack = new JPanel();
        comboDegreeTrack.setLayout(new GridLayout(1, 1));
        Object[] degreeTrack = AlumniCollection.getDegreeTrack();
        if (degreeTrack != null) {
            cmbDegreeTrack = new JComboBox(degreeTrack);
            comboDegreeTrack.add(new JLabel("Select Degree Track: "));
            comboDegreeTrack.add(cmbDegreeTrack);
            pnlReport.add(comboDegreeTrack);
        }        
        JPanel panel = new JPanel();
        btnGenerate = new JButton("Generate Report");
        btnGenerate.addActionListener(this);
        panel.add(btnGenerate);
        pnlReport.add(panel);         
    }

    @Override
    public void actionPerformed(final ActionEvent theE) {
        if (theE.getSource() == btnGenerate) {
            final String searchKey = (String) cmbCategories.getSelectedItem();
            final String searchKey1 = (String) cmbDegreeTrack.getSelectedItem();
            mList = getData(searchKey, searchKey1);
            pnlContent.removeAll();
            table = new JTable(mData, mItemColumnNames);
            table.getModel().addTableModelListener(this);
            scrollPane = new JScrollPane(table);
            pnlContent.add(scrollPane);
            pnlContent.revalidate();
            this.repaint();
        }
        
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        // TODO Auto-generated method stub
        
    }
}
