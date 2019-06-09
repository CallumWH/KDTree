public class KDTree
{
	private Node root = new Node();

	public KDTree()
	{}

	public void createKDTree(Vector2[] vector2Array)
	{		
		root = root.createTree(vector2Array);
	}

	public void printKDTree()
	{
		if (root != null)
		{
			root.recursivePrintNode(root);
		}
	}

	public Node getRoot()
	{
		return root;
	}

}