import java.util.Comparator;

public class XCompare implements Comparator<Vector2>
{
	public int compare (Vector2 v1, Vector2 v2)
	{
		return Integer.compare(v1.getX(), v2.getX());
	}
}