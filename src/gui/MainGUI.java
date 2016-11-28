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
        JComponent itemPanel = makeTextPanel("Search");
        tabbedPane.addTab("Search", itemPanel);

        add(tabbedPane);
    }

    /**
     * Create the particular part to add to the tab based on the type.
     * @param type of the system.
     * @return the panel
     */
    private JComponent makeTextPanel(String type) {
        JPanel panel = new JPanel();
        if (type.equalsIgnoreCase("Search")) {
            panel.add(new SearchGUI());
        } else {
            
        }

        return panel;
    }
}
