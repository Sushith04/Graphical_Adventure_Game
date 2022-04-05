# Dungeon Game

**1. About/Overview:** <br /><br />The world for our game consists of a dungeon, a network of tunnels and caves that are interconnected so that player can explore the entire world by traveling from cave to cave through the tunnels that connect them.<br />Each location in the grid represents a location in the dungeon where a player can explore and can be connected to at most four other locations - North, South, East, West.<br />Here's where everything becomes challenging beacuse the player won't be able to see the locations that they haven't visited. However, the player receives hints about items and monsters situated in the dungeon so that they can reach the end cave.

<br />

**2. List of Features:**<br /><br />The model should create and support a player moving through the dungeon. To do this, the follwing operations are supported:<br />

* The dungeon is to be represented on a **2-D grid**.
* There will be a path from every cave in the dungeon to every other cave in the dungeon.
* Each dungeon will be constructed with a degree of interconnectivity. We define an interconnectivity = 0 when there is exactly one path from every cave in the dungeon to every other cave in the dungeon. Increasing the degree of interconnectivity increases the number of paths between caves.
* Not all dungeons "wrap" from one side to the other, the can either wrap or not wrap. Both wrapping and non-wrapping dungeons will created with different degrees of interconnectivity.
* One cave is randomly selected as the start and one cave is randomly selected to be the end. The path between the start and the end locations will be at least of length 5.
* Provides support for at least three types of treasure: **diamonds**, **rubies**, and **sapphires**.
* Treasures will added to a specified percentage of caves. A cave can have more than one treasure.
* Provides a description of the player that, at a minimum, includes a description of what treasure the player has collected.
* Provides a description of the player's location that at the minimum includes a description of treasure in the room and the possible moves (north, east, south, west) that the player can make from their current location.
* A player can move from their current location. The player will pick up treasure that is located in their current location.
* There is always at least one Otyugh in the dungeon located at the specially designated end cave. The actual number is specified on the command line. There is never an Otyugh at the start.
* Otyugh only occupy caves and are never found in tunnels. Their caves can also contain treasure or other items.
* Otyugh's can be detected by their smell. In general, the player can detect two levels of smell:
  * A less pungent smell can be detected when there is a single Otyugh 2 positions from the player's current location.
  * Detecting a more pungent smell either means that there is a single Otyugh 1 position from the player's current location or that there are multiple Otyughs within 2 positions from the player's current location.
* A player entering a cave with an Otyugh that has not been slayed will be killed and eaten.
* To slay an Otyugh, player will be equipped with a bow that uses crooked arrows.
* Player starts with 3 crooked arrows but can find additional arrows in the dungeon with the same frequency as treasure. Arrows and treasure can be, but are not always, found together. Furthermore, arrows can be found in both caves and tunnels.
* A player that has arrows, can attempt to slay an Otyugh by specifying a direction and distance in which to shoot their crooked arrow. Distance is defined as the number of caves (but not tunnels) that an arrow travels. Arrows travel freely down tunnels (even crooked ones) but only travel in a straight line through a cave. For example,
  * A tunnel that has exits to the west and south can have an arrow enter the tunnel from the west and exit the tunnel to the south, or vice-versa (this is the reason the arrow is called a crooked arrow)
  * A cave that has exits to the east, south, and west will allow an arrow to enter from the east and exit to the west, or vice-versa; but an arrow that enters from the south would be stopped since there is no exit to the north
* Distances must be exact. For example, if you shoot an arrow a distance of 3 to the east and the Otyugh is at a distance of 2, you miss the Otyugh.
* It takes 2 hits to kill an Otyugh. Players has a 50% chance of escaping if the Otyugh if they enter a cave of an injured Otyugh that has been hit by a single crooked arrow.
* The player wins by reaching the end location. The player loses by being eaten by an Otyugh.
* The player can go back to main menu and change the parameters of the dungeon.
* The player can retry if they lose and they will get the same dungeon.
* The player can restart if they didn't like the way dungeon was set. This will create a new dungeon with the same parameters.
* They can quit as well if they don't want to play any further.
<br />

