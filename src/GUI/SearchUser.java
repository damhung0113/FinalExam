package GUI;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import B.UserB;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SearchUser extends JFrame {

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

	/**
	 * Create the frame.
	 */
	public SearchUser(UserB b, UserMgmt parent) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(750, 650, 523, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][]"));
		setContentPane(contentPane);

		JLabel lbSearch = new JLabel("SEARCH USER");
		lbSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lbSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lbSearch, "cell 1 0");

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

		JLabel lblCapacity = new JLabel("Full name");
		lblCapacity.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCapacity, "cell 0 5,alignx trailing,growy");

		tfName = new JTextField();
		contentPane.add(tfName, "cell 1 5,growx");
		tfName.setColumns(10);

		JLabel lblAirconditional = new JLabel("Email");
		lblAirconditional.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAirconditional, "cell 0 6,alignx trailing");

		tfEmail = new JTextField();
		contentPane.add(tfEmail, "cell 1 6,growx");
		tfEmail.setColumns(10);

		JLabel lblNewLabel = new JLabel("Date of Birth");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "cell 0 7,alignx trailing");

		tfDoB = new JTextField();
		contentPane.add(tfDoB, "cell 1 7,growx");
		tfDoB.setColumns(10);

		JLabel lblScreen = new JLabel("Sex");
		lblScreen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblScreen, "cell 0 8,alignx trailing");

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "NULL", "MALE", "FEMALE" }));
		contentPane.add(comboBox, "cell 1 8,growx");

		JLabel lblFan = new JLabel("Role");
		contentPane.add(lblFan, "cell 0 9,alignx trailing");

		tfRole = new JTextField();
		contentPane.add(tfRole, "cell 1 9,growx");
		tfRole.setColumns(10);

		JButton btnAdd = new JButton("SEARCH");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int id;
					if (!tfID.getText().equals("")) {
						id = Integer.parseInt(tfID.getText());
					} else
						id = 0;

					String username = tfUsername.getText();
					String password = "";
					String email = tfEmail.getText();
					String name = tfName.getText();
					Date dob = Date.valueOf("0001-01-01");
					if (!tfDoB.getText().equals(""))
						dob = Date.valueOf(tfDoB.getText());
					String sex = comboBox.getSelectedItem().toString();
					int role;
					if (!tfRole.getText().equals("")) {
						role = Integer.parseInt(tfRole.getText());
					} else {
						role = -1;
					}
					try {
						DefaultTableModel searchModel = b.searchUser(id, username, password, email, name, dob, sex,
								role);
						parent.searchModel(searchModel);
						parent.setRefreshVisible();
						SearchUser.this.dispose();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(rootPane, "Invalid input type");
				}

			}
		});
		contentPane.add(btnAdd, "cell 1 12,alignx trailing");

		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfID.setText("");
				tfUsername.setText("");
				tfEmail.setText("");
				tfName.setText("");
				tfDoB.setText("");

				tfRole.setText("");
			}
		});
		contentPane.add(btnReset, "cell 1 12,alignx trailing");

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchUser.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 12,alignx trailing");
	}

}
