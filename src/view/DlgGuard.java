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
import controller.JpaController;
import model.Guard;
import model.Secorg;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class DlgGuard extends JDialog implements IDlg {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private Guard guard;
	private Secorg sec;
	private IController controller;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;


	/**
	 * Launch the application.
	 */
	public void setController(JpaController controller) {
		this.controller = controller;
	}

	public static void main(String[] args) {
		try {
			DlgGuard dialog = new DlgGuard();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgGuard() {
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
			textField.setBounds(67, 37, 335, 33);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "FIO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textField_2.setColumns(10);
			textField_2.setBounds(67, 96, 124, 33);
			contentPanel.add(textField_2);
		}
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Age", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_3.setBounds(240, 96, 124, 33);
		contentPanel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Exp", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_4.setBounds(67, 156, 124, 33);
		contentPanel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Rank", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
			TableModel model = controller.getModel("Secorg");
			DlgSelect dlg = new DlgSelect(model,"Secorg");
			dlg.setVisible(true);
			sec = (Secorg) dlg.getObject();
			textField.setText(sec.toString());
		}
	}

	public void setFIO(String fio) {
		textField_2.setText(fio);
}
	public void setAge(String age) {
		textField_3.setText(age);
}
	public void setExp(String exp) {
		textField_4.setText(exp);
}
	public void setRank(String rank) {
		textField_5.setText(rank);
}

public void setSecOrg(String sec) {
		textField.setText(sec);
		textField.setEnabled(false);	
}
	
	protected void onCancel() {
		guard = null;
		setVisible(false);
	}

	protected void onStore() {
		try {
			guard = new Guard();
					String fio=textField_2.getText();
					String age=textField_3.getText();
					String exp=textField_4.getText();
					String rank=textField_5.getText();
					guard.setFio(fio);
					guard.setAge(Integer.parseInt(age));
					guard.setExp(Integer.parseInt(exp));
					guard.setRank(rank);
					guard.setSecorg(sec);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e, this.getTitle(),
			 JOptionPane.ERROR_MESSAGE);
					return;
				}
				setVisible(false);

	}

	@Override
	public Object getObject() {
		return guard;
	}
}
