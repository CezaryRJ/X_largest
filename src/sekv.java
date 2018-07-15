
public class sekv {

	int[] numbers;
	int start;
	int end;
	int sortedArea;

	int tmp = 0;

	sekv(int[] numbers, int start, int end, int sortedArea) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
		this.sortedArea = sortedArea;
	}

	void sort() {

		insertSort(numbers, start, (start + sortedArea));

		for (int i = sortedArea - 1; i <= end; i++) {
			if (numbers[i] > numbers[sortedArea - 1]) {
				// swap values
				tmp = numbers[i];
				numbers[i] = numbers[sortedArea - 1];
				numbers[sortedArea - 1] = tmp;
				insertOneElement(numbers, start, (start + sortedArea - 1));

			}

		}

	}

	/** Denne sorterer a[v..h] i stigende rekkefolge med innstikk-algoritmen */
	void insertSort(int[] a, int v, int h) {
		int i, t;
		for (int k = v; k < h; k++) {
			// invariant: a[v..k] er naa sortert stigende (minste forst)
			t = a[k + 1];
			i = k;
			while (i >= v && a[i] < t) {
				a[i + 1] = a[i];
				i--;
			}
			a[i + 1] = t;
		} // end for k
	} // end insertSort

	void insertOneElement(int[] a, int start, int end) {

		int t, i;
		t = a[end];
		i = end - 1;
		while (i >= start && a[i] < t) {
			a[i + 1] = a[i];
			i--;
		}
		a[i + 1] = t;
	}

}
