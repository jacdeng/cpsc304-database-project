package ca.ubc.cs304.model;

public class ModifiedTeamModel {
    // Here we are making a new class, this will be used to return a list fo Modified teams instead of the result set.
        private String teamName;
        private int teamID;
        private int phoneNum;

        public ModifiedTeamModel(String teamName, int teamID, int phoneNum) {
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

