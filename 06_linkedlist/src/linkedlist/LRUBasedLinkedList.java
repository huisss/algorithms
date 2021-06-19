package linkedlist;

import java.util.Scanner;

/**
 * 最近最少使用策略 LRU（Least Recently Used）
 * 基于单链表，实现LRU缓存算法
 * 没有使用散列表进行优化，所以基于单链表实现的LRU缓存算法的时间复杂度还是O(n)
 *
 * 思路：维护一个有序的单链表，越靠近链表尾部的节点是越早之前访问的。当有一个新的数据被访问时，从链表头开始顺序遍历链表。
 * 1）如果此数据已经被缓存在链表中了，则删除原位置，添加到链表头
 * 2）没有在缓存链表中：
 *     此时缓存未满，将新节点直接插在链表头部
 *     此时缓存已满，将链表尾结点删除，将新结点插入在链表头部
 */
public class LRUBasedLinkedList<T> {

    //默认链表的容量
    private static final Integer DEFAULT_CAPACITY = 10;
    //头节点
    private SNode<T> headNode;
    //链表长度
    private Integer length;
    //链表容量
    private Integer capacity;

    public LRUBasedLinkedList(){
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }
    public LRUBasedLinkedList(Integer capacity){
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    /**
     * 添加节点
     * 时间复杂度：O(n)
     * @param data
     */
    public void add(T data){
        SNode preNode = findPreNode(data); //O(n)
        //链表中存在，删除原数据，再插入到链表的头部
        if(preNode != null){
            deleteElemOptim(preNode); //O(1)
            insertElemAtBegin(data); //O(1)
        }else {
            if(length >= this.capacity){
                //删除尾结点
                deleteElemAtEnd(); //O(n)
            }
            insertElemAtBegin(data); //O(1)
        }
    }

    /**
     * 删除preNode的下一个元素
     * 时间复杂度O(1)
     * @param preNode
     */
    public void deleteElemOptim(SNode preNode){
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    /**
     * 链表头部插入节点
     * 时间复杂度：O(1)
     * @param data
     */
    private void insertElemAtBegin(T data){
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data, next));
        length++;
    }

    /**
     * 获取查找到元素的前一个节点
     * 时间复杂度O(n)
     * @param data
     * @return
     */
    private SNode findPreNode(T data){
        SNode node = headNode;
        while (node.getNext() != null){
            if(data.equals(node.getNext().getElement())){
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 删除尾结点
     * 时间复杂度：O(n)
     */
    private void deleteElemAtEnd(){
        SNode ptr = headNode;
        //空链表直接返回
        if(ptr.getNext() == null){
            return;
        }
        //找到倒数第二个节点
        while (ptr.getNext().getNext() != null){
            ptr = ptr.getNext();
        }
        SNode temp = ptr.getNext();
        ptr.setNext(null);
        temp = null;
        length--;//注意维护链表的长度
    }

    private void printAll(){
        SNode node = headNode.getNext();
        while (node != null){
            System.out.print(node.getElement() + ", ");
            node = node.getNext();
        }
        System.out.println();
    }


    public class SNode<T>{
        private T element;
        private SNode next;

        public SNode(T element){
            this.element = element;
        }
        public SNode(T element, SNode next){
            this.element = element;
            this.next = next;
        }
        public SNode(){
            this.next = null;
        }

        public T getElement(){
            return element;
        }
        public void setElement(T element){
            this.element = element;
        }
        public SNode getNext(){
            return next;
        }
        public void setNext(SNode next){
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUBasedLinkedList lruBasedLinkedList = new LRUBasedLinkedList();
        Scanner scanner = new Scanner(System.in);
        while (true){
            lruBasedLinkedList.add(scanner.nextInt());
            lruBasedLinkedList.printAll();
        }
    }
}
