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
    JButton loginBtn = new JButton("로그인");
    JButton createBtn =	new JButton("계정 생성");

    JFrame warnFrame = new JFrame();
    JLabel warnLabel = new JLabel();
    JButton warnBtn = new JButton("확인");

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
            // 소켓 서버에 접속
            socket = new Socket("127.0.0.1", 2030);
            System.out.println("서버에 접속 성공!"); // 접속 확인용

            // 서버에서 보낸 메세지 읽는 Thread
            ListenThread t1 = new ListenThread(socket);
            writeThread t2 = new writeThread(socket); // 서버로 메세지 보내는 Thread

            t1.start(); // ListeningThread Start
            t2.start(); // WritingThread Start

        } catch (IOException e) {
            e.printStackTrace(); // 예외처리
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

        newLabel[0] = new JLabel("아이디");
        newField[0] = new JTextField();
        newLabel[1] = new JLabel("패스워드(6자 이상)");
        newField[1] = new JTextField();
        newLabel[2] = new JLabel("패스워드 확인");
        newField[2] = new JTextField();
        newLabel[3] = new JLabel("닉네임");
        newField[3] = new JTextField();
        newBtn[0] = new JButton("생성");
        newBtn[1] = new JButton("취소");
        newBtn[2] = new JButton("중복확인");


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


            if(temp.equals("로그인")) {
                userid = mainUserField.getText();
                passwd = new String(pwField.getPassword());
                mainUserField.setText("");
                pwField.setText("");

                mode = LOGINCHECK;
                System.out.println("Login 정보 " + userid + " " + passwd);
                command();
            }
            else if(temp.equals("계정 생성")) {
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

            else if(temp.equals("생성")) {
                int admission = 1;
                String warnMessage = "";

                if(check == 0) {
                    warnMessage = "아이디 중복확인 확인하십시오.";
                    admission = 0;
                }
                else if(!newField[1].getText().equals(newField[2].getText()) ) {
                    warnMessage = "패스워드가 일치하지 않습니다.";
                    admission = 0;
                }
                else if(newField[1].getText().length() < 6) {
                    warnMessage = "패스워드는 6자 이상으로 설정해주세요.";
                    admission = 0;

                }

                else {
                    for(int i = 0; i < newField.length; i++) {
                        if(newField[i].getText().length()==0) {
                            warnMessage = warnMessage + " " + newField[i].getText();
                            admission = 0;

                        }
                    }
                    if(admission == 0) warnMessage = "정보를 빈 칸 없이 모두 입력해주세요.";
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
                    warnLabel.setText("계정 생성 완료!");
                    warnFrame.setVisible(true);
                    frame.remove(createPanel);
                    frame.add(mainPanel);
                    frame.revalidate();
                    frame.repaint();
                    mode = DEFAULT;
                    check = 0;
                }
            }

            else if(temp.equals("취소")) {
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
            else if(temp.equals("중복확인")) {
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

            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로드
            System.out.println("드라이버 연결 성공!" + mode);

            conn = DriverManager.getConnection(url);
            System.out.println("데이터베이스 연결 성공!");

            stmt = conn.createStatement();


            String useXproject = "use testDB";
            stmt.executeUpdate(useXproject);

            if(mode == LOGINCHECK) {
                col = "userid";
                what = userid;
                //username으로 검색하기
                String search = "select * from user where " + col + " like '" + what +"';";
                //username으로 받은 데이터가 rs에 저장되고 그 내용을 확인!
                rs = stmt.executeQuery(search);
                if(rs.next()) {
                    if(passwd.equals(rs.getString("passwd"))){
                        System.out.println("login succeeded");
                        chatname = rs.getString("nickname");
                        System.out.println("로그인에서 테스트 " + chatname);
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