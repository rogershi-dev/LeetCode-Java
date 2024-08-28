import java.util.*;

class Program {
    // The input class
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList mergingLinkedList(LinkedList linkedListOne, LinkedList linkedListTwo) {
        LinkedList currentNodeOne = linkedListOne;
        LinkedList currentNodeTwo = linkedListTwo;

        // When one pointer reaches the end, move it to the start of the other list.
        while(currentNodeOne != currentNodeTwo){
            currentNodeOne = (currentNodeOne == null) ? linkedListTwo : currentNodeOne.next;
            currentNodeTwo = (currentNodeTwo == null) ? linkedListOne : currentNodeTwo.next;
        }

        // Both pointers are either null(no intersection) or at the intersection node.
        // We can return either of the two pointers.
        return currentNodeOne;
    }

    // Helper method to construct Linked List using an array of intergers
    private static LinkedList buildLinkedList(int[] values) {
        if(values.length == 0) return null;
        // Save the dummyHead
        LinkedList dummyHead = new LinkedList(0);
        // Make a copy of the dummyHead and use the copy to continue constructing the Linked List
        LinkedList currentNode = dummyHead;

        for (int value : values) {
            currentNode.next = new LinkedList(value);
            // currentNode will point to the last node once the for loop ends
            currentNode = currentNode.next;
        }
        return dummyHead.next;
    }

    // Helper method to construct intersecting Linked Lists
    private static LinkedList[] buildIntersectingLinkedList(int[] valuesOne, int[] valuesTwo, int[] common){
        LinkedList linkedListOne = buildLinkedList(valuesOne);
        LinkedList linkedListTwo = buildLinkedList(valuesTwo);
        LinkedList commonList = buildLinkedList(common);

        // Attach the common part to the list one
        LinkedList currentNodeOne = linkedListOne;
        while(currentNodeOne.next != null) {
            currentNodeOne = currentNodeOne.next; // Navigate to the end of the list one
        }
        currentNodeOne.next = commonList;

        // Attach the common part to the list two
        LinkedList currentNodeTwo = linkedListTwo;
        while(currentNodeTwo.next != null) {
            currentNodeTwo = currentNodeTwo.next; // Navigate to the end of the list two 
        }
        currentNodeTwo.next = commonList;

        return new LinkedList[]{linkedListOne, linkedListTwo};
    }

    // Helper method to print out the Linked List
    private static void printLinkedList(LinkedList head) {
        LinkedList currentNode = head;
        if(currentNode == null) return;

        while (currentNode != null) {
            if(currentNode.next != null) {
                System.out.printf("%d -> ", currentNode.value);
            } else {
                System.out.printf("%d%n", currentNode.value);
            }
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {
        // Test cases: intersecting linked lists
        int[][] valuesOne = {
            {1, 2, 3, 4, 5, 6},
            {2, 1},
            {1, 2, 3, 4}
        };

        int[][] valuesTwo = {
            {8, 9, 10},
            {7, 6, 5, 4, 3, 0},
            {5, 6, 7, 8}
        };

        int[][] common = {
            {11, 13, 15},
            {8},
            {}
        };

        // Run tests
        for (int i = 0; i < valuesOne.length; i++) {
            System.out.printf("Test Case %d:%n", i + 1);
            LinkedList[] linkedLists = buildIntersectingLinkedList(valuesOne[i], valuesTwo[i], common[i]);
            LinkedList linkedListOne = linkedLists[0];
            LinkedList linkedListTwo = linkedLists[1];

            System.out.print("Linked List One: ");
            printLinkedList(linkedListOne);

            System.out.print("Linked List Two: ");
            printLinkedList(linkedListTwo);

            LinkedList mergingNode = mergingLinkedList(linkedListOne, linkedListTwo);
            if (mergingNode == null) {
                System.out.println("The two linked list don't intersect with each other.");
            } else {
                System.out.print("Intersection nodes: ");
                printLinkedList(mergingNode);
            }
            System.out.println();
            
        }

    }
}

// Test output
// Test Case 1:
// Linked List One: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 11 -> 13 -> 15
// Linked List Two: 8 -> 9 -> 10 -> 11 -> 13 -> 15
// Intersection nodes: 11 -> 13 -> 15

// Test Case 2:
// Linked List One: 2 -> 1 -> 8
// Linked List Two: 7 -> 6 -> 5 -> 4 -> 3 -> 0 -> 8
// Intersection nodes: 8

// Test Case 3:
// Linked List One: 1 -> 2 -> 3 -> 4
// Linked List Two: 5 -> 6 -> 7 -> 8
// The two linked list don't intersect with each other.