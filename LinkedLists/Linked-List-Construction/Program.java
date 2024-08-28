import java.util.*;


class Program {
  static class DoublyLinkedList {
    public Node head;
    public Node tail;

    public void setHead(Node node) {
        // If the list is empty, set both head and tail to the new node.
        if (this.head == null) {
            this.head = node;
            this.tail = node;
            return;
        }
        // If the list is not empty, insert the new ndoe before the current head.
        this.insertBefore(this.head, node);
    }

    public void setTail(Node node) {
        // If the list is empty, set both the head and tail to the new node.
        if (this.tail == null) {
            this.head = node;
            this.tail = node;
            return;
        }
        // If the list is not empty, insert the new node after the current tail.
        this.insertAfter(this.tail, node);
    }

    public void insertBefore(Node node, Node nodeToInsert) {
        // Skip the case where there's only one node in the list and nodeToInsert happens to be that single node
        if(nodeToInsert == this.head && nodeToInsert == this.tail) return;

        // If it's already in the list, we need to detach it from its current position
        this.remove(nodeToInsert);

        // Update the nodeToInsert's prev and next pointers 
        nodeToInsert.prev = node.prev;
        nodeToInsert.next = node;

        // Update the previous node's next pointer
        if(node.prev != null){
            node.prev.next = nodeToInsert;
        } else {
            // If the node is the current head, 
            // after inserting nodeToInsert, we need to update the current head
            this.head = nodeToInsert;
        }

        // Update the node's previous pointer
        node.prev = nodeToInsert;
    }

    public void insertAfter(Node node, Node nodeToInsert) {
        // Skip the case where there's only one node in the list and nodeToInsert happens to be that single node
        if (nodeToInsert == this.head && nodeToInsert == this.tail) return;

        // If it's already in the list, we need to detach it from its current position
        this.remove(nodeToInsert);

        // Udpate the nodeToInsert's prev and next pointers
        nodeToInsert.prev = node;
        nodeToInsert.next = node.next;

        // Update the node's next pointer's previous pointer
        if(node.next != null){
            node.next.prev = nodeToInsert;
        } else {
            // If the node is the current tail,
            // after inserting the nodeToInsert, we need to update the current tail
            this.tail = nodeToInsert;
        }

        // Update the node's next pointer
        node.next = nodeToInsert;
    }

    public void insertAtPosition(int position, Node nodeToInsert) {

        // The position of the head node is 1
        if(position == 1){
            this.setHead(nodeToInsert);
            return;
        }

        // Locate the original node at the specified position 
        Node currentNode = this.head;
        int currentPosition = 1;
        while(currentNode != null){
            if(currentPosition == position){
                break;
            } else {
                currentNode = currentNode.next;
                currentPosition++;
            }
        }

        if(currentNode != null){
            this.insertBefore(currentNode, nodeToInsert);
        } else {
            // If the position is greater than the length of the list, set the nodeToInsert as the tail node.
            this.setTail(nodeToInsert);
        }
        
    }

    public void removeNodesWithValue(int value) {
        // Start from the head node and remove all nodes with the specified value
        Node currentNode = this.head;
        while(currentNode != null){
            // Copy the reference to a new variable,
		    // so that we can update currentNode to its next pointer
            Node nodeToRemove = currentNode;

            // Save the next node before the current one being removed
            currentNode = currentNode.next;

            if(nodeToRemove.value == value) this.remove(nodeToRemove);
        }
    }

    public void remove(Node node) {
        // If the node is the current head or tail node,
        // we need to firstly update the head or tail node before removing it
        if(node == this.head) this.head = this.head.next;
        if(node == this.tail) this.tail = this.tail.prev;

        // Then we detach the node from the list completely
        // 1.Remove the incoming references to the node
        if(node.prev != null) node.prev.next = node.next;
        if(node.next != null) node.next.prev = node.prev;

        // 2.Remove the outgoing references from the node
        node.prev = null;
        node.next = null;
    }

    public boolean containsNodeWithValue(int value) {
        Node currentNode = this.head;
        while(currentNode != null){
            if(currentNode.value == value) return true;
            currentNode = currentNode.next;
        }
        return false;
    }

  }


    static class Node {
        public int value;
        public Node prev;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        DoublyLinkedList linkedList = new DoublyLinkedList();

        // Create test nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);


        // Set up the doubly linked list
        linkedList.setHead(node1);
        linkedList.insertAfter(node1, node2);
        linkedList.insertAfter(node2, node3);
        linkedList.insertAfter(node3, node4);
        linkedList.insertAfter(node4, node5);

        // Start the tests
        System.out.println("Initial List: ");
        printDoublyLinkedList(linkedList);

        // Test setHead()
        System.out.println("Test setHead():");
        linkedList.setHead(node4);
        System.out.println("After setting head to existing node4: ");
        printDoublyLinkedList(linkedList);

        linkedList.setHead(node6);
        System.out.println("After setting head to stand-alone node6: ");
        printDoublyLinkedList(linkedList);

