package collab;

/**
 @author zbarryte
 @purpose fighting crime
 @weapon justice
 @rival Dr. Barron Archibald Helmington
 */
public class ZBarryte {

	@Override
	public String toString() {
		String scribbles = "Scribbles";
		String name = "";
		for (int index = 0; index < scribbles.length(); index++) {
			if (Math.random() < 0.5) {
				name += scribbles.charAt(index);
			}
		}
		return name;
	}
	
}