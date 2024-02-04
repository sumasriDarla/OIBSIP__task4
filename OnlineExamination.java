import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

class login extends JFrame implements ActionListener {
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField textField1, textField2;

    login() {     
        userLabel = new JLabel();  
        userLabel.setText("    Username :");      
        textField1 = new JTextField(15);      
        passLabel = new JLabel();  
        passLabel.setText("    Password :");        
        textField2 = new JPasswordField(8);     
        b1 = new JButton("   SUBMIT   ");  
        newPanel = new JPanel(new GridLayout(3, 1));  

        // Set light green background color for login form
        newPanel.setBackground(new Color(144, 238, 144)); // RGB for light green

        newPanel.add(userLabel);     
        newPanel.add(textField1);  
        newPanel.add(passLabel);    
        newPanel.add(textField2);   
        newPanel.add(b1);          
        add(newPanel, BorderLayout.CENTER);  
        b1.addActionListener(this);    
        setTitle("Login Form ");         
    }

    public void actionPerformed(ActionEvent ae) {  
        String userValue = textField1.getText();        
        String passValue = textField2.getText();       
        if (!passValue.equals(""))
            new OnlineTestBegin(userValue); 
        else {
            textField2.setText("Enter Password");
            actionPerformed(ae);
        }
    }     
}

