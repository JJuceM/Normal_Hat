package Login;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import lobby.*;


public class Logintest2 {

    public static final int DEFAULT = 0;
    public static final int LOGINCHECK = 1;
    public static final int CREATEACCOUNT = 2;
    public static final int REDUNTCHECK = 3;
    static boolean socketcheck = false;
    static boolean logincheck = false;

    ArrayList<String> data = new ArrayList<String>();


    String what;
    String col;
    String userid;
    String nickname;
    String passwd;
    String namecheck;
    String chatname;

    int mode = DEFAULT;
    int check = 0;
    static 	String url = "jdbc:mysql://127.0.0.1/testDB?serverTimezone=UTC&&useSSL=false&user=root&password=admin0309!";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    JFrame frame = new JFrame("Login Program");
    JPanel mainPanel = new JPanel();
    JLabel mainUserLabel = new JLabel("ID");
    JTextField mainUserField = new JTextField("");
    JLabel pwLabel = new JLabel("Password");
    JPasswordField pwField = new JPasswordField();
    JButton loginBtn = new JButton("�α���");
    JButton createBtn =	new JButton("���� ����");

    JFrame warnFrame = new JFrame();
    JLabel warnLabel = new JLabel();
    JButton warnBtn = new JButton("Ȯ��");

    JFrame askFrame = new JFrame();
    JLabel askLabel = new JLabel();
    JButton askBtn1 = new JButton("Yes");
    JButton askBtn2 = new JButton("No");

    JPanel createPanel = new JPanel();

    JLabel [] newLabel = new JLabel[4];
    JTextField [] newField = new JTextField[4];

    JLabel [] userLabel = new JLabel[4];
    JTextField [] userField = new JTextField[4];
    JButton [] userBtn = new JButton[3];

    JButton [] newBtn = new JButton[3];

    JPanel userPanel = new JPanel();



    Dimension dim_Frame = new Dimension(400,600);
    Dimension dim_Label = new Dimension(300,30);
    Dimension dim_Field = new Dimension(300,40);
    Dimension dim_Button = new Dimension(300,70);
    Dimension dim_SButton = new Dimension(150,70);


    public static void main(String[] args) {
        new Logintest2();
        System.out.println(socketcheck);
            try {
            Socket socket = null;
            // ���� ������ ����
            socket = new Socket("127.0.0.1", 2030);
            System.out.println("������ ���� ����!"); // ���� Ȯ�ο�

            // �������� ���� �޼��� �д� Thread
            ListenThread t1 = new ListenThread(socket);
            writeThread t2 = new writeThread(socket); // ������ �޼��� ������ Thread

            t1.start(); // ListeningThread Start
            t2.start(); // WritingThread Start

        } catch (IOException e) {
            e.printStackTrace(); // ����ó��
        }
    }

