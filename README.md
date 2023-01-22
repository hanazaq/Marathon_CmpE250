# Marathon_CmpE250
Graph, Shortest path, Dijkstra Algorithm

# About project
A philosopher once said, "Some flowers... Some flowers don’t grow in some soils. Such is
life." A few days ago, I received such an e-mail from rambo.ocon@boun.edu.tr. He wrote in
that e-mail, "As a traveling race man, I could not do anything when it came to coding. I’m
not good enough to code and need help from cmpe250 students." So he asked me to create
this project for you to help him. And now it’s time to help him!<br/>
Esteban Ocon, a famous Formula 1 pilot, also known as Rambo Ocon, decides to participate in the 43rd Istanbul marathon, even though he has no previous running experience.<br/>
Since he has a competitive personality, he wants to finish the race in first place. So he decides
to cheat and complete the course in the shortest way possible.
In the meantime, he sees that at some points on the path, the opposing team’s flags
fly around. He wants to take down all these flags before the race starts. Because he doesn’t
want to get tired before the race, he wants to do it in the shortest possible way. Also, this
will mean that the chances of him getting caught by the police will decrease; hence he will
make it to the race.<br/>
Before the race, Rambo Ocon takes the map of Istanbul in front of him, marks the
points where the flags are, and starts calculating the shortest route. Since there are many
streets in Istanbul, and he is bad at math, he cannot calculate it. Besides, he doesn’t know
how to code, which is the icing on the cake. It’s time to help Rambo!<br/>

# Clarification
Let’s say the race starts from Samandıra Tesisleri and finishes at Şükrü Saraçoğlu stadium. The flags are at 1st Istanbul Bridge and 2nd Istanbul Bridge.
Before the race, he must cut all the flags using the shortest possible path. There are no
restrictions on the order in which he cuts the flags. He can start from the 1st Bridge, then
go to the 2nd Bridge, or vice versa. <br/>
Thanks to public transportation, he can travel the same exact path as many times as he
wants after traveling a path between two flags once, without getting tired. (More detailed
explanation is given with the graphs in the More Examples section.)<br/>
During the race, he has to start from Samandıra Tesisleri and finish at Şükrü Saraçoğlu
by running the shortest route.<br\>
• He has to cut all the flags, starting from any point with a flag.<br/>
• He can cut the flags in any order that will lead to the minimum distance path.<br/>
• There can be many different paths between any two flags.<br/>
• He can travel using the same point more than once. However, if he uses the same
exact path between two flags, he can travel with public transportation (at no cost).<br/>
• Exact route: Let’s say s1 and s4 contain flags. If Rambo Ocon uses the s1-s2-s3-s4
path without passing through any other points, then he can travel this same path with
no cost (s1-s2-s3-s4 and s4-s3-s2-s1).<br/>
• He can use both directions of all point connections (e.g., graph is undirected).<br/>

# Input
• The first line represents (V) the number of points in Istanbul (e.g., the
number of all the nodes in the graph).
0 ≤ Ei ≤ 100 where Ei
is the number of edge count for Vi
.<br/>
• The second line represents the total number of flags (M).
2 ≤ V ∗ M ≤ 5.106 and 2 ≤ V ≤ 5.106<br/>
• The third line represents the name of the starting and ending points, respectively.<br/>
• The fourth line represents the name of the points where the flags are located.<br/>
• Each next line will give the name of a point in Istanbul and the names and
lengths of the points that can be reached from that point in pairs. For example
There are ways from s1 point to s2, s3, and s4 points of length 1, 2, and 3,
respectively.<br/>
Then input line will be s1 s2 1 s3 2 s4 3 If there is no way from s2,
input line for s2 will be s2
<br/>

# Output
• An integer, the length of the path during the race day. (If finishing the race
is not possible, then there must be something wrong with the map so print -1.)<br/>
• An integer, the length of the path to cut the flags. (If cutting all the flags is
not possible, then there must be something wrong with the map so print -1.)
