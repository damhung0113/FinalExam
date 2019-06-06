package GUI;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import B.LectureroomB;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class RoomMgmt extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAdd, btnEdit, btnSearch, btnDelete;
	private DefaultTableModel model;
	private JTable table;
	private LectureroomB b = new LectureroomB();
	private JLabel lbRefresh;

	public void initModel() throws SQLException {
		model = b.getAllItem();
		table.setModel(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
		DefaultTableCellRenderer cellRenderer= new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(cellRenderer);
	}

	public void searchModel(DefaultTableModel searchModel) {
		table.setModel(searchModel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
		DefaultTableCellRenderer cellRenderer= new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(cellRenderer);
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public RoomMgmt() throws SQLException {
		setAutoRequestFocus(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 250, 1280, 720);
		setTitle("Lecureroom Management");
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(63, 163, 263, 445);
		contentPane.add(panel);
		panel.setLayout(null);

		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddRoom(b, RoomMgmt.this).setVisible(true);
			}
		});
		btnAdd.setEnabled(false);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(0, 90, 263, 39);
		btnAdd.setBackground(SystemColor.activeCaption);
		panel.add(btnAdd);

		btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EditRoom(b, RoomMgmt.this).setVisible(true);
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEdit.setBounds(0, 160, 263, 39);
		btnEdit.setBackground(SystemColor.activeCaption);
		panel.add(btnEdit);

		btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SearchRoom(b, RoomMgmt.this).setVisible(true);
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(0, 230, 263, 39);
		btnSearch.setBackground(SystemColor.activeCaption);
		panel.add(btnSearch);

		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selectedRow = -1;
					selectedRow = Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString());
					if (selectedRow == -1)
						JOptionPane.showMessageDialog(rootPane, "Please choose a row");
					else {
						int k = JOptionPane.showConfirmDialog(rootPane, "Do you really want to delete?");
						if (k == JOptionPane.YES_OPTION) {
							try {
								b.deleteRoom(selectedRow);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(rootPane, "Delete successed");
							try {
								initModel();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(rootPane, "Please choose a row");
				}
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(0, 300, 263, 39);
		btnDelete.setBackground(SystemColor.activeCaption);
		panel.add(btnDelete);

		lbRefresh = new JLabel("");
		lbRefresh.setVisible(false);
		lbRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					initModel();
					lbRefresh.setVisible(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		lbRefresh.setBounds(202, 389, 51, 45);
		lbRefresh.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/icons8_Reset_50px_1.png")));
		panel.add(lbRefresh);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(342, 163, 855, 445);
		panel_1.setLayout(new GridLayout(1, 0));
		contentPane.add(panel_1);

		JScrollPane scrollPane = new JScrollPane();

		panel_1.add(scrollPane);

		
		table= new JTable();
		scrollPane.setViewportView(table);
		try {
			initModel();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.CYAN);
		panel_2.setBounds(410, 0, 444, 5);
		contentPane.add(panel_2);

		JLabel lblNewLabel = new JLabel("LECTUREROOM MANAGEMENT");
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(63, 50, 273, 35);
		contentPane.add(lblNewLabel);
	}

	public void setRefreshVisible() {
		lbRefresh.setVisible(true);
	}
	
	public void enable(boolean isAdmin) {
		if (isAdmin) {
			btnAdd.setEnabled(true);
			btnEdit.setEnabled(true);
			btnDelete.setEnabled(true);
		}
	}
}
