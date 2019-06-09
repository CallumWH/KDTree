import java.util.Comparator;

public class YCompare implements Comparator<Vector2>
{
	public int compare (Vector2 v1, Vector2 v2)
	{
		return Integer.compare(v1.getY(), v2.getY());
	}
}