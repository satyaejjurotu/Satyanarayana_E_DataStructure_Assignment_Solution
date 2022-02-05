import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Building {
    Scanner scanner = new Scanner(System.in);

    private boolean isLargest(int currentFloorSize, List<Integer> list) {
        boolean isLargest = true;
        for (Integer floorSize : list) {
            if (currentFloorSize < floorSize) {
                isLargest = false;
            }
        }
        return isLargest;
    }

    public void init() {
        System.out.println("\nEnter the total no of floors in the building");
        int floorCount = scanner.nextInt();
        LinkedList<Integer> list = new LinkedList<Integer>();

        /// To hold all small floors till we get the correct floor size
        LinkedList<Integer> waitingList = new LinkedList<Integer>();

        getFloorsFromUser(floorCount, list);

        // Now, we get the list of floors per day, lets arrange it.
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Day " + (i + 1));
            int currentFloorSize = list.get(i);
            List<Integer> subList = list.subList(i + 1, list.size());

            /// If item is not largest then add it to waiting list
            boolean isLargest = isLargest(currentFloorSize, subList);
            if (isLargest) {
                /// If this is a largest floor and there are no
                /// floors are in waiting list then print it. (else statement)
                if (!waitingList.isEmpty()) {
                    // This will move the highest floor to start.
                    Collections.sort(waitingList, Collections.reverseOrder());
                    // new sub list created so that we won't modify the main list itself.
                    LinkedList<Integer> newSubList = new LinkedList<Integer>();
                    for (Integer subItem : subList) {
                        newSubList.add(subItem);
                    }

                    /// Now, sublist contains the highest at first.
                    Collections.sort(newSubList, Collections.reverseOrder());
                    String listStr = "" + currentFloorSize;

                    /// If newSubList & waitingList is not empty,
                    /// Iterate through the newSubList and check
                    /// there are no floor which is smaller than
                    /// waiting list biggest floor,
                    /// if it has then build this floor.
                    /// Eg: waitingList[0] = 4
                    /// newSubList = [7,6,5,3]
                    /// then print 4, 3 & newSubList -> [7,6,5]
                    if (getFilterCondition(newSubList, waitingList)) {
                        do {
                            listStr += " " + waitingList.get(0);
                            waitingList.remove(0);
                        } while (getFilterCondition(newSubList, waitingList));
                    }

                    /// After that iteration if we have items in waiting list then print it out.
                    if (newSubList.isEmpty() && !waitingList.isEmpty()) {
                        for (Integer integer : waitingList) {
                            listStr += " " + integer;
                        }
                        waitingList.clear();
                    }

                    System.out.println(listStr);
                } else {
                    System.out.println(currentFloorSize);
                }
            } else {
                waitingList.add(currentFloorSize);
                System.out.println("");
            }
        }
    }

    private void getFloorsFromUser(int floorCount, LinkedList<Integer> list) {
        for (int i = 0; i < floorCount; i++) {
            System.out.println("Enter the floor size given on day : " + (i + 1));
            list.add(scanner.nextInt());
        }
    }

    private boolean getFilterCondition(List<Integer> subList, LinkedList<Integer> waitingList) {
        return (!subList.isEmpty()) && (!waitingList.isEmpty()) && waitingList.getFirst() > subList.get(0);
    }
}
