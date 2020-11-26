package Tentamen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ProteomeConsensus extends JFrame implements ActionListener {
    static JButton browse_button1, browse_button2, analyse_button;
    static JTextField name_file1, name_file2;
    static JPanel visualisation;
    static JLabel file_name1, file_name2, entered_files;
    static JTextArea comparison_files;
    static BufferedReader file1, file2;
    private JFileChooser select_file;

    public static void main(String[] args) {
        ProteomeConsensus frame = new ProteomeConsensus();
        frame.setTitle("CPAT | Consensus Proteome Analyse Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(null);
        frame.GUI();
        frame.setVisible(true);
    }

    /**
     * Create a GUI
     */
    public void GUI() {
        Container window = getContentPane();

        entered_files = new JLabel();
        entered_files.setBounds(20, 20, 100, 20);
        entered_files.setText("Enter two files:");
        window.add(entered_files);

        file_name1 = new JLabel();
        file_name1.setBounds(20, 60, 60, 20);
        file_name1.setText("File one:");
        window.add(file_name1);

        name_file1 = new JTextField();
        name_file1.setBounds(80, 60, 180, 20);
        window.add(name_file1);

        browse_button1 = new JButton();
        browse_button1.setBounds(280, 60, 80, 20);
        browse_button1.setText("Browse");
        window.add(browse_button1);
        browse_button1.addActionListener(this);

        file_name2 = new JLabel();
        file_name2.setBounds(380, 60, 60, 20);
        file_name2.setText("File two:");
        window.add(file_name2);

        name_file2 = new JTextField();
        name_file2.setBounds(440, 60, 180, 20);
        window.add(name_file2);

        browse_button2 = new JButton();
        browse_button2.setBounds(640, 60, 80, 20);
        browse_button2.setText("Browse");
        window.add(browse_button2);
        browse_button2.addActionListener(this);

        analyse_button = new JButton();
        analyse_button.setBounds(20, 100, 700, 20);
        analyse_button.setText("Analyse the consensus proteome");
        window.add(analyse_button);
        analyse_button.addActionListener(this);

        visualisation = new JPanel();
        visualisation.setBounds(20, 140, 700, 300);
        visualisation.setBackground(Color.WHITE);
        window.add(visualisation);

        comparison_files = new JTextArea();
        comparison_files.setBounds(20, 480, 700, 200);
        window.add(comparison_files);
    }

    /**
     * Read two files,
     * compare the two files,
     * the text area should display the amount of lines found in each file,
     * the amount of unique lines in each file,
     * and the amount of lines which can be found in both files.
     * These numbers should then be passed on to a draw function. 
     */
    public void readFiles() throws FileNotFoundException {
        ArrayList<String> lines1 = new ArrayList<String>();
        ArrayList<String> lines2 = new ArrayList<String>();

        int total_file1 = 0;
        int total_file2 = 0;

        int counter = 0;
        int lineNum = 1;

        String[] temp;

        try {
            file1 = new BufferedReader(new FileReader(name_file1.getText()));
            file2 = new BufferedReader(new FileReader(name_file2.getText()));
            String line;

            while ((line = file1.readLine()) != null) {
                //text_area.append(line + "\n");
                if (!line.contains("Protein")) {
                    total_file1++;
                    if (line.startsWith("AT")) {
                        temp = line.split("\t");
                        lines1.add(temp[0]);
//                    line.split("\t");
//                    lines1.add(line);
//                    if(line.startsWith("AT")| line.endsWith(".1")){
//                    }
                    }
                }
                System.out.println(lines1);
                while ((line = file2.readLine()) != null) {
                    //text_area.append(line + "\n");
                    if (!line.contains("Protein")) {
                        total_file2++;
                        line.split("\t");
                        //String part1 = line[0];
                        lines2.add(line);
                    }
                }
                file1.close();
                file2.close();
                comparison_files.setText("Total number of lines in file one is: " + total_file1 + "\n" +
                        "Total number of lines in file two is: " + total_file2);
                //String element0 = lines1.get(0);
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null,
//                    "File Error: " + e.toString());
            }
            //counter(lines1);
            //return lines1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void counter(ArrayList<String> lines1){
//        int count = 0;
//        if (text_area.getText().matches("(AT4G30530.1)")){
//            count++;
//        }
//        if (text_area.getText().matches("(AT3G46970.1)")){
//            count++;
//        }
//        if (text_area.getText().matches("(AT1G67090.1)")){
//            count++;
//        }
//        if (text_area.getText().matches("(AT5G51830.1)")){
//            count++;
//        }
//        if (text_area.getText().matches("(AT5G30510.1)")){
//            count++;
//        }
//        if (text_area.getText().matches("(AT2G33845.1)")){
//            count++;
//        }
//        if (text_area.getText().matches("(AT1G08200.1)")){
//            count++;
//        }
//        if (text_area.getText().matches("(AT3G04720.1)")){
//            count++;
//        }
//
//    }


    @Override
    public void actionPerformed(ActionEvent e) {
        File selectedFile;
        int reply;

        if (e.getSource() == browse_button1) {
            select_file = new JFileChooser();
            reply = select_file.showOpenDialog(this);
            if (reply == JFileChooser.APPROVE_OPTION) {
                selectedFile = select_file.getSelectedFile();
                name_file1.setText(selectedFile.getAbsolutePath());
            }
        }
        if (e.getSource() == browse_button2) {
            select_file = new JFileChooser();
            reply = select_file.showOpenDialog(this);
            if (reply == JFileChooser.APPROVE_OPTION) {
                selectedFile = select_file.getSelectedFile();
                name_file2.setText(selectedFile.getAbsolutePath());
            }
        }
        if (e.getSource() == analyse_button) {
            try {
                readFiles();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }

    }
}
