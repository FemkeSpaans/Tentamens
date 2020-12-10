package Herkansing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DiseaseAnalyseTool extends JFrame implements ActionListener {

    static JButton browse_button, analyse_button;
    static JTextField name_file, search_word;
    static JPanel visualisation;
    static JLabel file_name_entered;
    static JTextArea information;
    private JFileChooser select_file;

    public static void main(String[] args) {
        DiseaseAnalyseTool frame = new DiseaseAnalyseTool();
        frame.setTitle("Disease Analyse Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.gui();
        frame.setVisible(true);
    }

    public void gui(){
        Container window = getContentPane();

        // create a jlabel which says to enter a file
        JLabel file_name_entered = new JLabel();
        file_name_entered.setBounds(20, 20, 100, 20);
        file_name_entered.setText("Enter a file:");
        window.add(file_name_entered);

        // create a jtextfield in which to enter a file
        JTextField name_file = new JTextField();
        name_file.setBounds(20, 60, 200, 20);
        window.add(name_file);

        // create a jbutton to browse files with
        JButton browse_button = new JButton();
        browse_button.setBounds(260, 60, 100, 20);
        browse_button.setText("Browse");
        window.add(browse_button);
        browse_button.addActionListener(this);

        // create textfield in which to add a word to search for
        JTextField search_word = new JTextField();
        search_word.setBounds(20, 100, 200, 20);
        window.add(search_word);

        // create a jbutton to analyse the chosen file with
        JButton analyse_button = new JButton();
        analyse_button.setBounds(260, 100, 100, 20);
        analyse_button.setText("Analyse");
        window.add(analyse_button);
        analyse_button.addActionListener(this);

        // create textarea in which to display information about the selected file
        JTextArea information = new JTextArea();
        information.setBounds(20, 140, 540, 200);
        window.add(information);

        // create jpanel in which to display the percentage of the word which was searched for
        JPanel visualisation = new JPanel();
        visualisation.setBounds(20, 360, 540, 160);
        visualisation.setBackground(Color.WHITE);
        window.add(visualisation);

    }

    public BufferedReader openFile(String filename) throws FileNotFoundException {
        BufferedReader fileContent = new BufferedReader(new FileReader(filename));
        return fileContent;
    }

    public void readFiles(){

        try{
            BufferedReader file1 = openFile(name_file.getText());
            String line;

            while ((line = file1.readLine()) != null){
                information.setText(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        File selectedFile;
        int reply;

        if (e.getSource() == browse_button) {
            select_file = new JFileChooser();
            reply = select_file.showOpenDialog(this);
            if (reply == JFileChooser.APPROVE_OPTION) {
                selectedFile = select_file.getSelectedFile();
                name_file.setText(selectedFile.getAbsolutePath());
            }
        }
        if (e.getSource() == analyse_button){
            readFiles();
        }

    }
}
