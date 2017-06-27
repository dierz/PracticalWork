package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import model.Company;
import model.Guard;
import model.Material;
import model.Product;
import model.Rms;
import model.Sm;
import model.Secorg;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class DlgSelect extends JDialog implements IDlg {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private TableModel model;
	private String className;
	private Object obj;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgSelect dialog = new DlgSelect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public Object getObject() {
		return obj;
	}

	/**
	 * Create the dialog.
	 */
	public DlgSelect(TableModel model, String className) {
		this();
		this.model = model;
		this.className = className;
		this.setTitle(className);
		table.setModel(model);
	}

	public DlgSelect() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Select");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					onSelect();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					onCancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void onCancel() {
		obj = null;
		this.setVisible(false);

	}

	protected void onSelect() {
		Class clz;
		try {
			clz = Class.forName("model."+className);
			obj = clz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		int row = table.getSelectedRow();
		if (obj instanceof Material){
		Material mat=(Material)obj;
		mat.setId(Integer.parseInt(model.getValueAt(row, 0).toString()));
		mat.setName(model.getValueAt(row, 1).toString());
		mat.setDensity(Float.parseFloat(model.getValueAt(row, 2).toString()));
		mat.setWeight(Float.parseFloat(model.getValueAt(row, 3).toString()));
		}
		else if (obj instanceof Company){
			Company comp=(Company)obj;
			comp.setId(Integer.parseInt(model.getValueAt(row, 0).toString()));
			comp.setName(model.getValueAt(row, 1).toString());
			comp.setYearprofit(Float.parseFloat(model.getValueAt(row, 2).toString()));
			comp.setAddress(model.getValueAt(row, 3).toString());
			comp.setPhnumber(model.getValueAt(row, 4).toString());
			Secorg sec=new Secorg();
			sec.setName(model.getValueAt(row, 5).toString());
			Rms rms=new Rms();
			rms.setName(model.getValueAt(row, 6).toString());
			comp.setSecorg(sec);
			comp.setRms(rms);
			}
		else if (obj instanceof Guard){
			Guard g=(Guard)obj;
			g.setId(Integer.parseInt(model.getValueAt(row, 0).toString()));
			g.setFio(model.getValueAt(row, 1).toString());
			g.setAge(Integer.parseInt(model.getValueAt(row, 2).toString()));
			g.setExp(Integer.parseInt(model.getValueAt(row, 3).toString()));
			g.setRank(model.getValueAt(row, 4).toString());
			Secorg sec=new Secorg();
			sec.setName(model.getValueAt(row, 5).toString());
			g.setSecorg(sec);
			}
		else if (obj instanceof Product){
			Product prod=(Product)obj;
			prod.setId(Integer.parseInt(model.getValueAt(row, 0).toString()));
			prod.setName(model.getValueAt(row, 1).toString());
			prod.setAmount(Integer.parseInt(model.getValueAt(row, 2).toString()));
			Company comp=new Company();
			comp.setName(model.getValueAt(row, 3).toString());
			Sm sm=new Sm();
			sm.setName(model.getValueAt(row, 4).toString());
			prod.setCompany(comp);
			prod.setSm(sm);
			}
		else if (obj instanceof Rms){
			Rms rms=(Rms)obj;
			rms.setId(Integer.parseInt(model.getValueAt(row, 0).toString()));
			rms.setName(model.getValueAt(row, 1).toString());
			rms.setSupplysize(Integer.parseInt(model.getValueAt(row, 2).toString()));
			Material mat=new Material();
			mat.setName(model.getValueAt(row, 3).toString());
			rms.setMaterial(mat);
			}
		else if(obj instanceof Secorg){
			Secorg sec=(Secorg)obj;
			sec.setId(Integer.parseInt(model.getValueAt(row, 0).toString()));
			sec.setName(model.getValueAt(row, 1).toString());
			sec.setAddress(model.getValueAt(row, 2).toString());
			sec.setHead(model.getValueAt(row, 3).toString());
		}
		else if(obj instanceof Sm){
			Sm sm=(Sm)obj;
			sm.setId(Integer.parseInt(model.getValueAt(row, 0).toString()));
			sm.setName(model.getValueAt(row, 1).toString());
			sm.setAddress(model.getValueAt(row, 2).toString());
			sm.setWebsite(model.getValueAt(row, 3).toString());
		}
		else{
			obj = null;
		}
		if(obj != null){
			this.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "ќб'Їкт не створено");
		}

	}

	public JTable getTable() {
		return table;
	}
}
