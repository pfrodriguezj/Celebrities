package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVCelebSource implements ICelebSource{

	public static void main(String[] args) {
		String source= "C:\\workspaceSTS\\Celebrities\\celebrities.csv";
		CSVCelebSource o = new CSVCelebSource();
		try {
			List<List<Boolean>> mapa = o.readSource(source);
			for(List<Boolean> row:mapa) {
				for(Boolean b:row) {
					System.out.print(b + " ");
				}
				System.out.println("");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	/**
	 * Read a source csv of knokwns: for each position, 1 means true (x knows y), 
	 * 0 means false (x doesn't know y)
	 * @param sourcePath path to find data source, including file name
	 * @return knowns boolean matrix 
	 */
	public List<List<Boolean>> readSource(String sourcePath) throws FileNotFoundException, IOException {
		List<List<Boolean>> records = new ArrayList<>();
		File file = new File(sourcePath);
		InputStream inputStream = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		// skip the header of the csv
		records = br.lines().map(mapToItem).collect(Collectors.toList());
		br.close();
		return records;
	}

	private Function<String, List<Boolean>> mapToItem = (line) -> {
		  String[] cells = line.split(COMMA_DELIMITER);// a CSV has comma separated lines
		  List<Boolean> knows = new ArrayList<Boolean>();
		  for(String c:cells) {
			  knows.add(c != null && c.equals("1")?true:false);
		  }
		  return knows;
	};
}