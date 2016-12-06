package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainGUI extends JFrame implements MouseListener {

    /**
     * 
     */
    private static final long serialVersionUID = 7352018073986710489L;
    /**
     * Report panel component.
     */
    private JComponent rPanel;
    /**
     * JPanel panel.
     */
    private JPanel panel;
    /**
     * TabbedPane.
     */
    private JTabbedPane tabbedPane;
    public static void main(String[] args) {
        new MainGUI();
    }

    /**
     * Launches the GUI.
     */
    public MainGUI() {
        super("University Graduated Students System");
        createComponents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(700, 700);
    }

    
    /**
     * Create the tabs for each part of the system. 
     * TODO - Categories, Clients, Transactions
     */
    private void createComponents()
    {
        tabbedPane = new JTabbedPane();
        JComponent alPanel = makeTextPanel("Add Alumni");
        tabbedPane.addTab("Add Alumni", alPanel);
        JComponent sPanel = makeTextPanel("View");
        tabbedPane.addTab("View", sPanel);
        rPanel = makeTextPanel("Report");
        rPanel.addMouseListener(this);
        tabbedPane.addTab("Report", rPanel);
        add(tabbedPane);
    }

    /**
     * Create the particular part to add to the tab based on the type.
     * @param type of the system.
     * @return the panel
     */
    private JComponent makeTextPanel(String type) {
        panel = new JPanel();
        if (type.equalsIgnoreCase("View")) {
            panel.add(new ViewGUI());
        } else if(type.equalsIgnoreCase("Add Alumni")){
            panel.add(new AlumniAddGUI());
        } else if ("Report".equalsIgnoreCase(type)) {
            panel.add(new ReportGUI());
        }
        return panel;
    }
    /**
     * Unimplemented method for MouseListener.
     */
    @Override
    public void mouseClicked(MouseEvent theE) {       
    }
    /**
     * Unimplemented method for MouseListener.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    /**
     * Unimplemented method for MouseListener.
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
    /**
     * This method will recall and update report pane.
     */
    @Override
    public void mousePressed(MouseEvent theE) {
        if (theE.getSource() == rPanel) {
            tabbedPane.remove(rPanel);
            rPanel = makeTextPanel("Report");
            tabbedPane.addTab("Report",rPanel);
            tabbedPane.setSelectedComponent(rPanel);
        }
        
    }
    /**
     * Unimplemented method for MouseListener.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
