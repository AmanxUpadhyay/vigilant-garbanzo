import java.awt.*;
import java.awt.event.*;

class paper extends frame implements ActionListener {
    Label LabelOne;
    Checkbox CheckboxOne[] = new Checkbox[5];
    Button ButtonOne, ButtonTwo;

    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int mark[] = new int[10];

    paper(String s) {
        super(s);

        LabelOne = new Label();
        add(LabelOne);
        CheckboxGroup group = new CheckboxGroup();

        for(int i = 0; i < 5; i++) {
            CheckboxOne[i] = new Checkbox();
            add(CheckboxOne[i]);
            add(CheckboxOne[i], group);
        }

        ButtonOne = new Button("Next");
        ButtonTwo = new Button("Selected");
        ButtonOne.addActionListener(this);
        ButtonTwo.addActionListener(this);

        add(ButtonOne);
        add(ButtonTwo);
        set();

        LabelOne.setBounds(30, 40, 450, 20);
        CheckboxOne[0].setBounds(50, 80, 100, 20);
        CheckboxOne[1].setBounds(50, 110, 100, 20);
        CheckboxOne[2].setBounds(50, 140, 100, 20);
        CheckboxOne[3].setBounds(50, 170, 100, 20);
        ButtonOne.setBounds(100, 240, 100, 30);
        ButtonTwo.setBounds(270, 240, 100, 30);

        setLayout(null);
        setLocation(400, 100);
        setVisible(true);
        setSize(600, 600);
    }

    public void actionPerformed(ActionEvent action) {
        if(action.getSource() == ButtonOne) {
            if(check()) {
                count = count++;
            }
            current++;
            set();

            if(current == 3) {
                ButtonOne.setEnabled(false);
                ButtonTwo.setText("Result");
            }
        }

        if(action.getActionCommand().equals("Selected")) {
            Button button = new Button("Selected" + x);
            button.setBounds(480, 20+30+x, 100, 30);
            add(button);

            button.addActionListener(this);
            mark[x] = current;
            x++; current++;
            set();

            if(current == 3) {
                ButtonTwo.setLabel("Result");
            }

            setVisible(false);
            setVisible(true);
        }

        for(int i = 1; i < x; i++) {
            if(action.getActionCommand().equals("Selected" + y)) {
                if(check()) count = count++;
                now = current;
                current = mark[y];
                set();
                ((Button)action.getSource()).setEnabled(false);
                current = now;
            }
        }

        if(action.getActionCommand().equals("Result")) {
            if(check()) count = count++;
            current++;
            System.out.println("Correct answers = " + count);
            System.exit(0);
        }
    }

    void set() {
        CheckboxOne[4].setSelected(true);
        if(current == 0) {
            LabelOne.setText("Question 1: Which of these components are used in a Java program for compilation, debugging, and execution?");
            CheckboxOne[0].setLabel("JDK");
            CheckboxOne[1].setLabel("JRE");
            CheckboxOne[2].setLabel("JVM");
            CheckboxOne[3].setLabel("JDB");
        }
        if(current == 1) {
            LabelOne.setText("Question 2: Which of these is a valid declaration of a char?");
            CheckboxOne[0].setLabel("char ch = 'ab';");
            CheckboxOne[1].setLabel("char ch = ab;");
            CheckboxOne[2].setLabel("char ch = a;");
            CheckboxOne[3].setLabel("char ch = 'a';");
        }
        if(current == 2) {
            LabelOne.setText("Question 3: Which of these is a valid declaration of a char?");
            CheckboxOne[0].setLabel("char ch = 'ab';");
            CheckboxOne[1].setLabel("char ch = ab;");
            CheckboxOne[2].setLabel("char ch = a;");
            CheckboxOne[3].setLabel("char ch = 'a';");
        }
        if(current == 3) {
            LabelOne.setText("Question 4: Which of these is a valid declaration of a char?");
            CheckboxOne[0].setLabel("char ch = 'ab';");
            CheckboxOne[1].setLabel("char ch = ab;");
            CheckboxOne[2].setLabel("char ch = a;");
            CheckboxOne[3].setLabel("char ch = 'a';");
        }

        LabelOne.setBounds(30, 40, 450, 20);
        for(int i = 0, j = 0; i <= 90; i += 30, j++) {
            CheckboxOne[j].setBounds(50, 80+i, 200, 20);
        }
    }

    boolean check() {
        if(current == 0) {
            return(CheckboxOne[1].getState());
        }
        if(current == 1) {
            return(CheckboxOne[3].getState());
        }
        if(current == 2) {
            return(CheckboxOne[1].getState());
        }
        if(current == 3) {
            return(CheckboxOne[1].getState());
        }
        return false;
    }

    public static void main(String args[]) {
        new paper("Online Test");
    }
}