    public Logintest2() {
        frame.setVisible(true);
        frame.setSize(dim_Frame);
        frame.setLocation(0, 0);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setLayout(null);
        mainUserLabel.setLocation(50, 10);
        mainUserLabel.setSize(dim_Label);
        mainUserField.setLocation(50, 40);
        mainUserField.setSize(dim_Field);
        pwLabel.setSize(dim_Label);
        pwLabel.setLocation(50, 110);
        pwField.setSize(dim_Field);
        pwField.setLocation(50, 140);
        loginBtn.setSize(dim_Button);
        loginBtn.setLocation(50, 210);
        createBtn.setSize(dim_Button);
        createBtn.setLocation(50, 280);

        loginBtn.addActionListener(new ButtonAction());
        createBtn.addActionListener(new ButtonAction());

        mainPanel.setFont(new Font("Arial", Font.BOLD, 20));

        frame.add(mainPanel);

        mainPanel.add(mainUserLabel);
        mainPanel.add(mainUserField);
        mainPanel.add(pwLabel);
        mainPanel.add(pwField);
        mainPanel.add(loginBtn);
        mainPanel.add(createBtn);

        newLabel[0] = new JLabel("���̵�");
        newField[0] = new JTextField();
        newLabel[1] = new JLabel("�н�����(6�� �̻�)");
        newField[1] = new JTextField();
        newLabel[2] = new JLabel("�н����� Ȯ��");
        newField[2] = new JTextField();
        newLabel[3] = new JLabel("�г���");
        newField[3] = new JTextField();
        newBtn[0] = new JButton("����");
        newBtn[1] = new JButton("���");
        newBtn[2] = new JButton("�ߺ�Ȯ��");


        warnFrame.setSize(400, 200);
        warnFrame.setLocation(0, 0);
        warnFrame.setLayout(null);
        warnFrame.setResizable(false);
        warnFrame.setVisible(false);

        warnLabel.setLocation(10, 10);
        warnLabel.setSize(380, 100);
        warnLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        warnBtn.setSize(100, 50);
        warnBtn.setLocation(150, 100);
        warnBtn.addActionListener(new ButtonAction());


        warnFrame.add(warnLabel);
        warnFrame.add(warnBtn);

        askFrame.setSize(400, 300);
        askFrame.setLocation(600, 500);
        askFrame.setResizable(false);
        askFrame.setVisible(false);
        askFrame.setLayout(null);
        askFrame.add(askLabel);
        askFrame.add(askBtn1);
        askFrame.add(askBtn2);




        askLabel.setText("Is it okay to delete?");
        askLabel.setLocation(50,10);
        askLabel.setSize(300, 50);
        askBtn1.addActionListener(new ButtonAction());
        askBtn2.addActionListener(new ButtonAction());
        askBtn1.setLocation(50, 60);
        askBtn1.setSize(dim_SButton);
        askBtn2.setLocation(200, 60);
        askBtn2.setSize(dim_SButton);

    }

    class ButtonAction implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JButton myButton = (JButton)e.getSource();
            String temp = myButton.getText();

            warnFrame.setVisible(false);


            if(temp.equals("�α���")) {
                userid = mainUserField.getText();
                passwd = new String(pwField.getPassword());
                mainUserField.setText("");
                pwField.setText("");

                mode = LOGINCHECK;
                System.out.println("Login ���� " + userid + " " + passwd);
                command();
            }
            else if(temp.equals("���� ����")) {
                mode = CREATEACCOUNT;
                createPanel.setLayout(null);
                frame.remove(mainPanel);
                frame.add(createPanel);
                frame.revalidate();
                frame.repaint();

                int x = 50, y = 10;

                for(int i =  0; i< newLabel.length ; i++) {
                    newLabel[i].setLocation(x, y);
                    newLabel[i].setSize(dim_Label);
                    createPanel.add(newLabel[i]);
                    y += 30;
                    newField[i].setLocation(x, y);
                    newField[i].setSize(dim_Field);
                    createPanel.add(newField[i]);
                    y += 40;
                    if(i==0) y += 50;

                }

                for(int i = 0; i < newBtn.length -1 ; i++) {
                    newBtn[i].setLocation(x, y+30);
                    newBtn[i].setSize(dim_SButton);
                    newBtn[i].addActionListener(this);
                    createPanel.add(newBtn[i]);
                    x += 150;
                }
                newBtn[2].setLocation(230, 80);
                newBtn[2].setSize(120, 50);
                newBtn[2].addActionListener(this);
                createPanel.add(newBtn[2]);

            }

