import java.io.*;
import java.util.*;

public class LinkedList<T> {
	private Node<T> head; // pointer to the front (first) element of the list

	public LinkedList() {
		head = null; // compiler does this anyway. just for emphasis
	}

	@SuppressWarnings("unchecked")
	// LOAD LINKED LIST FORM INCOMING FILE
	public LinkedList(String fileName, boolean orderedFlag) {
		head = null;
		try {
			BufferedReader infile = new BufferedReader(new FileReader(fileName));
			while (infile.ready()) {
				if (orderedFlag)
					insertInOrder((T) infile.readLine()); // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail((T) infile.readLine()); // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER
															// PRESERVED
			}
			infile.close();
		} catch (Exception e) {
			System.out.println("FATAL ERROR CAUGHT IN C'TOR: " + e);
			System.exit(0);
		}
	}

	// -------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place
	@SuppressWarnings("unchecked")
	public void insertAtFront(T data) {
		head = new Node(data, head);
	}

	// we use toString as our print
	public String toString() {
		String toString = "";

		for (Node curr = head; curr != null; curr = curr.getNext()) {
			toString += curr.getData(); // WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.getNext() != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U W R I T E T H E S E M E T H O D S
	// ########################

	public int size() {
		int counter = 0;
		for (Node curr = head; curr != null; curr = curr.getNext())
			counter++;
		return counter;
	}

	public boolean empty() {
		return (size() == 0) ? true : false;
	}

	public boolean contains(T key) {
		return (search(key) != null) ? true : false;
	}

	@SuppressWarnings("unchecked")
	public Node<T> search(T key) {
		if (empty() || head == null)
			return null;
		for (Node curr = head; curr != null; curr = curr.getNext())
			if (curr.getData().equals(key))
				return curr;
		return null;
	}

	@SuppressWarnings("unchecked")
	public void insertAtTail(T data) // TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	{
		if (head == null)
			insertAtFront(data);
		else {
			Node<T> temp = head;
			while (true) {
				if (temp.getNext() != null)
					temp = temp.getNext();
				else {
					temp.setNext(new Node(data));
					break;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void insertInOrder(T data) // PLACE NODE IN LIST AT ITS SORTED ORDER POSTIOPN
	{
		Comparable dataC = (Comparable<T>) data;
		if (head == null || dataC.compareTo(head.getData()) <= 0) {
			insertAtFront(data);
			return;
		}
		Node next = head.getNext();
		Node prev = head;
		while (next != null) {
			if (dataC.compareTo(next.getData()) <= 0)
				break;
			prev = next;
			next = next.getNext();
		}
		Node newNode = new Node(data, prev.getNext());
		prev.setNext(newNode);
	}

	@SuppressWarnings("unchecked")
	public boolean remove(T key) // FIND/REMOVE 1st OCCURANCE OF A NODE CONTAINING KEY
	{
		if (head == null)
			return false;
		if (head.getData().equals(key)) {
			head = head.getNext();
			return true;
		}
		Node curr = head;
		Node prev = null;
		while (curr != null && !curr.getData().equals(key)) {
			prev = curr;
			curr = curr.getNext();
		}
		if (curr == null)
			return false;
		prev.setNext(curr.getNext());
		return true;
	}

	public boolean removeAtTail() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if (head == null)
			return false;
		else if (head.getNext() == null){
			head = null;
			return true;
		}
		else {
			Node curr = head;
			Node afterCurr = head.getNext();
			while (afterCurr.getNext() != null){
				curr = afterCurr;
				afterCurr = afterCurr.getNext();
			}
			curr.setNext(null);
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if (head == null)
			return false;
		head = head.getNext();
		return true;
	}

	@SuppressWarnings("unchecked")
	public LinkedList<T> union(LinkedList<T> other) {
		LinkedList<T> unionList = new LinkedList<T>();
		for (Node curr = head; curr != null; curr = curr.getNext())
			if (!unionList.contains((T)curr.getData()))
				unionList.insertInOrder((T) curr.getData());
		for (Node curr = other.head; curr != null; curr = curr.getNext())
			if (!unionList.contains((T)curr.getData()))
				unionList.insertInOrder((T)curr.getData());
		return unionList;
	}

	@SuppressWarnings("unchecked")
	public LinkedList<T> inter(LinkedList<T> other) {
		LinkedList<T> interList = new LinkedList<T>();
		for (Node curr = head; curr != null; curr = curr.getNext())
			if (other.contains((T) curr.getData()) && !interList.contains((T) curr.getData()))
				interList.insertInOrder((T) curr.getData());
		return interList;
	}
	@SuppressWarnings("unchecked")
	public LinkedList<T> diff(LinkedList<T> other) {
		LinkedList<T> diffList = new LinkedList<T>();
		for (Node curr = head; curr != null; curr = curr.getNext())
			if (!other.contains((T) curr.getData()) && !diffList.contains((T) curr.getData()))
				diffList.insertInOrder((T) curr.getData());
		return diffList;
	}
	public LinkedList<T> xor(LinkedList<T> other) {
		return union(other).diff(inter(other));
	}
} // END LINKEDLIST CLASS
