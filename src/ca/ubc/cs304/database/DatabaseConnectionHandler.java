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

	public void deleteArena(int name) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Arena WHERE name = ?");
			ps.setInt(1, name);
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
			PreparedStatement ps = connection.prepareStatement("DELETE FROM arena1 WHERE name = ?");
			ps.setString(1, address);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Arena " + address + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteCoach (int licencenum){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM coach WHERE name = ?");
			ps.setInt(1, licencenum);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Arena " + licencenum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteDoctor (int licencenum){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM doctor WHERE name = ?");
			ps.setInt(1, licencenum);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Arena " + licencenum + " does not exist!");
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
			PreparedStatement ps = connection.prepareStatement("DELETE FROM match WHERE name = ?");
			ps.setInt(1, matchID);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Arena " + matchID + " does not exist!");
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
			PreparedStatement ps = connection.prepareStatement("DELETE FROM match1 WHERE name = ?");
			ps.setString(1, homeTeam);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Arena " + homeTeam + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deletePlays (int matchID){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM plays WHERE name = ?");
			ps.setInt(1, matchID);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Arena " + matchID + " does not exist!");
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
			PreparedStatement ps = connection.prepareStatement("DELETE FROM referee WHERE name = ?");
			ps.setInt(1, licencenum);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Arena " + licencenum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteReferees (int licencenum){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM referees WHERE name = ?");
			ps.setInt(1, licencenum);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Arena " + licencenum + " does not exist!");
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
				System.out.println(WARNING_TAG + " Arena " + licencenum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deletestatHas (int licencenum){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Stats_Has WHERE name = ?");
			ps.setInt(1, licencenum);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Arena " + licencenum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deletePenalty (int licencenum){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Penalty_Recieves WHERE name = ?");
			ps.setInt(1, licencenum);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Arena " + licencenum + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteInjury (int licencenum){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Injury_Has WHERE name = ?");
			ps.setInt(1, licencenum);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Arena " + licencenum + " does not exist!");
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
