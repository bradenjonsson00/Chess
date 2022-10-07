# Java Chess

## Introduction

In this repository you will find the code to a Chess game, written in the Java programming language.  
This was an excercise to familiarize myself with creating a GUI in Java, using the packages AWT and Swing.  
Thanks to Javatpoint tutorials.  
https://www.javatpoint.com/java-awt  
https://www.javatpoint.com/java-swing  


<img src="https://user-images.githubusercontent.com/61176938/194627841-3259fe80-e078-41ac-9115-ea8906557f99.png" width="500" height="375">


## Running the Program in 7 Easy Steps

1. [Install the JRE](https://docs.oracle.com/goldengate/1212/gg-winux/GDRAD/java.htm#BGBFJHAB)
2. Download the chess folder
3. Open it in file explorer
4. Unzip it
5. Open the unzipped folder in terminal
6. Compile: &nbsp; javac *.java  
7. Run: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; java Chess.java

## Movement

Moving one space north is xInt-1.  
Moving one space south is xInt+1.  
Moving one space east is yInt+1.  
Moving one space west is xInt-1.  

Thus the board matrix can be represented like this:  
00 01 02 03 04 05 06 07  
10 11 12 13 14 15 16 17  
20 21 22 23 24 25 26 27  
30 31 32 33 34 35 36 37  
40 41 42 43 44 45 46 47  
50 51 52 53 54 55 56 57  
60 61 62 63 64 65 66 67  
70 71 72 73 74 75 76 77  
