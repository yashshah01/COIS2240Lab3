import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App extends JFrame {
    //Declaring a JTextArea
    private JTextArea textArea;


//App Class Constructor.
    public App() {
        // JFrame settings
        //Setting Title
        setTitle("Your Name's CSV Loader Application");
        //Setting Dimensions of Window
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /*
         * 1-Create a a new button named loadButton. The text in the Button should say LoadCSV
         * 2-After that initialize a new TextArea (we already declared a textArea on Line 11), this is the area where we will display our CSV.
         * 3-Set the new textAtrea to be uneditable (e.g., .setEditable(false))
         * 4-Create a JScrollPane within the text area, so that we can scroll up and down.
         * 5-Add an action listener to the button that calls the load CSV function (below)
         * 6-Play around with coloring and styling to make your application look more professional
         */

         JButton loadButton = new JButton("LoadCSV");

         textArea = new JTextArea();

         textArea.setEditable(false);

         JScrollPane scrollPane = new JScrollPane(textArea);

         loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Set a CSV file");
                fileChooser.setFileFilter(null);
                FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("CSV Files", "csv");
                fileChooser.setFileFilter(fileFilter);
                int filepath = fileChooser.showOpenDialog(null);
                if(filepath == JFileChooser.APPROVE_OPTION){
                    String selectedfilepath = fileChooser.getSelectedFile().getAbsolutePath();
                    loadCsv(selectedfilepath);
                }
                
            }
         });
        
         textArea.setBackground(Color.BLUE);
         loadButton.setBackground(Color.BLACK);
         loadButton.setForeground(Color.WHITE);

        add(loadButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadCsv(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            textArea.setText(content.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to load the CSV file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App().setVisible(true);
            }
        });
    }
}
