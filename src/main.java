import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class main {

	public static void main(String[] args) {

		int ammountOfTests = 7;

		int sortedAreaSize[] = { 20, 100 };

		int[] testValues = { 1000, 10000, 100000, 1000000, 10000000, 100000000 };

		double[][][] resultsSekv = new double[testValues.length][sortedAreaSize.length][ammountOfTests];

		double[][][] resultsPar = new double[testValues.length][sortedAreaSize.length][ammountOfTests];

		randomGenerator rand = new randomGenerator();

		int[] randomArraySekv;

		int[] randomArrayPar = null;

		sekv sekv;

		par par;

		Timer timer = new Timer();

		for (int i = 0; i < testValues.length; i++) {

			for (int x = 0; x < sortedAreaSize.length; x++) {

				for (int y = 0; y < ammountOfTests; y++) {

					randomArraySekv = rand.getRandomArray(testValues[i]);

					randomArrayPar = randomArraySekv;

					System.arraycopy(randomArraySekv, 0, randomArrayPar, 0, randomArraySekv.length);

					sekv = new sekv(randomArraySekv, 0, randomArraySekv.length - 1, sortedAreaSize[x]);

					par = new par(randomArrayPar, sortedAreaSize[x]);

					timer.start();
					sekv.sort();
					resultsSekv[i][x][y] = timer.stop();

					testSolution(randomArraySekv, sortedAreaSize[x]);

					timer.reset();
					timer.start();
					par.sort();
					resultsPar[i][x][y] = timer.stop();

					testSolution(randomArrayPar, sortedAreaSize[x]);

				}

			}

		}
		System.out.println("All tests passed \n");

		for (int i = 0; i < testValues.length; i++) {

			for (int x = 0; x < sortedAreaSize.length; x++) {

				Arrays.sort(resultsSekv[i][x]);
				System.out.println(
						"Sekv n = " + testValues[i] + " k = " + sortedAreaSize[x] + " time = " + resultsSekv[i][x][5]);

				Arrays.sort(resultsPar[i][x]);
				System.out.println(
						"Par n =  " + testValues[i] + " k = " + sortedAreaSize[x] + " time = " + resultsPar[i][x][5]);

				System.out.println("Sekv/Par = " + (resultsSekv[i][x][5] / resultsPar[i][x][5]) + "\n");

			}
		}

		try {
			PrintWriter writer = new PrintWriter(new File("Results.txt"));

			for (int i = 0; i < testValues.length; i++) {

				for (int x = 0; x < sortedAreaSize.length; x++) {

					writer.println("N = " + testValues[i] + " K = " + sortedAreaSize[x]);
					writer.println("Sekv = " + resultsSekv[i][x][5]);

					writer.println("Par  = " + resultsPar[i][x][5]);

					writer.println("Sekv/Par = " + (resultsSekv[i][x][5] / resultsPar[i][x][5]) + "\n");
					writer.println("----------------------------");

				}
				writer.println();
				writer.println();
			}

			writer.close();

		} catch (FileNotFoundException e) {
			System.out.println("Printers jammed m8");
			e.printStackTrace();
		}

	}

	static void testSolution(int[] a, int sortedSize) {
		// first, verify that there are no larger values in the array ,than the
		// smallest one in
		// the sorted area
		for (int i = sortedSize; i < a.length; i++) {
			if (a[i] > a[sortedSize - 1]) {
				System.out.println(
						"Error 1 in index " + i + " " + a[i] + " > " + a[sortedSize - 1] + " " + (sortedSize - 1));

				System.exit(0);
			}
		}
		// then, verify that the area is sorted correctly
		for (int i = 0; i < sortedSize; i++) {
			if (a[i + 1] > a[i]) {

				System.out.println("Error 2 in index " + i + " " + a[i]);

				System.exit(0);
			}
		}
		//System.out.println("Test passed");
	}

	static void print(int[] inn, int ammount) {
		for (int i = 0; i < ammount; i++) {
			System.out.println(inn[i]);
		}
	}
}
