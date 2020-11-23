package ca.ubc.cs304.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import ca.ubc.cs304.delegates.TransactionsWindowDelegate;
import ca.ubc.cs304.model.BranchModel;

public class TransactionsWindow extends JFrame{
	private static final int TEXT_FIELD_WIDTH = 10;

	private GridBagLayout gb;
	private GridBagConstraints c;
	private JPanel contentpane;

	// delegate
	private TransactionsWindowDelegate delegate;

	//BRANCH FIELDS
	private JTextField branch_IDtxt, branch_nametxt, branch_addrtxt, branch_citytxt, branch_phonenumtxt;
	private JTextField branch_delIDtxt;
	private JButton branchInsertButton;
	private JButton branchDeleteButton;
	private JButton branchShowButton;
	private JTextArea branchTable;

	//constructor
	public TransactionsWindow(){ super("Transactions Window");}

	public void showFrame(TransactionsWindowDelegate delegate){
		// create masterpanel that will contain all the smaller panels
		JPanel MasterPanel = new JPanel();
		this.setContentPane(MasterPanel);
		this.contentpane = MasterPanel;
		this.delegate = delegate;

		MasterPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
		GridBagLayout gb = new GridBagLayout();
		this.gb = gb;
		GridBagConstraints c = new GridBagConstraints();
		this.c = c;
		MasterPanel.setLayout(gb);

		Branch();

		// anonymous inner class for closing the window
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// size the window to obtain a best fit for the components
		this.pack();

		// center the frame
		Dimension d = this.getToolkit().getScreenSize();
		Rectangle r = this.getBounds();
		this.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

		// make the window visible
		this.setVisible(true);
	}

	// BRANCH
	private void Branch(){
		insertBranch();
		deleteBranch();
		showBranchStatic();
	}
	private void insertBranch(){
		JButton insertButton = new JButton("insert branch");
		branchInsertButton = insertButton;
		JLabel IDlbl, namelbl, addrlbl, citylbl, phonenumlbl;

		// initing labels
		IDlbl = new JLabel("enter branch ID to insert: ");
		namelbl = new JLabel("enter branch name to insert: ");
		addrlbl = new JLabel("enter branch address to insert: ");
		citylbl = new JLabel("enter branch city to insert: ");
		phonenumlbl = new JLabel("enter branch phonenum to insert: ");

		// initing txt
		branch_IDtxt = new JTextField(TEXT_FIELD_WIDTH);
		branch_nametxt = new JTextField(TEXT_FIELD_WIDTH);
		branch_addrtxt = new JTextField(TEXT_FIELD_WIDTH);
		branch_citytxt = new JTextField(TEXT_FIELD_WIDTH);
		branch_phonenumtxt = new JTextField(TEXT_FIELD_WIDTH);

		// place the id label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(IDlbl, c);
		contentpane.add(IDlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		gb.setConstraints(branch_IDtxt, c);
		contentpane.add(branch_IDtxt);

		// place the name label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 5, 0);
		gb.setConstraints(namelbl, c);
		contentpane.add(namelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 5, 10);
		gb.setConstraints(branch_nametxt, c);
		contentpane.add(branch_nametxt);

		// place the address label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 5, 0);
		gb.setConstraints(addrlbl, c);
		contentpane.add(addrlbl);
		// place the text field for the address
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 5, 10);
		gb.setConstraints(branch_addrtxt, c);
		contentpane.add(branch_addrtxt);

		// place the city label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 5, 0);
		gb.setConstraints(citylbl, c);
		contentpane.add(citylbl);
		// place the text field for the city
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 5, 10);
		gb.setConstraints(branch_citytxt, c);
		contentpane.add(branch_citytxt);

		// place the phonenum label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 5, 0);
		gb.setConstraints(phonenumlbl, c);
		contentpane.add(phonenumlbl);
		// place the text field for the phonenum
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 5, 10);
		gb.setConstraints(branch_phonenumtxt, c);
		contentpane.add(branch_phonenumtxt);

		// place the insert button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(2, 10, 15, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(insertButton, c);
		contentpane.add(insertButton);

		insertButton.addActionListener(new BranchButtonListener());
	}
	private void deleteBranch(){
		JButton deleteButton = new JButton("delete branch");
		branchDeleteButton = deleteButton;
		JLabel IDlbl;

		// initing labels
		IDlbl = new JLabel("enter branch ID to delete: ");

		// initing txt
		branch_delIDtxt = new JTextField(TEXT_FIELD_WIDTH);

		// place the id label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(IDlbl, c);
		contentpane.add(IDlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		gb.setConstraints(branch_delIDtxt, c);
		contentpane.add(branch_delIDtxt);

		// place the delete button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(2, 10, 15, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(deleteButton, c);
		contentpane.add(deleteButton);

		deleteButton.addActionListener(new BranchButtonListener());

	}
	private void showBranchStatic(){
		JButton showButton = new JButton("show branch");
		branchShowButton = showButton;
		branchTable = new JTextArea(10,50);

		// place the Textarea
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(branchTable, c);
		contentpane.add(branchTable);

		// place the show button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(2, 10, 15, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(showButton, c);
		contentpane.add(showButton);

		showButton.addActionListener(new BranchButtonListener());

	}
	private void showBranchDynamic(BranchModel[] models){
		branchTable.setText("");
		branchTable.append(" legend:  [  id  :  name  :  city  :  address  :  phonenum  ] \t \n");
		branchTable.append(parseTable(models));
		contentpane.update(branchTable.getGraphics());

	}
	private String parseTable(BranchModel[] models){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<models.length; i++){
			String str = parseModel(models[i]);
			sb.append(str);
			sb.append("\n");
		}
		return sb.toString();
	}
	private String parseModel(BranchModel model){
		StringBuilder sb = new StringBuilder();
		sb.append("  [  ");
		sb.append(model.getId());
		sb.append("  :  ");
		sb.append(model.getName());
		sb.append("  :  ");
		sb.append(model.getCity());
		sb.append("  :  ");
		sb.append(model.getAddress());
		sb.append("  :  ");
		sb.append(model.getPhoneNumber());
		sb.append("  ]");
		return sb.toString();
	}

	// Branch Button listener
	private class BranchButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == branchInsertButton){

				String sr = String.format("trying to insert branch with: \n addr: %s, city: %s, id: %s, name: %s, phone: %s \n", branch_addrtxt.getText(), branch_citytxt.getText(),branch_IDtxt.getText(), branch_nametxt.getText(), branch_phonenumtxt.getText());
				System.out.print(sr);

				BranchModel model = new BranchModel(branch_addrtxt.getText(),
						branch_citytxt.getText(), Integer.parseInt(branch_IDtxt.getText()),
						branch_nametxt.getText(), Integer.parseInt(branch_phonenumtxt.getText()));
				delegate.insertBranch(model);

			} else if (e.getSource() == branchDeleteButton){

				String sr = String.format("trying to delete branch with: \n id: %d \n", Integer.parseInt(branch_delIDtxt.getText()));
				System.out.print(sr);

				delegate.deleteBranch(Integer.parseInt(branch_delIDtxt.getText()));

			} else if (e.getSource() == branchShowButton){
				System.out.print("trying to get table \n");
				BranchModel[] models = delegate.getBranch();
				showBranchDynamic(models);
			}
			else{
				System.out.print("ugh something is wrong \n");
			}
		}
	}


}


