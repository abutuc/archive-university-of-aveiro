import java.util.Iterator;
import java.util.ListIterator;

public class Demo {
    public static void main(String[] args) {
        VectorGeneric<Integer> vg = new VectorGeneric<>();
        
        for (int i = 0; i < 15; i++){
            vg.addElem(i);
        }

        Iterator<Integer> iter1 = vg.Iterator();
        Iterator<Integer> iter2 = vg.Iterator();
        ListIterator<Integer> l_iter1 = vg.listIterator();
        ListIterator<Integer> l_iter2 = vg.listIterator(vg.totalElem() - 1);

        while (iter1.hasNext()){
            System.out.print(iter1.next() + " ");
        }
        System.out.println();

        System.out.println("Iterator | List Iterator starting from last index");
        while(iter2.hasNext() && l_iter2.hasPrevious()){
            System.out.println(iter2.next() + " | " +  l_iter2.previous());
        }


        while (l_iter1.hasNext()){
            System.out.print(l_iter1.next() + " ");
        }

        System.out.println();
        while(l_iter1.hasPrevious()){
            System.out.print(l_iter1.previous() + " ");
        }
        System.out.println();

        System.out.println(l_iter1.previousIndex());
        System.out.println(l_iter1.nextIndex());


    }
}
