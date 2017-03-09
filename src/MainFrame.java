import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by Oly on 09.03.2017.
 */
public class MainFrame extends JFrame {
    private static MainFrame mainFrame;
    private static JPanel headerPanel;
    private static JPanel mainPanel;
    private static JLabel headerLabel;
    private static JLabel studNLabel;
    private static JComboBox studNComboBox;
    private static JLabel expressionLabel;
    private static JLabel nLabel;
    private static JTextField nField;
    private static JTextPane resultTextPane;
    private static JScrollPane resultScrollPane;
    private static JLabel theAverageLabel;

    private MainFrame() {
        int nOfStudents = 20;
        String[] internals = new String[nOfStudents];
        for (int i = 1; i < nOfStudents + 1; i++) {
            internals[i-1] = Integer.toString(i);
        }

        headerLabel = new JLabel("ULTIMATE PROBLEM SOLVER 4000"); headerLabel.setFont(new Font("Distant Galaxy",Font.PLAIN,16)); headerLabel.setForeground(new Color(235, 56, 0));
        nField = new JTextField();
        resultTextPane = new JTextPane(); resultTextPane.setOpaque(true); resultTextPane.setBackground(Color.WHITE); resultTextPane.setContentType("text/html"); resultTextPane.setEditable(false);
        expressionLabel = new JLabel("x[i] = -1*5/n*log(y[i])");
        studNLabel = new JLabel("Student #");
        nLabel = new JLabel("The number of answers:");
        studNComboBox = new JComboBox(internals); studNComboBox.setBackground(Color.WHITE); //studNComboBox.
        mainPanel = new JPanel(new GridBagLayout());
        headerPanel = new JPanel(new GridBagLayout());
        resultScrollPane = new JScrollPane(resultTextPane,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        theAverageLabel = new JLabel("The average: ");
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1;
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.weightx = 1.0; c.anchor = GridBagConstraints.NORTH; c.fill = GridBagConstraints.NONE; headerPanel.add(headerLabel,c);
        c.gridx = 0; c.gridy = 1; c.gridwidth = 1; c.weightx = 0.3; c.anchor = GridBagConstraints.WEST;  c.fill = GridBagConstraints.NONE; headerPanel.add(studNLabel,c);
        c.gridx = 1; c.gridy = 1; c.gridwidth = 1; c.weightx = 0.7; c.anchor = GridBagConstraints.CENTER; c.fill = GridBagConstraints.HORIZONTAL; headerPanel.add(studNComboBox,c);
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2; c.weightx = 1.0; c.anchor = GridBagConstraints.CENTER; c.fill = GridBagConstraints.NONE; headerPanel.add(expressionLabel,c);
        c.gridx = 0; c.gridy = 3; c.gridwidth = 1; c.weightx = 0.3; c.anchor = GridBagConstraints.WEST; c.fill = GridBagConstraints.NONE; headerPanel.add(nLabel,c);
        c.gridx = 1; c.gridy = 3; c.gridwidth = 1; c.weightx = 0.7; c.anchor = GridBagConstraints.CENTER; c.fill = GridBagConstraints.HORIZONTAL; headerPanel.add(nField,c);

        c.weighty = 1;
        c.gridx = 0; c.gridy = 0; c.gridwidth = 1; c.weightx = 1.0; c.anchor = GridBagConstraints.NORTH; c.fill = GridBagConstraints.HORIZONTAL; mainPanel.add(headerPanel);
        c.weighty = 5;
        c.gridx = 0; c.gridy = 1; c.gridwidth = 1; c.weightx = 1.0; c.anchor = GridBagConstraints.CENTER; c.fill = GridBagConstraints.BOTH; mainPanel.add(resultScrollPane,c);
        c.weighty = 0.5;
        c.gridx = 0; c.gridy = 2; c.gridwidth = 1; c.weightx = 1.0; c.anchor = GridBagConstraints.SOUTH; c.fill = GridBagConstraints.HORIZONTAL; mainPanel.add(theAverageLabel,c);
        add(mainPanel);

        studNComboBox.setSelectedIndex(0);

        setResizable(false);
        setSize(300,400);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("UPS 4k");
        setVisible(true);

        studNComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (!nField.getText().equals("")) {
                            int studentIndex = Integer.parseInt((String) studNComboBox.getSelectedItem());
                            int answersN = Integer.parseInt(nField.getText());
                            solveTheProblem(studentIndex,answersN);
                        }
                    }
                });
            }
        });

        nField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int studentIndex = Integer.parseInt((String) studNComboBox.getSelectedItem());
                int answersN = Integer.parseInt(nField.getText());
                solveTheProblem(studentIndex,answersN);
            }
        });
    }

    private static void solveTheProblem(int stInd, int answN) {
        StringBuilder sb = new StringBuilder("<html><pre>");
        Random random = new Random();
        double sum = 0;
        double x;
        for (int i = 0; i < answN; i++) {
            x = ((-5*Math.log(random.nextDouble())))/stInd;
            sum += x;
            sb.append(x);
            sb.append("<br>");
        }
        sb.append("</pre><html>");
        resultTextPane.setText(sb.toString());
        theAverageLabel.setText("The average: " + sum/answN);
    }

    public static void main(String[] args) {
        mainFrame = new MainFrame();
    }
}
