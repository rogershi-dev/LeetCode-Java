import java.util.*;

class LinkedList {
    int value;
    LinkedList next;

    public LinkedList(int value) {
        this.value = value;
        this.next = null;
    }
}

public class Program {

    public static LinkedList findLoop(LinkedList head) {
        LinkedList slow = head;
        LinkedList fast = head;

        // Detect if a loop exists
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                break; // A loop has been detected
            }
        }

        // If there's no loop, the fast pointer will be null once finished the while loop
        if(fast != slow) {
            return null;
        }

        // If there is a loop, move the slow pointer to the start of the list
        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Finally, both pointers will point to the entry of the loop
        return slow;
    }

    // Helper method to generate LinkedLists
    public static LinkedList generateLinkedList(int length, boolean hasLoop){
        if(length <= 0) return null;

        // Create the head of the list
        LinkedList head = new LinkedList(0);
        LinkedList current = head;
        LinkedList loopEntryNode = null;

        // Create the rest of the nodes
        for(int i = 1; i < length; i++){
            current.next = new LinkedList(i);
            current = current.next;

            if(hasLoop && i == length / 2) {
                loopEntryNode = current; // save the reference for loop creation
            }
        }

        // If loop is needed, point the last node to the saved loop entry node
        if(hasLoop && loopEntryNode != null) {
            current.next = loopEntryNode;
        }

        return head;
    }

    // Helper method to print out the linked list
    public static void printLinkedList(LinkedList head, LinkedList loopEntryNode) {
        int count = 0;
        LinkedList current = head;

        while(current != null) {

            if(loopEntryNode != null && current == loopEntryNode) {
                count++;
            }

            if(current.next != null && count < 2) {
                System.out.print(current.value + " -> " );
            } else {
                System.out.print(current.value);
                break;
            }


            current = current.next;

        }
    }

    public static void main(String[] args) {
        List<LinkedList> linkedLists = new ArrayList<>();
        int max = 24;
        int min = 7;

        // Create 5 linked lists, 3 with loops, 2 without loops
        for(int i = 0; i < 5; i++) {
            boolean hasLoop = (i < 3); // first 3 loops will have loops
            int randomLength = (int) (Math.random() * (max - min)) + min;
            linkedLists.add(generateLinkedList(randomLength, hasLoop));
        }

        // Run tests
        for(int i = 0; i < linkedLists.size(); i++) {
            LinkedList head = linkedLists.get(i);
            LinkedList loopEntryNode = findLoop(head);

            System.out.println("Linked list " + (i + 1) + ": ");
            printLinkedList(head, loopEntryNode);

            if(loopEntryNode != null) {
                System.out.printf("\nLinked list %d has a loop: ", i + 1);
                printLinkedList(loopEntryNode, loopEntryNode);
            } else {
                System.out.printf("\nLinked list %d has no loop.", (i + 1));
            }

            if(i != linkedLists.size() - 1){
                System.out.printf("\n\n");
            } else {
                System.out.printf("\n");
            }
        }
    }
}

// Test output
// Linked list 1: 
// 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 4
// Linked list 1 has a loop: 4 -> 5 -> 6 -> 7 -> 8 -> 4

// Linked list 2: 
// 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> 12 -> 13 -> 14 -> 15 -> 16 -> 17 -> 18 -> 9
// Linked list 2 has a loop: 9 -> 10 -> 11 -> 12 -> 13 -> 14 -> 15 -> 16 -> 17 -> 18 -> 9

// Linked list 3: 
// 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> 6
// Linked list 3 has a loop: 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> 6

// Linked list 4: 
// 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
// Linked list 4 has no loop.

// Linked list 5: 
// 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
// Linked list 5 has no loop.