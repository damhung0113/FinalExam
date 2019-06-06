package GUI;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import B.LectureroomB;
import Object.Room;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SearchRoom extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfLocation;
	private JTextField tfType;
	private JTextField tfCapacity;
	private JTextField tfNote;
	private Room room;

	public Room getRoom() {
		return room;
	}

	/**
	 * Create the frame.
	 */
	public SearchRoom(LectureroomB b, RoomMgmt parent) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(750, 650, 527, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][]"));
		setContentPane(contentPane);

		JLabel lbSearch = new JLabel("SEARCH ROOM");
		lbSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lbSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lbSearch, "cell 1 0");

		JLabel lblId = new JLabel("ID ");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblId, "cell 0 2,alignx trailing");

		tfID = new JTextField();
		contentPane.add(tfID, "cell 1 2,growx");
		tfID.setColumns(10);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLocation, "cell 0 3,alignx trailing");

		tfLocation = new JTextField();
		contentPane.add(tfLocation, "cell 1 3,growx");
		tfLocation.setColumns(10);

		JLabel lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblType, "cell 0 4,alignx trailing");

		tfType = new JTextField();
		contentPane.add(tfType, "cell 1 4,growx");
		tfType.setColumns(10);

		JLabel lblCapacity = new JLabel("Capacity");
		lblCapacity.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCapacity, "cell 0 5,alignx trailing");

		tfCapacity = new JTextField();
		contentPane.add(tfCapacity, "cell 1 5,growx");
		tfCapacity.setColumns(10);

		JLabel lblAirconditional = new JLabel("Air-Conditional");
		lblAirconditional.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAirconditional, "cell 0 6,alignx trailing");
		
		JComboBox<String> cbAirC = new JComboBox<String>();
		cbAirC.setModel(new DefaultComboBoxModel<String>(new String[] {"NULL", "YES", "NO"}));
		contentPane.add(cbAirC, "cell 1 6,growx");

		JLabel lblNewLabel = new JLabel("Projector");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "cell 0 7,alignx trailing");
		
		JComboBox<String> cbProjector = new JComboBox<String>();
		cbProjector.setModel(new DefaultComboBoxModel<String>(new String[] {"NULL", "YES", "NO"}));
		contentPane.add(cbProjector, "cell 1 7,growx");

		JLabel lblScreen = new JLabel("Screen");
		lblScreen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblScreen, "cell 0 8,alignx trailing");
		
		JComboBox<String> cbScreen = new JComboBox<String>();
		cbScreen.setModel(new DefaultComboBoxModel<String>(new String[] {"NULL", "YES", "NO"}));
		contentPane.add(cbScreen, "cell 1 8,growx");

		JLabel lblFan = new JLabel("Fan");
		contentPane.add(lblFan, "cell 0 9,alignx trailing");
		
		JComboBox<String> cbFan = new JComboBox<String>();
		cbFan.setModel(new DefaultComboBoxModel<String>(new String[] {"NULL", "YES", "NO"}));
		contentPane.add(cbFan, "cell 1 9,growx");

		JLabel lblNote = new JLabel("Note");
		lblNote.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNote, "cell 0 10,alignx trailing");

		tfNote = new JTextField();
		contentPane.add(tfNote, "cell 1 10,growx");
		tfNote.setColumns(10);

		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int id;
					if (!tfID.getText().equals("")) {
						id = Integer.parseInt(tfID.getText());
					} else
						id = 0;
					String location = tfLocation.getText();
					int capacity;
					if (!tfCapacity.getText().equals("")) {
						capacity = Integer.parseInt(tfCapacity.getText());
					} else
						capacity = 0;
					String type = tfType.getText();
					String airConditioner = cbAirC.getSelectedItem().toString();
					String projector = cbProjector.getSelectedItem().toString();
					String screen = cbScreen.getSelectedItem().toString();
					String fan = cbFan.getSelectedItem().toString();
					String note = tfNote.getText();

					try {
						DefaultTableModel searchModel = b.searchRoom(id, location, type, capacity, airConditioner, projector, fan, screen, note);
						parent.searchModel(searchModel);
						parent.setRefreshVisible();
						SearchRoom.this.dispose();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(rootPane, "Invalid input type");
				}
			}
		});
		contentPane.add(btnSearch, "cell 1 12,alignx trailing");

		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfID.setText("");
				tfCapacity.setText("");
				tfLocation.setText("");
				tfType.setText("");
				tfNote.setText("");
				cbAirC.setSelectedIndex(0);
				cbProjector.setSelectedIndex(0);
				cbScreen.setSelectedIndex(0);
				cbFan.setSelectedIndex(0);
			}
		});
		contentPane.add(btnReset, "cell 1 12,alignx trailing");

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchRoom.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 12,alignx trailing");
	}

}
