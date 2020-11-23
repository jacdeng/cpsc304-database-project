package ca.ubc.cs304.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import ca.ubc.cs304.delegates.TransactionsWindowDelegate;
import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.FootballPlayerModel;
import com.sun.tools.javac.comp.Flow;

public class TransactionsWindow extends JFrame{
	private static final int TEXT_FIELD_WIDTH = 10;

	private GridBagLayout gb;
	private GridBagConstraints c;

	private JPanel contentpane;
	private JScrollPane masterpane;

	// delegate
	private TransactionsWindowDelegate delegate;

	//Football player FIELDS
	private JPanel playerpane;
	private JTextField player_firstnametxt, player_lastnametxt, player_licensenumtxt, player_dateofbirthtxt,
			player_nationalitytxt, player_jersynumtxt, player_goalconcededtxt, player_goalsavedtxt, player_succtackletxt, player_blockstxt,
			player_cleartxt, player_intercepttxt, player_recoveriestxt, player_keypasstxt, player_bigchancetxt,
			player_contractstart, player_contractend, player_teamID;
	private JTextField player_upfirstnametxt, player_uplastnametxt, player_uplicensenumtxt, player_updateofbirthtxt,
			player_upnationalitytxt, player_upjersynumtxt, player_upgoalconcededtxt, player_upgoalsavedtxt, player_upsucctackletxt, player_upblockstxt,
			player_upcleartxt, player_upintercepttxt, player_uprecoveriestxt, player_upkeypasstxt, player_upbigchancetxt,
			player_upcontractstart, player_upcontractend, player_upteamID;
	private JTextField player_dellicensenumtxt;
	private JButton playerInsertButton;
	private JButton playerDeleteButton;
	private JButton playerUpdateButton;
	private JButton playerShowButton;
	private JTextArea playerTable;

	//constructor
	public TransactionsWindow(){ super("Transactions Window");}

