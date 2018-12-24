package scheduler;

import java.util.Random;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

//gene represents permutation of slots as timetable for a single student group(looks like {5,22,14,1,...} )
public class Gene implements Serializable {

    public int slotno[];

    public int slotno1[];
    int days = inputdata.daysperweek;
    int hours = inputdata.hoursperday;
    int[] practIndex = new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28};
    Random r = new Random();

    Gene(int i) {
        ArrayList<Integer> practs = new ArrayList<>();
        ArrayList<Integer> all = new ArrayList<>(30);

        boolean[] flag = new boolean[days * hours];

        /*  generating an array of slot no corresponding to index of gene eg suppose index
		 *	is 2 then slotno will vary from 2*hours*days to 3*hours*days
         */
        slotno = new int[days * hours];
        slotno1 = new int[days * hours];
        for (int j = 0; j < days * hours; j++) {

            int rnd;
            while (flag[rnd = r.nextInt(days * hours)] == true) {
            }
            flag[rnd] = true;
            int slt = i * days * hours + rnd;
            slotno[j] = slt;
            all.add(slt);
        }

        for (int j = 0; j < slotno.length; j++) {
            int b = slotno[j];

            Slot s = TimeTable.slot[b];
            if (s != null) {
                if (s.isPract) {
                    practs.add(b);
                    Integer i1 = new Integer(b);
                    all.remove(i1);
                }

            }
        }
        Collections.sort(practs);

        LinkedHashMap<Integer, Integer> prMap = new LinkedHashMap<>();
       // System.out.println("ps "+practs.size());
        if (practs.size() > 0) {

            
            ArrayList<Integer> lll = testRandom.get();

            int idx = 0;
            for (int j = 0; j < lll.size(); j++) {

                Integer get = lll.get(j);

                int start = practIndex[get];

                int end = start + 1;

                prMap.put(start, practs.get(idx++));
                prMap.put(end, practs.get(idx++));

                if (idx == practs.size()) {
                    break;
                }
            }

            int[] ss = new int[30];

            for (int j = 0; j < ss.length; j++) {
                ss[j] = -1;

            }

            for (Map.Entry<Integer, Integer> entry : prMap.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                ss[key] = value;

            }

            for (Integer integer : all) {

                for (int j = 0; j < ss.length; j++) {
                    int s = ss[j];
                    if (s == -1) {
                        ss[j] = integer;
                        break;
                    }
                }

            }

            slotno = ss;
        }

    }

    public Gene deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Gene) ois.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
