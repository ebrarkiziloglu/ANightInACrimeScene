<br /><br />
<div align="center">
<h1 align="center">Port Simulation<img src = "https://www.carlswebgraphics.com/boat/three-sail-boat-2018.gif" width = 50px>
</h1>
</div>


<br />
<br />

This project implements a simulation of a Port Management System. 
In such implementation, Ships carry some Containers -according to their limits- and sail between the Ports. 
The following documentation briefly describes the content of the project and implementation details.

## Classes

There are four main packages; containers, ports, ships, main, and interfaces.
### container package contains:
  1. **Container.java** -parent class- -implements **Comparable** interface-
  2. **BasicContainer.java** -extends Container.java-
  3. **HeavyContainer.java** -extends Container.java-
  4. **RefrigeratedContainer.java** -extends HeavyContainer.java-
  5. **LiquidContainer.java** -extends HeavyContainer.java-
  
  - Containers are handled differently, according to their types.
  - BasicContainers have weights less than or equal to 3000. 
  - Heavy Containers, on the other hand, have weights of more than 3000. 
  - Containers in a Port can be loaded to a Ship that is currently on this Port. 
  - Conversely, they can be unloaded from such a Ship to the Port. 
  To sail from one Port to another, Ships need a certain amount of fuel. 

### ports package contains **Port.java**:
  - **Port.java** implements **IPort.java** interface, which is in the interfaces package.
  - Methods of **Port.java** include but are not limited to loading or unloading Containers, and calculating the distance between the Ports. 
  - To sail from one Port to another, Ships need a certain amount of fuel. 
  
### ships package containes **Ship.java**:
  - **Ship.java** implements **IShip.java** interface from the interfaces package.
  - **Ship.java** includes some checking methods to determine whether a certain Container can be loaded to the ship, or to determine whether the Ship's fuel is enough to sail to a certain Port.
    - Fuel consumption depends on the current load of the Ship. 
    - Different types of Containers have different fuel consumption rates. Fuel consumption is as follows:
      - BasicContainer:  2.50per unit of weight
      - HeavyContainer: 3.00 per unit of weight
      - RefrigeratedContainer: 5.00 per unit of weight
      - LiquidContainer:4.00 per unit of weight 
  
### interfaces package containes **IShip.java** and **IPort.java**.

### main package containes **Main.java**:

  * **Main.java** class just reads the input from the file that consists of several commands for the relative operations, and prints the necessary output to a file.
  * Input file starts with an integer, implying the number of the following commands. The possible commands include:
      * Creating a Container
      * Creating a Ship
      * Creating a Port
      * Loading a Container to a Ship (If a Ship is currently in the Port of the Container, and if the capacity of the Ship is enough to load this Container)
      * Unloading a Container from a Ship (If this Ship even contains this Container)
      * Ship sails to another Port (If Ship has the necessary fuel to sail)
      * Ship is refueled

  * After all of the commands are processed, Main.java prints the Ports in the ascending order of their IDs, 
  and the Containers in the Port, along with the Ships sailed to the Port. 


## Design

This project is based on inheritance and encapsulation aspects of Java Object-Oriented Programming. 
Therefore, for me, the most significant part of the implementation was to first provide the necessary accessibility and visibility, 
then to build class hierarchy properly. For this, I used the hierarchy diagrams I had drawn along the way. 


<div>
<h2>Installation <img src = "https://media2.giphy.com/media/QssGEmpkyEOhBCb7e1/giphy.gif?cid=ecf05e47a0n3gi1bfqntqmob8g9aid1oyj2wr3ds3mg700bl&rid=giphy.gif" width = 28px></h2>
</div>

In this project, **Main.java** class takes the input commmands from an input file given. To read from the file, the class uses [Scanner][Scanner] and [File][File] built in classes, and implements the following code:
```java
Scanner in = new Scanner(new File(args[0]));
```
The output is also printed to an output file. For this, I used [PrintStream][PrintStream] and [File][File] built in classes, and implemented the following code:
```java
PrintStream out = new PrintStream(new File(args[1]));
```
**Commands to run the project is:**

```sh
java -cp PortSimulation/bin/main/Main.java PortSimulation/src/input0.txt PortSimulation/src/output.txt
```

<div>
<p class="has-line-data" data-line-start="87" data-line-end="88">To see the code in the project click:
<a href= https://github.com/ebrarkiziloglu/PortSimulation/blob/master/src/main/Main.java > <img width ='32px' src ='https://raw.githubusercontent.com/rahulbanerjee26/githubAboutMeGenerator/main/icons/java.svg'> </a>
</p>
</div>


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
[File]: <https://docs.oracle.com/javase/7/docs/api/java/io/File.html>
[PrintStream]: <https://docs.oracle.com/javase/7/docs/api/java/io/PrintStream.html>
[Scanner]: <https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html>
[Main.java]: <https://github.com/ebrarkiziloglu/PortSimulation/blob/master/src/main/Main.java>
