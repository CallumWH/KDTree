import java.util.Arrays;

class Node
{
	enum Axis
	{
		X, Y;
	}

	private Vector2 vector;
	private Node    leftNode;
	private Node    rightNode;
	private Axis currentAxis;

	public Node()
	{}

	public Node(Vector2 vector)
	{
		this.vector = vector;
		rightNode   = null;
		leftNode    = null;
	}

	public Vector2 getVector()
	{
		return vector;
	}

	public Node getLeft()
	{
		return leftNode;
	}

	public Node getRight()
	{
		return rightNode;
	}

	public Node createTree(Vector2[] vector2Array)
	{
		return recursiveCreateNode(vector2Array, Axis.X);
	}

	private Node recursiveCreateNode(Vector2[] vector2Array, Axis sortDirection)
	{
		if(vector2Array.length == 0)
		{
			return null;
		}
		
		sortVectorArray(vector2Array, sortDirection);
		int       medianIndex   = findMedian(vector2Array);
		Node      medianNode    = new Node(vector2Array[medianIndex]);
		medianNode.currentAxis = sortDirection;
		Vector2[] leftOfMedian  = Arrays.copyOfRange(vector2Array, 0, medianIndex);
		Vector2[] rightOfMedian = Arrays.copyOfRange(vector2Array, medianIndex + 1, vector2Array.length);

		if (sortDirection == Axis.X)
		{
			medianNode.leftNode = recursiveCreateNode(leftOfMedian, Axis.Y);	
			medianNode.rightNode = recursiveCreateNode(rightOfMedian, Axis.Y);
		}
		else
		{		
			medianNode.leftNode = recursiveCreateNode(leftOfMedian, Axis.X);		
			medianNode.rightNode = recursiveCreateNode(rightOfMedian, Axis.X);
			
		}

		return medianNode;
	}
	
	public void recursivePrintNode(Node root)
	{
		if(root == null)
		{
			return;
		}
		
		System.out.println(root.getVector().OutputString());
		
		recursivePrintNode(root.getRight());
		recursivePrintNode(root.getLeft());	
	}

	private int findMedian(Vector2[] vector2Array)
	{
		return vector2Array.length / 2;
	}

	private void sortVectorArray(Vector2[] vector2Array, Axis sortDirection)
	{
		if (sortDirection == Axis.X)
		{
			XCompare xCompare = new XCompare();
			Arrays.sort(vector2Array, xCompare);
		}
		else
		{
			YCompare yCompare = new YCompare();
			Arrays.parallelSort(vector2Array, yCompare);
		}
	}
}