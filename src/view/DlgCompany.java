package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import controller.IController;
import controller.JdbsController;
import model.Company;
import model.RMS;
import model.SecOrg;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class DlgCompany extends JDialog implements IDlg {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private Company comp;
	private SecOrg sec;
	private RMS rms;
	private IController controller;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;


	/**
	 * Launch the application.
	 */
	public void setController(JdbsController controller) {
		this.controller = controller;
	}

	public static void main(String[] args) {
		try {
			DlgCompany dialog = new DlgCompany();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCompany() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					selectSecOrg();
				}
			});
			textField.setEditable(false);
			textField.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SecOrg", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textField.setBounds(67, 11, 335, 33);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					selectRMS();
				}
			});
			textField_1.setEditable(false);
			textField_1.setColumns(10);
			textField_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "RMS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textField_1.setBounds(67, 45, 335, 33);
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBorder(new TitledBorder(null, "Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			textField_2.setColumns(10);
			textField_2.setBounds(67, 96, 124, 33);
			contentPanel.add(textField_2);
		}
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "YearProfit", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_3.setBounds(240, 96, 124, 33);
		contentPanel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Address", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_4.setBounds(67, 156, 124, 33);
		contentPanel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Phone number", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_5.setBounds(240, 156, 124, 33);
		contentPanel.add(textField_5);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onStore();
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

	protected void selectSecOrg() {
		if (textField.isEnabled()) {
			TableModel model = controller.getModel("SecOrg");
			DlgSelect dlg = new DlgSelect(model,"SecOrg");
			dlg.setVisible(true);
			sec = (SecOrg) dlg.getObject();
			textField.setText(sec.toString());
		}
	}
	protected void selectRMS() {
		if (textField.isEnabled()) {
			TableModel model = controller.getModel("RMS");
			DlgSelect dlg = new DlgSelect(model,"RMS");
			dlg.setVisible(true);
			rms = (RMS) dlg.getObject();
			textField_1.setText(rms.toString());
		}
	}

	public void setName(String name) {
		textField_2.setText(name);
}
	public void setProfit(String profit) {
		textField_3.setText(profit);
}
	public void setAddress(String adr) {
		textField_4.setText(adr);
}
	public void setPhNumber(String num) {
		textField_5.setText(num);
}

public void setSecOrg(String sec) {
		textField.setText(sec);
		textField.setEnabled(false);	
}
public void setRMS(String rms) {
	textField_1.setText(rms);
	textField_1.setEnabled(false);	
}
	
	protected void onCancel() {
		comp = null;
		setVisible(false);

	}

	protected void onStore() {
		try {
			comp = new Company();
					String name=textField_2.getText();
					String profit=textField_3.getText();
					String adr=textField_4.getText();
					String num=textField_5.getText();
					comp.setName(name);
					comp.setProfit(Float.parseFloat(profit));
					comp.setAddress(adr);
					comp.setPhnumber(num);
					comp.setRms(rms);
					comp.setSecorg(sec);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e, this.getTitle(),
			 JOptionPane.ERROR_MESSAGE);
					return;
				}
				setVisible(false);

	}

	@Override
	public Object getObject() {
		return comp;
	}
}
