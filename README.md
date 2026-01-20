AthletiCore – Smart Athlete Coaching & Session Management System
AthletiCore is a simplified training center management system developed to streamline how athletes are registered, coached, and assigned training sessions. The primary goal is to automate core administrative tasks so that athletes can focus more on performance while the system handles operational details efficiently.
 Key Problem
Traditional sports academies often manage athlete data manually. This leads to:
• delays in athlete enrollment
• improper or mismatched coach assignment
• unorganized session scheduling
• lack of unique identification and tracking of athlete progress
AthletiCore solves these challenges through a structured software solution built using core Java principles.
________________________________________
 Functional Overview
AthletiCore performs three essential operations automatically:
1. Unique Athlete ID Generation
When registering a new athlete, a globally unique ID is auto-generated using an internal counter.
This eliminates errors and duplicate entries.
2. Smart Coach Assignment
Every athlete is assigned a coach based on the sport they choose.
Example:
• Football → Football Coach
• Swimming → Swimming Coach
• Badminton → Badminton Coach
This ensures that training is guided by domain-specialist coaches.
3. Automated Training Session Allocation
Once an athlete is mapped to a coach, a training session ID is also generated automatically and linked to that athlete.
This keeps session tracking smooth and structured.
________________________________________
 Technical Elements
The solution is built entirely using core Java, focusing on Object-Oriented Programming fundamentals.
Concept Used	How it is Applied
Classes & Objects	To represent Athlete, Coaches, Session generators, etc.
Encapsulation	Attributes are private; accessed through methods
Static variables & methods	For ID generation based on counters
Constructors	For dynamic athlete object creation
________________________________________
 Architecture Summary
Central Entities:
• Athlete – holds identity and sport information
• CoachAssigner – assigns coach based on sport
• SessionGenerator – creates unique session IDs
• AthleteIDGenerator – handles athlete identity tracking
The system automates:
• Athlete registration
• Coach mapping
• Session allocation
Everything becomes traceable, structured, and organized.
________________________________________
 Outcomes & Benefits
1.	Zero manual errors in athlete registration
2.	Perfect matching of coach expertise to athlete sport
3.	Organized session planning
4.	Scalable base for future extensions like:
• Performance tracking
• Attendance system
• Session scheduling calendar
• Payment & membership module
• Database integration
________________________________________
 Conclusion
AthletiCore demonstrates how software automation can enhance the management of training academies. It showcases strong object-oriented design principles while solving a real-world operational challenge in sports coaching environments.




CLASS UML DIAGRAM:

AthleteIDGenerator
+ -counter : int (static)
+ +generateID() : String (static synchronized)  
	


SessionGenerator

+ -counter : int (static)

+ +generateID() : String (static synchronized)


SessionManager

+ -sessions : ArrayList<TrainingSession>

+ +addSession(session:TrainingSession) : void   
+ +getAllSessions() : List<TrainingSession>           
+ +searchByAthleteId(id:String) : List<TrainingSession>
+ +sortBySport() : void                               
+ +sortBySessionId() : void                           
+ +totalSessions() : int
	

Athlete
+ -athleteId : String (final)                         
+ -name : String (final)                          
- -age : int (final)
- -sport : String (final)
+ +Athlete(name:int, age:int, sport:String)           
 + +Athlete(id:String, name:String, age:int, sport:String)|
+ +getAthleteId() : String                            
 + +getName() : String                                 
 + +getAge() : int                                       
 + +getSport() : String                                
 + +toString() : String    
	




SessionNotifier
- -sessionId : String
+ +run() : void                                       
 + +start() : void (inherited from Thread)


Coach
- -coachId : String (final)
 - -name : String (final)  
 - -speciality : String (final)
+ +getCoachId() : String                               
+ +getName() : String                                  
+ +getSpeciality() : String                           
+ +toString() : String


Main
(contains menu & user input logic)
+ +main(args:String[]) : void



DataStore
- -coaches : Coach[] (static)
+ +getCoachBySport(sport:String) : Coach              
+ +getAllCoaches() : Coach[]



BookingException
(inherits Exception)
+ +BookingException(msg:String)


TrainingSession
- -sessionId : String                                  
- -sport : String                                     
- -athlete : Athlete                                  
- -coach : Coach
+ +createSession(Athlete) : void                       
+ +getSessionId() : String                            
+ +getSport() : String                                
 + +getAthlete() : Athlete                             
 + +getCoach() : Coach                                 
 + +getSessionDetails() : String                        
 + +toString() : String







![alt text](image.png)