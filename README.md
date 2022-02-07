Cell Society
====

This project implements a cellular automata simulator.

Names: Cynthia France, Diane Kim, Jose Santillan 

####Note: Cynthia wrote this README, so everything documented below is to her best knowledge
For individual contributions, please review commit history for a more accurate & specific
understanding.

### Timeline

Start Date: 1/26/2022

Finish Date: 2/7/2022

Hours Spent: 100

### Primary Roles
The following are our result roles
###Cynthia France:
  * General things
    * Set up the back-end and the front-end logic, connecting the two together
      * Cell super class
      * SimulationModel super class
      * ViewCell super class
      * View superclass
  * Backend
    * Simulations: Schelling's, Spreading of Fire, Game of Life, WaTor
      * Note: for WaTor, Jose started the simulation & Cynthia finished it
      * Note: for Game of Life, both Jose & Cynthia debugged it (separately)
    * Each of the above simulation's their corresponding components (Cells, Models, ViewCells, View, etc.)
    * Helped come up with logic for finding neighbors for differently shaped cells (even odd orientation (see code))
  * Frontend
    * Responsible for the connection part of the UI (ie updating the grid, creating viewCells)
    * Design of the grid, display grid & cellSize logic
    * play/pause, step
    * load new file
    * save configuration
      * including pop up dialog boxes that ask for simulation information
    * a version of changing speed (is not used anymore, but can still find the code for it
      in Main, handleKeyInput)
    * Gave Diane her breakout splash screen code to help her implement the splash screen 
  * Everything related to XML, including:
    * XML Parser
    * Saving XML Files from current configuration
    * XML File format design
    * Integration of Tag values into code/program
    * Making sure the correct simulation is played, values are read correctly
    * Most of the initial XML configurations from basic
  * Refactoring & abstractions
###Jose Santillan
  * Backend
    * Simulations: Falling Sand, Game of Life, Rock Paper Scissors, 
      * Note: for WaTor, Jose started the simulation & Cynthia finished it
      * Note: for Game of Life, both Jose & Cynthia debugged it (separately)
    * Each of the above simulation's their corresponding components (Cells, Models, etc)
    * Grid class
    * Different Shape neighbors (triangle, hexagonal)
    * Grid Edge Types
  * Frontend
    * Responsible for the connection part of the UI (ie updating the grid, creating viewCells)
  * XML
    * Some GameOfLife XML files
    * all Sand and RPS XML files
  * Refactoring & abstractions
###Diane Kim
  * Frontend/UI
    * Look & Feel, general window layout
    * Info/About/Rules button & dialog
    * simulation speed slider
    * files/text & styles displayed in window
      * All .css (style) files
      * All .properties (language) files
    * Splash screen

Please see Plan document for planned roles

### Resources Used
* XML Parser: Class example
* XML Saver: [https://mkyong.com/java/how-to-create-xml-file-in-java-dom/](https://mkyong.com/java/how-to-create-xml-file-in-java-dom/)

### Running the Program

Main class:
1. Run the main class
2. Select an XML file
3. Use the play/pause button to toggle the animation
4. Use the step button to step through generations
5. Use the slider to speed up the animation
6. Click "Load New" to load a new XML file/configuration/game
7. Click "Save Configuration" and fill out the desired information to save the current configuration 

Data files needed: 
* XML configuration files from /data

Features implemented:
* All of Basic
* Complete:
  * cell shapes
  * grid edge types
  * RPS
  * Falling Sand
  * Styles


### Notes/Assumptions

Assumptions or Simplifications:
* WaTor: Eating one fish restores one point of health for a shark
* All configuration files will be saved in the /data folder (saved in-program)

Interesting data files:

Known Bugs:
* The implementation of different cell shapes & grid edge types may have cause the malfunctioning of 
  some simulations (ie. Percolation)

Noteworthy Features:


### Impressions
This project was fun & got us thinking, but would've been more enjoyable if given more time to work 
& fewer "change"/additional features to implement. 
