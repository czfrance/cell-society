# Refactoring Lab Discussion
#### Cynthia France, Jose Santillan, Diane Kim
#### TEAM 03


## Principles

### Current Abstractions

#### Abstraction #1: Cell
* Open/Close
  * The way that we deal with cells follows this principle as we have many subclasses that inherit
    from the Cell superclass, thus extending the class instead of modifying it to fit every
    possible cell specification

* Liskov Substitution
  * All cell subclasses extend Cell, and passing a subclass in for the superclass will not cause 
    any errors.

#### Abstraction #2: Model
* Open/Close
  * There are model subclasses for each type of simulation. Each subclass adds what it needs to
    do in its own file instead of forcing the main class to do so.

* Liskov Substitution
  * All methods across the models are the same, so no errors will occur when substituting in a more
    specific class.

#### Abstraction #3: View
* Open/Close
  * Similar with Model, there is a view for each simulation. Since there are slight differences between
    each simulation, we make these changes in the subclass instead of the parent View class

* Liskov Substitution
  * All methods across the models are the same, so no errors will occur when substituting in a more
    specific class.
  
### New Abstractions

#### Abstraction #1
* There seems to be some duplicated code in some of our view classes that we thought were different
    from each other, but actually not. We can fix this by moving said code to the overall parent class
    and don't need to specify it each time in each subclass.

#### Abstraction #2
* A problem we have is nested control flow statements. Perhaps it would be good to fix this by changing 
  the classes it deals with so that this level of nesting is not required. We're not quite sure how
  exactly to go about it, but that a change is both possible and necessary.



## Issues in Current Code

### *View Classes
* code duplication (*View, makeGrid & updateGrid)
* makeGrid()
  * nested control flow (for, for, switch)
* updateGrid()
  * nested control flow (for, for, switch)
* magic numbers
* empty methods

### *Model
* returns List<List<Cell>>, which reveals the structure of our grid (Simulation Model, getCellNextState())
* unused methods (Simulation Model, lines 136-161)


## Refactoring Plan

* What are the code's biggest issues?
  * One very big issue is magic numbers. We need to assign constants for each.
  * We also have some public getters that return List<List<Cell>>, which reveals the structure 
    of our grid
  * We also need to make some of our final variables constants, since they're never changed.
  * Another big issue is that we have too many nested control statements.
  * We also have some duplication of code, within each file (and across files), which we need to take
    care of.

* Which issues are easy to fix and which are hard?
  * The magic numbers issue is easy to fix. We just forgot to assign the values to constants
  * Making variables static will also be easy

* What are good ways to implement the changes "in place"?
  * Just making one change at a time is necessary so that we can ensure each change doesn't break 
    our code.



## Refactoring Work

* Issue chosen: magic numbers
  * Fix: created constants for each magic number
  * Alternatives: instead of creating many constants, have a file/data structure that contains all
    the constants

* Issue chosen: non static final variables
  * Fix: made the variables static (a constant)

* Issue chosen: method is too long
  * Fix: identified section of code that would work best as a method (it performs one specific purpose)

* Issue chosen: duplication across files
  * Fix: move the duplicated code to the parent class instead of keeping it in each subclass

* Issue chosen: nested control flow
  * Fix: We figured out that the innermost nest of control flow was sometimes duplicated, so we created
    a function that fixed it.