	public void showFrame(TransactionsWindowDelegate delegate){
		this.delegate = delegate;

		// create contentpanel that will contain all the smaller panels
		JPanel ContentPanel = new JPanel();
		this.setContentPane(ContentPanel);
		this.contentpane = ContentPanel;

		ContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
		GridBagLayout gb = new GridBagLayout();
		ContentPanel.setLayout(gb);

		Player();

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

	// PLAYER
	private void Player(){
		this.playerpane = new JPanel();
		playerpane.setBorder(BorderFactory.createTitledBorder("football player"));
		GridBagLayout gb = new GridBagLayout();
		this.gb = gb;
		GridBagConstraints c = new GridBagConstraints();
		this.c = c;
		playerpane.setLayout(gb);
		insertPlayer();
		deletePlayer();
		updatePlayer();
		showBranchStatic();
		contentpane.add(playerpane);
	}
	private void insertPlayer(){
		JButton insertButton = new JButton("insert player");
		playerInsertButton = insertButton;
		JLabel licenselbl, firstnamelbl, lastnamelbl, doblbl, nationlbl, jrynumlbl, golconcelbl, golsvdlbl, succtacklbl,
				blklbl, clrlbl, interceptlbl, recoverlbl, keypaslbl, bigchancelbl,
				contractstartlbl, contractendlbl, teamIDlbl;

		// initing labels
		licenselbl = new JLabel("enter player license num to insert: ");
		firstnamelbl = new JLabel("enter player first name to insert: ");
		lastnamelbl = new JLabel("enter player last name to insert: ");
		doblbl = new JLabel("enter date of birth to insert: ");
		nationlbl = new JLabel("enter player nationality to insert: ");
		jrynumlbl = new JLabel("enter player jersey number to insert: ");

		golconcelbl = new JLabel("enter player goals conceded to insert (or leave empty): ");
		golsvdlbl = new JLabel("enter player goals saved to insert (or leave empty): ");
		succtacklbl = new JLabel("enter player successful tackles to insert (or leave empty): ");
		blklbl = new JLabel("enter player blocks to insert (or leave empty): ");
		clrlbl = new JLabel("enter player clears to insert (or leave empty): ");
		interceptlbl = new JLabel("enter player intercepts to insert (or leave empty): ");
		recoverlbl = new JLabel("enter player recoveries to insert (or leave empty): ");
		keypaslbl = new JLabel("enter player key passes to insert (or leave empty): ");
		bigchancelbl = new JLabel("enter player big chances to insert (or leave empty): ");

		contractstartlbl = new JLabel("enter player contract start to insert: ");
		contractendlbl = new JLabel("enter player contract end to insert: ");
		teamIDlbl = new JLabel("enter player's teamID to insert: ");

		// initing txt
		player_firstnametxt = new JTextField(TEXT_FIELD_WIDTH);
		player_lastnametxt = new JTextField(TEXT_FIELD_WIDTH);
		player_licensenumtxt = new JTextField(TEXT_FIELD_WIDTH);
		player_dateofbirthtxt = new JTextField(TEXT_FIELD_WIDTH);
		player_nationalitytxt = new JTextField(TEXT_FIELD_WIDTH);
		player_jersynumtxt = new JTextField(TEXT_FIELD_WIDTH);

		player_goalconcededtxt = new JTextField(TEXT_FIELD_WIDTH);
		player_goalsavedtxt = new JTextField(TEXT_FIELD_WIDTH);
		player_succtackletxt = new JTextField(TEXT_FIELD_WIDTH);
		player_blockstxt = new JTextField(TEXT_FIELD_WIDTH);
		player_cleartxt = new JTextField(TEXT_FIELD_WIDTH);
		player_intercepttxt = new JTextField(TEXT_FIELD_WIDTH);
		player_recoveriestxt = new JTextField(TEXT_FIELD_WIDTH);
		player_keypasstxt = new JTextField(TEXT_FIELD_WIDTH);
		player_bigchancetxt = new JTextField(TEXT_FIELD_WIDTH);

		player_contractstart = new JTextField(TEXT_FIELD_WIDTH);
		player_contractend = new JTextField(TEXT_FIELD_WIDTH);
		player_teamID = new JTextField(TEXT_FIELD_WIDTH);

		// place the license label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 3, 0);
		gb.setConstraints(licenselbl, c);
		playerpane.add(licenselbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 3, 10);
		gb.setConstraints(player_licensenumtxt, c);
		playerpane.add(player_licensenumtxt);

		// place the firstname label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(firstnamelbl, c);
		playerpane.add(firstnamelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_firstnametxt, c);
		playerpane.add(player_firstnametxt);

		// place the lastname label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(lastnamelbl, c);
		playerpane.add(lastnamelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_lastnametxt, c);
		playerpane.add(player_lastnametxt);

		// place the date of birth label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(doblbl, c);
		playerpane.add(doblbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_dateofbirthtxt, c);
		playerpane.add(player_dateofbirthtxt);

		// place the nationality label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(nationlbl, c);
		playerpane.add(nationlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_nationalitytxt, c);
		playerpane.add(player_nationalitytxt);

		// place the jerseynum label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(jrynumlbl, c);
		playerpane.add(jrynumlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_jersynumtxt, c);
		playerpane.add(player_jersynumtxt);

		// place the goals conceded label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(golconcelbl, c);
		playerpane.add(golconcelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_goalconcededtxt, c);
		playerpane.add(player_goalconcededtxt);

		// place the goals saved label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(golsvdlbl, c);
		playerpane.add(golsvdlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_goalsavedtxt, c);
		playerpane.add(player_goalsavedtxt);

		// place the successful tackles label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(succtacklbl, c);
		playerpane.add(succtacklbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_succtackletxt, c);
		playerpane.add(player_succtackletxt);

		// place the blocks label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(blklbl, c);
		playerpane.add(blklbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_blockstxt, c);
		playerpane.add(player_blockstxt);

		// place the clearances label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(clrlbl, c);
		playerpane.add(clrlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_cleartxt, c);
		playerpane.add(player_cleartxt);

		// place the intercept label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(interceptlbl, c);
		playerpane.add(interceptlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_intercepttxt, c);
		playerpane.add(player_intercepttxt);

		// place the recoveries label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(recoverlbl, c);
		playerpane.add(recoverlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_recoveriestxt, c);
		playerpane.add(player_recoveriestxt);

		// place the keypass label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(keypaslbl, c);
		playerpane.add(keypaslbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_keypasstxt, c);
		playerpane.add(player_keypasstxt);

		// place the big chance label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(bigchancelbl, c);
		playerpane.add(bigchancelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_bigchancetxt, c);
		playerpane.add(player_bigchancetxt);

		// place the contract start label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(contractstartlbl, c);
		playerpane.add(contractstartlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_contractstart, c);
		playerpane.add(player_contractstart);

		// place the contract end label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(contractendlbl, c);
		playerpane.add(contractendlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_contractend, c);
		playerpane.add(player_contractend);

		// place the team id label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(teamIDlbl, c);
		playerpane.add(teamIDlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_teamID, c);
		playerpane.add(player_teamID);

		// place the insert button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(2, 10, 15, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(insertButton, c);
		contentpane.add(insertButton);

		insertButton.addActionListener(new BranchButtonListener());
	}
	private void updatePlayer(){
		JButton updateButton = new JButton("update player");
		playerUpdateButton = updateButton;
		JLabel licenselbl, firstnamelbl, lastnamelbl, doblbl, nationlbl, jrynumlbl, golconcelbl, golsvdlbl, succtacklbl,
				blklbl, clrlbl, interceptlbl, recoverlbl, keypaslbl, bigchancelbl,
				contractstartlbl, contractendlbl, teamIDlbl;

		// initing labels
		licenselbl = new JLabel("enter player license num to update: ");
		firstnamelbl = new JLabel("enter new player first name: ");
		lastnamelbl = new JLabel("enter new player last name: ");
		doblbl = new JLabel("enter new date of birth: ");
		nationlbl = new JLabel("enter new player nationality: ");
		jrynumlbl = new JLabel("enter new player jersey number: ");

		golconcelbl = new JLabel("enter new player goals conceded: ");
		golsvdlbl = new JLabel("enter new player goals saved: ");
		succtacklbl = new JLabel("enter new player successful tackles: ");
		blklbl = new JLabel("enter new player blocks: ");
		clrlbl = new JLabel("enter new player clears: ");
		interceptlbl = new JLabel("enter new player intercepts: ");
		recoverlbl = new JLabel("enter new player recoveries: ");
		keypaslbl = new JLabel("enter new player key passes: ");
		bigchancelbl = new JLabel("enter new player big chances: ");

		contractstartlbl = new JLabel("enter new player contract start: ");
		contractendlbl = new JLabel("enter new player contract end: ");
		teamIDlbl = new JLabel("enter new player's teamID: ");

		// initing txt
		player_upfirstnametxt = new JTextField(TEXT_FIELD_WIDTH);
		player_uplastnametxt = new JTextField(TEXT_FIELD_WIDTH);
		player_uplicensenumtxt = new JTextField(TEXT_FIELD_WIDTH);
		player_updateofbirthtxt = new JTextField(TEXT_FIELD_WIDTH);
		player_upnationalitytxt = new JTextField(TEXT_FIELD_WIDTH);
		player_upjersynumtxt = new JTextField(TEXT_FIELD_WIDTH);

		player_upgoalconcededtxt = new JTextField(TEXT_FIELD_WIDTH);
		player_upgoalsavedtxt = new JTextField(TEXT_FIELD_WIDTH);
		player_upsucctackletxt = new JTextField(TEXT_FIELD_WIDTH);
		player_upblockstxt = new JTextField(TEXT_FIELD_WIDTH);
		player_upcleartxt = new JTextField(TEXT_FIELD_WIDTH);
		player_upintercepttxt = new JTextField(TEXT_FIELD_WIDTH);
		player_uprecoveriestxt = new JTextField(TEXT_FIELD_WIDTH);
		player_upkeypasstxt = new JTextField(TEXT_FIELD_WIDTH);
		player_upbigchancetxt = new JTextField(TEXT_FIELD_WIDTH);

		player_upcontractstart = new JTextField(TEXT_FIELD_WIDTH);
		player_upcontractend = new JTextField(TEXT_FIELD_WIDTH);
		player_upteamID = new JTextField(TEXT_FIELD_WIDTH);

		// place the license label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 3, 0);
		gb.setConstraints(licenselbl, c);
		playerpane.add(licenselbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 3, 10);
		gb.setConstraints(player_uplicensenumtxt, c);
		playerpane.add(player_uplicensenumtxt);

		// place the firstname label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(firstnamelbl, c);
		playerpane.add(firstnamelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upfirstnametxt, c);
		playerpane.add(player_upfirstnametxt);

		// place the lastname label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(lastnamelbl, c);
		playerpane.add(lastnamelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_uplastnametxt, c);
		playerpane.add(player_uplastnametxt);

		// place the date of birth label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(doblbl, c);
		playerpane.add(doblbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_updateofbirthtxt, c);
		playerpane.add(player_updateofbirthtxt);

		// place the nationality label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(nationlbl, c);
		playerpane.add(nationlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upnationalitytxt, c);
		playerpane.add(player_upnationalitytxt);

		// place the jerseynum label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(jrynumlbl, c);
		playerpane.add(jrynumlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upjersynumtxt, c);
		playerpane.add(player_upjersynumtxt);

		// place the goals conceded label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(golconcelbl, c);
		playerpane.add(golconcelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upgoalconcededtxt, c);
		playerpane.add(player_upgoalconcededtxt);

		// place the goals saved label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(golsvdlbl, c);
		playerpane.add(golsvdlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upgoalsavedtxt, c);
		playerpane.add(player_upgoalsavedtxt);

		// place the successful tackles label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(succtacklbl, c);
		playerpane.add(succtacklbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upsucctackletxt, c);
		playerpane.add(player_upsucctackletxt);

		// place the blocks label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(blklbl, c);
		playerpane.add(blklbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upblockstxt, c);
		playerpane.add(player_upblockstxt);

		// place the clearances label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(clrlbl, c);
		playerpane.add(clrlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upcleartxt, c);
		playerpane.add(player_upcleartxt);

		// place the intercept label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(interceptlbl, c);
		playerpane.add(interceptlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upintercepttxt, c);
		playerpane.add(player_upintercepttxt);

		// place the recoveries label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(recoverlbl, c);
		playerpane.add(recoverlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_uprecoveriestxt, c);
		playerpane.add(player_uprecoveriestxt);

		// place the keypass label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(keypaslbl, c);
		playerpane.add(keypaslbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upkeypasstxt, c);
		playerpane.add(player_upkeypasstxt);

		// place the big chance label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(bigchancelbl, c);
		playerpane.add(bigchancelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upbigchancetxt, c);
		playerpane.add(player_upbigchancetxt);

		// place the contract start label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(contractstartlbl, c);
		playerpane.add(contractstartlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upcontractstart, c);
		playerpane.add(player_upcontractstart);

		// place the contract end label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(contractendlbl, c);
		playerpane.add(contractendlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upcontractend, c);
		playerpane.add(player_upcontractend);

		// place the team id label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(teamIDlbl, c);
		playerpane.add(teamIDlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upteamID, c);
		playerpane.add(player_upteamID);

		// place the insert button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(2, 10, 15, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(updateButton, c);
		contentpane.add(updateButton);

		updateButton.addActionListener(new BranchButtonListener());
	}
	private void deletePlayer(){
		JButton deleteButton = new JButton("delete branch");
		playerDeleteButton = deleteButton;
		JLabel licenselbl;

		// initing labels
		licenselbl = new JLabel("enter player license to delete: ");

		// initing txt
		player_dellicensenumtxt = new JTextField(TEXT_FIELD_WIDTH);

		// place the id label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(licenselbl, c);
		playerpane.add(licenselbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		gb.setConstraints(player_dellicensenumtxt, c);
		playerpane.add(player_dellicensenumtxt);

		// place the delete button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(2, 10, 15, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(deleteButton, c);
		playerpane.add(deleteButton);

		deleteButton.addActionListener(new BranchButtonListener());

	}
	private void showBranchStatic(){
		JButton showButton = new JButton("show branch");
		playerShowButton = showButton;
		playerTable = new JTextArea(10,40);

		// place the Textarea
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(playerTable, c);
		playerpane.add(playerTable);

		// place the show button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(2, 10, 15, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(showButton, c);
		playerpane.add(showButton);

		showButton.addActionListener(new BranchButtonListener());

	}
	private void showPlayerDynamic(FootballPlayerModel[] models){
		playerTable.setText("");
		playerTable.append(" [ license : jerseynum : firstname : lastname : nationality : date of birth : goals conceded : goals saved : big chances : key passes : interceptions : recoveries : successful tackles : blocks : clearances : contract start : contract end : teamID ] \t \n");
		playerTable.append(parseTable(models));
		playerpane.update(playerTable.getGraphics());

	}
	private String parseTable(FootballPlayerModel[] models){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<models.length; i++){
			String str = parseModel(models[i]);
			sb.append(str);
			sb.append("\n");
		}
		return sb.toString();
	}
	private String parseModel(FootballPlayerModel model){
		StringBuilder sb = new StringBuilder();
		sb.append("  [ ");
		sb.append(model.getLicenseNum());
		sb.append(" : ");
		sb.append(model.getJerseyNum());
		sb.append(" : ");
		sb.append(model.getFirstName());
		sb.append(" : ");
		sb.append(model.getLastName());
		sb.append(" : ");
		sb.append(model.getNationality());
		sb.append(" : ");
		sb.append(model.getDateOfBirth());
		sb.append(" : ");

		if(model.getGoalsConceded() == -1){
			sb.append(" null ");
			sb.append(" : ");
		}else{
			sb.append(model.getGoalsConceded());
			sb.append(" : ");
		}
		if(model.getGoalsSaved() == -1){
			sb.append(" null ");
			sb.append(" : ");
		}else{
			sb.append(model.getGoalsSaved());
			sb.append(" : ");
		}
		if(model.getBigChances() == -1){
			sb.append(" null ");
			sb.append(" : ");
		}else{
			sb.append(model.getBigChances());
			sb.append(" : ");
		}
		if(model.getKeyPasses() == -1){
			sb.append(" null ");
			sb.append(" : ");
		}else{
			sb.append(model.getKeyPasses());
			sb.append(" : ");
		}
		if(model.getInterceptions() == -1){
			sb.append(" null ");
			sb.append(" : ");
		}else{
			sb.append(model.getInterceptions());
			sb.append(" : ");
		}
		if(model.getRecoveries() == -1){
			sb.append(" null ");
			sb.append(" : ");
		}else{
			sb.append(model.getRecoveries());
			sb.append(" : ");
		}
		if(model.getSuccessfulTackles() == -1){
			sb.append(" null ");
			sb.append(" : ");
		}else{
			sb.append(model.getSuccessfulTackles());
			sb.append(" : ");
		}
		if(model.getBlocks() == -1){
			sb.append(" null ");
			sb.append(" : ");
		}else{
			sb.append(model.getBlocks());
			sb.append(" : ");
		}
		if(model.getBlocks() == -1){
			sb.append(" null ");
			sb.append(" : ");
		}else{
			sb.append(model.getBlocks());
			sb.append(" : ");
		}
		if(model.getClearances() == -1){
			sb.append(" null ");
			sb.append(" : ");
		}else{
			sb.append(model.getClearances());
			sb.append(" : ");
		}

		sb.append(model.getContractStart());
		sb.append(" : ");
		sb.append(model.getContractEnd());
		sb.append(" : ");
		sb.append(model.getTeamID());
		sb.append(" ]");
		return sb.toString();
	}

	// Branch Button listener
	private class BranchButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == playerInsertButton){
				int golconc;
				int golsav;
				int chanc;
				int keypas;
				int inter;
				int recover;
				int succtack;
				int blk;
				int clr;

