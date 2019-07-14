package stargarth.irrelevant;

import stargarth.parser.AbstractSyntaxTree;

public class Printer {
	public AbstractSyntaxTree getRoot(AbstractSyntaxTree n) {
		AbstractSyntaxTree currentNode =n ;
		while(currentNode.getParent()!=null) {
			currentNode=currentNode.getParent();
		}
		
		return currentNode;
	}
	
	public void printTree(AbstractSyntaxTree n, String comment, int level) {
		if (n==null) return;
		if (n.getParent()!=null)
			System.out.println(n.getData().toString() +" "+level+" "+comment+". Parent: "+n.getParent().getData().toString()+" weight:"+n.getWeight());
		else System.out.println(n.getData().toString() +" "+level+" "+comment+" weight:"+n.getWeight());
		level+=1;
		printTree(n.getLeftChild(),"left", level);
		printTree(n.getRightChild(), "right", level);
		
	}
}
