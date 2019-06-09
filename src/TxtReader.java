import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtReader {
	
	public List<String> ReadStringFromFile(String filePath) throws IOException
	{
		List<String> returnList = new ArrayList<String>();
		FileReader fr = new FileReader(filePath);		
		BufferedReader textReader = new BufferedReader(fr);
		String output;
		
		while((output = textReader.readLine()) != null)
		{
			returnList.add(output);
		}
		
		textReader.close();
		
		
		return returnList;
	}

}
