package GUI;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import B.UserB;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class EditUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsername;
	private JTextField tfName;
	private JTextField tfCurrentID;
	private JTextField tfEmail;
	private JTextField tfDob;
	private JTextField tfRole;
	private JPasswordField pwField;

	/**
	 * Create the frame.
	 */
	public EditUser(UserB b, UserMgmt parent) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(750, 650, 565, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][][]"));
		setContentPane(contentPane);

		JLabel lbEdit = new JLabel("EDIT EXITS USER");
		lbEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lbEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lbEdit, "cell 1 0");

		JLabel lbCurrentId = new JLabel("Current ID");
		contentPane.add(lbCurrentId, "cell 0 2,alignx trailing");

		tfCurrentID = new JTextField();
		contentPane.add(tfCurrentID, "cell 1 2,growx");
		tfCurrentID.setColumns(10);

		JLabel lbEnterNewInfor = new JLabel("ENTER NEW LECTURE ROOM' INFORMATION");
		contentPane.add(lbEnterNewInfor, "cell 1 3");

		JLabel lblLocation = new JLabel("Username");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLocation, "cell 0 5,alignx trailing");

		tfUsername = new JTextField();
		contentPane.add(tfUsername, "cell 1 5,growx");
		tfUsername.setColumns(10);

		JLabel lblType = new JLabel("Password");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblType, "cell 0 6,alignx trailing,aligny baseline");

		pwField = new JPasswordField();
		contentPane.add(pwField, "cell 1 6,growx");

		JLabel lblCapacity = new JLabel("Full name");
		lblCapacity.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCapacity, "cell 0 7,alignx trailing");

		tfName = new JTextField();
		contentPane.add(tfName, "cell 1 7,growx,aligny top");
		tfName.setColumns(10);

		JLabel lblAirconditional = new JLabel("Email");
		lblAirconditional.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAirconditional, "cell 0 8,alignx trailing");

		tfEmail = new JTextField();
		contentPane.add(tfEmail, "cell 1 8,growx");
		tfEmail.setColumns(10);

		JLabel lblNewLabel = new JLabel("Date of Birth");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "cell 0 9,alignx trailing");

		tfDob = new JTextField();
		contentPane.add(tfDob, "cell 1 9,growx");
		tfDob.setColumns(10);

		JLabel lblScreen = new JLabel("Sex (male)");
		lblScreen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblScreen, "cell 0 10");

		JCheckBox chbIsMale = new JCheckBox("");
		contentPane.add(chbIsMale, "cell 1 10");

		JLabel lblFan = new JLabel("Role");
		contentPane.add(lblFan, "cell 0 11,alignx trailing");

		tfRole = new JTextField();
		contentPane.add(tfRole, "cell 1 11,growx");
		tfRole.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int id = Integer.parseInt(tfCurrentID.getText());
					String username = tfUsername.getText();
					String password = String.valueOf(pwField.getPassword());
					String email = tfEmail.getText();
					String name = tfName.getText();
					Date dob = Date.valueOf(tfDob.getText());
					boolean isMale = chbIsMale.isSelected();
					int role = Integer.parseInt(tfRole.getText());

					try {
						b.editUser(id, username, password, email, name, dob, isMale, role);
						JOptionPane.showMessageDialog(rootPane, "Edit user successed");
						parent.initModel(true);
						EditUser.this.dispose();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(rootPane, "Invalid input type");
				}
			}
		});
		contentPane.add(btnOk, "flowx,cell 1 13,alignx trailing");

		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCurrentID.setText("");
				tfUsername.setText("");
				pwField.setText("");
				tfEmail.setText("");
				tfName.setText("");
				tfDob.setText("");
				chbIsMale.setSelected(false);
				tfRole.setText("");
			}
		});
		contentPane.add(btnReset, "cell 1 13");

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditUser.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 13");
	}

}
