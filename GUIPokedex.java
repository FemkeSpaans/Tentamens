package OefententamenJavaKansTwee20182019;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class GUIPokedex extends JFrame implements ActionListener {

    static JButton browse_button, open_button, choice_button;
    static JTextField name_file, name_pokemon;
    static JPanel visualisation;
    static JLabel file_name, pokemon_name1, pokemon_name2, name, id, advice;
    private BufferedReader inFile;
    private JFileChooser fileChooser;

    public static void main(String[] args) {
        GUIPokedex frame = new GUIPokedex();
        frame.setTitle("PokeDex");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.gui();
        frame.setBackground(Color.DARK_GRAY);// why wont you work???
        frame.setVisible(true);
    }

    /**
     * visualistion of the pokedex code, creates a gui
     */
    public void gui() {
        Container window = getContentPane();

        pokemon_name1 = new JLabel();
        pokemon_name1.setBounds(20, 20, 60, 20);
        pokemon_name1.setText("Pokemon:");
        window.add(pokemon_name1);

        name_pokemon = new JTextField();
        name_pokemon.setBounds(100, 20, 300, 20);
        window.add(name_pokemon);

        choice_button = new JButton();
        choice_button.setBounds(440, 20, 100, 20);
        choice_button.setText("Choose");
        choice_button.setBackground(Color.white);
        window.add(choice_button);
        choice_button.addActionListener(this);

        pokemon_name2 = new JLabel();
        pokemon_name2.setBounds(560, 20, 120, 20);
        pokemon_name2.setText("...");
        window.add(pokemon_name2);

        file_name = new JLabel();
        file_name.setBounds(20, 60, 60, 20);
        file_name.setText("File:");
        window.add(file_name);

        name_file = new JTextField();
        name_file.setBounds(100, 60, 300, 20);
        window.add(name_file);

        browse_button = new JButton();
        browse_button.setBounds(440, 60, 100, 20);
        browse_button.setText("Browse");
        browse_button.setBackground(Color.WHITE);
        window.add(browse_button);
        browse_button.addActionListener(this);

        open_button = new JButton();
        open_button.setBounds(560, 60, 100, 20);
        open_button.setText("Open");
        open_button.setBackground(Color.WHITE);
        window.add(open_button);
        open_button.addActionListener(this);

        name = new JLabel();
        name.setBounds(20, 100, 80, 20);
        name.setText("Name:");
        window.add(name);

        advice = new JLabel();
        advice.setBounds(300, 100, 80, 20);
        advice.setText("Advice:");
        window.add(advice);

        id = new JLabel();
        id.setBounds(20, 140, 80, 20);
        id.setText("ID:");
        window.add(id);

        visualisation = new JPanel();
        visualisation.setBounds(20, 200, 740, 340);
        visualisation.setBackground(Color.WHITE);
        window.add(visualisation);
    }


    public BufferedReader openFile(String filename) throws FileNotFoundException {
        BufferedReader fileContent = new BufferedReader(new FileReader(filename));
        return fileContent;
    }

    /**
     * what do you wanna do?
     * first you want to read the file, line for line
     * every line needs to be split on the ;
     * then you wanna cry cause your code doesnt work
     */
    public void readFile() throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        String str = null;

        try {
            inFile = new BufferedReader(new FileReader(name_file.getText()));
            String line;
            while ((line = inFile.readLine()) != null) {
                if (!line.contains("ID")) {
                    list.add(str);
                    String[] stringArr = list.toArray(new String[0]);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
////        ArrayList<String> id = new ArrayList<>(); // arraylist for the id's of the pokemon
////        ArrayList<String> name = new ArrayList<>(); // arraylist for the names of the pokemon
////        ArrayList<Integer> species_id = new ArrayList<>(); // arraylist for the species id's
////        ArrayList<Integer> height = new ArrayList<>(); // arraylist for the height of the pokemon
////        ArrayList<Integer> weight = new ArrayList<>(); // arraylist for the weight of the pokemon
////        ArrayList<Integer> base_xp = new ArrayList<>(); // arraylist for the base xp of the pokemon
////        ArrayList<Integer> order = new ArrayList<>(); // arraylist for the order of the pokemon??
//
//        try {
//            inFile = new BufferedReader(new FileReader(name_file.getText()));
//            String line;
//            while ((line = inFile.readLine()) != null) {
//                if (!line.contains("ID")) {
//                    String[] temp = line.split(";");
//                    String code = temp[0];
//                    id.add(code);
////                } else {
////                    String[] temp = line.split(";");
////                    System.out.println(temp);
////                    String code = temp[0];
////                    System.out.println(code);
////                    id.add(code);
//                }
////                }else if (!line.startsWith("ID")){
////                    String[] temp = line.split(";");// getting rid of the square bracket also doesn't work
////                    System.out.println(temp);
////                    String code = temp[0];
////                    System.out.println(code);
////                    id.add(code); // why does this not work???
////                    // you wanna put every element in the right arraylist
////                    //id.add(line.substring(0));
//
//            }
//            System.out.println(id);
//        } catch (FileNotFoundException fileNotFoundException) {
//            fileNotFoundException.printStackTrace();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//    }
//        inFile.close();
//        }
//        catch (IOException e) {
//            JOptionPane.showMessageDialog(null,
//                    "File Error: " + e.toString());
//        }
//        System.out.println(id);
//    }

            @Override
            public void actionPerformed (ActionEvent event){
                File selectedFile;
                int reply;

                if (event.getSource() == browse_button) {
                    fileChooser = new JFileChooser();
                    reply = fileChooser.showOpenDialog(this);
                    if (reply == JFileChooser.APPROVE_OPTION) {
                        selectedFile = fileChooser.getSelectedFile();
                        name_file.setText(selectedFile.getAbsolutePath());
                    }
                }
                if (event.getSource() == open_button) {
                    try {
                        readFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (event.getSource() == choice_button) {
                    System.out.println("choice");
                    // enter a the name of a pokemon and find that pokemon using the name
                }
            }
        }
