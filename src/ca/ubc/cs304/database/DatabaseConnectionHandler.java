package ca.ubc.cs304.database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import ca.ubc.cs304.model.*;
import ca.ubc.cs304.utils.ScriptRunner;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	// Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
	// Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	
	private Connection connection = null;
	
	public DatabaseConnectionHandler() {
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
	
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	/**
	 * Projection Handler
	 * Projecting a teamName, TeamID and Phonenumber of a particular team
	 * Input: teamName, TeamID, Phonenumber
	 * Output: List of teams with the columns teamName, teamID, Phonenumber.
	 */
	// Here we are making a new class, this will be used to return a list fo Modified teams instead of the result set.
	private class ModifiedTeam {
		private String teamName;
		private int teamID;
		private int phoneNum;

		public ModifiedTeam(String teamName, int teamID, int phoneNum) {
			this.teamName = teamName;
			this.teamID = teamID;
			this.phoneNum = phoneNum;
		}

		public String getTeamName() {
			return teamName;
		}

		public void setTeamName(String teamName) {
			this.teamName = teamName;
		}

		public int getTeamID() {
			return teamID;
		}

		public void setTeamID(int teamID) {
			this.teamID = teamID;
		}

		public int getPhoneNum() {
			return phoneNum;
		}

		public void setPhoneNum(int phoneNum) {
			this.phoneNum = phoneNum;
		}
	}


	public ArrayList<ModifiedTeam> getteamNameIDandNum(){

		ArrayList<ModifiedTeam> result = new ArrayList<>();

		try {
			// Create a statement for passing into result set
			Statement stmt = connection.createStatement();
			// Prepare statement where we select the columns of teamID, teamName and phoneNum
			PreparedStatement ps = connection.prepareStatement("SELECT teamID, teamName, phoneNum FROM Team_HasManages ");

			// executing the statement to get a resultset.
			// String.valueOf converts the prepared statement into a useable string for the ResultSet class.
			ResultSet rs = stmt.executeQuery(String.valueOf(ps));

			// While loop to go over result set and create new models for teams and then put them into the result(list),
			// For the return statement.

			while(rs.next()) {
				ModifiedTeam model = new ModifiedTeam(
						rs.getString("teamName"),
						rs.getInt("teamID"),
						rs.getInt("PhoneNum")
				);

				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		// Return the resulting list of modfiedTeam models.
		return result;
	}


	/**
	 * Selection Handler
	 * Selection is for a team, and you may select the payers on a given team.
	 * Input: teamID (Primary Key)
	 * Output: List of Players on the teamID
	 */
	public ArrayList<FootballPlayerModel> select(int wantedTeamID){
//		// Making new list of football players to output.
		ArrayList<FootballPlayerModel> result = new ArrayList<FootballPlayerModel>();

		try {
			// Create a statement for passing into result set
			Statement stmt = connection.createStatement();
			// Prepare statement wehre teamID is equal to the argument wantedteamID
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM FootballPlayer WHERE teamID = ?");
			// Setting the parameter in SQL to be wantedteamID
			ps.setInt(1, wantedTeamID);
			// executing the statement to get a resultset.
			// String.valueOf converts the prepared statement into a useable string for the ResultSet class.
			ResultSet rs = stmt.executeQuery(String.valueOf(ps));




//    		// get info on ResultSet
//    		ResultSetMetaData rsmd = rs.getMetaData();
//
//    		System.out.println(" ");
//
//    		// display column names;
//    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
//    			// get column name and print it
//    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
//    		}
			// While loop to go over result set and create new models for the players and then put them into the result (list),
			// For the return statement.
			while(rs.next()) {
				FootballPlayerModel model = new FootballPlayerModel(
						rs.getInt("licenceNum"),
						rs.getInt("jerseyNum"),
						rs.getString("firstName"),
						rs.getString("lastName"),
						rs.getString("nationality"),
						rs.getString("dateOfBirth"),
						rs.getInt("goalsConceded"),
						rs.getInt("goalsSaved"),
						rs.getInt("bigChances"),
						rs.getInt("keyPasses"),
						rs.getInt("interceptions"),
						rs.getInt("recoveries"),
						rs.getInt("successfulTackles"),
						rs.getInt("blocks"),
						rs.getInt("clearances"),
						rs.getString("contractStart"),
						rs.getString("contractEnd"),
						rs.getInt("teamID"));

				result.add(model);
			}

			rs.close();
			stmt.close();


		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		// Return the resulting list of footballplayer models.
		return result;

	}

	/**
	 * START OF DELETION HANDLER METHODS
	 */

	public void deleteBranch(int branchId) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM branch WHERE branch_id = ?");
			ps.setInt(1, branchId);
			
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Branch " + branchId + " does not exist!");
			}
			
			connection.commit();
	
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteTeam(int teamId) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Team_HasManages WHERE teamID = ?");
			ps.setInt(1, teamId);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Team " + teamId + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteArena(String name) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Arena WHERE name = ?");
			ps.setString(1, name);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Arena " + name + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}


	public void deleteArena1 (String address){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Arena1 WHERE address = ?");
			ps.setString(1, address);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Arena1 with address " + address + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteCoach (int licenseNum){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Coach WHERE licenseNum = ?");
			ps.setInt(1, licenseNum);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Coach " + licenseNum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteDoctor (int licenseNum){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Doctor_Treat WHERE licenseNum = ?");
			ps.setInt(1, licenseNum);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Doctor " + licenseNum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteMatch (int matchID){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Match WHERE matchID = ?");
			ps.setInt(1, matchID);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Match " + matchID + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteMatch1 (String homeTeam){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Match1 WHERE homeTeam = ?");
			ps.setString(1, homeTeam);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Match1 with home team " + homeTeam + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deletePlays (int matchID, int teamID){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Plays WHERE matchID = ? AND teamID = ?");
			ps.setInt(1, matchID);
			ps.setInt(2, teamID);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Plays " + matchID + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteReferee (int licencenum){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Referee WHERE name = ?");
			ps.setInt(1, licencenum);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Referee " + licencenum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteReferees (int licencenum, int matchID){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Referees WHERE name = ? AND matchID = ?");
			ps.setInt(1, licencenum);
			ps.setInt(2, matchID);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Referees " + licencenum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteFootballPlayer (int licencenum){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM FootballPlayer_PlaysFor WHERE name = ?");
			ps.setInt(1, licencenum);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " FootballPlayer " + licencenum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteStatHas (int licencenum, String type){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Stats_Has WHERE name = ? AND type = ?");
			ps.setInt(1, licencenum);
			ps.setString(2, type);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Stats_Has with player licensenum: " + licencenum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deletePenalty (int licencenum, int timeStamp){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Penalty_Recieves WHERE name = ? AND timeStamp = ?");
			ps.setInt(1, licencenum);
			ps.setInt(2, timeStamp);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Penalty with player licensenum: " + licencenum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteInjury (int licencenum, int timeStamp){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Injury_Has WHERE name = ? AND timeStamp = ?");
			ps.setInt(1, licencenum);
			ps.setInt(2, timeStamp);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Injury with play licencenum: " + licencenum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}


	//TODO: FINISH UP THE DELETE FUNCTIONS FOR ALL THE TABLES!

	/**
	 * END OF DELETION HANDLER METHODS
	 *
	 * START OF INSERT HANDLER METHODS
	 */
	
	public void insertBranch(BranchModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO branch VALUES (?,?,?,?,?)");
			ps.setInt(1, model.getId());
			ps.setString(2, model.getName());
			ps.setString(3, model.getAddress());
			ps.setString(4, model.getCity());
			if (model.getPhoneNumber() == 0) {
				ps.setNull(5, java.sql.Types.INTEGER);
			} else {
				ps.setInt(5, model.getPhoneNumber());
			}

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertTeam(TeamModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Team_HasManages VALUES (?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, model.getPhoneNum());
			ps.setString(2, model.getWebsite());
			ps.setString(3, model.getTeamName());
			ps.setInt(4, model.getTeamID());

			ps.setString(5, model.getSince());
			ps.setString(6, model.getArenaName());

			ps.setString(7,model.getContractStart());
			ps.setString(8, model.getContractEnd());
			ps.setInt(9, model.getCoachLicenseNum());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertArena(ArenaModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Arena VALUES (?,?,?,?)");
			ps.setString(1, model.getAddress());
			ps.setString(2, model.getSurfaceType());
			ps.setInt(3, model.getCapacity());
			ps.setString(4, model.getName());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertArena1(Arena1Model model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Arena1 VALUES (?,?)");
			ps.setString(1, model.getCity());
			ps.setString(2, model.getAddress());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertCoach(CoachModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Coach VALUES (?,?,?,?)");
			ps.setString(1, model.getNationality());
			ps.setString(2, model.getFirstName());
			ps.setString(3, model.getLastName());
			ps.setInt(4, model.getLicenseNum());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertDoctor(DoctorModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Doctor_Treat VALUES (?,?,?,?,?,?,?)");
			ps.setString(1, model.getFirstName());
			ps.setString(2, model.getLastName());
			ps.setString(3, model.getFieldOfPractice());
			ps.setInt(4, model.getLicenseNum());

			ps.setString(5, model.getStartDate());
			ps.setString(6, model.getEndDate());
			ps.setInt(7,model.getTeamID());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertMatch(MatchModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Match VALUES (?,?,?,?,?)");
			ps.setString(1, model.getHomeTeam());
			ps.setString(2, model.getAwayTeam());
			ps.setString(3, model.getScore());
			ps.setString(4, model.getDate());
			ps.setInt(5, model.getMatchID());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertMatch1(Match1Model model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Match1 VALUES (?,?)");
			ps.setString(1, model.getArena());
			ps.setString(2, model.getHomeTeam());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertPlays(PlaysModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Plays VALUES (?,?)");
			ps.setInt(1, model.getMatchID());
			ps.setInt(2, model.getTeamID());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertReferee(RefereeModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Referee VALUES (?,?,?,?)");
			ps.setString(1, model.getCity());
			ps.setString(2, model.getFirstName());
			ps.setString(3,model.getLastName());
			ps.setInt(4, model.getLicenseNum());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertReferees(RefereesModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Referees VALUES (?,?,?,?)");
			ps.setInt(1, model.getLicenseNum());
			ps.setInt(2, model.getMatchID());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertFootballPlayers(FootballPlayerModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO FootballPlayer_PlaysFor VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, model.getJerseyNum());
			ps.setString(2, model.getFirstName());
			ps.setString(3, model.getLastName());
			ps.setString(4, model.getNationality());
			ps.setString(5, model.getDateOfBirth());

			ps.setInt(6, model.getGoalsConceded());
			ps.setInt(7, model.getGoalsSaved());
			ps.setInt(8, model.getBigChances());
			ps.setInt(9, model.getKeyPasses());
			ps.setInt(10, model.getInterceptions());
			ps.setInt(11, model.getRecoveries());
			ps.setInt(12, model.getSuccessfulTackles());
			ps.setInt(13, model.getBlocks());
			ps.setInt(14, model.getClearances());

			ps.setInt(15, model.getLicenseNum());

			ps.setString(16, model.getContractStart());
			ps.setString(17, model.getContractEnd());
			ps.setInt(18, model.getTeamID());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertStatsHas(StatsHasModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Stats_Has VALUES (?,?,?)");
			ps.setInt(1, model.getAmount());
			ps.setString(2, model.getType());
			ps.setInt(3, model.getLicenseNum());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertPenaltyRecieves(PenaltyRecievesModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Penalty_Receives VALUES (?,?,?,?,?)");
			ps.setInt(1, model.getTimestamp());
			ps.setString(2, model.getType());
			ps.setString(3, model.getCard());
			ps.setInt(4, model.getDuration());
			ps.setInt(5, model.getLicenseNum());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertInjuryHas(InjuryHasModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Injury_Has VALUES (?,?,?)");
			ps.setInt(1, model.getTimeStamp());
			ps.setString(2, model.getType());
			ps.setInt(3, model.getLicenseNum());

			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}
	/**
	 * END OF INSERTION HANDLER METHODS
	 *
	 * START OF GET INFO QUERY HANDLER METHODS
	 */


	public BranchModel[] getBranchInfo() {
		ArrayList<BranchModel> result = new ArrayList<BranchModel>();
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM branch");
		
//    		// get info on ResultSet
//    		ResultSetMetaData rsmd = rs.getMetaData();
//
//    		System.out.println(" ");
//
//    		// display column names;
//    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
//    			// get column name and print it
//    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
//    		}
			
			while(rs.next()) {
				BranchModel model = new BranchModel(rs.getString("branch_addr"),
													rs.getString("branch_city"),
													rs.getInt("branch_id"),
													rs.getString("branch_name"),
													rs.getInt("branch_phone"));
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}	
		
		return result.toArray(new BranchModel[result.size()]);
	}

	public TeamModel[] getTeamInfo(){
		ArrayList<TeamModel> result = new ArrayList<TeamModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Team_HasManages");

			while(rs.next()) {
				TeamModel model = new TeamModel(
						rs.getString("Team_HasManages_teamName"),
						rs.getInt("Team_HasManages_teamID"),
						rs.getString("Team_HasManages_website"),
						rs.getInt("Team_HasManages_phoneNum"),
						rs.getString("Team_HasManages_since"),
						rs.getString("Team_HasManages_arenaName"),
						rs.getString("Team_HasManages_contractStart"),
						rs.getString("Team_HasManages_contractEnd"),
						rs.getInt("Team_HasManages_licenseNum")
				);
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new TeamModel[result.size()]);
	}

	public ArenaModel[] getArenaInfo(){
		ArrayList<ArenaModel> result = new ArrayList<ArenaModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Arena");

			while(rs.next()) {
				ArenaModel model = new ArenaModel(
						rs.getString("Arena_address"),
						rs.getString("Arena_surfaceType"),
						rs.getInt("Arena_capacity"),
						rs.getString("Arena_name"));
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new ArenaModel[result.size()]);
	}

	public Arena1Model[] getArena1Info(){
		ArrayList<Arena1Model> result = new ArrayList<Arena1Model>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Arena1");

			while(rs.next()) {
				Arena1Model model = new Arena1Model(
						rs.getString("Arena1_address"),
						rs.getString("Arena1_city"));
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new Arena1Model[result.size()]);
	}

	public CoachModel[] getCoachInfo(){
		ArrayList<CoachModel> result = new ArrayList<CoachModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Coach");

			while(rs.next()) {
				CoachModel model = new CoachModel(
						rs.getString("Coach_nationality"),
						rs.getString("Coach_firstName"),
						rs.getString("Coach_lastName"),
						rs.getInt("Coach_licenseNum")
						);
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new CoachModel[result.size()]);
	}

	public DoctorModel[] getDoctorInfo(){
		ArrayList<DoctorModel> result = new ArrayList<DoctorModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Doctor_Treat");

			while(rs.next()) {
				DoctorModel model = new DoctorModel(
						rs.getString("Doctor_Treat_firstName"),
						rs.getString("Doctor_Treat_lastName"),
						rs.getString("Doctor_Treat_fieldOfPractice"),
						rs.getInt("Doctor_Treat_licenseNum"),
						rs.getString("Doctor_Treat_startDate"),
						rs.getString("Doctor_Treat_endDate"),
						rs.getInt("Doctor_Treat_teamID")
				);
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new DoctorModel[result.size()]);
	}

	public MatchModel[] getMatchInfo(){
		ArrayList<MatchModel> result = new ArrayList<MatchModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Match");

			while(rs.next()) {
				MatchModel model = new MatchModel(
						rs.getString("Match_homeTeam"),
						rs.getString("Match_awayTeam"),
						rs.getString("Match_score"),
						rs.getString("Match_date"),
						rs.getInt("Match_matchID")
						);
				result.add(model);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return result.toArray(new MatchModel[result.size()]);
	}

	public Match1Model[] getMatch1Info(){
		ArrayList<Match1Model> result = new ArrayList<Match1Model>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Match1");

			while(rs.next()) {
				Match1Model model = new Match1Model(
						rs.getString("Match1_arena"),
						rs.getString("Match1_homeTeam")
				);
				result.add(model);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return result.toArray(new Match1Model[result.size()]);
	}

	public PlaysModel[] getPlaysInfo(){
		ArrayList<PlaysModel> result = new ArrayList<PlaysModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Plays");

			while(rs.next()) {
				PlaysModel model = new PlaysModel(
						rs.getInt("Plays_matchID"),
						rs.getInt("Plays_teamID")
				);
				result.add(model);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return result.toArray(new PlaysModel[result.size()]);
	}

	public RefereeModel[] getRefereeInfo(){
		ArrayList<RefereeModel> result = new ArrayList<RefereeModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Referee");

			while(rs.next()) {
				RefereeModel model = new RefereeModel(
						rs.getString("Plays_city"),
						rs.getString("Plays_firstName"),
						rs.getString("Plays_lastName"),
						rs.getInt("Plays_licenseNum")
						);
				result.add(model);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return result.toArray(new RefereeModel[result.size()]);
	}

	public RefereesModel[] getRefereesInfo(){
		ArrayList<RefereesModel> result = new ArrayList<RefereesModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Referees");

			while(rs.next()) {
				RefereesModel model = new RefereesModel(
						rs.getInt("Plays_matchID"),
						rs.getInt("Plays_licenseNum")
				);
				result.add(model);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new RefereesModel[result.size()]);
	}

	public FootballPlayerModel[] getFootballPlayerInfo(){
		ArrayList<FootballPlayerModel> result = new ArrayList<FootballPlayerModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM FootballPlayer_PlaysFor");

			while(rs.next()) {
				FootballPlayerModel model = new FootballPlayerModel(
						rs.getInt("FootballPLayer_PlaysFor_licenseNum"),
						rs.getInt("FootballPLayer_PlaysFor_jerseyNum"),
						rs.getString("FootballPlayer_PlaysFor_firstName"),
						rs.getString("FootballPlayer_PlaysFor_lastName"),
						rs.getString("FootballPlayer_PlaysFor_nationality"),
						rs.getString("FootballPlayer_PlaysFor_dateOfBirth"),
						rs.getInt("FootballPlayer_PlaysFor_goalsConcede"),
						rs.getInt("FootballPlayer_PlaysFor_goalsSaved"),
						rs.getInt("FootballPlayer_PlaysFor_bigChances"),
						rs.getInt("FootballPlayer_PlaysFor_keyPasses"),
						rs.getInt("FootballPlayer_PlaysFor_interceptions"),
						rs.getInt("FootballPlayer_PlaysFor_recoveries"),
						rs.getInt("FootballPlayer_PlaysFor_successfulTackles"),
						rs.getInt("FootballPlayer_PlaysFor_blocks"),
						rs.getInt("FootballPlayer_PlaysFor_clearances"),
						rs.getString("FootballPlayer_PlaysFor_contractStart"),
						rs.getString("FootballPlayer_PlaysFor_contractEnd"),
						rs.getInt("FootballPlayer_PlaysFor_teamID")
						);
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new FootballPlayerModel[result.size()]);
	}

	public StatsHasModel[] getStatsHasInfo(){
		ArrayList<StatsHasModel> result = new ArrayList<StatsHasModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Stats_Has");

			while(rs.next()) {
				StatsHasModel model = new StatsHasModel(
						rs.getString("Stats_Has_type"),
						rs.getInt("Stats_Has_amount"),
						rs.getInt("Stats_Has_licenseNum")
				);
				result.add(model);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new StatsHasModel[result.size()]);
	}

	public PenaltyRecievesModel[] getPenaltyRecievesInfo(){
		ArrayList<PenaltyRecievesModel> result = new ArrayList<PenaltyRecievesModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Penalty_Receives");

			while(rs.next()) {
				PenaltyRecievesModel model = new PenaltyRecievesModel(
						rs.getString("Penalty_Receives_type"),
						rs.getString("Penalty_Receives_card"),
						rs.getInt("Penalty_Receives_duration"),
						rs.getInt("Penalty_Receives_timeStamp"),
						rs.getInt("Penalty_Receives_licenseNum")
						);
				result.add(model);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new PenaltyRecievesModel[result.size()]);
	}

	public InjuryHasModel[] getInjuryHasInfo(){
		ArrayList<InjuryHasModel> result = new ArrayList<InjuryHasModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Injury_Has");

			while(rs.next()) {
				InjuryHasModel model = new InjuryHasModel(
						rs.getString("Injury_Has_type"),
						rs.getInt("Injury_Has_timeStamp"),
						rs.getInt("Injury_Has_licenseNum")
						);
				result.add(model);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new InjuryHasModel[result.size()]);
	}

	//TODO: NEED TO FIGURE OUT HOW TO SELECT AND PROJECTION SPECIFIC QUERIES? LETS FIGURE OUT HOW TO DO THIS SOON!

	/**
	 * END OF THE GET INFO QUERY HANDLERS
	 *
	 * START OF THE UPDATE HANDLER METHODS
	 */

	public void updateBranch(int id, String name) {
		try {
		  PreparedStatement ps = connection.prepareStatement("UPDATE branch SET branch_name = ? WHERE branch_id = ?");
		  ps.setString(1, name);
		  ps.setInt(2, id);
		
		  int rowCount = ps.executeUpdate();
		  if (rowCount == 0) {
		      System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
		  }
	
		  connection.commit();
		  
		  ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}	
	}

	public void updateTeam(int phoneNum, String website, String teamName, int teamID, String since, String arenaName, String contractStart, String contractEnd, int coachLicenseNum) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE Team_HasManages SET phoneNum = ?,website = ?,teamName = ?,teamID = ?,since = ?,arenaName = ?,contractStart = ?,contractEnd = ?,licenseNum = ? WHERE teamID = ?");
			ps.setInt(1, phoneNum);
			ps.setString(2, website);
			ps.setString(3, teamName);
			ps.setInt(4, teamID);

			ps.setString(5, since);
			ps.setString(6, arenaName);

			ps.setString(7,contractStart);
			ps.setString(8, contractEnd);
			ps.setInt(9, coachLicenseNum);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Team " + teamID + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void updateCoach(String nationality, String firstName, String lastName, int licenseNum) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE Coach SET nationality = ?,firstName = ?,lastName = ?,licenseNum = ? WHERE licenseNum = ?");
			ps.setString(1, nationality);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setInt(4, licenseNum);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Coach " + licenseNum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void updateDoctor(String firstName, String lastName, String fieldOfPractice, int licenseNum, String startDate, String endDate, int teamID) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE Doctor_Treat SET firstName = ?,lastName = ?,fieldOfPractice = ?,licenseNum = ?,startDate = ?,endDate = ?,teamID = ? WHERE licenseNum = ?");
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, fieldOfPractice);
			ps.setInt(4, licenseNum);

			ps.setString(5, startDate);
			ps.setString(6, endDate);
			ps.setInt(7, teamID);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Doctor " + licenseNum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void updateMatch(String homeTeam, String awayTeam, String score, String date, int matchID) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE Match SET homeTeam = ?,awayTeam = ?,score = ?,date = ?,matchID = ? WHERE matchID = ?");
			ps.setString(1, homeTeam);
			ps.setString(2, awayTeam);
			ps.setString(3, score);
			ps.setString(4, date);
			ps.setInt(5, matchID);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Match " + matchID + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void updateMatch1(String arena, String homeTeam) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE Match1 SET arena = ?,homeTeam = ? WHERE homeTeam = ?");
			ps.setString(1, arena);
			ps.setString(2, homeTeam);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Match1 with hometeam " + homeTeam + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void updateFootballPlayer(int jerseyNum, String firstName, String lastName, String nationality, String dateOfBirth, int goalsConceded, int goalsSaved, int bigChances, int keyPasses, int interceptions, int recoveries, int successfulTackles, int blocks, int clearances, int licenseNum, String contractStart, String contractEnd, int teamID) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE FootballPlayer_PlaysFor SET jerseyNum = ?,firstName = ?,lastName = ?,nationality = ?,dateOfBirth = ?,goalsConceded = ?,goalsSaved = ?,bigChances = ?,keyPasses = ?,interceptions = ?,recoveries = ?,successfulTackles = ?,blocks = ?,clearances = ?,licenseNum = ?,contractStart = ?,contractEnd = ?,teamID = ? WHERE licenseNum = ?");
			ps.setInt(1, jerseyNum);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, nationality);
			ps.setString(5, dateOfBirth);

			ps.setInt(6, goalsConceded);
			ps.setInt(7, goalsSaved);
			ps.setInt(8, bigChances);
			ps.setInt(9, keyPasses);
			ps.setInt(10, interceptions);
			ps.setInt(11, recoveries);
			ps.setInt(12, successfulTackles);
			ps.setInt(13, blocks);
			ps.setInt(14, clearances);

			ps.setInt(15, licenseNum);

			ps.setString(16, contractStart);
			ps.setString(17, contractEnd);
			ps.setInt(18, teamID);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Player " + licenseNum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}



	//TODO: NEED TO DO A LOT MORE UPDATE METHODS!!! LETS FIGURE OUT A WAY TO DO THIS EFFICIENTLY SINCE WE HAVE SO MANY ATTRIBUTES?

	/**
	 * END OF THE UPDATE HANDLER METHODS
	 */
	
	public boolean login(String username, String password) {
		try {
			if (connection != null) {
				connection.close();
			}
	
			connection = DriverManager.getConnection(ORACLE_URL, username, password);
			connection.setAutoCommit(false);
	
			System.out.println("\nConnected to Oracle!");
			return true;
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			return false;
		}
	}

	private void rollbackConnection() {
		try  {
			connection.rollback();	
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
	
	public void databaseSetup() {
		dropBranchTableIfExists();
		
		try {
			// creating script runner object with current connection
			ScriptRunner sr = new ScriptRunner(connection, connection.getAutoCommit(), true);
			// creating reader object
			Reader reader = new BufferedReader(new FileReader("src/ca/ubc/cs304/sql/scripts/databaseSetup.sql"));
			sr.runScript(reader);
//			Statement stmt = connection.createStatement();
//			stmt.executeUpdate("CREATE TABLE branch (branch_id integer PRIMARY KEY, branch_name varchar2(20) not null, branch_addr varchar2(50), branch_city varchar2(20) not null, branch_phone integer)");
//			stmt.close();
		} catch (SQLException | FileNotFoundException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		} catch (IOException e) {
			// catch exception from runScript
			e.printStackTrace();
		}

		BranchModel branch1 = new BranchModel("123 Charming Ave", "Vancouver", 1, "First Branch", 1234567);
		insertBranch(branch1);

		BranchModel branch2 = new BranchModel("123 Coco Ave", "Vancouver", 2, "Second Branch", 1234568);
		insertBranch(branch2);
	}
	
	private void dropBranchTableIfExists() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select table_name from user_tables");
			
			while(rs.next()) {
				if(rs.getString(1).toLowerCase().equals("branch")) {
					stmt.execute("DROP TABLE branch");
					break;
				}
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
}
