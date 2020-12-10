package Herkansing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @author Femke Spaans
 * Disease Analyse Tool
 * a tool to open a file, search its contents and display certain aspects of the file.
 */
public class DiseaseAnalyseTool extends JFrame implements ActionListener {

    static JButton browse_button, analyse_button;
    static JTextField name_file, search_word;
    static JPanel visualisation;
    static JLabel file_name_entered;
    static JTextArea information;
    private JFileChooser select_file;

    /**
     * create a frame for the graphical user interface
     * @param args
     */
    public static void main(String[] args) {
        DiseaseAnalyseTool frame = new DiseaseAnalyseTool();
        frame.setTitle("Disease Analyse Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.gui();
        frame.setVisible(true);
    }

    /**
     * create a graphical user interface.
     * one text field for the name of the file, one for the word which is to be searched for.
     * one button to browse files and select a file, one button to analyse the data of the file selected.
     * a textarea to display several features about the search word and the file.
     * a panel to display a visualisation of the percentage of the search word found in the file.
     */
    public void gui(){
        Container window = getContentPane();

        // create a jlabel which says to enter a file
        file_name_entered = new JLabel();
        file_name_entered.setBounds(20, 20, 100, 20);
        file_name_entered.setText("Enter a file:");
        window.add(file_name_entered);

        // create a jtextfield in which to enter a file
        name_file = new JTextField();
        name_file.setBounds(20, 60, 200, 20);
        window.add(name_file);

        // create a jbutton to browse files with
        browse_button = new JButton();
        browse_button.setBounds(260, 60, 100, 20);
        browse_button.setText("Browse");
        window.add(browse_button);
        browse_button.addActionListener(this);

        // create textfield in which to add a word to search for
        search_word = new JTextField();
        search_word.setBounds(20, 100, 200, 20);
        window.add(search_word);

        // create a jbutton to analyse the chosen file with
        analyse_button = new JButton();
        analyse_button.setBounds(260, 100, 100, 20);
        analyse_button.setText("Analyse");
        window.add(analyse_button);
        analyse_button.addActionListener(this);

        // create textarea in which to display information about the selected file
        information = new JTextArea();
        information.setBounds(20, 140, 540, 200);
        window.add(information);

        // create jpanel in which to display the percentage of the word which was searched for
        visualisation = new JPanel();
        visualisation.setBounds(20, 360, 540, 160);
        visualisation.setBackground(Color.WHITE);
        window.add(visualisation);

    }

    /**
     * a method to open a file with
     * @param filename
     * @return
     * @throws FileNotFoundException
     */
    public BufferedReader openFile(String filename) throws FileNotFoundException {
        BufferedReader fileContent = new BufferedReader(new FileReader(filename));
        return fileContent;
    }

    /**
     * a method to read a file with line for line.
     * count the lines, and the number of times the search word is found,
     * only the first occurrence of the word in a line is counted.
     * a percentage is made of this number and then made into a float.
     * show information about the file and the search word is shown in the text area of the file.
     * @return
     * @throws IOException
     */
    public float readFiles() throws IOException{

        String line;
        String searchWord = search_word.getText();
        int number_search_word = 0;
        int number_of_lines = 0;

        try{
            BufferedReader file = openFile(name_file.getText());

            while ((line = file.readLine()) != null){
                if (line.startsWith("C")){
                    number_of_lines++;
                    if(line.contains(searchWord)){
                        number_search_word++;
                    }
                }

            }

            float number_of_lines1 = number_of_lines;
            float number_search_word1 = number_search_word;
            float percentage_search_word = number_search_word1 / number_of_lines1 * 100;
            information.append("The search word is: "+searchWord + "\n");
            information.append("The total number of lines in this file is: "+ number_of_lines + "\n");
            information.append("The number of lines the search word is found in is: "+ number_search_word + "\n");
            information.append("The percentage this search word is found is: "+ percentage_search_word +"%");

            draw(percentage_search_word);
            return percentage_search_word;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * makes an int out of the percentage float which was given.
     * draw two separate rectangles, one of the percentage the search word is found in the file,
     * and one of the remainder.
     * the percentages of both are writing into the rectangles.
     * @param percentage_search_word
     */
    public void draw(float percentage_search_word){

        // round the percentage to whole numbers
        int number_of_search_word = Math.round(percentage_search_word);
        int rest = 100 - number_of_search_word;

        Graphics percentage_visualisation = visualisation.getGraphics();

        percentage_visualisation.setColor(Color.BLUE);
        percentage_visualisation.drawRect(0,0,400, 50);
        percentage_visualisation.fillRect(0,0, (400 * number_of_search_word/100), 50);

        percentage_visualisation.setColor(Color.ORANGE);
        percentage_visualisation.drawRect(0,60,400, 50);
        percentage_visualisation.fillRect(0,60, (400 * rest/100), 50);

        percentage_visualisation.setColor(Color.BLACK);
        percentage_visualisation.drawString(String.valueOf(number_of_search_word+ "%"), 200, 25);
        percentage_visualisation.drawString(String.valueOf(rest+ "%"), 200, 85);
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
            try {
                readFiles();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }
}
