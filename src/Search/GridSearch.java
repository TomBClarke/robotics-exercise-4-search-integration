package Search;

import ilist.Cons;
import ilist.IList;
import ilist.Nil;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import maybe.Just;
import maybe.Maybe;
import maybe.Nothing;
import robotSearches.AStarFunctions;
import robotSearches.Container;
import robotSearches.Node;
import rp.robotics.mapping.RPLineMap;

public class GridSearch<A> {
	private RPLineMap lineMap;

	public GridSearch(RPLineMap lineMap) {
		this.lineMap=lineMap;
	}
	
		public Maybe<IList<Node<A>>> findPathFromGeneralisation(Node<A> origin, Node<A> destination, AStarFunctions<Node<A>> functions, Container<Node<A>> container){
			Set<Node<A>> visited = new HashSet<Node<A>>();
			Hashtable<Node<A>, Double> estimatedTotalCost = new Hashtable<Node<A>, Double>();
			Hashtable<Node<A>, Node<A>> predecessors = new Hashtable<Node<A>, Node<A>>();
			Hashtable<Node<A>, Double> Di = new Hashtable<Node<A>, Double>();
			Di.put(origin, 0.0);
			estimatedTotalCost.put(origin, functions.heuristicFunction(origin, destination));
			container.add(origin);
			
			while(!container.isEmpty()){
				Node<A> node = container.poll();
				//if the node from the queue has the same coordinates as the destination node, 
				//then we have found our destination and we return the path.
				if(node.contentsEquals(destination.contents())){
					System.out.println("found the Node, calculating path...");
					IList<Node<A>> path = new Cons<Node<A>>(node,new Nil<Node<A>>());
					while (predecessors.containsKey(node)) {
						path = new Cons<Node<A>>((predecessors.get(node)),path);
						node = predecessors.get(node);						
					} 
					//finally return the list of nodes, which is the path to follow to get from one node to another.
					return new Just<IList<Node<A>>>(path);
				}
				
				visited.add(node);//We add that node to the visited set.
				Set<Node<A>> successors = node.successors();
				for (Iterator<Node<A>> iterator = successors.iterator(); iterator.hasNext();) {
					Node<A> node2 = (Node<A>) iterator.next();
					if(!visited.contains(node2)){
						Double cost = Di.get(node) + functions.distanceFunction(node, node2);
						
						if(!container.contains(node2) || cost<Di.get(node2)){
							predecessors.put(node2, node);
							Di.put(node2, cost);
							estimatedTotalCost.put(node2, cost + functions.heuristicFunction(node2, destination));
							if(!container.contains(node2))
								container.add(node2);
						}
					}
					
				}
			}
			return new Nothing<IList<Node<A>>>();		
		}
	

	public static void main(String args[]){
	
	}
	
}

