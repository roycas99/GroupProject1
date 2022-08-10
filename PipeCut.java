public class PipeCut {
    private int[] cache; // temporary priceay
    private int[] price;   // price array
    private int n;

    public PipeCut(int[] cache) {
        this.cache = cache;
    }

    public PipeCut(int[] price, int n) {
        this.price = price;
        this.n = n;

    }

    public int[] getCache() {
        return cache;
    }

    public void setCache(int[] cache) {
        this.cache = cache;
    }

    public int[] getprice() {
        return price;
    }

    public void setprice(int[] price) {
        this.price = price;
    }

    public int getn() {
        return n;
    }

    public void setn(int n) {
        this.n = n;
    }

    public int cut() {
        cache = new int[n + 1];
        cache[0] = 0;
        // Build the table val[] in bottom up manner and return
        // the last entry from the table
        // f(n)= max( p(i)+ f(n-i)) for 1<=i<=n

        // consider a rod of length `i`
        for (int i = 1; i <= n; i++) {
            int max_val = Integer.MIN_VALUE;
             // divide the rod of length `i` into two rods of length `j`
            // and `i-j` each and take maximum
            for (int j = 1; j <= i; j++)
                max_val = Math.max(max_val,
                        price[j-1] + cache[i - j ]);
            cache[i] = max_val;
        }
        //stores the maximum profit achieved from a rod of length `
        return cache[n];
    }

}
