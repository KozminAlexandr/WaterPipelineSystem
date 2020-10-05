This application calculates minimum route length between two points

The libraries used:
  1. h2-1.4.200

Requirements:
  JDK version 11+

Application utilize leverage the CLI (command-line interface).

Application takes for input two files:
  1. A CSV file that describes the water pipeline system. This file looks as follows:
    idX;idY;LENGTH
    1;2;10
    2;3;20
    3;4;30
    3;5;15
    6;7;20
    ...
  2. A CSV file with a set of points, between which you need to find the route. This file looks as follows:
    idA;idB
    2;5
    2;6
    6;7
    ...
  These files must have a header!

Appplication generate output CSV file as follows:
  ROUTE EXISTS;MIN LENGTH
  TRUE;35
  FALSE;
  TRUE;20

Run:
After starting the application, you need enter paths to input and output files (CLI will tell you where to enter them)

Functional:
1. Application upload into an H2 database the file that describes the water pipeline system (file with pipelines).
2. In application uses Dijkstra’s algorithm:
  - pipeline system initialize with a graph
  - for every start point calculates lengths to anothers points.
  - if the route between two points does not exist -> in output file writes FALSE
  - if the route exist -> in output file writes TRUE and calculated minimum length
  
Result:
The result is written to a CSV file with the directory and name that you enter into the CLI. 

Enjoy ;)
