package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.FootballPlayerModel;
import ca.ubc.cs304.model.ModifiedTeamModel;

import java.util.ArrayList;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the 
 * controller class (in this case Bank).
 * 
 * TerminalTransactions calls the methods that we have listed below but 
 * Bank is the actual class that will implement the methods.
 */
public interface TransactionsWindowDelegate {
	public void databaseSetup();
	public void deleteBranch(int branchId);
	public void insertBranch(BranchModel model);
	public void showBranch();
	public void updateBranch(int branchId, String name);
	public void terminalTransactionsFinished();

	public void insertPlayer(FootballPlayerModel model);
	public void deletePlayer(int licensenum);
	public FootballPlayerModel[] getPlayers();
	public void updateFootballPlayer(int jerseyNum, String firstName, String lastName, String nationality, String dateOfBirth,
									 int goalsConceded, int goalsSaved,
									 int bigChances, int keyPasses,
									 int interceptions, int recoveries,
									 int successfulTackles, int blocks, int clearances,
									 int licenseNum, String contractStart, String contractEnd, int teamID);

	public void getteamNameIDandNum();
	public FootballPlayerModel[] selectteamplayers(int wantedTeamID);
	public void getEligibleSquads();
	public ModifiedTeamModel[] getteamsforarena(String city);
	public void GGOAT();

}
