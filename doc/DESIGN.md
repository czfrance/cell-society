# Cell Society Design Final
### Cynthia France, Jose Santillan, Diane Kim

####Note: This document was written by Cynthia, so everything documented below is to her best knowledge

## Team Roles and Responsibilities

 * Cynthia France:
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
   * Major refactoring & abstractions

 * Jose Santillan
   * Backend
     * Simulations: Falling Sand, Game of Life, Rock Paper Scissors, Sugar Scape, Foraging Ants
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
     * all Sand, RPS, Sugar, and Ants XML files
   * Refactoring & abstractions

 * Diane Kim
   * Frontend/UI
     * Look & Feel, general window layout
     * Info/About/Rules button & dialog
     * simulation speed slider
     * files/text & styles displayed in window
         * All .css (style) files
         * All .properties (language) files


## Design goals
* Easy to add new simulations
* Package purposes are clear
* Obvious separation between model & view & the roles they play
* Clear flow of logic

#### What Features are Easy to Add
* Simulations
  * steps:
    * backend logic in model & cell
    * connect grid to front end view
    * connect grid cells to viewCells
* New cell types (both the ones in the back-end and the front-end ones)
* New configurations
* New simulation specifications
  * Once you add it once, you don't need to add it again (ie between loading & saving)
* New cell types

## High-level Design
* The body of each simulation's logic (where everything's called) is located in it's model class.
  However, it does this by telling the cells to figure out their own next state, so computationally,
  everything is happening in the cell class(es) specific to that simulation.
* After the user chooses an XML file, 
* When a simulation is first chosen, the parser parses through the information and passes it to
  Main. Main creates the appropriate model and view classes. In model, the grid is created 
  containing the proper information & the view reflects this visually.
* The model is told to update itself (which tells the cells to calculate their states) by the view, 
  which knows when it should be updating itself. To do so, it first tells the model to update itself,
  then using that the new backend states, updates itself in turn
  * Note: after calling model.updateGrid(), each cell (in backend) is already in the correct state,
    but has not been updated visually to reflect it yet.
* The view updates itself by obtaining the new cell states from the model and telling each view cell
  to update itself (visually) to the correct state
* This process repeats unless other actions are taken (ie stepping, pausing, loading a new
  configuration, saving a configuration, etc)

#### Core Classes
* SimulationModel
* Cell
* Grid
* SimulationView
* ViewCell
* XML*

## Assumptions that Affect the Design
* All triangle cells start facing down

#### Features Affected by Assumptions
* The triangle grid

## Significant differences from Original Plan
* The most significant difference was our roles & the amount of work that each member did
* Design-wise, we created more packages than we had expected. In addition to our planned
  Simulation, Cell, and GUI, we also had an XML package (to deal with XML-related events) as well
  as split Cell up into two classes: one for the backend and one for the frontend

## New Features HowTo
* New Simulations
  * Create as many new Cell classes as needed for the simulation & code their logic (ie how to
    determine their next state, if they die or live, if they move, etc)
  * In the model, specify how cells are greated & generally updated, if there are any specific overall
    logic that needs to take place (ie cells moving, being created, etc.)
  * In the view, specify how a grid must be updated & maintained (if different from default code)
  * In view cell, ensure that each cell can be visually updated to the correct state (ie color)
* New file format
  * create a new package, preferably named the file's extension
  * Write a parser & a saver
    * The parser should take in a file and output a Map with the key being the simulation information
      the value being its corresponding value in the file
    * The saver should take in the simulation's current information, grid configuration, as well as
      any new changes/specifications the user would like and create a new file of correct extension 
      to the data/ folder

#### Easy to Add Features
* Simulations
    * steps:
        * backend logic in model & cell
        * connect grid to front end view
        * connect grid cells to viewCells
* New cell types (both the ones in the back-end and the front-end ones)
* New configurations
* New simulation specifications
    * Once you add it once, you don't need to add it again (ie between loading & saving)
* New cell types

#### Other Features not yet Done
* Error Checking
  * Add a new package containing all errors
    * In each .java file that throws an error (probably all), import this package and throw the
      correct error
    * Files inside this package should be organized by type of error, ie
        * XML errors
        * Errors that come from the model
        * "" view
        * etc
* Random initial cell state configurations
  * In the model & XML files, add the additional necessary XML tags
    * Add a check for which method we should use to create the grid
    * Add new Grid subclasses that take care of random cell creation
        * ie one for "based on a total number of locations to occupy"
        * and one for "based on probability/concentration distributions"
        * Pass in the necessary information to do this
* Visual configurations
  * Add in the necessary tags & include them in the model
    * Pass in these variables to the model, view, and whatever controls these values
      * grid types & cell shapes -> model
      * Cell outline & color/fill -> view -> viewCell
* Any number of simulations
  * Requires refactoring of the view
    * New class: GUI, is what the user sees, contains multiple simulation views
    * Simulation specific views
      * keep all old buttons & toggles, as each would control its own simulation
  * In the GUI, add some way to indicate simulations to run (ie dropdown, plus sign)
    * When button is pressed, trigger file selection
    * THOUGHT PROCESS ONE: recalculate position & size of cells based on the number of simulations
      * Update the Grid class to include coordinates/location
      * If load multiple at once, recalculate all at once
      * If use "plus" sign, recalculate & shift old simulations + add new simulation
  * THOUGHT PROCESS TWO: Each simulation view would probably go in its own panel
    * When adding a simulation, just add a panel, where each panel is organized the same way as 
      each simulationView is now.
    * This way, the window/java can do the heavy-lifting when it comes to locating the panels in
      the window
* User Interaction
  * In the GUI, create buttons/sliders that control this information
  * Once something is changed, communicate the new information to where it needs to go
    * Different ratio of cells = Model
    * update rate = View
* Different Views
  * Refactoring
    * Super super class: View
    * All classes (ie SimulationView, HistogramView) extend this
  * Create new classes each type of view (HistogramView, TotalsView, etc)
    * And new subclasses for each simulation, if needed
  * In these new classes, specify what information to display & how to display it
    * ie for HistogramView:
      * take in the Grid
      * obtain the count of each type of cell
      * transform that count into a value that makes sense for the view
        * ie: multiplier = (histogramHeight) / max(cellCount)
          * barHeight = multiplier * cellCount
      * create the histogram
      * update as normal
  * In the GUI, add a button that allows the user to switch between types of views & update
    as desired