            else if(temp.equals("����")) {
                int admission = 1;
                String warnMessage = "";

                if(check == 0) {
                    warnMessage = "���̵� �ߺ�Ȯ�� Ȯ���Ͻʽÿ�.";
                    admission = 0;
                }
                else if(!newField[1].getText().equals(newField[2].getText()) ) {
                    warnMessage = "�н����尡 ��ġ���� �ʽ��ϴ�.";
                    admission = 0;
                }
                else if(newField[1].getText().length() < 6) {
                    warnMessage = "�н������ 6�� �̻����� �������ּ���.";
                    admission = 0;

                }

                else {
                    for(int i = 0; i < newField.length; i++) {
                        if(newField[i].getText().length()==0) {
                            warnMessage = warnMessage + " " + newField[i].getText();
                            admission = 0;

                        }
                    }
                    if(admission == 0) warnMessage = "������ �� ĭ ���� ��� �Է����ּ���.";
                }
                if(admission == 0) {
                    warnLabel.setText(warnMessage);
                    warnFrame.setVisible(true);
                }
                else {
                    System.out.println("new account added");
                    userid = newField[0].getText();
                    passwd = newField[1].getText();
                    nickname = newField[3].getText();

                    for(int i = 0; i < newField.length; i++) {
                        newField[i].setText("");
                    }
                    command();
                    warnLabel.setText("���� ���� �Ϸ�!");
                    warnFrame.setVisible(true);
                    frame.remove(createPanel);
                    frame.add(mainPanel);
                    frame.revalidate();
                    frame.repaint();
                    mode = DEFAULT;
                    check = 0;
                }
            }

            else if(temp.equals("���")) {
                frame.remove(createPanel);
                frame.remove(userPanel);
                frame.add(mainPanel);
                frame.revalidate();
                frame.repaint();
                for(int i = 0; i < newField.length; i++) {
                    newField[i].setText("");
                }
                for(int i = 0; i < userField.length; i++) {
                    userField[i].setText("");
                }
                mode = DEFAULT;
            }
            else if(temp.equals("�ߺ�Ȯ��")) {
                System.out.println("hey");
                mode = REDUNTCHECK;
                namecheck = newField[0].getText();
                command();
                if(check == 0) {
                    warnLabel.setText("Username exists! Try different userid!");
                    warnFrame.setVisible(true);
                }
                else {
                    warnLabel.setText("Good! You can use this userid!");
                    warnFrame.setVisible(true);
                }
                mode = CREATEACCOUNT;
            }
   }

    public void command() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC ����̹� �ε�
            System.out.println("����̹� ���� ����!" + mode);

            conn = DriverManager.getConnection(url);
            System.out.println("�����ͺ��̽� ���� ����!");

            stmt = conn.createStatement();


            String useXproject = "use testDB";
            stmt.executeUpdate(useXproject);

            if(mode == LOGINCHECK) {
                col = "userid";
                what = userid;
                //username���� �˻��ϱ�
                String search = "select * from user where " + col + " like '" + what +"';";
                //username���� ���� �����Ͱ� rs�� ����ǰ� �� ������ Ȯ��!
                rs = stmt.executeQuery(search);
                if(rs.next()) {
                    if(passwd.equals(rs.getString("passwd"))){
                        System.out.println("login succeeded");
                        chatname = rs.getString("nickname");
                        System.out.println("�α��ο��� �׽�Ʈ " + chatname);
                        lobby.setChatname(chatname);
                        socketcheck = true;
                        logincheck = true;

                        frame.setVisible(false);
                        new lobby();

//                      new lobby(nickname);
                    }
                    else {
                        System.out.println("login failed");
                        warnFrame.setVisible(true);
                        warnLabel.setText("Password is wrong.");
                    }
                }
                else {
                    System.out.println("login failed");
                    warnFrame.setVisible(true);
                    warnLabel.setText("UserName does not exist.");


                }
            }

            else if(mode == CREATEACCOUNT) {

                String adduser = "insert into user(userid, passwd, nickname) "
                        + "values('"+ userid + "', '" + passwd + "', '"
                        + nickname + "');";
                System.out.println(adduser);
                stmt.executeUpdate(adduser);
            }
            else if(mode == REDUNTCHECK) {
                String search = "select * from user where userid like '" + namecheck +"';";
                rs = stmt.executeQuery(search);
                if(rs.next()) {
                    System.out.println(rs.getString("userid"));
                    check = 0;
                }
                else {
                    System.out.println("check changed");
                    check = 1;
                }
            }
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
}