package GeneticSystem;

/**
 * Created by IntelliJ IDEA.
 * User: Moori
 * Date: 7/29/11
 * Time: 11:20 AM
 */
public class test {
    public static void main(String[] args) {
        double solve;
        double max = 0;
        double min = 1000;
        int index = 0;
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1048576; i++) {
          //  for (int j = 0; j < 4096; j++) {
                //solve = -(1 / 1000) * i * j * (i - 1023) + (j / (j + 1023)) + 5 * Math.tan(i / 8) * Math.cos(i / 19) + 5 * Math.sin((j * j) / 8) * Math.cos(j / 19);
                solve = -(1 / 1000) * i * (i - 1023) + 5 * Math.sin(i / 8) * Math.cos(i / 19)+100;
                if (solve > max) {
                    max = solve;
                    index = i;
                }
                if (solve < min) {
                    min = solve;
          //      }
            }

        }
        long t2 =  System.currentTimeMillis()-t1;
        // solve = -(1 / 1099511627) * 1099511627 * (1099511627 - 1023) + 5 * Math.sin(1099511627 / 8) * Math.cos(1099511627 / 19);
        System.out.println("max = " + max);
        System.out.println("index = " + index);
        System.out.println("t2 = " + t2);

    }
}
