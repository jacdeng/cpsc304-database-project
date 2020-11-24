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
	private JTextField arenanametxt;

	//constructor
	public TransactionsWindow(){ super("Transactions Window");}

	public void showFrame(TransactionsWindowDelegate delegate){
		this.delegate = delegate;

		// create contentpanel that will contain all the smaller panels
//		JPanel masterPanel = new JPanel();
//		this.setContentPane(masterPanel);
//		this.masterpane = masterPanel;

		JPanel ContentPanel = new JPanel();
		this.setContentPane(ContentPanel);
		this.contentpane = ContentPanel;

		ContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

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
		GridBagConstraints c = new GridBagConstraints();
		this.gb = gb;
		this.c = c;
		playerpane.setLayout(this.gb);

		insertPlayer();
		deletePlayer();
		updatePlayer();
		showPlayerStatic();
		getteamsforarena();

		JScrollPane playerscrollpane = new JScrollPane(playerpane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		playerscrollpane.setMinimumSize(new Dimension(1100, 800));
		playerscrollpane.setPreferredSize(new Dimension(1100, 800));

		contentpane.add(playerscrollpane);
	}
	private void getteamsforarena() {
		JPanel insplayerpane = new JPanel();
		insplayerpane.setBorder(BorderFactory.createTitledBorder("get teams"));
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		insplayerpane.setLayout(gb);

		JButton insertButton = new JButton("get teams");
		JLabel arenalbl;
		arenalbl = new JLabel("enter the arena to search");

		arenanametxt = new JTextField(TEXT_FIELD_WIDTH);

		// place the firstname label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(arenalbl, c);
		insplayerpane.add(arenalbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(arenanametxt, c);
		insplayerpane.add(arenanametxt);

		// place the insert button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(2, 10, 15, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(insertButton, c);
		insplayerpane.add(insertButton);

		insertButton.addActionListener(new BranchButtonListener());

		// place panel inside playerpane
		this.c.gridwidth = GridBagConstraints.REMAINDER;
		this.c.insets = new Insets(0, 2, 0, 2);
		this.c.anchor = GridBagConstraints.CENTER;
		this.gb.setConstraints(insplayerpane, this.c);
		playerpane.add(insplayerpane);

	}
	private void insertPlayer(){
		JPanel insplayerpane = new JPanel();
		insplayerpane.setBorder(BorderFactory.createTitledBorder("insert"));
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		insplayerpane.setLayout(gb);

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
		insplayerpane.add(licenselbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 3, 10);
		gb.setConstraints(player_licensenumtxt, c);
		insplayerpane.add(player_licensenumtxt);

		// place the firstname label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(firstnamelbl, c);
		insplayerpane.add(firstnamelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_firstnametxt, c);
		insplayerpane.add(player_firstnametxt);

		// place the lastname label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(lastnamelbl, c);
		insplayerpane.add(lastnamelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_lastnametxt, c);
		insplayerpane.add(player_lastnametxt);

		// place the date of birth label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(doblbl, c);
		insplayerpane.add(doblbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_dateofbirthtxt, c);
		insplayerpane.add(player_dateofbirthtxt);

		// place the nationality label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(nationlbl, c);
		insplayerpane.add(nationlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_nationalitytxt, c);
		insplayerpane.add(player_nationalitytxt);

		// place the jerseynum label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(jrynumlbl, c);
		insplayerpane.add(jrynumlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_jersynumtxt, c);
		insplayerpane.add(player_jersynumtxt);

		// place the goals conceded label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(golconcelbl, c);
		insplayerpane.add(golconcelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_goalconcededtxt, c);
		insplayerpane.add(player_goalconcededtxt);

		// place the goals saved label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(golsvdlbl, c);
		insplayerpane.add(golsvdlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_goalsavedtxt, c);
		insplayerpane.add(player_goalsavedtxt);

		// place the successful tackles label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(succtacklbl, c);
		insplayerpane.add(succtacklbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_succtackletxt, c);
		insplayerpane.add(player_succtackletxt);

		// place the blocks label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(blklbl, c);
		insplayerpane.add(blklbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_blockstxt, c);
		insplayerpane.add(player_blockstxt);

		// place the clearances label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(clrlbl, c);
		insplayerpane.add(clrlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_cleartxt, c);
		insplayerpane.add(player_cleartxt);

		// place the intercept label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(interceptlbl, c);
		insplayerpane.add(interceptlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_intercepttxt, c);
		insplayerpane.add(player_intercepttxt);

		// place the recoveries label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(recoverlbl, c);
		insplayerpane.add(recoverlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_recoveriestxt, c);
		insplayerpane.add(player_recoveriestxt);

		// place the keypass label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(keypaslbl, c);
		insplayerpane.add(keypaslbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_keypasstxt, c);
		insplayerpane.add(player_keypasstxt);

		// place the big chance label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(bigchancelbl, c);
		insplayerpane.add(bigchancelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_bigchancetxt, c);
		insplayerpane.add(player_bigchancetxt);

		// place the contract start label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(contractstartlbl, c);
		insplayerpane.add(contractstartlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_contractstart, c);
		insplayerpane.add(player_contractstart);

		// place the contract end label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(contractendlbl, c);
		insplayerpane.add(contractendlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_contractend, c);
		insplayerpane.add(player_contractend);

		// place the team id label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(teamIDlbl, c);
		insplayerpane.add(teamIDlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_teamID, c);
		insplayerpane.add(player_teamID);

		// place the insert button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(2, 10, 15, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(insertButton, c);
		insplayerpane.add(insertButton);

		insertButton.addActionListener(new BranchButtonListener());

		// place panel inside playerpane
		this.c.gridwidth = GridBagConstraints.REMAINDER;
		this.c.insets = new Insets(0, 2, 0, 2);
		this.c.anchor = GridBagConstraints.CENTER;
		this.gb.setConstraints(insplayerpane, this.c);
		playerpane.add(insplayerpane);
	}
	private void updatePlayer(){
		JPanel upplayerpane = new JPanel();
		upplayerpane.setBorder(BorderFactory.createTitledBorder("update"));
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		upplayerpane.setLayout(gb);

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
		upplayerpane.add(licenselbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 3, 10);
		gb.setConstraints(player_uplicensenumtxt, c);
		upplayerpane.add(player_uplicensenumtxt);

		// place the firstname label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(firstnamelbl, c);
		upplayerpane.add(firstnamelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upfirstnametxt, c);
		upplayerpane.add(player_upfirstnametxt);

		// place the lastname label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(lastnamelbl, c);
		upplayerpane.add(lastnamelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_uplastnametxt, c);
		upplayerpane.add(player_uplastnametxt);

		// place the date of birth label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(doblbl, c);
		upplayerpane.add(doblbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_updateofbirthtxt, c);
		upplayerpane.add(player_updateofbirthtxt);

		// place the nationality label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(nationlbl, c);
		upplayerpane.add(nationlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upnationalitytxt, c);
		upplayerpane.add(player_upnationalitytxt);

		// place the jerseynum label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(jrynumlbl, c);
		upplayerpane.add(jrynumlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upjersynumtxt, c);
		upplayerpane.add(player_upjersynumtxt);

		// place the goals conceded label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(golconcelbl, c);
		upplayerpane.add(golconcelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upgoalconcededtxt, c);
		upplayerpane.add(player_upgoalconcededtxt);

		// place the goals saved label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(golsvdlbl, c);
		upplayerpane.add(golsvdlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upgoalsavedtxt, c);
		upplayerpane.add(player_upgoalsavedtxt);

		// place the successful tackles label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(succtacklbl, c);
		upplayerpane.add(succtacklbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upsucctackletxt, c);
		upplayerpane.add(player_upsucctackletxt);

		// place the blocks label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(blklbl, c);
		upplayerpane.add(blklbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upblockstxt, c);
		upplayerpane.add(player_upblockstxt);

		// place the clearances label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(clrlbl, c);
		upplayerpane.add(clrlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upcleartxt, c);
		upplayerpane.add(player_upcleartxt);

		// place the intercept label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(interceptlbl, c);
		upplayerpane.add(interceptlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upintercepttxt, c);
		upplayerpane.add(player_upintercepttxt);

		// place the recoveries label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(recoverlbl, c);
		upplayerpane.add(recoverlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_uprecoveriestxt, c);
		upplayerpane.add(player_uprecoveriestxt);

		// place the keypass label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(keypaslbl, c);
		upplayerpane.add(keypaslbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upkeypasstxt, c);
		upplayerpane.add(player_upkeypasstxt);

		// place the big chance label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(bigchancelbl, c);
		upplayerpane.add(bigchancelbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upbigchancetxt, c);
		upplayerpane.add(player_upbigchancetxt);

		// place the contract start label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(contractstartlbl, c);
		upplayerpane.add(contractstartlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upcontractstart, c);
		upplayerpane.add(player_upcontractstart);

		// place the contract end label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(contractendlbl, c);
		upplayerpane.add(contractendlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upcontractend, c);
		upplayerpane.add(player_upcontractend);

		// place the team id label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 3, 0);
		gb.setConstraints(teamIDlbl, c);
		upplayerpane.add(teamIDlbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 3, 10);
		gb.setConstraints(player_upteamID, c);
		upplayerpane.add(player_upteamID);

		// place the insert button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(2, 10, 15, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(updateButton, c);
		upplayerpane.add(updateButton);

		updateButton.addActionListener(new BranchButtonListener());

		// place panel inside playerpane
		this.c.gridwidth = GridBagConstraints.REMAINDER;
		this.c.insets = new Insets(0, 2, 0, 2);
		this.c.anchor = GridBagConstraints.CENTER;
		this.gb.setConstraints(upplayerpane, this.c);
		playerpane.add(upplayerpane);
	}
	private void deletePlayer(){
		JPanel delplayerpane = new JPanel();
		delplayerpane.setBorder(BorderFactory.createTitledBorder("delete"));
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		delplayerpane.setLayout(gb);

		JButton deleteButton = new JButton("delete player");
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
		delplayerpane.add(licenselbl);
		// place the text field for the id
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		gb.setConstraints(player_dellicensenumtxt, c);
		delplayerpane.add(player_dellicensenumtxt);

		// place the delete button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(2, 10, 15, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(deleteButton, c);
		delplayerpane.add(deleteButton);

		deleteButton.addActionListener(new BranchButtonListener());

		// place panel inside playerpane
		this.c.gridwidth = GridBagConstraints.REMAINDER;
		this.c.insets = new Insets(0, 2, 0, 2);
		this.c.anchor = GridBagConstraints.CENTER;
		this.gb.setConstraints(delplayerpane, this.c);
		playerpane.add(delplayerpane);
	}
	private void showPlayerStatic(){
		JPanel showplayerpane = new JPanel();
		showplayerpane.setBorder(BorderFactory.createTitledBorder("show"));
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		showplayerpane.setLayout(gb);

		JButton showButton = new JButton("show player");
		playerShowButton = showButton;
		playerTable = new JTextArea(10,40);
		playerTable.setEditable(false);

		// place the Textarea
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 10);
		gb.setConstraints(playerTable, c);
		showplayerpane.add(playerTable);

		// place the show button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(2, 10, 15, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(showButton, c);
		showplayerpane.add(showButton);

		showButton.addActionListener(new BranchButtonListener());

		// place panel inside playerpane
		this.c.gridwidth = GridBagConstraints.REMAINDER;
		this.c.insets = new Insets(0, 2, 0, 2);
		this.c.anchor = GridBagConstraints.CENTER;
		this.gb.setConstraints(showplayerpane, this.c);
		playerpane.add(showplayerpane);
	}
	private void showPlayerDynamic(FootballPlayerModel[] models){
		playerTable.setText("");
		playerTable.append(" [ license : jerseynum : firstname : lastname : nationality : date of birth : \n   goals conceded : goals saved : big chances : key passes : interceptions : recoveries : successful tackles : blocks : clearances : \n   contract start : contract end : teamID ] \t \n");
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
		sb.append(": ");
		sb.append(model.getJerseyNum());
		sb.append(": ");
		sb.append(model.getFirstName());
		sb.append(": ");
		sb.append(model.getLastName());
		sb.append(": ");
		sb.append(model.getNationality());
		sb.append(": ");
		sb.append(model.getDateOfBirth());
		sb.append(": \n   ");

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
			sb.append(" : \n   ");
		}else{
			sb.append(model.getClearances());
			sb.append(" : \n   ");
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
				System.out.print("trying to insert player \n");

				int golconc;
				int golsav;
				int chanc;
				int keypas;
				int inter;
				int recover;
				int succtack;
				int blk;
				int clr;

				if(player_goalconcededtxt.getText().equals("")){
					golconc = -1;
				}else{
					golconc = Integer.parseInt(player_goalconcededtxt.getText());
				}

				if(player_goalsavedtxt.getText().equals("")){
					golsav = -1;
				}else{
					golsav = Integer.parseInt(player_goalsavedtxt.getText());
				}

				if(player_bigchancetxt.getText().equals("")){
					chanc = -1;
				}else{
					chanc = Integer.parseInt(player_bigchancetxt.getText());
				}

				if(player_keypasstxt.getText().equals("")){
					keypas = -1;
				}else{
					keypas = Integer.parseInt(player_keypasstxt.getText());
				}

				if(player_intercepttxt.getText().equals("")){
					inter = -1;
				}else{
					inter = Integer.parseInt(player_intercepttxt.getText());
				}

				if(player_recoveriestxt.getText().equals("")){
					recover = -1;
				}else{
					recover = Integer.parseInt(player_recoveriestxt.getText());
				}

				if(player_succtackletxt.getText().equals("")){
					succtack = -1;
				}else{
					succtack = Integer.parseInt(player_succtackletxt.getText());
				}

				if(player_blockstxt.getText().equals("")){
					blk = -1;
				}else{
					blk = Integer.parseInt(player_blockstxt.getText());
				}

				if(player_cleartxt.getText().equals("")){
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
				System.out.print("got model \n");
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


