# Celebrities

Find a celebrity in a population. 

A celebrity is a person who is known by every one, but doesn't know anybody.
The population is modeled by a Boolean matrix, where each position represents if a person (row) knows other person (column).
For example, the next matrix represents a population where person in position 4 (in a zero based count) is famous and person in position 0 knows everyone else.

true ,true ,true ,true ,true ,true ,true ,true ,true ,true 
false,true ,false,false,true ,false,false,false,false,false
false,false,true ,true ,true ,true ,false,false,true ,true 
true ,false,false,false,true ,false,false,false,false,false
false,false,false,false,true ,false,false,false,false,false
true ,true ,false,false,true ,false,false,false,false,true 
false,true ,false,false,true ,true ,true ,true ,true ,true 
false,true ,true ,true ,true ,false,true ,false,true ,true 
false,true ,false,false,true ,false,false,true ,false,true 
true ,false,true ,false,true ,true ,false,true ,true ,true 


The diagonal of the matrix is not really important, algorithm assumes every person knows himself, so positions [x][x] are taken as true 
(besides psicological and filosophical matters).

BUILD

Download or clone Git repo: https://github.com/pfrodriguezj/Celebrities.git
Create Project as Java Project in Eclipse from location where downloaded or cloned
Run Maven -> Update Project



EXECUTION

The program execution requires some parameters be passed as arguments.
First parameter can be only "CSV", "DB" or "RAND".
Second parameter depends on first
Parameters examples:
	* "CSV c:\\my_folder\\my_file.csv" (data are stored in a CSV file, whose path/name is the second parameter), 
	* "DB population" (data are stored in a in-memory database, second parameter is the table name )
	* "RAND" (data are generated randomly)
	
A CSV file called "celebrities.csv" can be found inside the project.

Run As -> Run Configurations
In arguments tab, write parameters, then Apply and Run

That's all I made in 4 hours. ;)
	
