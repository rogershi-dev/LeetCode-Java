import java.util.*;

class Program {

    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList sumOfLinkedLists(LinkedList linkedListOne, LinkedList linkedListTwo) {
        LinkedList dummyHead = new LinkedList(0);
        LinkedList currentNode = dummyHead;
        int carry = 0;

        while(linkedListOne != null || linkedListTwo != null || carry != 0){
            int valueOne = (linkedListOne != null) ? linkedListOne.value : 0;
            int valueTwo = (linkedListTwo != null) ? linkedListTwo.value : 0;
            int currentSum = valueOne + valueTwo + carry;

            int newValue = currentSum % 10;
            currentNode.next = new LinkedList(newValue);
            currentNode = currentNode.next;

            // update carry
            carry = currentSum / 10;
            if(linkedListOne != null) linkedListOne = linkedListOne.next;
            if(linkedListTwo != null) linkedListTwo = linkedListTwo.next;
        }

        return dummyHead.next;
    }

    private static LinkedList buildLinkedList(int[] values){
        if(values.length == 0) return null;

        LinkedList head = new LinkedList(values[0]);
        LinkedList currentNode = head;

        for (int i = 1; i < values.length; i++){
            currentNode.next = new LinkedList(values[i]);
            currentNode = currentNode.next;
        }

        return head;
    }

    private static void printLinkedList(LinkedList head){
        LinkedList currentNode = head;
        if (currentNode == null) return;

        while (currentNode != null){
            if (currentNode.next != null){
                System.out.printf("%d -> ", currentNode.value);
            } else {
                System.out.printf("%d%n", currentNode.value);
                break;
            }
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {
        // Test cases
        int[][] valuesOne = {
            {1, 5, 6, 4, 3},
            {7, 6, 2},
            {3}
        };

        int[][] valuesTwo = {
            {9, 5, 3, 1},
            {5, 6},
            {3, 9, 7, 3}
        };

        for (int i = 0; i < valuesOne.length; i++){
            LinkedList linkedListOne = buildLinkedList(valuesOne[i]);
            LinkedList linkedListTwo = buildLinkedList(valuesTwo[i]);
            LinkedList sumOfLists = sumOfLinkedLists(linkedListOne, linkedListTwo);

            System.out.printf("Test Case %d:%n", i + 1);
            System.out.print("linkedListOne: ");
            printLinkedList(linkedListOne);
            System.out.print("linkedListTwo: ");
            printLinkedList(linkedListTwo);
            System.out.print("Sum of linked lists: ");
            printLinkedList(sumOfLists);
            System.out.println();
        }
    }
}
// Test output
// Test Case 1:
// linkedListOne: 1 -> 5 -> 6 -> 4 -> 3
// linkedListTwo: 9 -> 5 -> 3 -> 1
// Sum of linked lists: 0 -> 1 -> 0 -> 6 -> 3

// Test Case 2:
// linkedListOne: 7 -> 6 -> 2
// linkedListTwo: 5 -> 6
// Sum of linked lists: 2 -> 3 -> 3

// Test Case 3:
// linkedListOne: 3
// linkedListTwo: 3 -> 9 -> 7 -> 3
// Sum of linked lists: 6 -> 9 -> 7 -> 3