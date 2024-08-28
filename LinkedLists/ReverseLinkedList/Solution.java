
class LinkedList {
    int value;
    LinkedList next;

    public LinkedList(int value) {
        this.value = value;
        this.next = null;
    }
}

public class Solution {
    
    public static LinkedList reverseLinkedList(LinkedList head) {
        if(head == null) return null;

        LinkedList prev = null;
        LinkedList curr = head;

        while(curr != null) {
            // Save the reference to the next node
            LinkedList next = curr.next;

            // Reverse the next pointer to prev
            curr.next = prev;

            // Update curr and prev to reverse the next node
            prev = curr;
            curr = next;

        }

        return prev;
    }

    // Helper method to construct the linked lists
    public static LinkedList generateLinkedList(int length) {
        if(length <= 0) return null;

        LinkedList head = new LinkedList(0);
        LinkedList curr = head;
        for(int i = 1; i < length; i++) {
            curr.next = new LinkedList(i);
            curr = curr.next;
        }

        return head;
    }

    // Helper method to print out the linked list
    public static void printLinkedList(LinkedList head) {
        while(head != null) {
            if(head.next == null) {
                System.out.print(head.value + "\n");
            } else {
                System.out.print(head.value + " -> ");
            }
            head = head.next;
        }
    }

    public static void main(String[] args) {
        
        int numOfTests = 5;
        int maxListLen = 16;
        int minListLen = 0;
        for(int i = 0; i < numOfTests; i++) {
            int randomListLen = (int)(Math.random() * (maxListLen - minListLen)) + minListLen;
            LinkedList testCase = generateLinkedList(randomListLen);
            System.out.printf("Test Linked List %d: \n", (i + 1));
            printLinkedList(testCase);

            System.out.println("Reversed List: ");
            printLinkedList(reverseLinkedList(testCase));
            System.out.printf("\n\n");
        }

        System.out.println("Empty List: ");
        printLinkedList(reverseLinkedList(null));
    }
}

// Test cases:
// Test Linked List 1: 
// 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> 12 -> 13 -> 14
// Reversed List: 
// 14 -> 13 -> 12 -> 11 -> 10 -> 9 -> 8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0


// Test Linked List 2: 
// 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
// Reversed List: 
// 10 -> 9 -> 8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0


// Test Linked List 3: 
// 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
// Reversed List: 
// 8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0


// Test Linked List 4: 
// 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11
// Reversed List: 
// 11 -> 10 -> 9 -> 8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0


// Test Linked List 5: 
// 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> 12 -> 13 -> 14
// Reversed List: 
// 14 -> 13 -> 12 -> 11 -> 10 -> 9 -> 8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0


// Empty List: 