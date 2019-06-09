import java.lang.Math;

public class Vector2
{
	private int x;
	private int y;
	private int originalIndex;

	public Vector2(int x, int y)
	{
		setX(x);
		setY(y);
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getOriginalIndex()
	{
		return originalIndex;
	}

	public void setOriginalIndex(int originalIndex)
	{
		this.originalIndex = originalIndex;
	}

	public String OutputString()
	{
		return new String(x + " " + y + " Original Index : " + originalIndex);
	}

	public double getDistance(Vector2 targetV2)
	{
		Vector2 compositVector2 = new Vector2(targetV2.x - x, targetV2.y - y);
		return Math.sqrt(Math.pow(compositVector2.x, 2) + Math.pow(compositVector2.y, 2));
	}

	public double getDistance(int inputX, int inputY)
	{
		Vector2 compositVector2 = new Vector2(inputX - x, inputY - y);
		return Math.sqrt(Math.pow(compositVector2.x, 2) + Math.pow(compositVector2.y, 2));
	}
	
	
}