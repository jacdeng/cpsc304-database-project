package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.TransactionsWindowDelegate;
import ca.ubc.cs304.model.*;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.TransactionsWindow;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class FootballLeague implements LoginWindowDelegate, TransactionsWindowDelegate {
	private DatabaseConnectionHandler dbHandler = null;
	private LoginWindow loginWindow = null;

	public FootballLeague() {
		dbHandler = new DatabaseConnectionHandler();
	}

	private void start() {
		loginWindow = new LoginWindow();
		loginWindow.showFrame(this);
	}
	
	/**
	 * LoginWindowDelegate Implementation
	 * 
     * connects to Oracle database with supplied username and password
     */ 
	public void login(String username, String password) {
		boolean didConnect = dbHandler.login(username, password);

		if (didConnect) {
			// Once connected, remove login window and start text transaction flow
			loginWindow.dispose();

			TransactionsWindow transaction = new TransactionsWindow();
			transaction.setupDatabase(this);
			transaction.showMainMenu(this);
		} else {
			loginWindow.handleLoginFailed();

			if (loginWindow.hasReachedMaxLoginAttempts()) {
				loginWindow.dispose();
				System.out.println("You have exceeded your number of allowed attempts");
				System.exit(-1);
			}
		}
	}
	
	/**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Insert a branch with the given info
	 */
    public void insertBranch(BranchModel model) {
    	dbHandler.insertBranch(model);
    }

	public void insertTeam(TeamModel model) {
		dbHandler.insertTeam(model);
	}
	public void insertArena(ArenaModel model) {
		dbHandler.insertArena(model);
	}
	public void insertArena1(Arena1Model model) {
		dbHandler.insertArena1(model);
	}
	public void insertCoach(CoachModel model) {
		dbHandler.insertCoach(model);
	}
	public void insertDoctor(DoctorModel model) {
		dbHandler.insertDoctor(model);
	}
	public void insertMatch(MatchModel model) {
		dbHandler.insertMatch(model);
	}
	public void insertMatch1(Match1Model model) {
		dbHandler.insertMatch1(model);
	}
	public void insertPlays(PlaysModel model) {
		dbHandler.insertPlays(model);
	}
	public void insertReferee(RefereeModel model) {
		dbHandler.insertReferee(model);
	}
	public void insertReferees(RefereesModel model) {
		dbHandler.insertReferees(model);
	}
	public void insertFootballPlayers(FootballPlayerModel model) {
		dbHandler.insertFootballPlayers(model);
	}
	public void insertStatsHas(StatsHasModel model) {
		dbHandler.insertStatsHas(model);
	}
	public void insertPenaltyRecieves(PenaltyRecievesModel model) {
		dbHandler.insertPenaltyRecieves(model);
	}
	public void insertInjuryHas(InjuryHasModel model) {
		dbHandler.insertInjuryHas(model);
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Delete branch with given branch ID.
	 */ 
    public void deleteBranch(int branchId) {
    	dbHandler.deleteBranch(branchId);
    }

	public void deleteTeam(int teamId) {
		dbHandler.deleteTeam(teamId);
	}
	public void deleteArena(String name) {
		dbHandler.deleteArena(name);
	}
	public void deleteArena1(String address) {
		dbHandler.deleteArena1(address);
	}
	public void deleteCoach(int licenseNum) {
		dbHandler.deleteCoach(licenseNum);
	}
	public void deleteDoctor(int licenseNum) {
		dbHandler.deleteDoctor(licenseNum);
	}
	public void deleteMatch(int matchId) {
		dbHandler.deleteMatch(matchId);
	}
	public void deleteMatch1(String homeTeam) {
		dbHandler.deleteMatch1(homeTeam);
	}
	public void deletePlays(int matchID, int teamID) {
		dbHandler.deletePlays(matchID, teamID);
	}
	public void deleteReferee(int licenseNum) {
		dbHandler.deleteReferee(licenseNum);
	}
	public void deleteReferees(int licenseNum, int matchID) {
		dbHandler.deleteReferees(licenseNum, matchID);
	}
	public void deleteFootballPlayer(int licenseNum) {
		dbHandler.deleteFootballPlayer(licenseNum);
	}
	public void deleteStatsHas(int licenseNum, String type) {
		dbHandler.deleteStatHas(licenseNum, type);
	}
	public void deletePenaltyReceives(int licenseNum, int timeStamp) {
		dbHandler.deletePenalty(licenseNum, timeStamp);
	}
	public void deleteInjuryHas(int licenseNum, int timeStamp) {
		dbHandler.deleteInjury(licenseNum, timeStamp);
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Update the branch name for a specific ID
	 */

    public void updateBranch(int branchId, String name) {
    	dbHandler.updateBranch(branchId, name);
    }

    /**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Displays information about varies bank branches.
	 */
    public void showBranch() {
    	BranchModel[] models = dbHandler.getBranchInfo();
    	
    	for (int i = 0; i < models.length; i++) {
    		BranchModel model = models[i];
    		
    		// simplified output formatting; truncation may occur
    		System.out.printf("%-10.10s", model.getId());
    		System.out.printf("%-20.20s", model.getName());
    		if (model.getAddress() == null) {
    			System.out.printf("%-20.20s", " ");
    		} else {
    			System.out.printf("%-20.20s", model.getAddress());
    		}
    		System.out.printf("%-15.15s", model.getCity());
    		if (model.getPhoneNumber() == 0) {
    			System.out.printf("%-15.15s", " ");
    		} else {
    			System.out.printf("%-15.15s", model.getPhoneNumber());
    		}
    		
    		System.out.println();
    	}
    }
	
    /**
	 * TerminalTransactionsDelegate Implementation
	 * 
     * The TerminalTransaction instance tells us that it is done with what it's 
     * doing so we are cleaning up the connection since it's no longer needed.
     */ 
    public void terminalTransactionsFinished() {
    	dbHandler.close();
    	dbHandler = null;
    	
    	System.exit(0);
    }
    
    /**
	 * TerminalTransactionsDelegate Implementation
	 * 
     * The TerminalTransaction instance tells us that the user is fine with dropping any existing table
     * called branch and creating a new one for this project to use
     */ 
	public void databaseSetup() {
		dbHandler.databaseSetup();;
		
	}
    
	/**
	 * Main method called at launch time
	 */
	public static void main(String args[]) {
		FootballLeague footballLeague = new FootballLeague();
		footballLeague.start();
	}
}
