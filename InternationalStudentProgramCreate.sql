-- Project Task 2
-- Group 8
-- CS331
-- By Andrew Middleton, Ayalew Wolde, and Gihane Ndjeuha

DROP SCHEMA IF EXISTS International_Student_Program;
CREATE SCHEMA International_Student_Program;
USE International_Student_Program;

CREATE TABLE IF NOT EXISTS Person (
    PersonID INT UNSIGNED NOT NULL PRIMARY KEY,
    FullName VARCHAR(60),
    Address VARCHAR(60),
    PhoneNumber VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS College (
    CollegeID INT UNSIGNED NOT NULL,
    CollegeName VARCHAR(45) NOT NULL,
    Address VARCHAR(60),
    DeanPID INT UNSIGNED,
    PRIMARY KEY (CollegeID),
    CONSTRAINT C_Dean_FK FOREIGN KEY (DeanPID)
        REFERENCES Person (PersonID)
        ON DELETE SET NULL ON UPDATE CASCADE
);
  
CREATE TABLE IF NOT EXISTS Regulation (
    RegulationID SMALLINT UNSIGNED NOT NULL,
    RegulationName VARCHAR(45) NOT NULL,
    RegulationDescription LONGTEXT,
    PRIMARY KEY (RegulationID)
);

CREATE TABLE IF NOT EXISTS AppliesTo (
    RegulationID SMALLINT UNSIGNED NOT NULL,
    CollegeID INT UNSIGNED NOT NULL,
    PRIMARY KEY (RegulationID , CollegeID),
    FOREIGN KEY (CollegeID)
        REFERENCES College (CollegeID)
        ON DELETE CASCADE,
    FOREIGN KEY (RegulationID)
        REFERENCES Regulation (RegulationID)
        ON DELETE CASCADE
);
  
CREATE TABLE IF NOT EXISTS Department (
    DepartmentID SMALLINT UNSIGNED NOT NULL,
    DepartmentName VARCHAR(45) NOT NULL,
    Address VARCHAR(60),
    DepartmentHeadPID INT UNSIGNED,
    CollegeID INT UNSIGNED NOT NULL,
    PRIMARY KEY (DepartmentID),
    FOREIGN KEY (CollegeID)
        REFERENCES College (CollegeID)
        ON DELETE CASCADE,
    CONSTRAINT D_Head_FK FOREIGN KEY (DepartmentHeadPID)
        REFERENCES Person (PersonID)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Course (
    CourseID SMALLINT UNSIGNED NOT NULL,
    CourseName VARCHAR(45) NOT NULL,
    Credits TINYINT UNSIGNED NOT NULL,
    DepartmentID SMALLINT UNSIGNED NOT NULL,
    PRIMARY KEY (CourseID),
    FOREIGN KEY (DepartmentID)
        REFERENCES Department (DepartmentID)
        ON DELETE CASCADE
);
  
CREATE TABLE IF NOT EXISTS Degree (
    DegreeName VARCHAR(60) NOT NULL PRIMARY KEY,
    DegreeDescription LONGTEXT
);

CREATE TABLE IF NOT EXISTS Student (
    PersonID INT UNSIGNED NOT NULL,
    SSN INT UNSIGNED NOT NULL,
    Birthday DATE,
    Gender ENUM('M', 'F', 'O'),
    Nationality VARCHAR(45),
    StudentType ENUM('graduate','undergraduate'),
    EnrollDate DATE,
    CollegeAttendingID INT UNSIGNED NOT NULL,
    MajorDepartmentID SMALLINT UNSIGNED,
    DegreePursuing VARCHAR(60),
    PRIMARY KEY (PersonID , SSN),
    CONSTRAINT S_Person_FK FOREIGN KEY (PersonID)
        REFERENCES Person (PersonID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT S_CollegeAttending_FK FOREIGN KEY (CollegeAttendingID)
        REFERENCES College (CollegeID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT S_MajorDepartment_FK FOREIGN KEY (MajorDepartmentID)
        REFERENCES Department (DepartmentID)
        ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT S_DegreePursuingFK FOREIGN KEY (DegreePursuing)
        REFERENCES Degree (DegreeName)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS TakesCourse (
	StudentPID INT UNSIGNED NOT NULL,
    StudentSSN INT UNSIGNED NOT NULL,
    CourseID SMALLINT UNSIGNED NOT NULL,
    PRIMARY KEY (StudentPID, StudentSSN, CourseID),
    CONSTRAINT S_TakesCourse_FK FOREIGN KEY (StudentPID, StudentSSN)
		REFERENCES Student(PersonID, SSN)
        ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT C_TakesCourse_FK FOREIGN KEY (CourseID)
		REFERENCES Course(CourseID)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS EducationalHistory (
    StudentPID INT UNSIGNED NOT NULL,
    StudentSSN INT UNSIGNED NOT NULL,
    SchoolName VARCHAR(45) NOT NULL,
    DegreeEarned VARCHAR(60),
    GPA FLOAT,
    PRIMARY KEY (StudentPID , StudentSSN , SchoolName),
    CONSTRAINT EH_Student_FK FOREIGN KEY (StudentPID , StudentSSN)
        REFERENCES Student (PersonID , SSN)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT Degree_FK FOREIGN KEY (DegreeEarned)
        REFERENCES Degree (DegreeName)
);

CREATE TABLE IF NOT EXISTS Job (
    JobName VARCHAR(45) NOT NULL PRIMARY KEY,
    JobType VARCHAR(10),
    HoursPerWeek SMALLINT UNSIGNED,
    EmployerPID INT UNSIGNED,
    CONSTRAINT J_Employer_FK FOREIGN KEY (EmployerPID)
        REFERENCES Person (PersonID)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS WorksAt (
    JobName VARCHAR(45) NOT NULL,
    StudentPID INT UNSIGNED NOT NULL,
    StudentSSN INT UNSIGNED NOT NULL,
    CONSTRAINT WA_Job_FK FOREIGN KEY (JobName)
        REFERENCES Job (JobName)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT WA_Student_FK FOREIGN KEY (StudentPID , StudentSSN)
        REFERENCES Student (PersonID , SSN)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Country (
    CountryName VARCHAR(45) NOT NULL,
    CountryLanguage VARCHAR(25),
    CapitalCity VARCHAR(45),
    Ethnicity VARCHAR(25),
    PRIMARY KEY (CountryName)
);

CREATE TABLE CitizenOf (
    CountryName VARCHAR(45) NOT NULL,
    StudentPID INT UNSIGNED NOT NULL,
    StudentSSN INT UNSIGNED NOT NULL,
    PRIMARY KEY (CountryName , StudentPID , StudentSSN),
    FOREIGN KEY (CountryName)
        REFERENCES Country (CountryName)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (StudentPID , StudentSSN)
        REFERENCES Student (PersonID , SSN)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Visas (
    VisaID INT UNSIGNED NOT NULL,
    StudentPID INT UNSIGNED,
    StudentSSN INT UNSIGNED,
    PRIMARY KEY (VisaID),
    FOREIGN KEY (StudentPID , StudentSSN)
        REFERENCES Student (PersonID , SSN)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE UngraduatedVisas (
    VisaID INT UNSIGNED NOT NULL,
    VisaType ENUM('F-1', 'J-1'),
    PRIMARY KEY (VisaID),
    FOREIGN KEY (VisaID)
        REFERENCES Visas (VisaID)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE GraduatedVisas (
    VisaID INT UNSIGNED NOT NULL,
    VisaType ENUM('H-1', 'OPT'),
    PRIMARY KEY (VisaID),
    FOREIGN KEY (VisaID)
        REFERENCES Visas (VisaID)
        ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS GraduatedVisas;
DROP TABLE IF EXISTS UngraduatedVisas;
DROP TABLE IF EXISTS Visas;
DROP TABLE IF EXISTS CitizenOf;
DROP TABLE IF EXISTS Country;
DROP TABLE IF EXISTS WorksAt;
DROP TABLE IF EXISTS Job;
DROP TABLE IF EXISTS EducationalHistory;
DROP TABLE IF EXISTS TakesCourse;
DROP TABLE IF EXISTS Student; 
DROP TABLE IF EXISTS Degree;
DROP TABLE IF EXISTS Course;
DROP TABLE IF EXISTS Department;
DROP TABLE IF EXISTS AppliesTo;
DROP TABLE IF EXISTS Regulation;
DROP TABLE IF EXISTS College;
DROP TABLE IF EXISTS Person;

DROP SCHEMA IF EXISTS International_Student_Program;
