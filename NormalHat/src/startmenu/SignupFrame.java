package startmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

import lobby.lobby;


public class SignupFrame extends JFrame {

    public static final int DEFAULT = 0;
    public static final int LOGINCHECK = 1;
    public static final int CREATEACCOUNT = 2;
    public static final int REDUNTCHECK = 3;

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


    JPanel panel;
    JButton signup;
    JButton end_frame;
    JButton redunt_btn;

    Font font = new Font("Neo둥근모",Font.BOLD,24);

    JTextField [] signup_field = new JTextField[2];
    JPasswordField [] signup_pw = new JPasswordField[2];
//    JFrame warnFrame = new JFrame();
//    JLabel warnLabel = new JLabel();
//    JButton warnBtn = new JButton("확인");
    JLabel msg = new JLabel();

    public void command() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로드
            System.out.println("드라이버 연결 성공!" + mode);

            conn = DriverManager.getConnection(url);
            System.out.println("데이터베이스 연결 성공!");

            stmt = conn.createStatement();


            String useProjectDB = "use testDB";
            stmt.executeUpdate(useProjectDB);

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
//                                socketcheck = true;
//                                logincheck = true;
//
//                                frame.setVisible(false);
                        new lobby();

//                      new lobby(nickname);
                    }
                    else {
                        System.out.println("login failed");
//                        warnFrame.setVisible(true);
                        SignupFrame.this.msg.setText("Password is wrong.");
                    }
                }
                else {
                    System.out.println("login failed");
//                    warnFrame.setVisible(true);
                    SignupFrame.this.msg.setText("UserName does not exist.");


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
                    System.out.println(rs.getString("userid") + " 는 사용중입니다.");
                    check = 0;
                }
                else {
                    System.out.println("ID 사용가능");
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

    public SignupFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1100, 800);
        this.setLocationRelativeTo(null); // 창 중앙에뜨기
        this.setTitle("회원가입");
        this.setResizable(false);    //	창 크기 고정

        getContentPane().setLayout(new BorderLayout(0, 0));
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                ImageIcon img = new ImageIcon("images/title/signup1.png");
                Dimension d = getSize();
                g.drawImage(img.getImage(), 0, 0, d.width, d.height, null);
            }
        };
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        msg.setBackground(Color.white);
        msg.setOpaque(true);		// 레이블 백그라운드 색 허용하겠다는 뜻
        msg.setBounds(240,215,580,50);
        msg.setHorizontalAlignment(JLabel.CENTER);
        msg.setFont(font);
        panel.add(msg);



        signup_field[0] = new JTextField();
        signup_field[0].setBackground(Color.white);
        signup_field[0].setBounds(400,300,420,50);
        panel.add(signup_field[0]);

        signup_pw[0] = new JPasswordField(10);
        signup_pw[0].setBackground(Color.white);
        signup_pw[0].setBounds(400,385,420,50);
        signup_pw[0].setEchoChar('*'); // 비밀번호 입력시 별모양 출력
        panel.add(signup_pw[0]);

        signup_pw[1] = new JPasswordField(10);
        signup_pw[1].setBackground(Color.white);
        signup_pw[1].setBounds(400,470,420,50);
        signup_pw[1].setEchoChar('*'); // 비밀번호 입력시 별모양 출력
        panel.add(signup_pw[1]);

        signup_field[1] = new JTextField();
        signup_field[1].setBackground(Color.white);
        signup_field[1].setBounds(400,555,420,50);
        panel.add(signup_field[1]);





//        warnFrame.setSize(400, 200);
//        warnFrame.setLocation(0, 0);
//        warnFrame.setLayout(null);
//        warnFrame.setResizable(false);
//        warnFrame.setVisible(false);
//
//        warnLabel.setLocation(10, 10);
//        warnLabel.setSize(380, 100);
//        warnLabel.setFont(new Font("Arial", Font.PLAIN, 15));
//
//        warnBtn.setSize(100, 50);
//        warnBtn.setLocation(150, 100);
//        warnBtn.addActionListener(new .ButtonAction());

