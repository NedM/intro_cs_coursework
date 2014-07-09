package collab;

import java.util.ArrayList;

/**
 * 
 * @author bhos
 *
 */
public class BHos {

	@Override
	public String toString() {
		String result = new String();
		ArrayList<String> sentence = new ArrayList<String>();
		sentence.add("this"); sentence.add("is"); sentence.add("a"); sentence.add("random"); sentence.add("sentence");
		loop:
		while (sentence.size() >0) {
			double x = Math.random();
			for (int i=0;i<sentence.size();i++) {
				if (x> ((float) i)/((float)sentence.size()) && x <= ((float) (i+1))/((float) sentence.size())) {
					result += " " + sentence.get(i);
					sentence.remove(i);
					continue loop;
				}
			}			
		}
		result = result.substring(1);
		return "<bhos>" + result;
	}
}
