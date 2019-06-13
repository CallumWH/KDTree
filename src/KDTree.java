import java.io.IOException;

public class KDTree
{
	private Node root = new Node();

	public KDTree()
	{}

	public void createKDTree(Vector2[] vector2Array)
	{
		root = root.createTree(vector2Array);
	}

	public Node getRoot()
	{
		return root;
	}

	public Double findClosestNeighbor(Vector2 targetVector2) throws IOException
	{
		if (root.getVector() != null)
		{
			return root.getClosestNeighbourDistance(targetVector2, root);
		}
		else
		{
			throw new IOException("Root node not found / tree does not exist");
		}
	}

	public Vector2 findMostIsolatedCoordinate(Vector2[] vector2Array)
	{
		double  bestDistance = -1;
		Vector2 mostIsolatedCoordinate = new Vector2(-1, -1);
		
		String testString = "";

		for (int i = 0; i < vector2Array.length; i++)
		{
			double currentDistance = -1;
			try
			{
				currentDistance = findClosestNeighbor(vector2Array[i]);
				System.out.println(vector2Array[i].OutputString() + " - Distance : " + currentDistance);
			}
			catch (IOException e)
			{
				System.out.println("Tree not found / first element missing");
			}

			if (currentDistance > bestDistance || bestDistance < 0)
			{
				bestDistance = currentDistance;
				mostIsolatedCoordinate = vector2Array[i];
			}
			
			if(vector2Array[i].getOriginalIndex() == 5771)
			{
				testString = new String("place55163 : " + currentDistance);
			}
		}
		
		System.out.println("Highest Distance = " + mostIsolatedCoordinate.OutputString() + " : " + bestDistance);
		System.out.println(testString);
		return mostIsolatedCoordinate;
	}

}