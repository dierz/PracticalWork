package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.IController;
import controller.JdbsController;
import model.Sm;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class DlgSM extends JDialog implements IDlg {

	private final JPanel contentPanel = new JPanel();
	private Sm sm;
	private IController controller;
	static DlgSM dialog;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public void setController(JdbsController controller) {
		this.controller = controller;
	}
	public void setName(String name) {
		textField.setText(name);
	}
	public void setAddress(String adr) {
		textField_1.setText(adr);
	}
	public void setWebsite(String site) {
		textField_2.setText(site);
	}

	public static void main(String[] args) {
		try {
			dialog = new DlgSM();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgSM() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBorder(new TitledBorder(null, "Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField.setBounds(122, 43, 188, 34);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Address", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_1.setColumns(10);
		textField_1.setBounds(122, 88, 188, 34);
		contentPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Website", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_2.setColumns(10);
		textField_2.setBounds(122, 140, 188, 34);
		contentPanel.add(textField_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Store");
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
	protected void onCancel() {
		sm = null;
		setVisible(false);

	}

	protected void onStore() {
		sm = new Sm();
		String name = textField.getText();
		String adr = textField_1.getText();
		String site = textField_2.getText();
		sm.setName(name);
		sm.setAddress(adr);
		sm.setWebsite(site);
		setVisible(false);	
	}

	public JTextField getTextField() {
		return textField;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JTextField getTextField_2() {
		return textField_2;
	}

	@Override
	public Object getObject() {
		return sm;
	}
}
