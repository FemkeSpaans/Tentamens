package Oefententamens.JavaKansEen20182019;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GCpercentageCalculator extends JFrame implements ActionListener {
    static JButton browse_button, open_button, calculate_button;
    static JTextField name_file, from, till;
    static JPanel visualisation;
    static JLabel file_name, information, accession_code, name_gene, from_position, till_position,
            zero, gc_perc, one_hunderd;
    private BufferedReader inFile;
    private JFileChooser fileChooser;



    public static void main(String[] args) {
        GCpercentageCalculator frame = new GCpercentageCalculator();
        frame.setTitle("GC percentage calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
        frame.setLayout(null);
        frame.createGUI();
        frame.setVisible(true);
    }

    public void createGUI(){
        Container window = getContentPane();

        file_name = new JLabel();
        file_name.setBounds(20, 20, 40, 20);
        file_name.setText("File:");
        window.add(file_name);

        name_file = new JTextField();
        name_file.setBounds(60, 20, 420, 20);
        window.add(name_file);

        browse_button = new JButton();
        browse_button.setBounds(520, 20, 100, 20);
        browse_button.setText("Browse");
        window.add(browse_button);
        browse_button.addActionListener(this);

        open_button = new JButton();
        open_button.setBounds(640, 20, 100, 20);
        open_button.setText("Open");
        window.add(open_button);
        open_button.addActionListener(this);

        information = new JLabel();
        information.setBounds(20, 60, 80, 20);
        information.setText("Information:");
        window.add(information);

        accession_code = new JLabel();
        accession_code.setBounds(120, 60, 100, 20);
        accession_code.setText("Accession code:");
        window.add(accession_code);

        name_gene = new JLabel();
        name_gene.setBounds(120, 100, 100, 20);
        name_gene.setText("Name gene:");
        window.add(name_gene);

        from_position = new JLabel();
        from_position.setBounds(120, 140, 100, 20);
        from_position.setText("From:");
        window.add(from_position);

        till_position = new JLabel();
        till_position.setBounds(120, 160, 100, 20);
        till_position.setText("Till:");
        window.add(till_position);

        from = new JTextField();
        from.setBounds(160, 140, 100, 20);
        window.add(from);

        till = new JTextField();
        till.setBounds(160, 160, 100, 20);
        window.add(till);

        calculate_button = new JButton();
        calculate_button.setBounds(300, 140, 100, 40);
        calculate_button.setText("Calculate");
        window.add(calculate_button);
        //calculate.addActionListener(this);

        visualisation = new JPanel();
        visualisation.setBounds(20, 280, 740, 60);
        visualisation.setBackground(Color.WHITE);
        window.add(visualisation);

        zero = new JLabel();
        zero.setBounds(20, 260, 60, 20);
        zero.setText("0%");
        window.add(zero);

        one_hunderd = new JLabel();
        one_hunderd.setBounds(730,260,60,20);
        one_hunderd.setText("100%");
        window.add(one_hunderd);

        gc_perc = new JLabel();
        gc_perc.setBounds(380, 260, 60, 20);
        gc_perc.setText("GC%");
        window.add(gc_perc);
    }

    public void readFile() {
        try {
            inFile = new BufferedReader(new FileReader(name_file.getText()));
            String line;
            while ((line = inFile.readLine()) != null) {
                if (!line.contains(">")) {
                }
            }
            inFile.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "File Error: " + e.toString());
        }
//        if(Sequence.getSequence().matches("^[ATGC]*$")){
//            System.out.println("yay");
//        } else{
//            try {
//                throw new NotValidDNA();
//            } catch (NotValidDNA noValidSequence) {
//            }
//        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        File selectedFile;
        int reply;

        if (event.getSource() == browse_button) {
            fileChooser = new JFileChooser();
            reply = fileChooser.showOpenDialog(this);
            if (reply == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                name_file.setText(selectedFile.getAbsolutePath());
                readFile();
            }
        }

        if (event.getSource() == open_button) {
            readFile();
        }

    }
}

class NotValidDNA extends Exception {
    public NotValidDNA() {
        super("This is not a valid DNA sequence");
    }
}
