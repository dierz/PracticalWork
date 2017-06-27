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
import model.Material;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class DlgMaterial extends JDialog implements IDlg {

	private final JPanel contentPanel = new JPanel();
	private Material material;
	static DlgMaterial dialog;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private IController controller;
	/**
	 * Launch the application.
	 */
	public void setName(String name) {
		textField.setText(name);
	}
	public void setDensity(String dens) {
		textField_1.setText(dens);
	}
	public void setWeight(String weight) {
		textField_2.setText(weight);
	}
	public void setController(JdbsController controller) {
		this.controller = controller;
	}


	public static void main(String[] args) {
		try {
			dialog = new DlgMaterial();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgMaterial() {
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
		textField_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Density", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_1.setColumns(10);
		textField_1.setBounds(122, 88, 188, 34);
		contentPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Weight", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		material = null;
		setVisible(false);

	}

	protected void onStore() {
		material = new Material();
		String name = textField.getText();
		String dens = textField_1.getText();
		String weight = textField_2.getText();
		material.setDensity(Float.parseFloat(dens));
		material.setName(name);
		material.setWeight(Float.parseFloat(weight));
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
		return material;
	}
}
