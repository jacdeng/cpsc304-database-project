CREATE TABLE FootballPlayer_PlaysFor (
    jerseyNum int NOT NUll, 
    firstName char (50) NOT NUll,
    lastName char(30) NOT NUll,
    nationality char(50) NOT NULL,
    dateOfBirth char(8) NOT NULL, 

    goalsConceded int, 
    goalsSaves int,
    bigChances int, 
    keyPasses int,
    interceptions int, 
    recoveries int, 
    successfulTackles int, 
    blocks int, 
    clearances int, 

    licenceNum int, 

    contractStart char(8), 
    contractEnd char (8), 
    teamID int
); 

CREATE TABLE Team_HasManages(
    phoneNum int,
    website char(100),
    teamName char(40) NOT NULL, 
    teamID char(50), 

    since char (8) NOT NULL, 
    arenaName char(50) NOT NUll,

    contractStart char(8) NOT NULL, 
    contractEnd char(8) NOT NULL, 
    licenseNum int NOT NULL 
    
); 

CREATE TABLE Arena( 
    address char(100) NOT NULL,
    surfaceType char(50) NOT NULL, 
    capacity int NOT NULL, 
    name char(50) PRIMARY KEY, 

); 

CREATE TABLE Arena1 (
    city char(20) NOT NULL,
    address char(100) PRIMARY KEY, 
    FOREIGN KEY (address) REFERENCES Arena
); 

CREATE TABLE Coach (
    nationality char(20) NOT NULL,
    firstName char(50) NOT NULL,
    lastName char(50) NOT NULL,
    licenceNum int PRIMARY KEY
); 

CREATE TABLE Doctor_Treat (
    firstname char(50) NOT NULL,
    lastName char (50) NOT NULL, 
    fieldOfPractice char(50) NOT NULL, 
    licenceNum int PRIMARY KEY, 
    startDate char(8), 
    endDate char(8)
    teamID int 
); 

CREATE TABLE Match(
    homeTeam char(50) not null, 
    awayTeam char(50) not null, 
    score char(8) not null, 
    date char(10) not null, 
    matchID int PRIMARY KEY 
); 

CREATE TABLE Match1 (
    arena char(50) NOT NULL , 
    homeTeam char(50) PRIMARY KEY


);


CREATE TABLE Plays (
    matchID int not null PRIMARY KEY, 
    teamID int PRIMARY KEY
    FOREIGN KEY (matchID) REFERENCES Match,
    FOREIGN KEY (teamID) REFERENCES team

);

CREATE TABLE Referee (
    licenceNum int, 
    city char (50),
    firstName char(50) NOT NULL,
    lastName char(50) NOT NULL,
    
); 

CREATE TABLE Referees (
    licenceNum int, 
    matchID int NOT NULL
);

CREATE TABLE Stats_Has (
    amount int NOT NULL, 
    type char(50), 
    licenceNum int PRIMARY KEY 
); 

CREATE TABLE Penalty_Receives (
    timestamp int,
    type char(30) NOT NULL,
    card char (8),
    duration, int NOT NULL,
    licenseNum int PRIMARY KEY

);

CREATE TABLE Injury_Has (
    timeStamp int PRIMARY KEY, 
    type char(40) NOT NULL,
    licenceNum int PRIMARY KEY,

)