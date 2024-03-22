Algorithm to find a stable matching, meaning that there does not exist two studen-company-pairs (s1,c1),(s2,c2) such taht c2 is higher ranked on s1's preference list than c1 and simultaneously s1 is higher ranked on c2's preference list than s2 (thus that both s1 and c2 would prefer to leave their current matching and create new pair). Based on given data lists predefined.

The input files are quite dependent on only sending in integers, and also the order of how they are sent in. The first interger on line 1 tells how many pairs there will be, so for instance 10 pairs would be 20 lines of 10 me nand 10 women. The lines after will first have one integer showing which index/number the person has and then have the number of integers of the opposite gender to show their preferences. The first occuring index/number will always be the woman and the second occurence will be the man.

Run the program by either of the following lines, depending if you want extra output or not:
java StableMarriage.java < data-input/input1.in
java StableMarriage.java < data-input/input1.in output


