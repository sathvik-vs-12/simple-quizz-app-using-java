import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class Quiz extends JFrame implements ActionListener {
    JPanel panel;
    JRadioButton choice1, choice2, choice3, choice4;
    ButtonGroup bg;
    JLabel lblmess;
    JButton btnext;
    String[][] qpa; // Questions with options
    String[][] qca; // Correct answers
    int qaid; // Question index
    HashMap<Integer, String> map;

    Quiz() {
        initializedata();
        setTitle("Quiz Program");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(430, 350);
        setLocation(300, 100);
        setResizable(false);
        Container cont = getContentPane();
        cont.setLayout(null);
        cont.setBackground(Color.GRAY);
        
        bg = new ButtonGroup();
        choice1 = new JRadioButton("Choice1", true);
        choice2 = new JRadioButton("Choice2", false);
        choice3 = new JRadioButton("Choice3", false);
        choice4 = new JRadioButton("Choice4", false);
        bg.add(choice1);
        bg.add(choice2);
        bg.add(choice3);
        bg.add(choice4);
        
        lblmess = new JLabel("Choose the correct answer");
        lblmess.setForeground(Color.BLUE);
        lblmess.setFont(new Font("Arial", Font.BOLD, 14));

        btnext = new JButton("Next");
        btnext.setForeground(Color.BLUE);
        btnext.addActionListener(this);

        panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(10, 10, 400, 300);
        panel.setLayout(new GridLayout(6, 1));
        panel.add(lblmess);
        panel.add(choice1);
        panel.add(choice2);
        panel.add(choice3);
        panel.add(choice4);
        panel.add(btnext);

        cont.add(panel);
        setVisible(true);
        qaid = 0;
        readqa(qaid);
    }

    public void actionPerformed(ActionEvent e) {
        if (btnext.getText().equals("Next")) {
            if (qaid < 10) {
                map.put(qaid, getSelection());
                qaid++;
                if (qaid < 10) {
                    readqa(qaid);
                } else {
                    btnext.setText("Show Result");
                }
            }
        } else if (btnext.getText().equals("Show Result")) {
            new Report();
        }
    }

    private void initializedata() {
        qpa = new String[10][5];
        qpa[0] = new String[]{"How to run Java program on the command prompt?", "javac JavaProgram", "java JavaProgram", "run JavaProgram", "execute JavaProgram"};
        qpa[1] = new String[]{"What is the use of the println method?", "It prints text", "It prints text with a line break", "It scans input", "It stores data"};
        qpa[2] = new String[]{"How to read a character from the keyboard?", "Scanner", "BufferedReader", "char c=(char)System.in.read()", "Reader"};
        qpa[3] = new String[]{"Which one would be an int?", "1.5", "2", "3.14", "4.5"};
        qpa[4] = new String[]{"How do you declare an integer variable x?", "int x", "integer x", "x = int", "num x"};
        qpa[5] = new String[]{"How do you convert a string of numbers to a number?", "parseInt()", "Integer.toString()", "String.valueOf()", "convert()"};
        qpa[6] = new String[]{"What is the value of x? int x=3>>2", "1", "0", "3", "-3"};
        qpa[7] = new String[]{"How to exit a loop?", "Using exit", "Using break", "Using continue", "Using terminate"};
        qpa[8] = new String[]{"Correct way to allocate a one-dimensional array?", "int arr[]=new int[size]", "int arr[size]=new int[]", "int[size] arr=new int[size]", "int[] arr=new int[size]"};
        qpa[9] = new String[]{"Correct way to allocate a two-dimensional array?", "int[size][] arr=new int[][]", "int arr=new int[rows][cols]", "int arr[rows][]=new int[rows][cols]", "int[][] arr=new int[rows][cols]"};

        qca = new String[10][2];
        qca[0] = new String[]{"How to run Java program on the command prompt?", "java JavaProgram"};
        qca[1] = new String[]{"What is the use of the println method?", "It prints text with a line break"};
        qca[2] = new String[]{"How to read a character from the keyboard?", "char c=(char)System.in.read()"};
        qca[3] = new String[]{"Which one would be an int?", "2"};
        qca[4] = new String[]{"How do you declare an integer variable x?", "int x"};
        qca[5] = new String[]{"How do you convert a string of numbers to a number?", "parseInt()"};
        qca[6] = new String[]{"What is the value of x? int x=3>>2", "0"};
        qca[7] = new String[]{"How to exit a loop?", "Using break"};
        qca[8] = new String[]{"Correct way to allocate a one-dimensional array?", "int[] arr=new int[size]"};
        qca[9] = new String[]{"Correct way to allocate a two-dimensional array?", "int[][] arr=new int[rows][cols]"};

        map = new HashMap<>();
    }

    public String getSelection() {
        for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
            JRadioButton temp = (JRadioButton) buttons.nextElement();
            if (temp.isSelected()) {
                return temp.getText();
            }
        }
        return null;
    }

    public void readqa(int qid) {
        lblmess.setText(qpa[qid][0]);
        choice1.setText(qpa[qid][1]);
        choice2.setText(qpa[qid][2]);
        choice3.setText(qpa[qid][3]);
        choice4.setText(qpa[qid][4]);
        choice1.setSelected(true);
    }

    public int calCorrectAnswer() {
        int count = 0;
        for (int qid = 0; qid < 10; qid++) {
            if (qca[qid][1].equals(map.get(qid))) {
                count++;
            }
        }
        return count;
    }

    class Report extends JFrame {
        Report() {
            setTitle("Quiz Report");
            setSize(600, 400);
            setBackground(Color.WHITE);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                }
            });
            add(new Draw());
            setVisible(true);
        }

        class Draw extends Canvas {
            public void paint(Graphics g) {
                int y = 50;
                g.setFont(new Font("Arial", Font.BOLD, 14));
                for (int i = 0; i < 10; i++) {
                    g.drawString("Q: " + qca[i][0], 10, y);
                    g.drawString("Your Answer: " + map.get(i), 10, y + 20);
                    g.drawString("Correct Answer: " + qca[i][1], 10, y + 40);
                    y += 60;
                }
                g.setColor(Color.BLUE);
                g.drawString("Correct Answers: " + calCorrectAnswer() + " / 10", 200, y + 20);
            }
        }
    }
}

public class QuizProgram {
    public static void main(String[] args) {
        new Quiz();
    }
}
