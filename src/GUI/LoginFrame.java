package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import B.UserB;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField passwordField;
	private JLabel lbMessenge;
	private boolean isAdmin = false;
	int xx, xy;
	private String name;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		setTitle("LoginFrame");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 562, 659);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("");
		label.setBackground(Color.BLACK);

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();
				xy = e.getY();
			}
		});
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				LoginFrame.this.setLocation(x - xx, y - xy);
			}
		});
		label.setBounds(118, 110, 344, 414);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/logo.jpg")));
		panel.add(label);

		Button button = new Button("L O G I N");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					UserB userB = new UserB();
					if (userB.checkLogin(tfUsername.getText(), String.valueOf(passwordField.getPassword()))) {
						setName(userB.getName(tfUsername.getText()));
						MainFrame main = new MainFrame(LoginFrame.this);
						main.setVisible(true);
						isAdmin = userB.checkAdmin(tfUsername.getText(), String.valueOf(passwordField.getPassword()));
						LoginFrame.this.setVisible(false);
					} else {
						lbMessenge.setForeground(Color.RED);
						lbMessenge.setText("Incorect username or password!");
						tfUsername.requestFocus();
						tfUsername.selectAll();
						passwordField.setText("");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setForeground(Color.BLACK);
		button.setBackground(Color.CYAN);
		button.setBounds(755, 438, 283, 36);
		contentPane.add(button);

		JLabel lbUsername = new JLabel("USERNAME");
		lbUsername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lbUsername.setBounds(755, 160, 237, 36);
		lbUsername.setForeground(Color.CYAN);
		contentPane.add(lbUsername);

		tfUsername = new JTextField();
		tfUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()== KeyEvent.VK_ENTER)
				try {
					UserB userB = new UserB();
					if (userB.checkLogin(tfUsername.getText(), String.valueOf(passwordField.getPassword()))) {
						setName(userB.getName(tfUsername.getText()));
						MainFrame main = new MainFrame(LoginFrame.this);
						main.setVisible(true);
						isAdmin = userB.checkAdmin(tfUsername.getText(), String.valueOf(passwordField.getPassword()));
						LoginFrame.this.setVisible(false);
					} else {
						lbMessenge.setForeground(Color.RED);
						lbMessenge.setText("Incorect username or password!");
						tfUsername.requestFocus();
						tfUsername.selectAll();
						passwordField.setText("");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		tfUsername.setColumns(10);
		tfUsername.setBounds(755, 207, 283, 36);
		contentPane.add(tfUsername);

		JLabel lbPassword = new JLabel("PASSWORD");
		lbPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lbPassword.setBounds(755, 254, 237, 36);
		lbPassword.setForeground(Color.CYAN);
		contentPane.add(lbPassword);

		lbMessenge = new JLabel("Please enter your username and password");
		lbMessenge.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lbMessenge.setBounds(755, 369, 283, 48);
		lbMessenge.setForeground(Color.CYAN);
		contentPane.add(lbMessenge);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER)
				try {
					UserB userB = new UserB();
					if (userB.checkLogin(tfUsername.getText(), String.valueOf(passwordField.getPassword()))) {
						setName(userB.getName(tfUsername.getText()));
						MainFrame main = new MainFrame(LoginFrame.this);
						main.setVisible(true);
						isAdmin = userB.checkAdmin(tfUsername.getText(), String.valueOf(passwordField.getPassword()));
						LoginFrame.this.setVisible(false);
					} else {
						lbMessenge.setForeground(Color.RED);
						lbMessenge.setText("Incorect username or password!");
						tfUsername.requestFocus();
						tfUsername.selectAll();
						passwordField.setText("");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		passwordField.setBounds(755, 301, 283, 36);
		contentPane.add(passwordField);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

}
