package gui;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainGUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 7352018073986710489L;

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
        JTabbedPane tabbedPane = new JTabbedPane();
        JComponent alPanel = makeTextPanel("Add Alumni");
        tabbedPane.addTab("Add Alumni", alPanel);
        JComponent sPanel = makeTextPanel("View");
        tabbedPane.addTab("View", sPanel);
        JComponent rPanel = makeTextPanel("Report");
        tabbedPane.addTab("Report", rPanel);
        add(tabbedPane);
    }

    /**
     * Create the particular part to add to the tab based on the type.
     * @param type of the system.
     * @return the panel
     */
    private JComponent makeTextPanel(String type) {
        JPanel panel = new JPanel();
        if (type.equalsIgnoreCase("View")) {
            panel.add(new ViewGUI());
        } else if(type.equalsIgnoreCase("Add Alumni")){
            panel.add(new AlumniAddGUI());
        } else if ("Report".equalsIgnoreCase(type)) {
            panel.add(new ReportGUI());
        }
        return panel;
    }
}
