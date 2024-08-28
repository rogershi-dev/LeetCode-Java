import java.util.*;

class Program {
    public static void removeKthNodeFromEnd(LinkedList head, int k){
        LinkedList fast = head;
        LinkedList slow = head;

        // Move the fast pointer k steps ahead
        int i = k;
        while(i > 0 && fast != null){
            fast = fast.next;
            i--;
        }

        // If fast is null after moving k steps, remove the head node
        if(fast == null){
            head.value = head.next.value;
            head.next = head.next.next;
            return;
        }

        // Move both pointers until the fast pointer reaches the end
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the K-th node from the list
        slow.next = slow.next.next;
    }

    // Helper method to build the linkedlist from an array of values
    private static LinkedList buildLinkedList(int[] values){
        if(values.length == 0) return null;

        // Save the head node so that we can properly return it
        LinkedList head = new LinkedList(values[0]);
        // Make a copy of the head node and use it to construct the remaining part of the list
        LinkedList currentNode = head;

        for(int i = 1; i < values.length; i++){
            currentNode.next = new LinkedList(values[i]);
            // Once the for loop ends, the currentNode will point to the last node of the list
            currentNode = currentNode.next;
        }

        return head;
    }

    // Helper method to print out the node values in the list
    private static void printLinkedList(LinkedList head){
        LinkedList currentNode = head;
        while(currentNode != null){
            if(currentNode.next != null){
                System.out.printf("%d -> ", currentNode.value);
            } else {
                System.out.printf("%d%n", currentNode.value);
                break;
            }
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args){
        // Test cases
        int[][] values = {
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            {2, 3, 5, 7, 1},
            {7, 4, 5, 3, 9, 1, 0, 8}
        };

        int[] positions = {4, 1, 6};

        for(int i = 0; i < values.length; i++){
            LinkedList head = buildLinkedList(values[i]);
            System.out.printf("Test Linked List %d:%n", i + 1);
            printLinkedList(head);

            int k = positions[i];
            removeKthNodeFromEnd(head, k);
            System.out.printf("List %d after removing the %d-th node from end:%n", i + 1, k);
            printLinkedList(head);
            System.out.println();
        }
    }

    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value){
            this.value = value;
        }
    }
}

// Test output:
// Test Linked List 1:
// 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
// List 1 after removing the 4-th node from end:
// 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 7 -> 8 -> 9

// Test Linked List 2:
// 2 -> 3 -> 5 -> 7 -> 1
// List 2 after removing the 1-th node from end:
// 2 -> 3 -> 5 -> 7

// Test Linked List 3:
// 7 -> 4 -> 5 -> 3 -> 9 -> 1 -> 0 -> 8
// List 3 after removing the 6-th node from end:
// 7 -> 4 -> 3 -> 9 -> 1 -> 0 -> 8