**3. How to Run:** <br /><br />
* Download the .jar file that is located in the /res folder.
* Open the command prompt and navigate to where the .jar file has been downloaded.
* Make sure all the paths are correct.
* If you want the text based controller, run -> _**java -jar Graphical_Adventure_Game.jar <-rows-> <-columns-> (greater than 5 each for the model to work efficiently all the time) <-wrapping(true/false)-> <-Interconnectivity-> <-Treasure And Weapon Percentage-> <-Number of Otyugh's-> <-Player's Name->**_  in the command line. <br /> Example: java - jar Graphical_Adventure_Game.jar 6 6 true 2 50 8 Sushith
* If you want the graphical user interface, run _**java -jar Graphical_Adventure_Game.jar**_ in the command line.
<br />

**4. How to Use the Program:** <br /><br />
The player will explore the entire dungeon by traveling from cave to cave through the tunnels that connect them.<br /><br />
View:
* Initially, when the game opens, the plyer will be asked to enter parameters for the dungeon.
* After the parameters have been entered, click on submit.
* The game starts and then for the player to move click on the arrow buttons for the respective direction.
* If there is any monster nearby, you will be informed via location description that you smell something mild or terrible here.
* If there are arrows and treasures in a particular location, this will also be informed via location description.
* To pick up these arrows and treasures use "A" and "T" buttons respectively. For each "A" clicked one arrow will be picked up. For each "T" clicked all treasures will be picked up.
* All these picked up items will be displayed via player description.
* If you want to shoot and kill a monster, press "S" and then press the arrow keys in which direction you want and input the number of caves the aroow should travel to hit the monster.
* If you do not want to continue and leave the game, you can select "quit" in the menu.
* If you do not want to play with the same dungeon but with same parameters, you can select "restart-> different dungeon" in the menu.
* If you want to retry with the same dungeon, you can select "restart-> same dungeon" in the menu.
* If you want to start with a different dungeon, click "home". 
<br /><br />
Text-Based:

* Using command-line arguments we specify size of the dungeon, its interconnectivity, whether it is wrapping or not, and the percentage of the caves that have treasure.
* Basic information like name and treasure they have collected regarding the player is printed out.
* Now, the dungeon will be constructed based on the parameters like size of the dungeon, interconnectivity, wrapping or not, percentage of the caves that have treasure.
* Tresures will be added to the caves based on the percentage of caves that will have the treasure.
* The start and end locations of where in the dungeon the player has to move are set.
* The player will explore the entire dungeon by traveling from cave to cave through the tunnels that connect them.
* While travelling through the caves, the player will collect all the treasure that are located in that cave and reach the end cave.
* We may choose to print the description of the player and the location they are in whenever required.
* We may also choose to print the dungeon.

<br />

