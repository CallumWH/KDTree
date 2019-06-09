import java.util.ArrayList;
import java.util.List;

public class CoordinateFilter
{
	Vector2[] FilterStringList(List<String> coordinateStringList)
	{
		List<Vector2> formatedList = new ArrayList<Vector2>();
		for (int i = 0; i < coordinateStringList.size(); i++)
		{
			formatedList.add(Parser(coordinateStringList.get(i)));
			formatedList.get(i).setOriginalIndex(i);
		}
		return formatedList.toArray(new Vector2[formatedList.size()]);
	}

	private Vector2 Parser(String string)
	{
		Vector2 returnVector2 = new Vector2(-1, -1);
		String  workingString = "";
		boolean tagRemoved    = false;

		for (int i = 0; i < string.length(); i++)
		{

			if (!tagRemoved)
			{
				if (string.subSequence(i, i + 1).equals(" "))
				{
					tagRemoved = true;
				}
				continue;
			}

			if (string.subSequence(i, i + 1).equals(" "))
			{
				returnVector2.setX(Integer.parseInt(workingString));
				workingString = "";
				continue;
			} else if (i == string.length() - 1)
			{
				workingString += string.charAt(i);
				returnVector2.setY(Integer.parseInt(workingString));
				continue;
			} else
			{
				workingString += string.charAt(i);
			}
		}

		return returnVector2;

	}

}