//        warnFrame.add(warnLabel);
//        warnFrame.add(warnBtn);



        ImageIcon signup_i = new ImageIcon("images/title/join.png");
        ImageIcon signup_i_mouse = new ImageIcon("images/title/join_mouse.png");
        ImageIcon signup_i_click = new ImageIcon("images/title/join_click.png");
        signup = new JButton(signup_i); // 회원가입에 iogin_i 넣을 예정
        signup.setBorderPainted(false);		//회원가입 박스 테두리 없애는 기능
        signup.setContentAreaFilled(false);	//회원가입 박스 색깔 없애는 기능
        signup.setBounds(430, 650, signup_i.getIconWidth(), signup_i.getIconHeight());
        signup.setFocusPainted(false);		//회원가입 글자 테두리 없애는 기능
        signup.setCursor(new Cursor(Cursor.HAND_CURSOR));	// 회원가입쪽에 커서 갖다대면 손모양으로 바뀜
        panel.add(signup);
        signup.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {


            }

            @Override
            public void mousePressed(MouseEvent e) {
                signup.setPressedIcon(signup_i_click);
                int admission = 1;
                String warnmsg = "";

                if(check == 0) {
                    warnmsg = "아이디 중복확인 확인하십시오.";
                    admission = 0;
                }
                else if(!signup_pw[0].getText().equals(signup_pw[1].getText()) ) {
                    warnmsg = "패스워드가 일치하지 않습니다.";
                    admission = 0;
                }
                else if(signup_pw[0].getText().length() < 6) {
                    warnmsg = "패스워드는 6자 이상으로 설정해주세요.";
                    admission = 0;

                }

                else {
                    for(int i = 0; i < signup_field.length; i++) {
                        if(signup_field[i].getText().length()==0) {
                            warnmsg = warnmsg + " " + signup_field[i].getText();
                            admission = 0;
                        }
                        else
                        {
                            for (int j = 0 ; j < signup_pw.length; j++)
                            if(signup_pw[j].getText().length()==0)
                            {
                                warnmsg = warnmsg + " " + signup_pw[j].getText();
                                admission = 0;
                            }
                        }
                    }
                    if(admission == 0)
                        warnmsg = "정보를 빈 칸 없이 모두 입력해주세요.";
                }
                if(admission == 0) {
                    msg.setText(warnmsg);
                }
                else {
                    System.out.println("계정이 생성되었습니다.");
                    userid = signup_field[0].getText();
                    passwd = signup_pw[0].getText();
                    nickname = signup_field[1].getText();

                    for(int i = 0; i < signup_field.length; i++) {
                        signup_field[i].setText("");
                    }
                    for(int i = 0; i < signup_pw.length; i++) {
                        signup_pw[i].setText("");
                    }

                    command();
                    msg.setText("계정 생성 완료!");
                    mode = DEFAULT;
                    check = 0;
                }
                if (admission==1)
                {
                    SignupFrame.this.setVisible(false);
                    new StartMenu();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                signup.setIcon(signup_i_mouse);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                signup.setIcon(signup_i);

            }

        });


        ImageIcon end_i = new ImageIcon("images/title/end.png");
        ImageIcon end_i_mouse = new ImageIcon("images/title/end_mouse.png");
        ImageIcon end_i_click = new ImageIcon("images/title/end_click.png");
        end_frame = new JButton(end_i);
        end_frame.setBounds(650, 650 ,end_i.getIconWidth(), end_i.getIconHeight());
        end_frame.setBorderPainted(false);		//종료 박스 테두리 없애는 기능
        end_frame.setContentAreaFilled(false);	//종료 박스 색깔 없애는 기능
        end_frame.setFocusPainted(false);
        end_frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(end_frame);
        end_frame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {


            }

            @Override
            public void mousePressed(MouseEvent e) {
                end_frame.setPressedIcon(end_i_click);
                SignupFrame.this.setVisible(false);
                new StartMenu();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                end_frame.setIcon(end_i_mouse);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                end_frame.setIcon(end_i);

            }

        });





        ImageIcon redunt_i = new ImageIcon("images/title/redunt.png");
        ImageIcon redunt_i_mouse = new ImageIcon("images/title/redunt_mouse.png");
        ImageIcon redunt_i_click = new ImageIcon("images/title/redunt_click.png");
        redunt_btn = new JButton(redunt_i);
        redunt_btn.setBounds(840, 280 ,redunt_i.getIconWidth(), redunt_i.getIconHeight());
        redunt_btn.setBorderPainted(false);		//종료 박스 테두리 없애는 기능
        redunt_btn.setContentAreaFilled(false);	//종료 박스 색깔 없애는 기능
        redunt_btn.setFocusPainted(false);
        redunt_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(redunt_btn);
        redunt_btn.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {


            }

            @Override
            public void mousePressed(MouseEvent e) {
                redunt_btn.setPressedIcon(redunt_i_click);
                mode = REDUNTCHECK;
                namecheck = signup_field[0].getText();
                command();

                    if (check == 0) {
                        msg.setText("계정이 존재합니다. 다른 아이디를 사용해주세요.");
                    } else {
                            msg.setText(namecheck + " 는 아이디로 사용 가능합니다.");
                    }
                    mode = CREATEACCOUNT;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                redunt_btn.setIcon(redunt_i_mouse);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                redunt_btn.setIcon(redunt_i);

            }

        });



        this.setVisible(true);


    }
}