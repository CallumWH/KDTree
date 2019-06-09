import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main
{

	public static void main(String[] args)
	{

		TxtReader        txtReader      = new TxtReader();
		CoordinateFilter coFilter       = new CoordinateFilter();
		List<String>     coordinateList = new ArrayList<String>();
		KDTree           kDTree         = new KDTree();

		try
		{
			coordinateList = txtReader.ReadStringFromFile("src/FilesToRead/problem_big.txt");
		}
		catch (IOException e)
		{
			System.out.println("file not found");
		}

		Vector2[] vector2Array = coFilter.FilterStringList(coordinateList);
		kDTree.createKDTree(vector2Array);
		kDTree.printKDTree();
		
		System.out.println("done");
	}

}
