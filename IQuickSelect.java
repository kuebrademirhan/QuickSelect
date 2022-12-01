/**
 * Classes implementing this interface are used for identifying the k-smallest element in an array of length n in linear runtime O(n).
 * <p>
 * The technique is based on the idea of QuickSort but <i>avoids</i> sorting the entire input.
 * Instead, the recursive descent occurs only on one side of the pivot position (if required at all).
 * </p>
 * After choosing a pivot element and partitioning the array according to QuickSort, the pivot is placed into its final position:
 * <ul>
 *     <li>if its position is at index k, then the pivot is the desired element and can be returned immediately</li>
 *     <li>if the position of the pivot is less than k, the search continues recursively on the upper sub-array ("right of the pivot")</li>
 *     <li>if the position of the pivot is greater than k, the search continues recursively on the lower sub-array ("left of the pivot")</li>
 * </ul>
 * <b>Note that finding the k-smallest value must be implemented in linear runtime O(n) for n input elements.</b>
 *
 * @param <E> the type of elements in the array
 */
public interface IQuickSelect<E extends Comparable<? super E>> {
	/**
	 * Swaps the objects at index i and j in the given array.
	 *
	 * @param array the array to swap values in-place
	 * @param i     the index of the first element
	 * @param j     the index of the second element
	 * @return the input array
	 */
	E[] swap(E[] array, int i, int j);

	/**
	 * Chooses the <b>median</b> value out of the first, middle and last value from the given array within the interval [startIdx, endIdx] (both inclusive) and returns its index.
	 * <p>
	 * If the number of elements in the interval is even, then the index of the middle value is computed rounding down according to the Java integer rounding mode.
	 * In case of a tie, the smaller index is returned.
	 * </p>
	 * Example:
	 * <p>
	 * For input <kbd>[2, 5, 1, -5, 3]</kbd> this method would return <kbd>0</kbd>, as this is the index of value <kbd>2</kbd>, which is the median value from <kbd>{2, 1, 3}</kbd>.
	 * </p>
	 *
	 * @param array    the array to choose the pivot from
	 * @param startIdx the first index of the interval (inclusive)
	 * @param endIdx   the last index of the interval (inclusive)
	 * @return the index of the chosen median pivot element from the array within the interval [startIdx, endIdx]
	 */
	int choosePivot(E[] array, int startIdx, int endIdx);

	/**
	 * Partitions the elements in the given array within the interval [startIdx, endIdx] (both inclusive) according to the QuickSort algorithm
	 * and returns the resulting final position (index) of the given pivot after partitioning the interval.
	 * <p>After this method returns, the specified array interval first contains all elements less than or equal to the pivot element,
	 * followed by the pivot itself and finally contains all other elements greater than the pivot.</p>
	 *
	 * @param array    the array to partition according to the QuickSort algorithm
	 * @param pivotIdx the index of the pivot element to be used for partitioning
	 * @param startIdx the first index of the interval (inclusive)
	 * @param endIdx   the last index of the interval (inclusive)
	 * @return the new index of the pivot element after partitioning
	 */
	int partition(E[] array, int pivotIdx, int startIdx, int endIdx);

	/**
	 * Returns the k-smallest element from the specified array in linear runtime O(n) for n input elements using
	 * {@link #choosePivot(Comparable[], int, int)} to select a good pivot element and
	 * {@link #partition(Comparable[], int, int, int)} to rearrange the input array
	 * in order to decide which sub-array to search for recursively (if required).
	 * <p>
	 * Example: For k == 0, this method returns the smallest value.
	 * </p>
	 * <b>Note that finding the k-smallest value must be implemented in linear runtime O(n) for n input elements.</b>
	 *
	 * @param array the array to search for the k-smallest value
	 * @param k     the index of the element if the array would be sorted in ascending order
	 * @return the k-smallest element from the specified array
	 */
	E quickSelect(E[] array, int k);
}