# Database-Algorithm

CS 4112

### Function
-----

Implementing a selection condition optimizer theorized by Kenneth A. Ross, a professor a Columbia University.

### Run
-----
    make
    ./stage2.sh query.txt config.txt

### Clean
-----
    make clean

### Documentation: 

Within the codebase we implement a selection condition optimizer theorized by Kenneth A. Ross, a professor a Columbia University. 

The query.txt file includes selectivity for basic terms. Our program can process multiple sets of selectivities at a time by running through each set sequentially. We use this file as inputs for our main method. 

The config.txt file defines the values of estimated costs, which affects our best path algorithm. 

Within our Main.java file we compute the best path for each set of selecitivities. We feed the query.txt and config.txt files into our Read class, parseing the variables so that they can used in our methods. 

In order to find the best path, we generate all possible combinations of the set of selectivities, and find the one with the minimum cost. We save the path in a BestCombo variable. 

The computations leverage our recurse method to determine the cost for a given combination. Here we implement the selection condition optimizer theorized by Kenneth A. Ross to exlore various branching strategies, and return the path with the minumum cost. 

For each set of selectivities coupled with the best paths, our Printer class prints the set of selectivities, the C code for the algorithm, and the cost for the best path. 