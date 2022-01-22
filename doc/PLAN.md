# Cell Society Design Plan
### Team Number: 03
### Names: Cynthia France, Diane Kim, Jose Santillan


## Introduction
In this project, we want to create a user interface that allows the user to choose to run one of 5
cell simulations. The UI will consist of buttons and toggles that allows the user to select the 
simulation & any parameters. The primary design goal is to make it so that any future programmer
can make their own cell simulation and add it to our interface without any issues. The program 
will be able to take in an XML file specifying the simulation and starting configuration and run 
to its completion. Adding new projects/simulations/cell types should be open (just adding a new 
subclass). The code to check neighbors, characteristics of cells and their interactions with 
neighbors should be closed from modification. 

## Design Overview
* Simulation
  * GameOfLife
  * Fire
  * Schelling
  * WaTor
  * Percolation
* Cell
  * LifeCell (for game of life)
  * Empty (for all)
  * Burning (for fire)
  * Tree (for fire)
  * GroupA (sh)
  * GroupB
  * Fish
  * Shark
  * Filled
  * Active
  * NotOpen
* GUI
  * GameOfLifeGUI
  * FireGUI
  * SchellingGUI
  * WaTorGUI
  * PercolationGUI

## Design Details

Here is a graphical look at my design:



## Use Cases

 * Team generated Use Case
 ```java
 Something thing = new Something();
 Order o = thing.makeOrder("coffee,large,black");
 o.update(13);
 ```
* Apply the rules to a middle cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all its neighbors)


* Apply the rules to an edge cell: set the next state of a cell to live by counting its number of neighbors using the Game of Life rules for a cell on the edge (i.e., with some of its neighbors missing)


* Move to the next generation: update all cells in a simulation from their current state to their next state and display the result graphically


* Switch simulations: load a new simulation from a data file, replacing the current running simulation with the newly loaded one


* Set a simulation parameter: set the value of a parameter, probCatch, for a simulation, Fire, based on the value given in a data file




## Design Considerations

#### Design Issue #1

 * Alernative #1

 * Alernative #2

 * Trade-offs


#### Design Issue #2

 * Alernative #1

 * Alernative #2

 * Trade-offs



## User Interface

Here is our amazing UI:

![This is cool, too bad you can't see it](images/UI_Design.HEIC "An preliminary UI")

Ways the user can interact with the UI:
* click the dropdown menu with simulation title to choose another configuration file to load
* click the pause/play buttons
* click speed up/slow down buttons
* save the current state into another configuration file
    * User will be prompted for information about the configuration file to enter in
* click the "i" in the corner to read the current simulation's about info

Situations that prompt errors:
* "Missing inputs": the user did not enter in information for one or more fields when saving
  a file
* "Bad input data": the configuration file to be uploaded is not formatted correctly
* "Empty data": the configuration file cannot be found


## Team Responsibilities

 * Cynthia: 
   * Primary: Percolation, Schelling's
   * Secondary: GUI + XML + design + refactoring + documentation

 * Diane: 
   * Primary: Spreading of Fire, CSS
   * Secondary: GUI + XML + design + refactoring + documentation

 * Jose: 
   * Primary: Wa-Tor, XML
   * Secondary: GUI + design + refactoring + documentation


#### Proposed Schedule
1. Work together to implement interface basics + first simulation (game of life)
2. Split up each design a different simulation model
3. Come back together to refactor + document