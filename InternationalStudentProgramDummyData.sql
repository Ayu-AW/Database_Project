
USE `International_Student_Program`;


-- Data Insertion

INSERT INTO Person(PersonID, FullName, Address, PhoneNumber)
VALUES 	(10, "Brad Pitt", "9081 381st Ave NW", "818-9803"),
		(20, "George Clooney", "6615 99th Street", "772-9876"),
        (30, "Adam Sandler", NULL, "332-3098"),
        (40, "Jared Leto", "3786 Crooked Birch Lane", "288-3994"),
        (50, "Kevin Spacey", "9989 West Brook", NULL),
        (60, "Clint Eastwood", "7744 3rd Street", "1800-588-2300"),
        (70, "Robert De Niro", "7754 3rd Street", "177-4442");
        
INSERT INTO College(CollegeID, CollegeName, Address, DeanPID)
VALUES	(1, "Bellevue College", "3000 Landerholm Cir SE", 30),
		(2, "University of Washington", "1410 NE Campus Parkway", 10),
        (3, "University of Iowa", "200 Hawkins Drive", NULL),
        (4, "DeVry University", "3600 344th Way", NULL),
        (5, "DeVry University Online", NULL, NULL),
        (6, "Washington State University", "520 Carpenter Ave", 10),
        (7, "Another Random College", "111 Somewhere Lane", NULL);
        
INSERT INTO Regulation(RegulationID, RegulationName, RegulationDescription)
VALUES 	(1, "Credit Minimum Undergrad", "International undergraduate students must take at least 12 total credit hours per quarter."),
		(2, "Credit Minimum Grad", "International graduate students must take at least 9 total credit hours per quarter."),
        (3, "Valid I-20", "The I-20 form (if applicable) should not be expired."),
        (4, "Work Hour Maximum", "International students may work up to a maximum of 20 hours per week."),
        (5, "Work Location", "International students who work may only do so on campus, unless they have special USCIS permission."),
        (6, "Arbitrary Regulation", "International students must have formed out form I1845-b3 correctly."),
        (7, "Another Arbitrary Regulation", "Students must have completed course JL-245-K before being granted access to form I1845-b3 or pamphlet 0032-d.");
        
INSERT INTO AppliesTo (RegulationID, CollegeID)
VALUES	(1, 1),
		(2, 1),
        (3, 1),
        (3, 2),
        (4, 1),
        (5, 1),
        (6, 3),
        (7, 3),
        (5, 4);

INSERT INTO Department (DepartmentID, DepartmentName, Address, DepartmentHeadPID, CollegeID)
VALUES	(101, "Math", "3000 Landerholm Cir SW", 20, 1),
		(102, "Science", "3000 Landerholm Cir SSW", NULL, 1),
		(104, "English", "3000 Landerholm Cir NE", 20, 1),
        (301, "Math", "201 Hawkins Drive", NULL, 3),
        (202, "Computer Science", "1413 NE Campus Parkway", 10, 2),
        (507, "Basket Weaving", NULL, NULL, 5),
        (602, "Science", "520 Carpenter Ave West", 20, 6);
        
INSERT INTO Course (CourseID, CourseName, Credits, DepartmentID)
VALUES 	(1001, "Intro to Scuba Diving", 6, 102),
		(2331, "Intro to Databases", 5, 102),
        (4101, "English Literature I", 5, 104),
        (2552, "Intro To Experimental Quantum Computing", 2, 202),
        (5007, "Basket Weaving VII", 7, 507),
        (6109, "Abstract Unapplied Sciences II", 5, 602),
        (1992, "Calculus XXIV", 5, 101);
        
INSERT INTO Degree (DegreeName, DegreeDescription)
VALUES  ("Transfer", NULL),
		("Associate Degree", "Two Years"),
        ("Bachelor Degree", "Four Years" ),
        ("Graduate Degree", NULL),
        ("Master Degree", "Six Years"),
        ("Doctoral Degree", "Eight Years"),
        ("Undergraduate Degree", NULL);
        
INSERT INTO Student (PersonID, SSN, Birthday, Gender, Nationality, StudentType, EnrollDate, CollegeAttendingID, MajorDepartmentID, DegreePursuing)
VALUES  (10, 123, '1993-12-03', 'M', "China", 'graduate', '2015-11-04', "1", "101", "Bachelor Degree"),
		(20, 132, '1997-05-16', 'F', "Nigeria", 'graduate', '2018-01-06', "1", "104", "Associate Degree"),
        (30, 143, '2008-10-06', 'M', "India", 'undergraduate', '2018-06-17', "1", "104", "Doctoral Degree"),
        (40, 265, '2000-01-15', 'F',"Tunisia", 'graduate', '2012-09-30', "2", "202", "Bachelor Degree"),
        (50, 347, '2005-11-14', 'M', "Russia", 'undergraduate', '2020-03-20', "2", "202", "Master Degree"),
        (60, 289, '1999-02-24','F', "France", 'undergraduate', '2011-04-20', "6", NULL , NULL),
        (70, 678, '2014-08-15', 'M', "Canada", 'undergraduate', '2009-09-09', "5", "507", "Doctoral Degree");
        
