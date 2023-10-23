import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class ParallelSorting {
	public static void main(String[] args) {
//		int[] numbers = ThreadLocalRandom.current().ints(100_000_000).toArray();
		int[] numbers = IntStream.range(0, 100_000_000).toArray();
		for(int i = 0; i < 10; ++i) {
			testSorting(numbers);
		}
	}

	private static void testSorting(int[] numbers) {
		int[] numbersSeq = numbers.clone();
		int [] numbersParallel = numbers.clone();
		sort("seq", numbersSeq, Arrays::sort);
		sort("parallel", numbersParallel, Arrays::parallelSort);
	}

	private static void sort(String description, int[] numbers, Consumer<int[]> sortingMethod) {
		long time = System.currentTimeMillis();
		try {
			sortingMethod.accept(numbers);
		} finally {
			time = System.currentTimeMillis() - time;
			System.out.println(description + " time  = " + time + "ms");
		}
	}
}
