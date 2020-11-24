CREATE TABLE FootballPlayer_PlaysFor (
    jerseyNum int NOT NUll,
    firstName Char (50) NOT NUll, 
    lastName Char(30) NOT NUll,
    nationality char(50) NOT NULL,
    dateOfBirth char(8) NOT NULL, 

    goalsConceded int, 
    goalsSaves int,
    bigChances int, 
    keypasses int, 
    interceptions int, 
    recoveries int, 
    successfulTackles int, 
    blocks int, 
    clearances int, 

    licenceNum int PRIMARY KEY,

    contractStart char(8), 
    contractEnd char (8), 
    teamID int
); 

CREATE TABLE Team_HasManages(
    phoneNum int,
    website char(100),
    teamName char(40) NOT NULL, 
    teamID char(50) PRIMARY KEY,

    since char (8) NOT NULL, 
    managername char(50) NOT NUll, 

    contractStart char(8) NOT NULL, 
    contractEnd char(8) NOT NULL, 
    licenseNum int NOT NULL 
    
); 

CREATE TABLE Arena( 
    arenaName char(50) PRIMARY KEY, 
    arenaAddressy Char(20) NOT NULL,
    surfaceType char(50) NOT NULL, 
    capacity int NOT NULL,
    UNIQUE (arenaAddressy)
    
); 

CREATE TABLE Arenaaddy(
    arenaCity Char(20) NOT NULL,
    arena1Address Char(20) NOT NULL PRIMARY KEY,
    FOREIGN KEY (arena1Address) REFERENCES Arena(arenaAddressy)
);



INSERT INTO Team_HasManages(phoneNum, website, teamName, teamID, since, managername, contractStart, contractEnd, licenseNum)
VALUES (32,'hgfd','ghj','6543','ghj','dfghj','fghj','cvbn', 132);

INSERT INTO Team_HasManages(phoneNum, website, teamName, teamID, since, managername, contractStart, contractEnd, licenseNum)
VALUES (654,'hgf','cvbh', 7654,'gfds','wer','ghfd','gfds', 232);

INSERT INTO Team_HasManages(phoneNum, website, teamName, teamID, since, managername, contractStart, contractEnd, licenseNum)
VALUES(8765,'hgfd','gfds', 98765, 'ytre','jhgfds','nbvcx','ytrew', 332);




-- INSERT INTO Team_HasManages (phoneNum, website, teamName, teamID, since, managername, contractStart, contractEnd, licenseNum)
-- VALUES (654,"hgf","cvbh", 7654,"gfds","wer","ghfd","gfds", 232);
--
-- INSERT INTO Team_HasManages (phoneNum, website, teamName, teamID, since, managername, contractStart, contractEnd, licenseNum)
-- VALUES(8765,"hgfd","gfds", 98765, "ytre","jhgfds","nbvcx","ytrew", 332);

-- , ,
-- (8765,"hgfd","gfds", 98765, "ytre","jhgfds","nbvcx","ytrew",3);

-- FOREIGN KEY (arena1Address) REFERENCES Arena(arenaAddress)
-- CREATE TABLE Coach (
--     nationality Char(20) NOT NULL,
--     firstName Char(50) NOT NULL, 
--     lastName Char(50) NOT NULL,
--     licenceNum int PRIMARY KEY
-- ); 

-- CREATE TABLE Doctor_Treat (
--     firstname Char(50) NOT NULL, 
--     lastName char (50) NOT NULL, 
--     fieldOfPractice char(50) NOT NULL, 
--     licenceNum int PRIMARY KEY, 
--     startDate char(8), 
--     endDate char(8)
--     teamID int 
-- ); 

-- CREATE TABLE match(
--     homeTeam char(50) not null, 
--     awayTeam char(50) not null, 
--     score char(8) not null, 
--     date char(10) not null, 
--     matchID int PRIMARY KEY 
-- ); 

-- CREATE TABLE match2 (
--     arena char(50) NOT NULL , 
--     homeTeam char(50) PRIMARY KEY


-- );


-- CREATE TABLE plays (
--     matchID int not null PRIMARY KEY, 
--     teamID int PRIMARY KEY
--     FOREIGN KEY (matchID) REFERENCES Match,
--     FOREIGN KEY (teamID) REFERENCES team

-- );

-- CREATE TABLE Referee (
--     licenceNum int, 
--     city char (50),
--     firstName Char(50) NOT NULL, 
--     lastName Char(50) NOT NULL,
    
-- ); 

-- CREATE TABLE referees (
--     licenceNum int, 
--     matchID int NOT NULL
-- );

-- CREATE TABLE Stats_Has (
--     amount int NOT NULL, 
--     type char(50), 
--     licenceNum int PRIMARY KEY 
-- ); 

-- CREATE TABLE Penalty_Receives (
--     Timestamp int, 
--     Type char(30) NOT NULL,
--     Card char (8),
--     Duration, int not null, 
--     licenceNum int PRIMARY KEY

-- );

-- CREATE TABLE Injury_Has (
--     timeStamp int PRIMARY KEY, 
--     type char(40) NOT NULL,
--     licenceNum int PRIMARY KEY,

-- );