				if(player_goalconcededtxt.getText() == null){
					golconc = -1;
				}else{
					golconc = Integer.parseInt(player_goalconcededtxt.getText());
				}

				if(player_goalsavedtxt.getText() == null){
					golsav = -1;
				}else{
					golsav = Integer.parseInt(player_goalsavedtxt.getText());
				}

				if(player_bigchancetxt.getText() == null){
					chanc = -1;
				}else{
					chanc = Integer.parseInt(player_bigchancetxt.getText());
				}

				if(player_keypasstxt.getText() == null){
					keypas = -1;
				}else{
					keypas = Integer.parseInt(player_keypasstxt.getText());
				}

				if(player_intercepttxt.getText() == null){
					inter = -1;
				}else{
					inter = Integer.parseInt(player_intercepttxt.getText());
				}

				if(player_recoveriestxt.getText() == null){
					recover = -1;
				}else{
					recover = Integer.parseInt(player_recoveriestxt.getText());
				}

				if(player_succtackletxt.getText() == null){
					succtack = -1;
				}else{
					succtack = Integer.parseInt(player_succtackletxt.getText());
				}

				if(player_blockstxt.getText() == null){
					blk = -1;
				}else{
					blk = Integer.parseInt(player_blockstxt.getText());
				}

				if(player_cleartxt.getText() == null){
					clr = -1;
				}else{
					clr = Integer.parseInt(player_cleartxt.getText());
				}

