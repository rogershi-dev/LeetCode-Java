import java.util.*;

class Program {
    // The input class
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value){
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList){
        LinkedList currentNode = linkedList;

        while(currentNode != null && currentNode.next != null){
            if(currentNode.value == currentNode.next.value){
                currentNode.next = currentNode.next.next;
            } else {
                currentNode = currentNode.next;
            }
        }
        return linkedList;
    }

    // Helper method to construct linkedlists for tests
    public static LinkedList createLinkedList(int[] values){
        if(values.length == 0) return null;
        LinkedList head = new LinkedList(values[0]);
        LinkedList currentNode = head;
        for(int i = 1; i < values.length; i++){
            currentNode.next = new LinkedList(values[i]);
            currentNode = currentNode.next;
        }
        return head;
    }

    // Helper method to print out the linkedlist
    public static void printList(LinkedList head){
        LinkedList currentNode = head;
        while(currentNode != null){
            if(currentNode.next != null){
                System.out.printf("%d -> ", currentNode.value);
            } else {
                System.out.println(currentNode.value);
            }
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args){
        // Test cases
        int[][] testCases = {
            {1, 1, 3, 4, 4, 4, 5, 6, 6},
            {2, 2, 2, 3, 3, 4, 4, 5, 5, 5, 5},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15}
        };

        // Run tests
        for(int i = 0; i < testCases.length; i++){
            LinkedList head = createLinkedList(testCases[i]);
            System.out.printf("Test Case %d: %nOriginal linkedlist:%n", i + 1);
            printList(head);

            LinkedList result = removeDuplicatesFromLinkedList(head);
            System.out.printf("Linkedlist after removing duplicates:%n");
            printList(result);

            System.out.printf("%n");
        }
    }
}

// Test Case 1: 
// Original linkedlist:
// 1 -> 1 -> 3 -> 4 -> 4 -> 4 -> 5 -> 6 -> 6
// Linkedlist after removing duplicates:
// 1 -> 3 -> 4 -> 5 -> 6

// Test Case 2: 
// Original linkedlist:
// 2 -> 2 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5 -> 5 -> 5 -> 5
// Linkedlist after removing duplicates:
// 2 -> 3 -> 4 -> 5

// Test Case 3: 
// Original linkedlist:
// 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
// Linkedlist after removing duplicates:
// 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10

// Test Case 4: 
// Original linkedlist:
// 1 -> 1 -> 1 -> 1 -> 1 -> 1 -> 1 -> 1
// Linkedlist after removing duplicates:
// 1

// Test Case 5: 
// Original linkedlist:
// 10 -> 10 -> 11 -> 11 -> 12 -> 12 -> 13 -> 13 -> 14 -> 14 -> 15 -> 15
// Linkedlist after removing duplicates:
// 10 -> 11 -> 12 -> 13 -> 14 -> 15
