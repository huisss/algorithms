package array;

/**
 * 1）数组的插入、删除、按照下标随机访问操作；
 * 2）数组中的数据是int类型的
 */
public class Array {

    //定义整型数组，保存数据
    private int[] data;
    //定义数组的长度
    private int n;
    //定义数组中的实际个数
    private int count;

    //构造方法定义数组的大小
    public Array(int capacity){
        this.data = new int[capacity];
        this.n = capacity;
        this.count = 0;
    }

    //根据索引找到数组中的元素并返回
    public int find(int index){
        if(index < 0 || index >= count) return -1;//没有
        return data[index];
    }

    //插入元素：头部插入和尾部插入
    public boolean insert(int index, int value){

        //数组中没有元素
//        if (count == 0 && index == count){
//            data[index] = value;
//            ++count;
//            return true;
//        }

        //数组空间已满
        if(count == n){
            System.out.println("数组已满，没有可插入的位置了！");
            return false;
        }
        //数组空间未满count<n
        //位置不合法
        if (index < 0 || index > count){
            System.out.println("位置不合法！");
            return false;
        }
        //位置合法
        for(int i = count; i > index; i--){
            data[i] = data[i - 1];
        }
        data[index] = value;
        ++count;
        return true;
    }

    //根据索引删除数组中的元素
    public boolean delete(int index){
        if(index < 0 || index >= count) return false;
        for (int i = index + 1; i < count; i++){
            data[i - 1] = data[i];
        }
        --count;
        return true;
    }

    public void printAll(){
        for (int i = 0; i < count; i++){
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Array array = new Array(5);
        array.printAll();
        array.insert(0, 3);
        array.insert(0, 4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(3, 10);
        array.delete(2);
//        array.insert(3, 11);
        array.printAll();
    }
}
