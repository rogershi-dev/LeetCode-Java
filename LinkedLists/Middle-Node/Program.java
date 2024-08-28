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

    public static LinkedList middleNode(LinkedList linkedList) {
        LinkedList slow = linkedList;
        LinkedList fast = linkedList;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Helper method to generate linked list
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

    // Helper method to print out the linked list
    public static void printList(LinkedList head){
        LinkedList currentNode = head;
        while(currentNode != null){
            if(currentNode.next != null){
                System.out.printf("%d -> ", currentNode.value);
            } else {
                System.out.printf("%d%n", currentNode.value);
            }
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args){
        // Test cases
        int[][] testCases = {
            {2},
            {1, 4},
            {4, 3, 5},
            {6, 2, 4, 9, 8, 2, 4, 5, 3, 4},
            {1, 7, 9, 8, 5, 6, 4, 3, 2}
        };

        for(int i = 0; i < testCases.length; i++){
            System.out.printf("Test Case %d:%nInput:%n", i + 1);
            LinkedList head = createLinkedList(testCases[i]);
            printList(head);

            LinkedList middleNode = middleNode(head);
            System.out.printf("Output: %n");
            printList(middleNode);
            System.out.println();
        }
    }
}

// Test Case 1:
// Input:
// 2
// Output: 
// 2

// Test Case 2:
// Input:
// 1 -> 4
// Output: 
// 4

// Test Case 3:
// Input:
// 4 -> 3 -> 5
// Output: 
// 3 -> 5

// Test Case 4:
// Input:
// 6 -> 2 -> 4 -> 9 -> 8 -> 2 -> 4 -> 5 -> 3 -> 4
// Output: 
// 2 -> 4 -> 5 -> 3 -> 4

// Test Case 5:
// Input:
// 1 -> 7 -> 9 -> 8 -> 5 -> 6 -> 4 -> 3 -> 2
// Output: 
// 5 -> 6 -> 4 -> 3 -> 2

