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
import model.Material;
import model.Rms;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class DlgRMS extends JDialog implements IDlg {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private Rms rms;
	private Material mat;
	private IController controller;
	private JTextField textField_3;


	/**
	 * Launch the application.
	 */
	public void setController(JdbsController controller) {
		this.controller = controller;
	}

	public static void main(String[] args) {
		try {
			DlgRMS dialog = new DlgRMS();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRMS() {
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
					selectMaterial();
				}
			});
			textField.setEditable(false);
			textField.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Material", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textField.setBounds(47, 34, 335, 33);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textField_2.setColumns(10);
			textField_2.setBounds(154, 78, 124, 33);
			contentPanel.add(textField_2);
		}
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Size", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_3.setBounds(154, 122, 124, 33);
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

	protected void selectMaterial() {
		if (textField.isEnabled()) {
			TableModel model = controller.getModel("Material");
			DlgSelect dlg = new DlgSelect(model,"Material");
			dlg.setVisible(true);
			mat = (Material) dlg.getObject();
			textField.setText(mat.toString());
		}
	}

	public void setName(String name) {
		textField_2.setText(name);
}
	public void setSize(String size) {
		textField_3.setText(size);
}


public void setMaterial(String mat) {
		textField.setText(mat);
		textField.setEnabled(false);	
}
	
	protected void onCancel() {
		rms = null;
		setVisible(false);

	}

	protected void onStore() {
		try {
			rms = new Rms();
					String name=textField_2.getText();
					String size=textField_3.getText();
					rms.setName(name);
					rms.setSupplysize(Integer.parseInt(size));
					rms.setMaterial(mat);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e, this.getTitle(),
			 JOptionPane.ERROR_MESSAGE);
					return;
				}
				setVisible(false);

	}

	@Override
	public Object getObject() {
		return rms;
	}
}
