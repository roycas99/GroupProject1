public class Driver {

    public static void main(String[] args) {

        // fiboDynamic Prog
        System.out.println(new FiboDynamic(new int[11]).fibRec(10));

        // cut rod dynamic prog
        int price[] = new int[] { 1, 5, 8, 9, 14, 17, 17, 20,24,25 };
        int size = price.length;
        new PipeCut(price, size);
        System.out.println(new PipeCut(price, size).cut());

    }
}