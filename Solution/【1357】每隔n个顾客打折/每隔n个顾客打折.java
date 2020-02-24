/*
（1）注意点，构造函数中传入的是数组，不能直接用 this.array 对全局变量进行 赋值
（2）如果要对全局变量进行赋值，可以写个循环
（3）此处也可以用 map 存起来
*/




class Cashier {
    int n;
    int discount;
    int t;
    HashMap<Integer, Integer> map;   

    public Cashier(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discount = discount;
        this.t = n;
        map = new HashMap<Integer, Integer>();
        //添加产品和价钱的映射。
        for (int i = 0; i < products.length; i++) {
            map.put(products[i], prices[i]);
        }
    }

    public double getBill(int[] product, int[] amount) {
        n--;
        double x = 0;
        int N = product.length;

        for (int i = 0; i < N; i++) {
            x = x + map.get(product[i]) * amount[i];
        }
        //每n个顾客打折一次，打完，次数重新计数。
        if (n == 0) {
            x = x - (discount * x) / 100;
            n = t;
        }
        return x;
    }
}