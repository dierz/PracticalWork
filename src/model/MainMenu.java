package model;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import controller.IController;
import controller.JdbsController;
import view.DlgCompany;
import view.DlgGuard;
import view.DlgMaterial;
import view.DlgProduct;
import view.DlgRMS;
import view.DlgSM;
import view.DlgSecOrg;
import view.IDlg;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private static MainMenu frame;
	private JTable table;
	IController controller;
	String className;
	
	public void setController(IController controller) {
		this.controller = controller;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private boolean checkClassName() {
		return className.equals("Company") 
				|| className.equals("Guard")
				|| className.equals("Material")
				|| className.equals("Product")
				|| className.equals("RMS")
				|| className.equals("SecOrg")
				|| className.equals("SM");
	}

	private int getSelectedRow() {
		int row = table.getSelectedRow();
		if (row == -1)
			JOptionPane.showMessageDialog(frame, "Row is not selected",
	"Table " + className, JOptionPane.WARNING_MESSAGE);
		return row;
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 553, 21);
		contentPane.add(menuBar);
		
		JMenu mnTable = new JMenu("Table");
		menuBar.add(mnTable);
		
		JMenuItem mntmCreateDb = new JMenuItem("Create DB");
		mntmCreateDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onCreateDB();
			}
		});
		mnTable.add(mntmCreateDb);
		
		JMenuItem mntmCompany = new JMenuItem("Company");
		mntmCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			showCompany();
			}
		});
		mnTable.add(mntmCompany);
		
		JMenuItem mntmGuard = new JMenuItem("Guard");
		mntmGuard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showGuard();
			}
		});
		mnTable.add(mntmGuard);
		
		JMenuItem mntmMaterial = new JMenuItem("Material");
		mntmMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			showMaterial();
			}
		});
		mnTable.add(mntmMaterial);
		
		JMenuItem mntmProduct = new JMenuItem("Product");
		mntmProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showProduct();
			}
		});
		mnTable.add(mntmProduct);
		
		JMenuItem mntmRMS = new JMenuItem("RMS");
		mntmRMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showRMS();
			}
		});
		mnTable.add(mntmRMS);
		
		JMenuItem mntmSecOrg = new JMenuItem("SecOrg");
		mntmSecOrg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSecOrg();
			}
		});
		mnTable.add(mntmSecOrg);
		
		JMenuItem mntmSM = new JMenuItem("SM");
		mntmSM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSM();
			}
		});
		mnTable.add(mntmSM);
		
		JMenu mnOperation = new JMenu("Operation");
		menuBar.add(mnOperation);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			onAdd();
			}
		});
		mnOperation.add(mntmAdd);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			onEdit();
			}
		});
		mnOperation.add(mntmEdit);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			onDelete();
			}
		});
		mnOperation.add(mntmDelete);
		
		JMenu mnQuery = new JMenu("Query");
		menuBar.add(mnQuery);
		
		JMenuItem mntmInvalid = new JMenuItem("invalid");
		mntmInvalid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onQuery1();
			}
		});
		mnQuery.add(mntmInvalid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 21, 553, 325);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	protected void onQuery1() {
		className = "";
		table.setModel(controller.doQuery2());

	}

	protected void onDelete() {
		if (!checkClassName()) return;
		TableModel model =  table.getModel();
		int row = getSelectedRow();
				String str = model.getValueAt(row, 0).toString();
		int id = Integer.parseInt((str) );
		controller.delete(id, className);
		model = controller.getModel(className);
		table.setModel(model);
	}


	protected void onEdit() {
		if(!checkClassName()) return;
		TableModel model =  table.getModel();
		int row = getSelectedRow();
		if (row == -1) return;
				String str = model.getValueAt(row, 0).toString();
		int id = Integer.parseInt((str) );
		IDlg dlg = null;
		if (className.equals("Material")) {
			dlg = new DlgMaterial();
			((DlgMaterial) dlg).setController((JdbsController) controller);
			((DlgMaterial) dlg).setName(model.getValueAt(row, 1).toString());
			((DlgMaterial) dlg).setDensity(model.getValueAt(row, 2).toString());
			((DlgMaterial) dlg).setWeight(model.getValueAt(row, 3).toString());
		} else if (className.equals("Company")) {
			dlg = new DlgCompany();
			((DlgCompany) dlg).setController((JdbsController) controller);
			((DlgCompany) dlg).setName(model.getValueAt(row, 1).toString());
			((DlgCompany) dlg).setProfit(model.getValueAt(row, 2).toString());
			((DlgCompany) dlg).setAddress(model.getValueAt(row, 3).toString());
			((DlgCompany) dlg).setPhNumber(model.getValueAt(row, 4).toString());
			((DlgCompany) dlg).setSecOrg(model.getValueAt(row, 5).toString());
			((DlgCompany) dlg).setRMS(model.getValueAt(row, 6).toString());
		} else if (className.equals("Guard")) {
			dlg = new DlgGuard();
			((DlgGuard) dlg).setController((JdbsController) controller);
			((DlgGuard) dlg).setFIO(model.getValueAt(row, 1).toString());
			((DlgGuard) dlg).setAge(model.getValueAt(row, 2).toString());
			((DlgGuard) dlg).setExp(model.getValueAt(row, 3).toString());
			((DlgGuard) dlg).setRank(model.getValueAt(row, 4).toString());
			((DlgGuard) dlg).setSecOrg(model.getValueAt(row, 5).toString());
		} else if (className.equals("Product")) {
			dlg = new DlgProduct();
			((DlgProduct) dlg).setController((JdbsController) controller);
			((DlgProduct) dlg).setName(model.getValueAt(row, 1).toString());
			((DlgProduct) dlg).setAmount(model.getValueAt(row, 2).toString());
			((DlgProduct) dlg).setCompany(model.getValueAt(row, 3).toString());
			((DlgProduct) dlg).setSM(model.getValueAt(row, 4).toString());
		} else if (className.equals("RMS")) {
			dlg = new DlgRMS();
			((DlgRMS) dlg).setController((JdbsController) controller);
			((DlgRMS) dlg).setName(model.getValueAt(row, 1).toString());
			((DlgRMS) dlg).setSize(model.getValueAt(row, 2).toString());
			((DlgRMS) dlg).setMaterial(model.getValueAt(row, 3).toString());
		} else if (className.equals("SecOrg")) {
			dlg = new DlgSecOrg();
			((DlgSecOrg) dlg).setController((JdbsController) controller);
			((DlgSecOrg) dlg).setName(model.getValueAt(row, 1).toString());
			((DlgSecOrg) dlg).setAddress(model.getValueAt(row, 2).toString());
			((DlgSecOrg) dlg).setHead(model.getValueAt(row, 3).toString());
		} else if (className.equals("SM")) {
			dlg = new DlgSM();
			((DlgSM) dlg).setController((JdbsController) controller);
			((DlgSM) dlg).setName(model.getValueAt(row, 1).toString());
			((DlgSM) dlg).setAddress(model.getValueAt(row, 2).toString());
			((DlgSM) dlg).setWebsite(model.getValueAt(row, 3).toString());
		}
		((JDialog) dlg).setVisible(true);
		try {
			controller.edit(id, dlg.getObject());
			table.setModel(controller.getModel(className));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "Створення об'єкту " 
	+ className, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		
	}

	protected void onAdd() {
		if(!checkClassName()) return;
		IDlg dlg = null;
		if (className.equals("Material")){
			dlg = new DlgMaterial();}
		else if (className.equals("SecOrg")){
			dlg = new DlgSecOrg();}
		else if (className.equals("SM")){
			dlg = new DlgSM();}
		else if(className.equals("Company")){
			dlg=new DlgCompany();
		((DlgCompany) dlg).setController((JdbsController) controller);}
		else if(className.equals("RMS")){
			dlg=new DlgRMS();
		((DlgRMS) dlg).setController((JdbsController) controller);}
		else if(className.equals("Guard")){
			dlg=new DlgGuard();
		((DlgGuard) dlg).setController((JdbsController) controller);}
		else if(className.equals("Product")){
			dlg=new DlgProduct();
		((DlgProduct) dlg).setController((JdbsController) controller);}
		if (dlg == null)
			return;
		((JDialog) dlg).setVisible(true);
		try {
			controller.add(dlg.getObject());
			table.setModel(controller.getModel(className));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "Створення об'єкту "
	 								+ className, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	protected void showCompany() {
		className="Company";
		table.setModel(controller.getModel(className));
	}

	protected void showGuard() {
		className="Guard";
		table.setModel(controller.getModel(className));
	}

	protected void showMaterial() {
		className="Material";
		table.setModel(controller.getModel(className));
	}
	protected void showRMS() {
		className="RMS";
		table.setModel(controller.getModel(className));
	}
	protected void showProduct() {
		className="Product";
		table.setModel(controller.getModel(className));
	}
	protected void showSecOrg() {
		className="SecOrg";
		table.setModel(controller.getModel(className));
	}
	protected void showSM() {
		className="SM";
		table.setModel(controller.getModel(className));
	}

	protected void onCreateDB() {
		controller.createDB();
	}

	public JTable getTable() {
		return table;
	}
}
