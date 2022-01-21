# Cell Society Design Lab Discussion
#### Names and NetIDs
Cynthia France: cf219
Diane Kim: dyk3
Jose Santillan: jas299


### Simulations

* Commonalities
  * they all use cells/grids and have calculations involving their cell neighbors 
* Variations
  * Cell society cells have 2 states -- dead and alive, while fire has 2 states: empty, tree, and
    fire/burning
  


### Discussion Questions

* How does a Cell know what rules to apply for its simulation?
  * This will probably be dictated in the XML file. 
* How does a Cell know about its neighbors?
  * pass in the List of List of Cells
* How can a Cell update itself without affecting its neighbors update?
  * Perhaps having an in between variable (ie nextState) with two methods that involve its state:
    1. calcNextState: decide the cell's next state and put it into the in-between variable
    2. changeState: set the cell's state equal to the in-between variable
  * that way each cell can decide its state independently
* What behaviors does the Grid itself have?
  * It has a set size and must be able to tell the cells to update themselves
* How can a Grid update all the Cells it contains?
  * Loop through all the cells and tell them to update
* What information about a simulation needs to be in the configuration file?
  * Simulation type
  * Grid size
  * title
  * author
  * description of simulation
  * initial config
  * parameter values
* How is configuration information used to set up a simulation?
  * these values would be read and fed into the code that takes care of this simulation
    * ie. read the first line -> figure out which simulation it is -> call the correct function/class
      to take care of the specific simulation
* How is the graphical view of the simulation updated after all the cells have been updated?
  * The cell would update its own graphical view when the function to update the cell is called.


### Alternate Designs

#### Design Idea #1

* Data Structure #1 and File Format #1

* Data Structure #2 and File Format #2


#### Design Idea #2

* Data Structure #1 and File Format #1

* Data Structure #2 and File Format #2



### High Level Design Goals



### CRC Card Classes

This class's purpose or value is to represent a customer's order:
![Order Class CRC Card](images/order_crc_card.png "Order Class")


This class's purpose or value is to represent a customer's order:

|Order| |
|---|---|
|boolean isInStock(OrderLine)         |OrderLine|
|double getTotalPrice(OrderLine)      |Customer|
|boolean isValidPayment (Customer)    | |
|void deliverTo (OrderLine, Customer) | |


This class's purpose or value is to represent a customer's order:
```java
public class Order {
     // returns whether or not the given items are available to order
     public boolean isInStock (OrderLine items)
     // sums the price of all the given items
     public double getTotalPrice (OrderLine items)
     // returns whether or not the customer's payment is valid
     public boolean isValidPayment (Customer customer)
     // dispatches the items to be ordered to the customer's selected address
     public void deliverTo (OrderLine items, Customer customer)
 }
 ```


This class's purpose or value is to manage something:
```java
public class Something {
     // sums the numbers in the given data
     public int getTotal (Collection<Integer> data)
	 // creates an order from the given data
     public Order makeOrder (String structuredData)
 }
```


### Use Cases

* Apply the rules to a middle cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all its neighbors)
```java
 Something thing = new Something();
 Order o = thing.makeOrder("coffee,large,black");
 o.update(13);
```

* Apply the rules to an edge cell: set the next state of a cell to live by counting its number of neighbors using the Game of Life rules for a cell on the edge (i.e., with some of its neighbors missing)
```java
 Something thing = new Something();
 Order o = thing.makeOrder("coffee,large,black");
 o.update(13);
```

* Move to the next generation: update all cells in a simulation from their current state to their next state and display the result graphically
```java
 Something thing = new Something();
 Order o = thing.makeOrder("coffee,large,black");
 o.update(13);
```

* Set a simulation parameter: set the value of a parameter, probCatch, for a simulation, Fire, based on the value given in a data file
```java
 Something thing = new Something();
 Order o = thing.makeOrder("coffee,large,black");
 o.update(13);
```

* Switch simulations: load a new simulation from a data file, replacing the current running simulation with the newly loaded one
```java
 Something thing = new Something();
 Order o = thing.makeOrder("coffee,large,black");
 o.update(13);
```