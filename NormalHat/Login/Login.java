package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Login {

    //프레임에 들어가는 요소들
    JFrame frame = new JFrame();
//    Board board = new Board();
    MainPanel main;
//    ResultPanel resultPanel;
    JLabel user1 = new JLabel();
    JLabel user2 = new JLabel();
    JLabel user1Record = new JLabel("0승 0패");
    JLabel user2Record = new JLabel("0승 0패");
    JButton undoBtn1 = new JButton("무르기");
    JButton surrenBtn1 = new JButton("기권");
    JButton undoBtn2 = new JButton("무르기");
    JButton surrenBtn2 = new JButton("기권");
    JButton repickBtn = new JButton("재선택");
    JButton userModeBtn = new JButton("유저모드");
    JButton funModeBtn = new JButton("그냥한판!");


    //userMain
    UserMainPanel userMain;
    JLabel userUser1 = new JLabel();
    JLabel userUser2 = new JLabel();
    JLabel userNickname1 = new JLabel();
    JLabel userNickname2 = new JLabel();

    JLabel userUser1Record = new JLabel("0승 0패");
    JLabel userUser2Record = new JLabel("0승 0패");
    JButton userUndoBtn1 = new JButton("무르기");
    JButton userSurrenBtn1 = new JButton("기권");
    JButton userUndoBtn2 = new JButton("무르기");
    JButton userSurrenBtn2 = new JButton("기권");
//    UserBoard userBoard = new UserBoard();


    //loginPanel
    LoginPanel loginPanel = new LoginPanel();
    JPanel mainPanel = new JPanel();
    JLabel mainUserLabel = new JLabel("UserName");
    JTextField mainUserField1 = new JTextField("");
    JLabel pwLabel = new JLabel("Password");
    JPasswordField pwField1 = new JPasswordField();
    JButton loginBtn = new JButton("Log in");
    JButton createBtn = new JButton("Create Account");

    //addUserPanel
    AddUserPanel addUserPanel = new AddUserPanel();
    JLabel[] newLabel = new JLabel[5];
    JTextField[] newField = new JTextField[4];
    JButton[] newCharBtn = new JButton[7];
    JButton[] newBtn = new JButton[4];

    //네트워크
    String data = "";
    String str2;
    Socket socket = null;
    DataInputStream in = null;
    BufferedReader in2 = null;
    DataOutputStream out = null;
    public String nickname;
    public String username;
    public String passwd;
    public String myNickname;
    public String myWin;
    public String myLose;
    public String myPocketmon;
    public int myTurn;

    public String enemyUsername;
    public String enemyNickname;
    public String enemyWin;
    public String enemyLose;
    public String enemyPocketmon;

    //스타트 패널에 들어가는 요소들
    FunPanel funPanel;
    StartPanel startPanel = new StartPanel();
    JLabel startMsg = new JLabel("육목게임");
    JLabel connect6 = new JLabel("Connect 6");
    JLabel startMsg1 = new JLabel("육목게임");
    JLabel connect61 = new JLabel("Connect 6");
    JLabel startMsg2 = new JLabel("육목게임");
    JLabel connect62 = new JLabel("Connect 6");
    JLabel askMessage = new JLabel("캐릭터를 골라주세요!");
    JButton startBtn = new JButton("Start");
    JButton [] charBtn = new JButton[7];

    JLabel winLabel = new JLabel();
    JLabel loseLabel = new JLabel();
    JButton regameBtn = new JButton("재대결");
    JButton toStartBtn = new JButton("처음으로");
    JButton toStartBtn1 = new JButton("처음으로");
    JButton toStartBtn2 = new JButton("처음으로");
    JLabel winMsg = new JLabel("승");
    JLabel loseMsg = new JLabel("패");

    JLabel userWinLabel = new JLabel();
    JLabel userLoseLabel = new JLabel();
    JButton userRegameBtn = new JButton("재대결");
    JButton userToStartBtn = new JButton("대기실로");
    JLabel userWinMsg = new JLabel("승");
    JLabel userLoseMsg = new JLabel("패");


    String [] filePath = new String [11];
    
    BufferedImage imageA;
    BufferedImage imageB;
    BufferedImage imageC; //
    BufferedImage imageD;
    BufferedImage imageE;
    BufferedImage imageF;
    BufferedImage imageG;
    BufferedImage imageH;
    BufferedImage imageI;
    BufferedImage imageJ; //게임대기배경


    Login() {
        frame.add(startPanel);
        startPanel.setLayout(null);
        startPanel.setBounds(0, 0, 1440, 900);
        startPanel.add(connect6);
        startPanel.add(startMsg);
        startPanel.add(userModeBtn);
        startPanel.add(funModeBtn);

        filePath[0] = "psyduck.png";
        filePath[1] = "charmander.png";
        filePath[2] = "caterpie.png";
        filePath[3] = "meowth.png";
        filePath[4] = "zubat.png";
        filePath[5] = "jigglypuff.png";
        filePath[6] = "snorlax.png";
        filePath[7] = "포켓몬 게임 배경.jpg";
        filePath[8] = "포켓몬 게임 배경2.jpg";
        filePath[9] = "포켓몬 게임 배경3.jpg";
        filePath[10] = "배경.png";



        //userMain
        userMain = new UserMainPanel();

        userMain.setBounds(0,0,1440,900);
        userMain.setLayout(null);
//        userBoard.setLayout(null);
//        userBoard.setBounds(40, 30, 800, 800);

        userUser1Record.setBounds(870, 350, 240, 100);
        userUser1Record.setFont(new Font("Rix프리스타일 B", Font.BOLD, 60));
        userUser2Record.setBounds(1200, 350, 240, 100);
        userUser2Record.setFont(new Font("Rix프리스타일 B", Font.BOLD, 60));

        userUser1.setBounds(870, 50, 200, 200);
        userUser2.setBounds(1200, 50, 200, 200);

        userNickname1.setBounds(870, 250, 330, 100);
        userNickname1.setFont(new Font("Rix프리스타일 B", Font.BOLD, 60));
        userNickname1.setHorizontalAlignment(JLabel.CENTER);
        userNickname2.setBounds(1200, 250, 330, 100);
        userNickname2.setHorizontalAlignment(JLabel.CENTER);
        userNickname2.setFont(new Font("Rix프리스타일 B", Font.BOLD, 60));


        userUndoBtn1.setBounds(870, 450, 100, 50);
        userUndoBtn1.addActionListener(new MyActionListener());
        userSurrenBtn1.setBounds(970, 450, 100, 50);
        userSurrenBtn1.addActionListener(new MyActionListener());

        userUndoBtn2.setBounds(1200, 450, 100, 50);
        userUndoBtn2.addActionListener(new MyActionListener());
        userSurrenBtn2.setBounds(1300, 450, 100, 50);
        userSurrenBtn2.addActionListener(new MyActionListener());


        userModeBtn.setBounds(600, 600, 100, 50);
        userModeBtn.addActionListener(new MyActionListener());
        funModeBtn.setBounds(700, 600, 100, 50);
        funModeBtn.addActionListener(new MyActionListener());
        toStartBtn1.setBounds(680, 700, 100, 50);
        toStartBtn1.addActionListener(new MyActionListener());

        mainUserLabel.setBounds(500, 600, 100, 50);
        mainUserField1.setBounds(600, 600, 200, 50);

        pwLabel.setBounds(500, 650, 100, 50);
        pwField1.setBounds(600, 650, 200, 50);

        loginBtn.setBounds(800, 600, 100, 100);
        createBtn.setBounds(780, 700, 120, 50);

        loginBtn.addActionListener(new MyActionListener());
        createBtn.addActionListener(new MyActionListener());

        loginPanel.setLayout(null);
        loginPanel.setBounds(0, 0, 1440, 900);

        loginPanel.add(mainUserLabel);
        loginPanel.add(mainUserField1);
        loginPanel.add(toStartBtn1);
        loginPanel.add(pwLabel);
        loginPanel.add(pwField1);
        loginPanel.add(loginBtn);
        loginPanel.add(createBtn);
        loginPanel.add(connect62);
        loginPanel.add(startMsg2);


//        userMain.add(userBoard);
        userMain.add(userUser1);
        userMain.add(userUser2);
        userMain.add(userUndoBtn1);
        userMain.add(userSurrenBtn1);
        userMain.add(userUndoBtn2);
        userMain.add(userSurrenBtn2);
        userMain.add(userNickname1);
        userMain.add(userNickname2);
        userMain.add(userUser1Record);
        userMain.add(userUser2Record);



    }


    class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JButton myButton = (JButton) e.getSource();
            String temp = myButton.getText();
            if(myButton == loginBtn) {
                username = mainUserField1.getText();
                passwd = new String(pwField1.getPassword());
                data = username + " " + passwd;
                mainUserField1.setText("");
                pwField1.setText("");
                try {
                    out.writeUTF("LOGINCHECK");
                    out.writeUTF(data);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            else if(myButton == createBtn) {
                frame.remove(loginPanel);
                frame.add(addUserPanel);
                frame.revalidate();
                frame.repaint();
            }
        }
    }



    class LoginPanel extends JPanel {
        LoginPanel() { }
        public void paintComponent(Graphics g) {
            g.drawImage(imageC, 0, 0, null);
        }
    }
    class AddUserPanel extends JPanel{
        AddUserPanel(){
        }
        public void paintComponent(Graphics g) {
            g.drawImage(imageC, 0, 0, null);
        }
    }

    private class StartPanel extends JPanel{
        StartPanel(){

        }

        public void paintComponent(Graphics g) {
            g.drawImage(imageC, 0, 0, null);
        }
    }
    class UserMainPanel extends JPanel{
        UserMainPanel(){
        }
        public void paintComponent(Graphics g) {
            g.drawImage(imageF, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Login();
    }


    class MainPanel extends JPanel{
        MainPanel(){
        }
        public void paintComponent(Graphics g) {
            g.drawImage(imageF, 0, 0, null);
        }
    }


    private class FunPanel extends JPanel{
        FunPanel(){
        }
        public void paintComponent(Graphics g) {
            g.drawImage(imageC, 0, 0, null);
        }
    }
}