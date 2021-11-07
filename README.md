<br /><br />
<div align="center">
<h1 align="center">Communication Simulation</h1>
</div>

<br />
<br />
Today, surrounded by every aspect of technology, we regularly use digital tools and systems. One of the most popular systems that are used every day is the communication system. This project implements a simple version of a communication system. The following documentations briefly explains the content and duty  of the classes, input and output formats, how the project is designed, and the installation details.


# Classes

There are Customer.java, Operator.java, Bill.java, and Main.java classes. 
#### Customer.java:
- Each Customer is created with a name, an ID, an age. 
- Each Customer has a unique Bill and uses one of the Operations that have been created before. 
- When a Customer makes a call or sends a message or connects to the internet via their mobile ones, they are charged according to the price catalog of the Operation they use, and these payments are recorded in the Bill. 

#### Operator.java:
- Each Operator has a unique ID; talking, messaging, and network charges, and a discount rate that is only applied in the following cases: 
    1. When a Customer, age of whom is below 18 or over 65, makes a call, the discount of the Operator is applied. 
    2. When two Customers using the same Operator messages, the discount is applied. 

#### Bill.java:
- Each Bill has a limiting amount and a current debt. 
- For a Customer to call, message, or connect to the internet, they must not exceed their limits. 
- Check method of this class is important to check whether a Customer can afford a certain command, that is to say, if they exceed their limit while making this command.
- It is assumed that every Customer has enough money to pay their bills. Also, when they try to pay more than their current debt, only the current debt is erased, the debt does not go negative. 

#### Main.java:
- This class reads the commands from the input file and generates the corresponding output file. 
- Customer and Operator arrays in this class is majorly used to keep the records, according to the IDs given in the input.


# Input and Output Details
##### **In the input file;**
firstly and respectively, the number of Customers created [**C**], the numbers of Operators created [**O**], and the numbers of events that are going to be processed [**N**] are given. The next **N** line includes **N** commands, consisting of:
1. Creating a new Customer
2. Creating a new Operator
3. A customer can talk to another customer (only the caller pays)
4. A customer can send message to another customer (only the sender pays)
5. A customer can connect to the internet 
6. A customer can pay his/her bills
7. A customer can change his/her operator
8. A customer can change his/her Bill limit 

##### **As an Output;**
1. For each Operator, the amount of time they serviced for talking, number of messages sent via that Operator, and the amount of internet provided by it are printed.
2. For each Customer, how much money they spent to pay their bills, and the current debt are printed.
3. Then the name of the Customer that talks the most, and the amount of time in terms of minutes are provided.
4. Then the name of the Customer that sends messages the most, along with the number of messages are provided.
5. Lastly, the name of the Customer that connects to the internet the most, as well as the amount in terms of MBs are printed.

# Design
For me, the significant aspect of the project was to properly reflect the relation between a customer, the operations she uses, and her bill. Since the core of this implementations is for customers to make phone calls, send messages and connect to the internet, I constantly considered the price information of the Operator, and the current debt and upper bound the customer has, which are stored in her bill. When this three-legged connection is built properly, the rest is just filling in the blanks. 


# Installation
In this project, the input commmands are taken from an input file. To read from the file, I used [Scanner][Scanner] and [File][File] built in classes, and implemented the following code:
```java
File inFile = new File(args[0]);  // args[0] is the input file

Scanner reader;
	try {
		reader = new Scanner(inFile);
	} catch (FileNotFoundException e) {
		System.out.println("Cannot find input file");
		return;
	}
```
The output is also printed to an output file. For this, I used [PrintStream][PrintStream] and [File][File] built in classes, and implemented the following code:
```java
File outFile = new File(args[1]);  // args[1] is the output file
PrintStream outstream1;
	try {
		outstream1 = new PrintStream(outFile);
	}catch(FileNotFoundException e2) {
		e2.printStackTrace();
		reader.close();
	    return;
}
```
**Commands to run the project is:**

```sh
java -cp CommunicationSimulation/bin/runnable/Main.java CommunicationSimulation/src/input.txt CommunicationSimulation/src/output.txt
```
To see the code in the project [click][Main.java]


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
[File]: <https://docs.oracle.com/javase/7/docs/api/java/io/File.html>
[PrintStream]: <https://docs.oracle.com/javase/7/docs/api/java/io/PrintStream.html>
[Scanner]: <https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html>
[Main.java]: <https://github.com/ebrarkiziloglu/CommunicationSimulation/blob/master/src/runnable/Main.java>
