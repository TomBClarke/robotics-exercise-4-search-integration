// Sample usage of immutable lists implemented using the composite pattern.
// See first the interface file IList.java.

package ilist;

public class SampleUsage {
    
  public static void main(String[] args) {
    // The list [1,2,3] = Cons(1, Cons(2, Cons(3, Nil)))

    IList<Integer> l1 = new Cons<Integer> (1, 
                       new Cons<Integer> (2,
   		       new Cons<Integer> (3, 
                       new Nil<Integer>  () )));

    IList<Integer> l1again = new Cons<Integer> (1, 
                       new Cons<Integer> (2,
   		       new Cons<Integer> (3, 
                       new Nil<Integer>  () )));

    System.out.println(l1 == l1again); // Prints false. Why?
    


    IList<Integer> l2 = new Cons<Integer> (7, 
                       new Cons<Integer> (8,
   		       new Cons<Integer> (9, 
                       new Nil<Integer>  () )));

    IList<Integer> l3 = l1.append(l2); // Doesn't change l1.
    IList<Integer> l4 = l3.reverse();  // Doesn't change l3.

    System.out.println(l1 + " has size " + l1.size());
    System.out.println(l3 + " has size " + l3.size());
    System.out.println(l4 + " has size " + l4.size());

    System.out.println(l4 + " has the element 3 is " + l4.has(3));
    System.out.println(l4 + " has the element 17 is " + l4.has(17));
  }
    
}