class OnlineTestBegin extends JFrame implements ActionListener {
    JLabel l;
    JLabel l1;
    JRadioButton jb[] = new JRadioButton[6];
    JButton b1, b2, log, exitButton; // Removed toggleFullscreen button
    ButtonGroup bg;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];
    Timer timer = new Timer();

    OnlineTestBegin(String s) {      
        super(s); 
        l = new JLabel();
        l1 = new JLabel();
        add(l);
        add(l1);  
        bg = new ButtonGroup();  
        for(int i=0; i<5; i++) {  
            jb[i] = new JRadioButton();     
            add(jb[i]);  
            bg.add(jb[i]);  
        }  
        b1 = new JButton("Save and Next");  
        b2 = new JButton("Save for later");  
        exitButton = new JButton("Exit"); // New exit button
        b1.addActionListener(this);  
        b2.addActionListener(this);  
        exitButton.addActionListener(this); // Register exit button action
        add(b1); add(b2);  
        add(exitButton); // Add exit button to the frame

        set();    
        l.setBounds(30, 40, 450, 20);
        l1.setBounds(20, 20, 450, 20);
        jb[0].setBounds(50, 80, 600, 20);  
        jb[1].setBounds(50, 110, 600, 20);  
        jb[2].setBounds(50, 140, 600, 20);  
        jb[3].setBounds(50, 170, 600, 20);  
        b1.setBounds(95, 240, 140, 30);  
        b2.setBounds(270, 240, 150, 30);
        exitButton.setBounds(450, 240, 80, 30); // Set exit button position
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLayout(null);  
        setLocation(250, 100);  
        setVisible(true);
        setExtendedState(JFrame.NORMAL); // Set to normal mode initially
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 200;
            public void run() {  
                l1.setText("Time left: " + i);
                i--;   
                if (i < 0) {
                    timer.cancel();
                    l1.setText("Time Out");                     
                } 
            }
        }, 0, 1000);        
    }  

    public void actionPerformed(ActionEvent e) {          
        if (e.getSource() == b1) {  
            if (check())  
                count = count + 1;  
            current++;  
            set();    
            if (current == 9) {  
                b1.setEnabled(false);  
                b2.setText("Result");  
            }  
        }  
        if (e.getActionCommand().equals("Save for later")) {  
            JButton bk = new JButton("Review" + x);  
            bk.setBounds(480, 20 + 30 * x, 100, 30);  
            add(bk);  
            bk.addActionListener(this);  
            m[x] = current;  
            x++;  
            current++;  
            set();    
            if (current == 9)  
                b2.setText("Result");  
            setVisible(false);  
            setVisible(true);  
        }  
        for (int i = 0, y = 1; i < x; i++, y++) {  
            if (e.getActionCommand().equals("Review" + y)) {  
                if (check())  
                    count = count + 1;  
                now = current;  
                current = m[y];  
                set();  
                ((JButton)e.getSource()).setEnabled(false);  
                current = now;  
            }  
        }      
        if (e.getActionCommand().equals("Result")) {  
            if (check())  
                count = count + 1;  
            current++;  
            JOptionPane.showMessageDialog(this, "Score =" + count);  
            System.exit(0);  
        }

        if (e.getSource() == exitButton) {
            System.exit(0); // Terminate the application when exit button is clicked
        }
    }

    void set() {  
        jb[4].setSelected(true);  
        if(current >= 0 && current <= 9) {
            // Apply fullscreen only when showing questions
            setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen
        } else {
            // Set to normal mode for other sections
            setExtendedState(JFrame.NORMAL);
        }

        // Set light green background color for questions tab
        getContentPane().setBackground(new Color(144, 238, 144)); // RGB for light green
        
        switch(current) {  
            case 0:  
                l.setText("Que1: Which of the following option leads to the portability and security of Java?");  
                jb[0].setText("a) Bytecode is executed by JVM");jb[1].setText("b) The applet makes the Java code secure and portable");
                jb[2].setText("c) Use of exception handling");jb[3].setText("d) Dynamic binding between objects");   
                break;  
            case 1:  
                l.setText("Que2: Which of the following is not a Java feature?");  
                jb[0].setText("a) Dynamic");jb[1].setText("b) Architecture Neutral");jb[2].setText("c) Use of pointers");jb[3].setText("d) Object-oriented");  
                break;  
            case 2:  
                l.setText("Que3: Where is the system class defined?");  
                jb[0].setText("a) java.lang.package");jb[1].setText("b) java.util.package ");jb[2].setText("c) java.lo.package");jb[3].setText("d) None");  
                break;  
            case 3:  
                l.setText("Que4: Exception created by try block is caught in which block?");  
                jb[0].setText("a) catch");jb[1].setText("b) throw");jb[2].setText("c) final");jb[3].setText("d) thrown");  
                break;  
            case 4:  
                l.setText("Que5: Which of the following is not an OOPS concept in Java?");  
                jb[0].setText("a) Polymorphism");jb[1].setText("b) Inheritance");jb[2].setText("c) Compilation");jb[3].setText("d) Encapsulation");  
                break;  
            case 5:  
                l.setText("Que6: Which keyword is used for accessing the features of a package?");  
                jb[0].setText("a) package");jb[1].setText("b) import");jb[2].setText("c) extends");jb[3].setText("d) export");  
                break;  
            case 6:  
                l.setText("Que7: When is the finalize() method called?");  
                jb[0].setText("a) Before garbage collection");jb[1].setText("b) Before an object goes out of scope");
                jb[2].setText("c) Before a variable goes out of scope");jb[3].setText("d) None");  
                break;  
            case 7:  
                l.setText("Que8: What is the implicit return type of the constructor?");  
                jb[0].setText("a) No return type");jb[1].setText("b) A class object in which it is defined");
                jb[2].setText("c) void");jb[3].setText("d) None");         
                break;  
            case 8:  
                l.setText("Que9: The class at the top of the exception class is....?");  
                jb[0].setText("a) ArithmeticException");jb[1].setText("b) Throwable");jb[2].setText("c) Object");jb[3].setText("d) Console");  
                break;  
            case 9:  
                l.setText("Que10: Which provides runtime environment for Java bytecode to be executed?");  
                jb[0].setText("a) JDK");jb[1].setText("b) JVM");jb[2].setText("c) JRE");jb[3].setText("d) JAVAC");  
                break;  
        }  
        l.setBounds(30, 40, 600, 20);  
        for(int i=0, j=0; i<=90; i+=30, j++)  
            jb[j].setBounds(50, 80+i, 600, 20);  
    }  

    boolean check() {  
        switch(current) {  
            case 0:  
                return jb[1].isSelected();  
            case 1:  
                return jb[2].isSelected();  
            case 2:  
                return jb[2].isSelected();  
            case 3:  
                return jb[0].isSelected();  
            case 4:  
                return jb[2].isSelected();  
            case 5:  
                return jb[1].isSelected();  
            case 6:  
                return jb[0].isSelected();  
            case 7:  
                return jb[2].isSelected();  
            case 8:  
                return jb[1].isSelected();  
            case 9:  
                return jb[1].isSelected();  
            default:
                return false;  
        }  
    }    
}

class OnlineExamination {  
    public static void main(String args[]) {  
        try {  
            login form = new login();  
            form.setSize(400, 150);  
            form.setVisible(true);  
        } catch(Exception e) {     
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
    }  
}
