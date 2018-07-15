import java.util.Random;

public class randomGenerator {

	Random rand = new Random();

	public int[] getRandomArray(int size) {

		int[] out = new int[size];

		for (int i = 0; i < out.length; i++) {
			out[i] = rand.nextInt(size);
		}

		return out;

	}

}
