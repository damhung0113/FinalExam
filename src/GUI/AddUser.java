package GUI;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import B.UserB;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AddUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfUsername;
	private JTextField tfName;
	private JTextField tfDoB;
	private JTextField tfEmail;
	private JTextField tfRole;
	private JPasswordField pwField;

	/**
	 * Create the frame.
	 */
	public AddUser(UserB b, UserMgmt parent) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		System.out.println();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(750, 650, 549, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][][]"));
		setContentPane(contentPane);

		JLabel lbAdd = new JLabel("ADD NEW USER");
		lbAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lbAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lbAdd, "cell 1 0");

		JLabel lblId = new JLabel("ID ");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblId, "cell 0 2,alignx trailing");

		tfID = new JTextField();
		contentPane.add(tfID, "cell 1 2,growx");
		tfID.setColumns(10);

		JLabel lblLocation = new JLabel("Username");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLocation, "cell 0 3,alignx trailing");

		tfUsername = new JTextField();
		contentPane.add(tfUsername, "cell 1 3,growx");
		tfUsername.setColumns(10);

		JLabel lblType = new JLabel("Password");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblType, "cell 0 4,alignx trailing");

		pwField = new JPasswordField();
		contentPane.add(pwField, "cell 1 4,growx");

		JLabel lbWarningPassword = new JLabel(
				"Your password must contain at least 8 characters, including UPPER/lowercase and numbers");
		contentPane.add(lbWarningPassword, "cell 1 5");

		JLabel lblCapacity = new JLabel("Full name");
		lblCapacity.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCapacity, "cell 0 6,alignx trailing,growy");

		tfName = new JTextField();
		contentPane.add(tfName, "cell 1 6,growx");
		tfName.setColumns(10);

		JLabel lblAirconditional = new JLabel("Email");
		lblAirconditional.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAirconditional, "cell 0 7,alignx trailing");

		tfEmail = new JTextField();
		contentPane.add(tfEmail, "cell 1 7,growx");
		tfEmail.setColumns(10);

		JLabel lblNewLabel = new JLabel("Date of Birth");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "cell 0 8,alignx trailing");

		tfDoB = new JTextField();
		contentPane.add(tfDoB, "cell 1 8,growx");
		tfDoB.setColumns(10);

		JLabel lblScreen = new JLabel("Sex (Male)");
		lblScreen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblScreen, "cell 0 9");

		JCheckBox chbSex = new JCheckBox("");
		contentPane.add(chbSex, "cell 1 9");

		JLabel lblFan = new JLabel("Role");
		contentPane.add(lblFan, "cell 0 10,alignx trailing");

		tfRole = new JTextField();
		contentPane.add(tfRole, "cell 1 10,growx");
		tfRole.setColumns(10);

		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int id = Integer.parseInt(tfID.getText());
					String username = tfUsername.getText();
					String password = String.valueOf(pwField.getPassword());
					String email = tfEmail.getText();
					String name = tfName.getText();
					Date dob = Date.valueOf(tfDoB.getText());
					boolean isMale = chbSex.isSelected();
					int role = Integer.parseInt(tfRole.getText());
					if ((role == 0) || (role == 1) || (role == 2))
						try {
							if (!b.checkID(id)) {
								if (!(b.checkUsername(username))) {
									if (b.valPassword(password)) {
										b.addUser(id, username, password, email, name, dob, isMale, role);
										JOptionPane.showMessageDialog(rootPane, "Add user successed");
										parent.initModel(true);
										AddUser.this.dispose();
									} else
										JOptionPane.showMessageDialog(rootPane, "Your password is too weak");
								} else
									JOptionPane.showMessageDialog(rootPane,
											"This Username has already been taken, please choose another");
							} else
								JOptionPane.showMessageDialog(rootPane,
										"This ID has already been taken, please choose another");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					else
						JOptionPane.showMessageDialog(rootPane, "Invalid role");
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(rootPane, "Invalid input type");
				}
			}
		});
		contentPane.add(btnAdd, "cell 1 13,alignx trailing");

		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfID.setText("");
				tfUsername.setText("");
				pwField.setText("");
				tfEmail.setText("");
				tfName.setText("");
				tfDoB.setText("");
				chbSex.setSelected(false);
				tfRole.setText("");
			}
		});
		contentPane.add(btnReset, "cell 1 13,alignx trailing");

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUser.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 13,alignx trailing");
	}

}