				FootballPlayerModel model = new FootballPlayerModel(
						Integer.parseInt(player_licensenumtxt.getText()),
						Integer.parseInt(player_jersynumtxt.getText()),
						player_firstnametxt.getText(),
						player_lastnametxt.getText(),
						player_nationalitytxt.getText(),
						player_dateofbirthtxt.getText(),
						golconc, golsav, chanc, keypas, inter, recover, succtack, blk, clr,
						player_contractstart.getText(),
						player_contractend.getText(),
						Integer.parseInt(player_teamID.getText())
				);
				delegate.insertPlayer(model);

			} else if (e.getSource() == playerDeleteButton){

				String sr = String.format("trying to delete player with: \n license num: %d \n", Integer.parseInt(player_dellicensenumtxt.getText()));
				System.out.print(sr);

				delegate.deletePlayer(Integer.parseInt(player_dellicensenumtxt.getText()));

			} else if (e.getSource() == playerShowButton){
				System.out.print("trying to get table \n");
				FootballPlayerModel[] models = delegate.getPlayers();
				showPlayerDynamic(models);
			}else if(e.getSource() == playerUpdateButton){
				int jerseynum;
				String firstname;
				String lastname;
				String nationality;
				String dateOfBirth;

				int golconc;
				int golsav;
				int chanc;
				int keypas;
				int inter;
				int recover;
				int succtack;
				int blk;
				int clr;

				String contractstart;
				String contractend;
				int teamID;

				if(player_upjersynumtxt.getText() == null){
					jerseynum = -1;
				}else{
					jerseynum = Integer.parseInt(player_upjersynumtxt.getText());
				}

				if(player_upfirstnametxt.getText() == null){
					firstname = "";
				}else{
					firstname = player_upfirstnametxt.getText();
				}

				if(player_uplastnametxt.getText() == null){
					lastname = "";
				}else{
					lastname = player_uplastnametxt.getText();
				}

				if(player_upnationalitytxt.getText() == null){
					nationality = "";
				}else{
					nationality = player_upnationalitytxt.getText();
				}

				if(player_updateofbirthtxt.getText() == null){
					dateOfBirth = "";
				}else{
					dateOfBirth = player_updateofbirthtxt.getText();
				}

				if(player_upgoalconcededtxt.getText() == null){
					golconc = -1;
				}else{
					golconc = Integer.parseInt(player_upgoalconcededtxt.getText());
				}

				if(player_upgoalsavedtxt.getText() == null){
					golsav = -1;
				}else{
					golsav = Integer.parseInt(player_upgoalsavedtxt.getText());
				}

				if(player_upbigchancetxt.getText() == null){
					chanc = -1;
				}else{
					chanc = Integer.parseInt(player_upbigchancetxt.getText());
				}

				if(player_upkeypasstxt.getText() == null){
					keypas = -1;
				}else{
					keypas = Integer.parseInt(player_upkeypasstxt.getText());
				}

				if(player_upintercepttxt.getText() == null){
					inter = -1;
				}else{
					inter = Integer.parseInt(player_upintercepttxt.getText());
				}

				if(player_uprecoveriestxt.getText() == null){
					recover = -1;
				}else{
					recover = Integer.parseInt(player_uprecoveriestxt.getText());
				}

				if(player_upsucctackletxt.getText() == null){
					succtack = -1;
				}else{
					succtack = Integer.parseInt(player_upsucctackletxt.getText());
				}

				if(player_upblockstxt.getText() == null){
					blk = -1;
				}else{
					blk = Integer.parseInt(player_upblockstxt.getText());
				}

				if(player_upcleartxt.getText() == null){
					clr = -1;
				}else{
					clr = Integer.parseInt(player_upcleartxt.getText());
				}

				if(player_upcontractstart.getText() == null){
					contractstart = "";
				}else{
					contractstart = player_upcontractstart.getText();
				}

				if(player_upcontractend.getText() == null){
					contractend = "";
				}else{
					contractend = player_upcontractstart.getText();
				}

				if(player_upteamID.getText() == null){
					teamID = -1;
				}else{
					teamID = Integer.parseInt(player_upteamID.getText());
				}

				delegate.updateFootballPlayer(jerseynum, firstname, lastname, nationality, dateOfBirth, golconc, golsav,
						chanc, keypas, inter, recover, succtack, blk, clr, Integer.parseInt(player_uplicensenumtxt.getText()),
						contractstart, contractend, teamID);

			}
			else{
				System.out.print("ugh something is wrong \n");
			}
		}
	}


}