        // Test setTail()
        System.out.println("Test setTail(): ");
        linkedList.setTail(node3);
        System.out.println("After setting tail to existing node3: ");
        printDoublyLinkedList(linkedList);

        linkedList.setTail(node7);
        System.out.println("After setting tail to stand-alone node7: ");
        printDoublyLinkedList(linkedList);

        // Test insertBefore()
        System.out.println("Test insertBefore(): ");
        linkedList.insertBefore(node6, node5);
        System.out.println("After inserting node5 before node6: ");
        printDoublyLinkedList(linkedList);

        linkedList.insertBefore(node4, node3);
        System.out.println("After inserting node3 before node4: ");
        printDoublyLinkedList(linkedList);

        // Test isnertAfter()
        System.out.println("Test insertAfter(): ");
        linkedList.insertAfter(node7, node8);
        System.out.println("After inserting stand-alone node8 after node7: ");
        printDoublyLinkedList(linkedList);

        linkedList.insertAfter(node2, node6);
        System.out.println("After inserting the existing node6 after node2: ");
        printDoublyLinkedList(linkedList);

        // Test insertAtPosition()
        System.out.println("Test insertAtPosition(): ");
        linkedList.insertAtPosition(99, node9);
        System.out.println("After inserting the stand-alone node9 at position 99: ");
        printDoublyLinkedList(linkedList);

        System.out.println("After inserting existing node4 at position 1: ");
        linkedList.insertAtPosition(1, node4);
        printDoublyLinkedList(linkedList);

        // Test removeNodesWithValue()
        System.out.println("Test removeNodesWithValue(): ");
        linkedList.removeNodesWithValue(3);
        System.out.println("After removing node with value 3: ");
        printDoublyLinkedList(linkedList);

        // Test remove()
        System.out.println("Test remove(): ");
        linkedList.remove(node4);
        System.out.println("After removing existing node4: ");
        printDoublyLinkedList(linkedList);

        linkedList.remove(node10);
        System.out.println("Tried to remove stand-alone node10");
        printDoublyLinkedList(linkedList);

        // Test containsNodeWithValue
        System.out.println("Test containsNodeWithValue(): ");
        System.out.println("Contains node with value 5? " + linkedList.containsNodeWithValue(5));
        System.out.println("Contains node with value 10? " + linkedList.containsNodeWithValue(10));

    }

    // Helper method to print out the linked list
    private static void printDoublyLinkedList(DoublyLinkedList linkedList){
        Node currentNode = linkedList.head;
        while(currentNode != null) {
            if(currentNode != linkedList.tail){
                System.out.printf("%d <-> ", currentNode.value);
            } else {
                System.out.printf("%d%n%n", currentNode.value);
            }
            currentNode = currentNode.next;
        }
    }
}

// Initial List: 
// 1 <-> 2 <-> 3 <-> 4 <-> 5

// Test setHead():
// After setting head to existing node4: 
// 4 <-> 1 <-> 2 <-> 3 <-> 5

// After setting head to stand-alone node6: 
// 6 <-> 4 <-> 1 <-> 2 <-> 3 <-> 5

// Test setTail(): 
// After setting tail to existing node3: 
// 6 <-> 4 <-> 1 <-> 2 <-> 5 <-> 3

// After setting tail to stand-alone node7: 
// 6 <-> 4 <-> 1 <-> 2 <-> 5 <-> 3 <-> 7

// Test insertBefore(): 
// After inserting node5 before node6: 
// 5 <-> 6 <-> 4 <-> 1 <-> 2 <-> 3 <-> 7

// After inserting node3 before node4: 
// 5 <-> 6 <-> 3 <-> 4 <-> 1 <-> 2 <-> 7

// Test insertAfter(): 
// After inserting stand-alone node8 after node7: 
// 5 <-> 6 <-> 3 <-> 4 <-> 1 <-> 2 <-> 7 <-> 8

// After inserting the existing node6 after node2: 
// 5 <-> 3 <-> 4 <-> 1 <-> 2 <-> 6 <-> 7 <-> 8

// Test insertAtPosition(): 
// After inserting the stand-alone node9 at position 99: 
// 5 <-> 3 <-> 4 <-> 1 <-> 2 <-> 6 <-> 7 <-> 8 <-> 9

// After inserting existing node4 at position 1: 
// 4 <-> 5 <-> 3 <-> 1 <-> 2 <-> 6 <-> 7 <-> 8 <-> 9

// Test removeNodesWithValue(): 
// After removing node with value 3: 
// 4 <-> 5 <-> 1 <-> 2 <-> 6 <-> 7 <-> 8 <-> 9

// Test remove(): 
// After removing existing node4: 
// 5 <-> 1 <-> 2 <-> 6 <-> 7 <-> 8 <-> 9

// Tried to remove stand-alone node10
// 5 <-> 1 <-> 2 <-> 6 <-> 7 <-> 8 <-> 9

// Test containsNodeWithValue(): 
// Contains node with value 5? true
// Contains node with value 10? false


