# cpsc304-database-project

This is the code repo for our cpsc304 database project.

## Setup

see https://www.students.cs.ubc.ca/~cs-304/resources/jdbc-oracle-resources/jdbc-java-setup.html.

For intellij, the data sources and drivers are under view -> tools windows -> database.
Then select add at the top left, choose data source -> oracle.

Set up the information under the general tab and the ssh/ssl tab as shown in the images in the link provided above.

Try running the main controller. If you are running into a IO network adapter issue, please open a new terminal and enter:

`ssh -l <CWLusername> -L localhost:1522:dbhost.students.cs.ubc.ca:1522 remote.students.cs.ubc.ca`

You should be set to go!

## TODO

- Project specs. Someone please upload our ER/DDL etc. documents into this repo for easier reference, thanks!
- Main controller. Bank, under controller/ will need to be expanded to operate on the various tables we have.
  - Table initialization
  - more?
- Handlers. We will need multiple new handler functions inside DatabaseConnectionHandler to achieve the various functionality inside the specs.
- Models. We will need multiple model classes to parse queries on our various tables.
- GUI. In addition to login, we will be needing a gui to interact with the tables.
- more?
