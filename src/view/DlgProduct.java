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
import model.Product;
import model.SM;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class DlgProduct extends JDialog implements IDlg {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private Company comp;
	private Product prod;
	private SM sm;
	private IController controller;
	private JTextField textField_1;
	private JTextField textField_3;


	/**
	 * Launch the application.
	 */
	public void setController(JdbsController controller) {
		this.controller = controller;
	}

	public static void main(String[] args) {
		try {
			DlgProduct dialog = new DlgProduct();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgProduct() {
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
					selectCompany();
				}
			});
			textField.setEditable(false);
			textField.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Company", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textField.setBounds(65, 27, 335, 33);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					selectSM();
				}
			});
			textField_1.setEditable(false);
			textField_1.setColumns(10);
			textField_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SM", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textField_1.setBounds(65, 61, 335, 33);
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBorder(new TitledBorder(null, "Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			textField_2.setColumns(10);
			textField_2.setBounds(170, 105, 124, 33);
			contentPanel.add(textField_2);
		}
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Amount", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_3.setBounds(170, 149, 124, 33);
		contentPanel.add(textField_3);
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

	protected void selectCompany() {
		if (textField.isEnabled()) {
			TableModel model = controller.getModel("Company");
			DlgSelect dlg = new DlgSelect(model,"Company");
			dlg.setVisible(true);
			comp = (Company) dlg.getObject();
			textField.setText(comp.toString());
		}
	}
	protected void selectSM() {
		if (textField.isEnabled()) {
			TableModel model = controller.getModel("SM");
			DlgSelect dlg = new DlgSelect(model,"SM");
			dlg.setVisible(true);
			sm = (SM) dlg.getObject();
			textField_1.setText(sm.toString());
		}
	}

	public void setName(String name) {
		textField_2.setText(name);
}
	public void setAmount(String am) {
		textField_3.setText(am);
}


public void setCompany(String comp) {
		textField.setText(comp);
		textField.setEnabled(false);	
}
public void setSM(String sm) {
	textField_1.setText(sm);
	textField_1.setEnabled(false);	
}
	
	protected void onCancel() {
		prod = null;
		setVisible(false);

	}

	protected void onStore() {
		try {
			prod = new Product();
					String name=textField_2.getText();
					String amount=textField_3.getText();
					prod.setName(name);
					prod.setAmount(Integer.parseInt(amount));
					prod.setCompany(comp);
					prod.setSm(sm);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e, this.getTitle(),
			 JOptionPane.ERROR_MESSAGE);
					return;
				}
				setVisible(false);

	}

	@Override
	public Object getObject() {
		return prod;
	}
}
