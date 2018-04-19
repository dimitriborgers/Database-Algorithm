# compiler and compiler flag variables
JCC = javac
JFLAGS = -g

#clear any default targets
.SUFFIXES: .java .class

#target entry
.java.class:
		$(JCC) $(JFLAGS) $*.java

#all working java source files
CLASSES = \
		Algorithm.java

#default make target entry
default: classes
classes: $(CLASSES:.java=.class)

#remove .class files for cleanup
clean:
		$(RM) *.class
