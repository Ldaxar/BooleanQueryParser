package stargarth.parser;

public class AbstractSyntaxTree {
	private AbstractSyntaxTree parent = null;
	private AbstractSyntaxTree leftChild = null;
	private AbstractSyntaxTree rightChild = null;

	private int weight;
	private String data;

	public AbstractSyntaxTree(String data) {
		this.data = data;
	}

	public AbstractSyntaxTree(AbstractSyntaxTree parent, String data) {
		this.parent = parent;
		this.data = data;
	}

	public void bindNode(AbstractSyntaxTree newNode) {
		// If new node is lighter or equal to this node - go up
		if (this.weight >= newNode.getWeight()) {
			if (this.parent == null) newNode.setLeftChild(this);
			else this.parent.bindNode(newNode);
		} 
		else if (leftChild == null)  setLeftChild(newNode);
		else if (rightChild == null) setRightChild(newNode);
		else {
			//If newNode is heavier than THIS and THIS has left and right child
			//Bind rightChild of THIS to newNode and replace rightChild of THIS with newNode
			newNode.bindNode(rightChild);
			setRightChild(newNode);
		}
	}

	public int getWeight() {
		return weight;
	}
	
	public AbstractSyntaxTree getRoot() {
		if (parent != null) return parent.getRoot();
		else return this;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public AbstractSyntaxTree getParent() {
		return parent;
	}

	public void setParent(AbstractSyntaxTree parent) {
		this.parent = parent;
	}

	public AbstractSyntaxTree getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(AbstractSyntaxTree leftChild) {
		leftChild.setParent(this);
		this.leftChild = leftChild;
	}

	public AbstractSyntaxTree getRightChild() {
		return rightChild;
	}

	public void setRightChild(AbstractSyntaxTree rightChild) {
		rightChild.setParent(this);
		this.rightChild = rightChild;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
