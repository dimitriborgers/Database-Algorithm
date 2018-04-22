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

The query.txt file includes selectivity for basic terms. Our program can process multiple sets of selectivities at a time by running through each set sequentially. We use this file as inputs for our main 

The config.txt file defines the values of estimated costs. We use this file 