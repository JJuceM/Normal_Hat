package Login;

import lobby.lobby;

import java.sql.*;

public class DBConnection extends Logintest2 {
    static 	String url = "jdbc:mysql://127.0.0.1/testDB?serverTimezone=UTC&&useSSL=false&user=root&password=admin0309!";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
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



