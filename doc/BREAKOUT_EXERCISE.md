# Breakout Abstractions Lab Discussion
#### NAMEs
Diane Kim, Cynthia France, Jose Santillan


## Princple Slogans

* Single Responsibility

* Open Closed



### Block

This superclass's purpose as an abstraction:
```java
 public class Block {
     public int something ()
     // no implementation, just a comment about its purpose in the abstraction 
 }
```

#### Subclasses (the Open part)

This subclass's high-level behavorial differences from the superclass:
```java
 public class X extends Block {
     public int something ()
     // no implementation, just a comment about what it does differently 
 }
```

#### Affect on Game/Level class (the Closed part)



### Power-up

This superclass's purpose as an abstraction:
```java
 public class PowerUp {
    public PowerUp(int num, double x, double y) {
        switch (num) {
            case 1:
                myPowerUp = new Circle(POWERUP_SIZE, Color.RED);
                ID = 1;
                break;
            case 2:
                myPowerUp = new Circle(POWERUP_SIZE, Color.WHITE);
                ID = 2;
                break;
            case 3:
                myPowerUp = new Circle(POWERUP_SIZE, Color.BLUE);
                ID = 3;
                break;
            case 4:
                myPowerUp = new Circle(POWERUP_SIZE, Color.GREEN);
                ID = 4;
                break;
            default:
                ID = 0;
                myPowerUp = null;
                break;
        }
        if (myPowerUp != null) {
            myPowerUp.setCenterX(x);
            myPowerUp.setCenterY(y);
        }
    } 
 }
 
 
```

#### Subclasses (the Open part)

This subclass's high-level behavorial differences from the superclass:
```java
 public class X extends PowerUp {
     public int something ()
     // no implementation, just a comment about what it does differently 
 }
```

#### Affect on Game/Level class (the Closed part)



### Level

This superclass's purpose as an abstraction:
```java
 public class Level {
     public int something ()
     // no implementation, just a comment about its purpose in the abstraction 
 }
```

#### Subclasses (the Open part)

This subclass's high-level behavorial differences from the superclass:
```java
 public class X extends Level {
     public int something ()
     // no implementation, just a comment about what it does differently 
 }
```

#### Affect on Game class (the Closed part)



### Others?
