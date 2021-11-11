package Login;

import javax.swing.*;

public class LoginFrame extends Logintest2 {

    String what;
    String col;
    String userid;
    String nickname;
    String passwd;
    String namecheck;
    String chatname;

    JFrame frame = new JFrame("Login Program");
    JPanel mainPanel = new JPanel();
    JLabel mainUserLabel = new JLabel("ID");
    JTextField mainUserField = new JTextField("");
    JLabel pwLabel = new JLabel("Password");
    JPasswordField pwField = new JPasswordField();
    JButton loginBtn = new JButton("로그인");
    JButton createBtn =	new JButton("계정 생성");
}


