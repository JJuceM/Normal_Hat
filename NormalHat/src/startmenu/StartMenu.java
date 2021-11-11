package startmenu;

import lobby.lobby;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;


public class StartMenu extends JFrame  {
	
	JPanel panel;
	JButton login;
	JButton join;
	JButton end;
	JTextField login_id;
	JPasswordField login_pw;
	JLabel msg;
	Font font = new Font("Neo둥근모",Font.BOLD,24);

	String what;
	String col;
	String userid;
	String nickname;
	String passwd;
	String namecheck;
	String chatname;

	static 	String url = "jdbc:mysql://127.0.0.1/testDB?serverTimezone=UTC&&useSSL=false&user=root&password=admin0309!";
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;


	public StartMenu() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1100, 800);
		this.setLocationRelativeTo(null); // 창 중앙에뜨기
		this.setTitle("Nomal Hat");
		this.setResizable(false);	//	창 크기 고정
		
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				ImageIcon img = new ImageIcon("images/title/title.png");
				Dimension d = getSize();
				g.drawImage(img.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel msg = new JLabel();
		msg.setBackground(Color.white);
		msg.setOpaque(true);		// 레이블 백그라운드 색 허용하겠다는 뜻
		msg.setBounds(225,520,625,50);
		msg.setHorizontalAlignment(JLabel.CENTER);
		msg.setFont(font);
		panel.add(msg);

		JTextField login_id = new JTextField();
		login_id.setBackground(Color.white);
		login_id.setBounds(335,355,390,50);
		panel.add(login_id);

		JPasswordField login_pw = new JPasswordField(10);
		login_pw.setBackground(Color.white);
		login_pw.setBounds(335,430,390,50);
		login_pw.setEchoChar('*'); // 비밀번호 입력시 별모양 출력
		panel.add(login_pw);



		ImageIcon login_i = new ImageIcon("images/title/login.png");
		ImageIcon login_i_mouse = new ImageIcon("images/title/login_mouse.png");
		ImageIcon login_i_click = new ImageIcon("images/title/login_click.png");
		login = new JButton(login_i); // 로그인에 iogin_i 넣을 예정
		login.setBorderPainted(false);		//로그인 박스 테두리 없애는 기능
		login.setContentAreaFilled(false);	//로그인 박스 색깔 없애는 기능
		login.setBounds(740, 340, login_i.getIconWidth(), login_i.getIconHeight());
		login.setFocusPainted(false);		//로그인 글자 테두리 없애는 기능
		login.setCursor(new Cursor(Cursor.HAND_CURSOR));	// 로그인쪽에 커서 갖다대면 손모양으로 바뀜
		panel.add(login);
		login.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				login.setPressedIcon(login_i_click);
				userid = login_id.getText();
				passwd = new String(login_pw.getPassword());
				login_id.setText("");
				login_pw.setText("");
				System.out.println("Login 정보 " + userid + " " + passwd);
				Login();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				login.setIcon(login_i_mouse);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				login.setIcon(login_i);
				
			}
			public void Login(){
				try {
					Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로드
					System.out.println("드라이버 연결 성공!");

					conn = DriverManager.getConnection(url);
					System.out.println("데이터베이스 연결 성공!");

					stmt = conn.createStatement();


					String useProjectDB = "use testDB";
					stmt.executeUpdate(useProjectDB);

					col = "userid";
					what = userid;
					//username으로 검색하기
					String search = "select * from user where " + col + " like '" + what + "';";
					//username으로 받은 데이터가 rs에 저장되고 그 내용을 확인!
					rs = stmt.executeQuery(search);
					if (rs.next()) {
						if (passwd.equals(rs.getString("passwd"))) {
							System.out.println("login succeeded");
							chatname = rs.getString("nickname");
							System.out.println("로그인에서 테스트 " + chatname);
							lobby.setChatname(chatname);
							//                                socketcheck = true;
							//                                logincheck = true;
							new lobby();
							//                      new lobby(nickname);
						}
						else {
							System.out.println("로그인 실패");
							msg.setText("패스워드가 올바르지 않습니다.");
						}
					}
					else {
						System.out.println("로그인 실패");
						msg.setText("사용자 계정이 존재하지 않습니다.");
					}
				}
				catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		});


		
		
		ImageIcon join_i = new ImageIcon("images/title/join.png");
		ImageIcon join_i_mouse = new ImageIcon("images/title/join_mouse.png");
		ImageIcon join_i_click = new ImageIcon("images/title/join_click.png");
		join = new JButton(join_i); 
		join.setBounds(740,415,join_i.getIconWidth(), join_i.getIconHeight());
		join.setBorderPainted(false);		//로그인 박스 테두리 없애는 기능
		join.setContentAreaFilled(false);	//로그인 박스 색깔 없애는 기능
		join.setFocusPainted(false);
		join.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(join);
		join.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				join.setPressedIcon(join_i_click);
				msg.setText("회원가입 페이지로 이동합니다.");
				StartMenu.this.setVisible(false);
				new SignupFrame();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				join.setIcon(join_i_mouse);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				join.setIcon(join_i);
				
			}
			
		});
		
		ImageIcon end_i = new ImageIcon("images/title/end.png");
		ImageIcon end_i_mouse = new ImageIcon("images/title/end_mouse.png");
		ImageIcon end_i_click = new ImageIcon("images/title/end_click.png");
		end = new JButton(end_i);
		end.setBounds(450,595,end_i.getIconWidth(),end_i.getIconHeight());
		end.setBorderPainted(false);
		end.setContentAreaFilled(false);
		end.setFocusPainted(false);
		end.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(end);
		end.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				end.setPressedIcon(end_i_click);
				System.exit(0);
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				end.setIcon(end_i_mouse);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				end.setIcon(end_i);
				
			}
			
		});
		

//		login_pw.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				isLoginCheck();
//				
//			}
//
//			private void isLoginCheck() {
//				// TODO Auto-generated method stub
//				
//			}
//
//			
//		});
		
		this.setVisible(true);
	}


	public static void main(String[] args) {
		new StartMenu();
	}
	
}




