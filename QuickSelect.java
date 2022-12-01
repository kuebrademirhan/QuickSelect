public class QuickSelect<T extends Comparable<? super T>> implements IQuickSelect<T> {

    @Override
    public T[] swap(T[] array, int i, int j) {
        T value_i=array[i];
        T value_j=array[j];
        array[i]=value_j;
        array[j]=value_i;
        return array;
    }

    @Override
    public int choosePivot(T[] array, int startIdx, int endIdx) {
        int middle=(startIdx+endIdx)/2;
        int medianindex=0;
        if(array[startIdx].compareTo(array[middle])>=0 && array[startIdx].compareTo(array[endIdx])<0 ){
                  medianindex=startIdx;
        }
        if(array[startIdx].compareTo(array[middle])<=0 && array[startIdx].compareTo(array[endIdx])>0 ){
            medianindex=startIdx;
        }
        if(array[middle].compareTo(array[startIdx])>=0 && array[middle].compareTo(array[endIdx])<0 ){
            medianindex=middle;
        }
        if(array[middle].compareTo(array[startIdx])<=0 && array[middle].compareTo(array[endIdx])>0 ){
            medianindex=middle;
        }
        if(array[endIdx].compareTo(array[middle])>=0 && array[endIdx].compareTo(array[startIdx])<0 ){
            medianindex=endIdx;
        }
        if(array[endIdx].compareTo(array[middle])<=0 && array[endIdx].compareTo(array[startIdx])>0 ){
            medianindex=endIdx;
        }
        return medianindex;
    }

    @Override
    public int partition(T[] array, int pivotIdx, int startIdx, int endIdx) {
        if(choosePivot(array,startIdx,endIdx)!=endIdx){
            swap(array,endIdx,choosePivot(array, startIdx, endIdx));
        }
        T pivot=array[endIdx];
        int i=startIdx-1;
        for(int j=startIdx;j<=endIdx-1;j++){
            //current element smaller
            if(array[j].compareTo(pivot)<0){
                i++;
                swap(array,i,j);
            }
        }

        swap(array,i+1,endIdx);
        return i+1;

    }

    @Override
    public T quickSelect(T[] array, int k) {
        quickSort(array,0,array.length-1);

        return array[k];

    }
    private void quickSort(T[] array,int startIdx,int endIdx){
        if(startIdx<endIdx){
            int partidx=partition(array,choosePivot(array,startIdx,endIdx),startIdx,endIdx);

            quickSort(array,startIdx,partidx-1);
            quickSort(array,partidx+1,endIdx);
        }
    }
}