**5. Description of Examples:** <br /><br/>A description of what each example represents/does is provided below.<br /><br />
This is for the view.<br />
File -- IntroPanel.png shows the introduction panel which asks for the parameters.<br />
File -- GameStarts.png shows the frame that consists of the game board.<br />
File -- MenuDisplay.png show the menu that has the home, retry, restart and quit options.<br />
File -- DungeonInfoDisplay.png shows the details of the dungeon.<br />
File -- ArrowsBeforePickup.png shows that there are arrows in that location before they were picked up.<br />
File -- ArrowsAfterPickup.png shows that there are no arrows remaining in that location after they were picked up by the player.<br />
File -- TreasureBeforePickup.png shows that there is treasure in that location before they were picked up.<br />
File -- TreasureAfterPickup.png shows that there is no treasure remaining in that location after they were picked up by the player.<br />
File -- InputWhileShooting.png shows the input asked before shooting.<br />
File -- MonsterAfterOneHit.png shows the monster was damaged after one hit.<br />
File -- MonsterAfterTwoHit.png shows the monster was damaged after one hit.<br />
File -- PlayerAliveAfterEnteringCaveOfInjuredMonster.png shows that the player survives after entering a cave with injured monster in it.<br />
File -- PlayerLosing.png shows that the player dies after entering a cave with monster in it.<br />
File -- PlayerWinning.png shows that the player winning by killing the monster in the end cave and moving to it.<br />
This is for the text based controller.<br /><br />
File -- TextBasedDriverRuns.txt:<br />
* 1. A first run which shows a non-wrapping dungeon.
* 3. The input for number of rows is taken.
* 6. The input for number of columns is taken.
* 9. The input for wrapping is taken.
* 12. The input for wrapping is taken.
* 15. The input for percentage of caves is taken.
* 18. A non-wrapping dungeon is create dusing the above inputs.
* 35. A second run which shows a wrapping dungeon.
* 37. The input for number of rows is taken.
* 40. The input for number of columns is taken.
* 43. The input for wrapping is taken.
* 46. The input for wrapping is taken.
* 49. The input for percentage of caves is taken.
* 52. A wrapping dungeon is create dusing the above inputs.
* 66. A third run which shows the player starting at the start and reaching the end.
* 68. Using fixed inputs, we create a dungeon.
* 80. Shows the start location. the player starts exploring the dungeon from this location.
* 81. Shows the end location. Using these locations the player moves across the dungeon.
* 82. Shows that there are no treasures in the location(0,3).
* 86. Shows that there are treasures in the location(1,4). Similarly rest all directions will be put into action.
* 99. The player reaches the end location(2,0).
* 101. This shows the treasures collected by the player while exploring the dungeon.
* 105. A fourth run which shows the player visiting every location in the dungeon.
* 107. Using fixed inputs, we create a dungeon.
* 118. A player with the name "Sam" is created and the set the name.
* 119. Shows the start location. the player starts exploring the dungeon from this location.
* 120. Shows the end location. Using these locations the player moves across the dungeon. Like above, the player starts exploring the dungeon, only this time, they will explore all the locations in the dungeon. This keeps on happening until 199.
* 200. This shows the treasures collected by the player while exploring the dungeon.

<br />


**6. Design/Model Changes:**<br />
* Redundant methods were removed.
* Made all the unnecessary methods private.
* Made the Otyugh class a package private class.
* Changed the arrow from enum to count.
* I added an interface to the Otyugh class.
* A readonly model for dungeon was created.
* A seperate controller for view was done.
<br />

**7. Assumptions:**<br />
* The movement of the player is based upon the user input.
* Adding the left over edges from all the edges to the dungeon is not random as we have already picked the required edges randomly.
<br />

**8. Limitations:**<br />
* Even though the required edges are picked randomly, random locations are not picked while applying interconnectivity.
* Instead of random movement of the player in the dungeon, user input is used for the movement of the player.
<br />

**9. Citations:**<br />
* [Tutorial Horizon]  (https://algorithms.tutorialhorizon.com/kruskals-algorithm-minimum-spanning-tree-mst-complete-java-implementation/)
* [Geeks for Geeks] (https://www.geeksforgeeks.org/disjoint-set-data-structures/)
* [Geeks for Geeks] (https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples/)
* [Geeks for Geeks] (https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/)
* [Geeks for Geeks] (https://www.geeksforgeeks.org/java-swing-simple-user-registration-form/)
* [Geeks for Geeks] (https://www.geeksforgeeks.org/java-swing-jmenubar/)
* [Javat Point] (https://www.javatpoint.com/exception-handling-in-java)
* [Javat Point] (https://www.javatpoint.com/java-jscrollpane)
* [Piazza] (https://piazza.com/class/kt0jcw0x7h955a?cid=1500)
* [Tutorials Point] (https://www.tutorialspoint.com/how-to-make-joptionpane-to-handle-yes-no-and-closed-buttons-in-java)
* [Stack Overflow] (https://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage)

