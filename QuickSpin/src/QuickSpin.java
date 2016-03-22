import com.google.common.collect.ImmutableSet;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by kefei on 3/21/16.
 */
public class QuickSpin {
    public static void main(String [] args)
    {
        final List<String> comm = new ArrayList<>(
                Arrays.asList("1", "2", "3", "22", "4", "23", "4", "5", "22","6", "23","7", "8"));
        System.out.println("original: " + comm);


        //1. iterator
        Set<String> ipset = new LinkedHashSet<>();
        ListIterator<String> iter = comm.listIterator();
        while(iter.hasNext()){
            String s  = iter.next();
            if(s.contains("2")) {
                if (!ipset.add(s)) {
                    iter.remove();
                }
            }
        }
        System.out.println("result1: " + comm);

        //2. guava, it preserves order :)
        List<String> result = ImmutableSet.copyOf(comm).asList();
        System.out.println("result2: " + result);


        //3. java8
        TreeSet<String> result3 = comm.stream()
                .collect(Collectors.toCollection(() ->
                                new TreeSet<>((p1, p2) -> p1.compareTo(p2))
                ));

        System.out.println("result3: " + result3);

    }
}
