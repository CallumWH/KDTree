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
	private Node    parentNode;
	private Axis    currentAxis;

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

	public Node getParentNode()
	{
		return parentNode;
	}

	public void setParentNode(Node parentNode)
	{
		this.parentNode = parentNode;
	}

	public Node createTree(Vector2[] vector2Array)
	{
		return recursiveCreateNode(vector2Array, Axis.X, null);
	}

	private Node recursiveCreateNode(Vector2[] vector2Array, Axis sortDirection, Node parent)
	{
		if (vector2Array.length == 0)
		{
			Node node = new Node();
			node.setParentNode(parent);
			return node;
		}

		sortVectorArray(vector2Array, sortDirection);
		int  medianIndex = findMedian(vector2Array);
		Node medianNode  = new Node(vector2Array[medianIndex]);
		medianNode.currentAxis = sortDirection;
		medianNode.setParentNode(parent);
		Vector2[] leftOfMedian  = Arrays.copyOfRange(vector2Array, 0, medianIndex);
		Vector2[] rightOfMedian = Arrays.copyOfRange(vector2Array, medianIndex + 1, vector2Array.length);

		if (sortDirection == Axis.X)
		{
			medianNode.leftNode  = recursiveCreateNode(leftOfMedian, Axis.Y, medianNode);
			medianNode.rightNode = recursiveCreateNode(rightOfMedian, Axis.Y, medianNode);
		}
		else
		{
			medianNode.leftNode  = recursiveCreateNode(leftOfMedian, Axis.X, medianNode);
			medianNode.rightNode = recursiveCreateNode(rightOfMedian, Axis.X, medianNode);

		}

		return medianNode;
	}

	public double getClosestNeighbourDistance(Vector2 targetVector, Node treeRoot)
	{
		Node   bottomLeaf   = findClosestLeafRecursive(targetVector, treeRoot);
		double bestDistance = getNighborDistanceRecursive(targetVector, bottomLeaf, -1);

		return bestDistance;
	}

	private Node findClosestLeafRecursive(Vector2 targetCoordinates, Node root)
	{

		Node target = new Node();

		if (root.getVector() != null)
		{
			if (root.currentAxis == Axis.X)
			{
				if (targetCoordinates.getX() >= root.getVector().getX())
				{
					target = findClosestLeafRecursive(targetCoordinates, root.getRight());
				}
				else
				{
					target = findClosestLeafRecursive(targetCoordinates, root.getLeft());
				}
			}
			else
			{
				if (targetCoordinates.getY() >= root.getVector().getY())
				{
					target = findClosestLeafRecursive(targetCoordinates, root.getRight());
				}
				else
				{
					target = findClosestLeafRecursive(targetCoordinates, root.getLeft());
				}
			}
		}
		else
		{
			target = root.getParentNode();
		}

		return target;
	}

	private double getNighborDistanceRecursive(Vector2 targetCoordinates, Node currentNode, double bestDistance)
	{
		double currentDistance = calculateDistance(targetCoordinates, currentNode.getVector());

		
		if(targetCoordinates.getX() == 9998616)				
		{
			int i = 1;
			i = 2;
		}			
		
		if (currentDistance > 0)
		{
			if (bestDistance > currentDistance || bestDistance < 0)
			{
				bestDistance = currentDistance;
			}
		}

		if (currentNode.getParentNode() != null)
		{
			bestDistance = getNighborDistanceRecursive(targetCoordinates, currentNode.getParentNode(), bestDistance);

			// check sibling if there is any closer points than the parent
			Node oppositeNode;
			int  distanceApart;

			if (currentNode.getParentNode().getLeft() != currentNode)
			{
				oppositeNode = currentNode.getParentNode().getLeft();
			}
			else
			{
				oppositeNode = currentNode.getParentNode().getRight();
			}

			if (currentNode.getParentNode().currentAxis == Axis.X)
			{
				distanceApart = currentNode.getParentNode().getVector().getX() - targetCoordinates.getX();
			}
			else
			{
				distanceApart = currentNode.getParentNode().getVector().getY() - targetCoordinates.getY();
			}

			if (distanceApart < 0)
			{
				distanceApart *= -1;
			}

			if (distanceApart < bestDistance)
			{
				
				if(currentNode.getParentNode().getParentNode() == null)
				{
					int i = 1;
					i = 1;
				}
				
				Node newLeaf = findClosestLeafRecursive(targetCoordinates, oppositeNode);
				bestDistance = recursiveCheckBranch(targetCoordinates, newLeaf, currentNode.getParentNode(),
						bestDistance);
			}
		}

		return bestDistance;
	}

	private double recursiveCheckBranch(Vector2 targetCoordinates, Node currentNode, Node endNode, double bestDistance)
	{

		double currentDistance = calculateDistance(targetCoordinates, currentNode.getVector());
		
		if(targetCoordinates.getX() == 9998616)				
		{
			int i = 1;
			i = 2;
		}	
		
		if (currentDistance > 0)
		{		
			if (bestDistance > currentDistance || bestDistance < 0)
			{
				bestDistance = currentDistance;
			}
		}

		if (currentNode != endNode)
		{

			bestDistance = recursiveCheckBranch(targetCoordinates, currentNode.getParentNode(), endNode, bestDistance);

			if (currentNode.getParentNode() != endNode)
			{
				// check sibling if there is any closer points than the parent
				Node oppositeNode;

				if (currentNode.getParentNode().getLeft() != currentNode)
				{
					oppositeNode = currentNode.getParentNode().getLeft();
				}
				else
				{
					oppositeNode = currentNode.getParentNode().getRight();
				}

				if (oppositeNode.getVector() != null)
				{
					int distanceApart;

					if (currentNode.getParentNode().currentAxis == Axis.X)
					{
						distanceApart = currentNode.getParentNode().getVector().getX() - targetCoordinates.getX();
					}
					else
					{
						distanceApart = currentNode.getParentNode().getVector().getY() - targetCoordinates.getY();
					}

					if (distanceApart < 0)
					{
						distanceApart *= -1;
					}

					if (distanceApart < bestDistance)
					{
						Node newLeaf = findClosestLeafRecursive(targetCoordinates, oppositeNode);
						bestDistance = recursiveCheckBranch(targetCoordinates, newLeaf, currentNode.getParentNode(),
								bestDistance);
					}
				}
			}
		}

		return bestDistance;
	}

	private int findMedian(Vector2[] vector2Array)
	{
		return vector2Array.length / 2;
	}

	private double calculateDistance(Vector2 target, Vector2 origin)
	{
		double x = Math.pow(target.getX() - origin.getX(), 2);
		double y = Math.pow(target.getY() - origin.getY(), 2);

		return Math.sqrt(x + y);
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
			Arrays.sort(vector2Array, yCompare);
		}
	}
}