INSERT INTO TakesCourse (StudentPID, StudentSSN, CourseID)
VALUES	(10, 123, 1001),
		(20, 132, 2331),
        (10, 123, 2331),
        (30, 143, 4101),
        (10, 123, 4101),
        (30, 143, 1001),
        (40, 265, 2552);

INSERT INTO EducationalHistory (StudentPID, StudentSSn, SchoolName, DegreeEarned, GPA)
VALUES
       (10, 123, "Bellevue College", "Associate Degree", 4.0),
	   (20, 132, "University of Washington", "Master Degree", 3.5),
       (30, 143, "University of Iowa", "Master degree", 2.8),
       (40, 265, "DeVry University", "Associate Degree", 3.8),
       (50, 347, "DeVry University Online", "Bachelor Degree", 3.4),
       (60, 289, "Washington State University", "Undergraduate Degree", 2.6),
       (70, 678, "Another Random College", "Master Degree", 3.0);

INSERT INTO Job (JobName, JobType, HoursPerWeek, EmployerPID)
VALUES  ("Accountant", "Full time", 40, 10),
        ("Software developer", "Part time", 32, 20),
        ("Data scientist", "Full time", 40, 30),
        ("Web Developer", "Contractor", 40, 40),
        ("SQL Developer", "Intern", 15, 50),
        ("Database Admin", "Part time", 32, 60),
        ("Manager", "Full time", 40, 70);
        
INSERT INTO WorksAt (JobName, StudentPID, StudentSSN)
VALUES  ("Software developer", 20, 132),
        ("Software developer", 30, 143),
        ("Accountant", 60, 289),
        ("Web Developer", 50, 347),
        ("Data scientist", 10, 123),
        ("Manager", 10, 123),
        ("Database Admin", 40, 265);
        
INSERT INTO Country (CountryName, CountryLanguage, CapitalCity, Ethnicity)
VALUES   
        ("China", "Mandarin", "Beijing", "Tibetan"),
        ("France", "French", "Paris", "White"),
        ("Canada", "English", "Ottawa", "White"),
        ("Nigeria", "English", "Abuja", " Black African"),
        ("Russia", "Russian", "Moscow", "White"),
        ("Tunisia", "Arabic", "Tunis", "Bebers"),
		("India", "Hindi", "New Delhi", "Rajput");

INSERT INTO CitizenOf (CountryName, StudentPID, StudentSSN)
VALUES  ("China", 10, 123),
        ("France", 20, 132),
        ("Canada", 30, 143),
        ("Nigeria", 40, 265),
        ("Russia", 50, 347),
        ("Tunisia", 60, 289),
        ("India", 70, 678);

INSERT INTO Visas (VisaID, StudentPID, StudentSSN)
VALUES  (12345, 10, 123),
        (22354, 20, 132),
        (32534, 30, 143),
        (42543, 40, 265),
        (55243, 50, 347),
        (55423, 60, 289),
        (65432, 70, 678);
        
INSERT INTO UngraduatedVisas (VisaID, VisaType)
VALUES  (12345, 'F-1'),
        (22354, null),
        (32534, 'F-1'),
        (42543, null),
        (55243, 'J-1'),
        (55423, null),
        (65432, 'J-1');
INSERT INTO GraduatedVisas (VisaID, VisaType)
VALUES  (12345, null),
        (22354, 'H-1'),
        (32534, null),
        (42543, 'H-1'),
        (55243, null),
        (55423, 'OPT'),
        (65432, null);
        
        
-- Export tables to CSV file; select the save icon next to export/import above the output displayed

SELECT * FROM Person;
SELECT * FROM College;
SELECT * FROM Regulation;
SELECT * FROM AppliesTo;
SELECT * FROM Department;
SELECT * FROM Course;
SELECT * FROM Job;
SELECT * FROM WorksAt;
SELECT * FROM Visas;
SELECT * FROM UngraduatedVisas;
SELECT * FROM GraduatedVisas;
SELECT * FROM Country;
SELECT * FROM CitizenOf;
SELECT * FROM Degree;
SELECT * FROM Student;
SELECT * FROM EducationalHistory;