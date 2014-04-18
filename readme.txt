/**********************************************
 * Please DO NOT MODIFY the format of this file
 **********************************************/


/*************************
 * Team Info & Time spent
 *************************/
/*
        Edit this to list all group members and time spent.
        Please follow the format or our scripts will break.
*/


        Name1: Ryan Fishel
        NetId1: ref13
        Time spent: 40


        Name2:Ryan Toussaint
        NetId2:rmt15
        Time spent: 38


        Name3:Carlos Reyes
        NetId3:cer26
        Time spent: 36


       Name4:Lyndsay Kerwin
        NetId4:lmk22
        Time spent: 34


/*
        
*/        


/******************
 * Files to submit
 ******************/


        


/************************
 * Implementation details
 *************************/


/*
To Run: Jar file needs an argument that is the name of the input file and the input file must be in the working directory.  We are providing four test files.


We also have JUnit tests for several elevator cases and also for testing the event barrier.


Our project is divided among several large components that integrate with one another to correctly operate a set of elevators.  The main classes include Rider.java, Building.java, Elevator.java, EventBarrier.java, and ElevatorController.java.  In order to run the program, a test file must be created that instantiates an EventController, a Building, and an EventBarrier for each Rider.  This initial EventBarrier must also have the correct floor set, where the Rider will be placed.  


Next, the program starts by each Rider calling its ‘start’ method.  The ‘start’ method will automatically call the implemented method ‘run’.  In our case, ‘run’ determines which way the Rider is going, using the boolean ‘goingUp’.  Note: ‘goingUp’ was set in the constructor of the Rider by using its currentFloor and destinationFloor.


Once the direction is known, the Rider is ready to press the correct elevator button based on the direction it is traveling -- these are denoted by methods ‘buttonUp’ and ‘buttonDown’.  Note:  These methods are mostly identical, except for equalities that pertain to the direction it is traveling, and the specific Sets and Lists that the methods have to iterate through.


For example, ‘buttonUp’ will notify the Building that an ‘up’ call has been placed -- by calling the ‘callUp’ building method.  The building acknowledges this call and adds the Rider’s eventBarrier to its list of upBarriers (e.g. a list denoting all the requests made in the ‘up’ direction). The Rider then calls arrive(), once all of this completes.  The Rider then sleeps until an elevator arrives at its floor.


Control now switches over the elevators.  If the elevators have not been instantiated yet, they denote which ElevatorController they will be using.  Next, elevators start and call their run method.  This is the most important method for the elevator, because it is the method that cycles through while the program is running (we have this loop run 10,000 times so that we could show that it worked with JUnit tests.  If the loop was to run forever then we would just set the while loop to true).  Before we dive into the implementation, it’s important to note our scheduling implementation.


Each building has ‘x’ elevators and ‘y’ floors.  After researching some different algorithms for elevator scheduling, we settled on dividing the building in different sectors.  Each elevator is assigned a sector of the building.  The sectors are a group of floors that each elevator will give priority too.  Note: the sector size = numFloors/numElevators (if there is any overflow then one elevator handles fewer floors than the others).  In the case of decimals, we round down.  Thus, the elevator will check this specific group of floors for requests first, when the elevator is empty.  If there is a request in this region, it will update its destinationFloor to this level and pick these Riders up.  However, if there are no requests within its priority sector, then it moves to search the remaining floors of the building and helps out other sectors of the building.  Note: Once the elevator picks up Riders, drops them off, and is empty again it will scan its sector before taking on another request and adding Riders to its empty elevator.


Looking at the code, the elevator calls its ‘run’ method.  The elevator determines its initial direction and then finds out if it is empty or not -- if empty, the elevator checks its priority sector for requests.  If the elevator has passengers, it loops through the Riders and determines which floor to go to next.  The elevator is able to determine this, by using its direction and the proper set of EventBarriers (e.g. if the elevator is moving up, it searches upBarriers).  As long as the elevator’s direction is not set to STAGNANT, it will call visitFloor() and deliver the Riders.  Note: The status of an elevator can be one of three enums -- UP, DOWN, or STAGNANT.


When the elevator arrives at the destination floor, it calls ‘VisitFloor’.  It changes its atFloor boolean to true, updates its ‘currentFloor’, and loops through its passengers (e.g. stored in ‘onBarriers’).  The passengers stored in onBarriers are all the Rider threads that need to be woken up (if the currentFloor is its destinationFloor), so that they can ‘exit’ the elevator and arrive at their destinationFloor.  The Elevator.java class implements this, by calling x.raise() on each Rider thread and then calling subtractRider() (which updates its currentFloor, subtracts it from the elevator’s number of passengers, removes it from the passenger list, and removes its EventBarrier from the ‘onBarrier’ list of EventBarriers stored by that specific elevator).  Note: This is our way of updating the elevator’s status, the elevator’s state, releasing the correct Riders, and getting ready to open the doors to the new Riders who are about to get on the elevator.


The correct Riders are added to the elevator, because the elevator requests the list of EventBarriers (based on its direction orientation), and loops through them to see which have a currentFloor that matches its currentFloor.  If there is a match, then that Rider should get on the elevator.  The elevator wakes up Rider (and all the other correct Riders from the list) and then calls ClosedDoors().  


Note: by calling ‘raise()’ on each Rider, this will automatically continue that thread from where it left off.  In our case, the Rider awakes, and checks if the elevator has space.  If so, it adds itself to the list of passengers, removes itself from upBarriers (or downBarriers -- depending on the direction), adds itself to the onBarriers (signifying that it is on the elevator), and updates some booleans.  On the other hand, if space is not available on the elevator, then the Rider will ‘complete()’ -- so that the elevator knows that that thread has completed for this attempt to board.  After this, it then calls buttonUp() or buttonDown() again, so that it can have another chance of getting on another elevator -- where it then falls asleep until it is woken up again by the next arriving elevator.


To conclude the elevator’s execution, it finishes with ClosedDoors().  The point of ClosedDoors(), is to see which floor to visit next.  This floor is then set as the destinationFloor and the elevator proceeds to this spot (in the elevators loop in the run() method, we check to see if there are any floors that we should stop at along the way and if there are we would set that floor to destination floor instead). The process documented above will continue as Riders continue to call buttonUp() and buttonDown().  Other implementation details include various ‘getters’, ‘setters’, and state variables, that are used to track current status and state and update them as necessary.


Extra Credit:


We identified badly behaved riders based on the type “BadlyBehaved” within the Rider class. Rider behaviors were denoted as “WELL_BEHAVED”, “PRESS_BUTTON_DONT_GET_ON”, or “NO_FLOOR_REQUEST”. If a rider pressed the call button but did not get on the elevator, we removed the up or down barrier (depending on the rider’s direction) and called complete on its event barrier so it would no longer be waiting.  Therefore this rider would be removed from the requests and would not go on the elevator. 


If a rider got on the elevator but did not choose a floor, we set his floor automatically to -2 if they entered above floor 0 or a floor above the building (number of floors + 2) if they entered on floor 0. This allowed the riders to move in an up or down direction and for our code to intercept the fake destinations. The rider with a -2 destination was then sent to the lobby (we assumed the person would want to get off on floor 0) to get off and the rider with the above building destination (who entered at the lobby and did not pick a destination floor) was sent to the first floor (we assumed this person would want to go up 1 floor) to remove these badly behaved riders.
 


/************************
 * Feedback on the lab
 ************************/


/*
 * It would be helpful to have an example test file or more direction about how the program would be tested.
 * */


/************************
 * References
 ************************/


/*
 * List of collaborators involved including any online references/citations.
 * */


http://www.quora.com/Is-there-any-public-elevator-scheduling-algorithm-standard