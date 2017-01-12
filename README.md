

If you run the prject it gives three options in the console. The first one only draws
the graph. The second one draws the graph with the shortest path of two points.
THe program also asks the points from the user. The third one draws the graph
and also highlights the minimum weight tree from any given point. This point
is also taken from the user which makes it user interactive.

The project contains a MainCanvas class. This contains the main method, and also a
method called Read, which reads the values from the file and divides the 
intersections as Vertices, and roads, into data structure.
The Graph class contains the InsertAdj method which basically inserts the roads
and makes an adjacency list. This same class also contains the Dijkstras method
and the MWT (Minimum Weight Tree) method. In doing both of the methods I took 
help from the Text book by Weiss in Chapter 9 from FIg 9.13 and Fig 9.15. 
After the implementation both methods had a run time of O(n^2).

The GNode class is used to save the distances, path, Vertex, and the ID for a point
The Vertex class takes in the lontitude and latitude as doubles.
The Edge class, takes in two Vertices representing a Road.
The MainCanvas class, contains the GUI. The paint component has the implementation
of the graphs being drawn. Drawing a graph takes O(n) runtime. The graph is drawn by 
scaling it down to the frame size. Therefore, the longitude and latitude values are
multiplied by those scalers, and the graph is drawn.
