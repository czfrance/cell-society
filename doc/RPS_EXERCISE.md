# Rock Paper Scissors Lab Discussion
#### Names & NetIDs
* Diane Kim: 
* Jose Santillan: jas299
* Cynthia France: cf219


### High Level Design Goals



### CRC Card Classes

This class's purpose or value is to represent a customer's order:
Game class
* Keeps track of:
  * players
* get player input
* display result/standings
* Depends on Player, Weapon

Player class
* keeps track of:
  * num wins
  * num losses
  * current weapon
* calculates score
* Depends on Weapon

Weapon super class
* abstract method getOutcome
* Vars:
  * (abstract) winsAgainst
  * (abstract) losesAgainst
* Scissors subclass
* Paper subclass
* Rock subclass
 

Game:
```java
public class RPSGame {
     // a list of the players in the game
     private List<Players> players;
     
     public void setupGame();
     
     private void getPlayerInput();
     
     private void displayResults();
     
     private void newGame();
 }
 ```

Player:
```java
public class Player {
     private int numWins;
     private int numLosses;
     private Weapon currWeapon;
     
     public void outcome();
 }
```

Weapon super class
* abstract method getOutcome
* Vars:
    * (abstract) winsAgainst
    * (abstract) losesAgainst
* Scissors subclass
* Paper subclass
* Rock subclass
```java
public class Weapon {
     private List<Weapon> winsAgainst;
     private List<Weapon> losesAgainst;
     
     public abstract void getOutcome();
 }
```

### Use Cases

* A new game is started with five players, their scores are reset to 0.
 ```java
for (int i = 0; i < 5; i++) {
  players.add(new Player());
}
 ```

* A player chooses his RPS "weapon" with which he wants to play for this round.
 ```java
  Player.setWeapon(scanner.nextInt());
 ```

* Given three players' choices, one player wins the round, and their scores are updated.
 ```java
    player1.outcome(player2);
    player2.outcome(player3);
    player3.outcome(player1);
    updateScores();
  
 ```

* A new choice is added to an existing game and its relationship to all the other choices is updated.
 ```java
     player.setWeapon();
 ```

* A new game is added to the system, with its own relationships for its all its "weapons".
 ```java
    Game game2 = newGame();
 ```
