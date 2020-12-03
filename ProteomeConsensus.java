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
    static JLabel file_name1, file_name2, entered_files, unique_file1, unique_file2, overlapping,
    unique_file1_number, unique_file2_number, overlapping_number;
    static JTextArea comparison_files;
    //static BufferedReader file1, file2;
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
     * Create a GUI,
     * entered_files shows a label with entered files:
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
     * @return
     */
    public ArrayList<Integer> readFiles() throws FileNotFoundException {

        ArrayList<String> lines1 = new ArrayList<String>();
        ArrayList<String> lines2 = new ArrayList<String>();
        ArrayList<Integer> total_over_unique = new ArrayList<Integer>();

        int total_lines_file1 = 0;
        int total_lines_file2 = 0;
        int overlapping = 0;

        try {
            BufferedReader file1 = openFile(name_file1.getText());
            BufferedReader file2 = openFile(name_file2.getText());
            String line, code;

            while ((line = file1.readLine()) != null) {
                if (!line.contains("Protein")) {
                    total_lines_file1++;
                    if (line.startsWith("AT")) {
                        String[] temp = line.split("\t");
                        code = temp[0];
                        lines1.add(code);
                    }
                }
            }
            while ((line = file2.readLine()) != null) {
                if (!line.contains("Protein")) {
                    total_lines_file2++;
                    if (line.startsWith("AT")) {
                        String[] temp = line.split("\t");
                        code = temp[0];
                        lines2.add(code);
                    }
                    }
                }
            file1.close();
            file2.close();


            for(int i = 0; i< lines1.size();i++ ){
                for(int j = 0; j<lines2.size();j++){
                    if(lines1.get(i).equals(lines2.get(j))){
                        overlapping++;
                    }
                }
            }
            int unique_lines_file1 = total_lines_file1 - overlapping;
            int unique_lines_file2 = total_lines_file2 - overlapping;

            total_over_unique.add(total_lines_file1);
            total_over_unique.add(total_lines_file2);
            total_over_unique.add(overlapping);
            total_over_unique.add(unique_lines_file1);
            total_over_unique.add(unique_lines_file2);

            comparison_files.setText("Total number of lines in file one is: " + total_lines_file1 + "\n" +
                    "Total number of lines in file two is: " + total_lines_file2 + "\n" +
                    "Unique number of lines in file one is: "+ unique_lines_file1 + "\n" +
                    "Unique number of lines in file one is: " + unique_lines_file2 + "\n" +
                    "The number of overlapping lines in the files is: "+ overlapping);

            } catch (IOException e) {
            e.printStackTrace();
        }
        draw(total_over_unique);
        return total_over_unique;
    }


    public BufferedReader openFile(String filename) throws FileNotFoundException {
        BufferedReader fileContent = new BufferedReader(new FileReader(filename));
        return fileContent;
    }

    public void draw(ArrayList<Integer> total_over_unique){
        //unique_file1, unique_file2, overlapping,
        //    unique_file1_number, unique_file2_number, overlapping_number
        System.out.println(total_over_unique);
        Container window = getContentPane();
        Graphics graphics = visualisation.getGraphics();
        //graphics.clearRect(20, 140, 700, 300);

        graphics.setColor(Color.RED);
        graphics.drawOval(200, 50, 200, 150);

        graphics.setColor(Color.BLUE);
        graphics.drawOval(300, 50, 200, 150);

        graphics.setColor(Color.RED);
        graphics.drawString("# unique from file 1", 80,60);
        graphics.drawString(String.valueOf(total_over_unique.get(0)), 200, 120);

        graphics.setColor(Color.BLUE);
        graphics.drawString("# unique from file 2", 560, 60);
        graphics.drawString(String.valueOf(total_over_unique.get(1)), 260, 120);

        graphics.setColor(Color.BLACK);
        graphics.drawString("# overlapping in both files", 260, 260);
        graphics.drawString(String.valueOf(total_over_unique.get(2)), 300, 120);



        }

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

