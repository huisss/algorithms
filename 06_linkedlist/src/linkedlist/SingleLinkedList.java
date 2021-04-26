package linkedlist;

/**
 * 1）单链表的插入、删除、查找操作
 * 2）链表中存储的是int类型的数据
 */
public class SingleLinkedList {

    private Node head = null;

    //通过value查找节点
    public Node findByValue(int value){
        Node p = head;
        while (p != null && p.data != value){
            p = p.next;
        }
        return p;
    }
    //通过index位置查找节点
    public Node findByIndex(int index){
        Node p = head;
        int pos = 0;
        while (pos < index && p != null){
            p = p.next;
            ++pos;
        }
        return p;
    }

    //无头结点
    //表头插入
    //这种操作将和输入的顺序相反，逆序
    public void insertToHead(int value){
        Node newNode = new  Node(value, null);
        insertToHead(newNode);
    }
    public void insertToHead(Node newNode){
        if(head == null){
            head = newNode;
        }else {
            newNode.next = head;
            head = newNode;
        }
    }
    //顺序插入，链表尾部插入
    public void insertTail(int value){
        Node newNode = new Node(value, null);
        //空链表，可以作为新结点插入，也可以不操作
        if(head == null){
            head = newNode;
        }else{
            Node q = head;
            while (q.next != null){
                q = q.next;
            }
            newNode.next = q.next;
            q.next = newNode;
        }
    }
    //在某节点的后面插入
    public void insertAfter(Node p, int value){
        Node newNode = new Node(value, null);
        insertAfter(p, newNode);
    }
    public void insertAfter(Node p, Node newNode){
        if(p == null){
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
    }
    public void insertBefore(Node p, int value){
        Node newNode = new Node(value, null);
        insertBefore(p, newNode);
    }
    public void insertBefore(Node p, Node newNode){
        if(p == null){
            return;
        }
        if(p == head){
            insertToHead(newNode);
            return;
        }
        Node q = head;
        while (q != null && q.next != p){
            q = q.next;
        }
        if(q == null){
            return;
        }
        newNode.next = p;
        q.next = newNode;
    }

    //删除节点
    public void deleteByNode(Node p){
        if(p == null || head == null){
            return;
        }
        if(p == head){
            head = head.next;
            return;
        }
        Node q = head;
        while (q != null && q.next != p){
            q = q.next;
        }
        if(q == null){
            return;
        }
        q.next = q.next.next;
    }
    public void deleteByValue(int value){
        if(head == null){
            return;
        }
        Node p = head;
        Node q = null;
        while (p != null && p.data != value){
            q = p;
            p = p.next;
        }
        if(p == null){
            return;
        }
        if(q == null){
            head = head.next;
        }else {
            q.next = q.next.next;
        }
        //所有值为value的节点都删掉
        /*if(head != null && head.data == value){
            head = head.next;
        }
        Node pNode = head;
        while (pNode != null){
            if(pNode.next.data == value){
                pNode.next = pNode.next.next;
                continue;
            }
            pNode = pNode.next;
        }*/
    }

    public void printAll(){
        Node p = head;
        while (p != null){
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    //判断true or false
    public boolean TFResult(Node left, Node right){
        Node l = left;
        Node r = right;
        boolean flag = true;
        System.out.println("left_:" + l.data);
        System.out.println("right_:" + r.data);
        while (l != null && r != null){
            if(l.data == r.data){
                l = l.next;
                r = r.next;
                continue;
            }else {
                flag = false;
                break;
            }
        }
        System.out.println("什么结果");
        return flag;
        /* if (l==null && r==null){
           System.out.println("什么结果");
           return true;
        }else{
           return false;
        }*/
    }
    //判断是否为回文
    public boolean palindrome(){
        if (head == null){
            return false;
        }else {
            System.out.println("开始执行找到中间节点");
            Node p = head;
            Node q = head;
            if(p.next == null){
                System.out.println("只有一个元素");
                return true;
            }
            while (q.next != null && q.next.next != null) {
                p = p.next;
                q = q.next.next;
            }
            System.out.println("中间节点：" + p.data);
            System.out.println("先判断链表的数目的奇偶性，然后判断回文");
            Node leftLink = null;
            Node rightLink = null;
            if(q.next == null){
                //p一定为整个链表的中点，且节点数目为奇数
                System.out.println("p一定为整个链表的中点，且节点数目为奇数");
                rightLink = p.next;
                leftLink = inverseLinkList(p).next;
                System.out.println("左边链表的第一个节点：" + leftLink.data);
                System.out.println("右边链表的第一个节点：" + rightLink.data);
            }else {
                //p q 均为中点，结点数为偶数个
                System.out.println("p q 均为中点，结点数为偶数个");
                rightLink = p.next;
                leftLink = inverseLinkList(p);
            }
            return TFResult(leftLink, rightLink);
        }
    }
    //有头节点的链表翻转
    public Node inverseLinkList_head(Node p){
        //此处的head为新建的头结点
        Node Head = new Node(9999, null);
        //p为原来整个链表的头结点，现在head指向 整个链表
        Head.next = p;
        /**
         * 带头结点的链表翻转等价于
         * 从第二个元素开始重新头插法建立链表
         */
        Node Cur = p.next;
        p.next = null;
        Node next = null;
        while (Cur != null){
            next = Cur.next;
            Cur.next = Head.next;
            Head.next = Cur;
//            System.out.println("first " + Head.data);
            Cur = next;
        }
        //返回左半部分的中点之前的那个节点
        //从此处开始同步向两边比较
        return Head;
    }
    //无头结点的链表翻转
    public Node inverseLinkList(Node p){
        Node pre = null;
        Node r = head;
        Node next = null;
        while (r != p){
            next = r.next;
            r.next = pre;
            pre = r;
            r = next;
        }
        r.next = pre;
        //返回左半部分中间点之前的那个节点
        //从此处开始向两边比较
        return r;
    }

    public static Node createNode(int value){
        return new Node(value, null);
    }

    public static class Node{
        private int data;
        private Node next;

        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
        public int getData(){
            return data;
        }
    }


    public static void main(String[] args) {
        SingleLinkedList link = new SingleLinkedList();
        System.out.println("hello");
        //int data[] = {1};
        //int data[] = {1,2};
        //int data[] = {1,2,3,1};
        //int data[] = {1,2,5};
        //int data[] = {1,2,2,1};
        // int data[] = {1,2,5,2,1};
        int data[] = {1,2,5,3,1};

        for(int i =0; i < data.length; i++){
//            link.insertToHead(data[i]);
            link.insertTail(data[i]);
        }
//         link.printAll();
//         Node p = link.inverseLinkList_head(link.head);
//         while(p != null){
//             System.out.println("aa"+p.data);
//             p = p.next;
//         }

        System.out.println("打印原始:");
        link.printAll();
        if (link.palindrome()){
            System.out.println("回文");
        }else{
            System.out.println("不是回文");
        }
    }
}
