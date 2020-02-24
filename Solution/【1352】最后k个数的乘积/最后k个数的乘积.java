/*
（1）维护一个前缀乘积数组 products
（2）直接 products.get(products.size() - 1) / products.get(products.size() - 1- k) 就可以了
（3）但是可能其中会出现 0，关键是怎么处理 0 比较合适
（4）如果是出现 0 的话，可以把 products 数组清空，然后和初始化一样，放 一个 1 进去
（5）如果数组的长度 <= k，则说明 k 个元素内会有 0，也就直接返回 0
*/




class ProductOfNumbers {
    private List<Integer> products;

    public ProductOfNumbers() {
        products = new ArrayList<>();
        products.add(1);
    }

    public void add(int num) {
        if(num == 0){
            products = new ArrayList<>();
            products.add(1);
        } 
        else {
            products.add(products.get(products.size() - 1) * num);
        }
    }

    public int getProduct(int k) {
        if(products.size() <= k ){
            return 0;
        }
        return products.get(products.size() - 1) / products.get(products.size() - 1- k);
    }
}