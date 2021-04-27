package linkedlist;

/**
 * 1）单链表反转
 * 2）链表中环的检测
 * 3）两个有序链表合并
 * 4）删除链表倒数第n个节点
 * 5）求链表的中间节点
 */
public class LinkedListAlgo {


    //1）单链表反转
    public static Node reverse(Node list){
        Node curr = list;
        Node pre = null;
        Node next = null;
        while (curr != null){
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    //2）链表中环的检测
    public static boolean checkCircle(Node list){
        if(list == null){
            return false;
        }
        Node slow = list;
        Node fast = list.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    //3）两个有序链表合并
    public static Node mergeSortedLists(Node la, Node lb){
        if (la == null) return lb;
        if (lb == null) return la;

        Node p = la;
        Node q = lb;
        Node head;
        if (p.data < q.data) {
            head = p;
            p = p.next;
        } else {
            head = q;
            q = q.next;
        }
        Node newHead = head;
        while (p != null && q != null){
            if(p.data < q.data){
                newHead.next = p;
                p = p.next;
            }else {
                newHead.next = q;
                q = q.next;
            }
            newHead = newHead.next;
        }
        if(p != null){
            newHead.next = p;
        }else {
            newHead.next = q;
        }
        return head;
    }
    //3）两个有序链表合并 Leetcode 21
    public static Node mergeTwoLists(Node l1, Node l2){
        Node soldier = new Node(0);//利用哨兵节点简化实现难度，技巧三
        Node p = soldier;

        while (l1 != null && l2 != null){
            if (l1.data < l2.data){
                p.next = l1;
                l1 = l1.next;
            }else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if(l1 != null) {
            p.next = l1;
        }
        if(l2 != null) {
            p.next = l2;
        }

        return soldier.next;
    }
    //4）删除链表倒数第n个节点
    public static Node deleteLastKth(Node list, int k){
        Node fast = list;//fast代表list
        int i = 1;
        while (fast != null && i < k){
            fast = fast.next;
            ++i;
        }
        if(fast == null){//list节点个数不足k个
            return list;
        }

        Node slow = list;//找到要删除的倒数第k个节点
        Node prev = null;//要删除的节点的前一个节点
        while (fast.next != null){
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev == null) {
            list = list.next;
        }else {
            prev.next = prev.next.next;
        }
        return list;
    }
    public static Node deleteLastKth2(Node list, int k){
        Node la = list;
        int nodenums = 0;
        while (la != null){
            nodenums++;
            la = la.next;
        }

        if(k < 1 || k > nodenums){//null
            return list;
        }
        if(k == nodenums){//one head
            return list.next;
        }

        Node lb = list;
        int i = 1;
        while (i < nodenums - k){//two tail
            lb = lb.next;
            i++;
        }
        lb.next = lb.next.next;
        return list;
    }
    //5）求链表的中间节点
    public static Node findMiddleNode(Node list){
        if(list == null) return null;

        Node slow = list;
        Node fast = list;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        //如果fast==null说明共有奇数个节点，slow正好是中间节点；
        //如果fast!=null说明共有偶数个节点，slow是后面的中间节点。
        return slow;
    }

    public static void printAll(Node list){
        Node p = list;
        while (p != null){
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static Node createNode(int val){
        return new Node(val, null);
    }

    public static class Node{
        private int data;
        private Node next;

        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
        public Node(int data){
            this(data, null);
        }

        public int getData(){
            return data;
        }
    }
}
