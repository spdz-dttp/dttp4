/**
 * @program: 2021.1.12
 * @description: 单链表逆序
 * @author: spdz
 * @create: 2021-01-12 15:15
 **/

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }
}

public class Demo {

    public Node head;

    //头插法
    public void addFirst1(int data) {
        Node node = new Node(data);

        //第一次插入节点
        if(this.head == null) {
            this.head = node;
            return;
        }
        node.next = this.head;
        this.head = node;
    }

    //打印单链表
    public void display() {
        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    //单链表逆序
    public Node reverseLists() {
        Node cur = this.head;
        Node node = cur.next;
        cur.next = null;
        if (node == null) {
            return cur;
        }
        Node next = node.next;
        while (node != null) {
            addFirst(node,cur);
            cur = node;
            node = next;
            if (next != null) {
                next = next.next;
            }
        }
        return cur;
    }

    public void addFirst(Node node,Node cur) {
        if (cur == null) {
            cur = node;
            return;
        }
        node.next = cur;

    }

    //逆序的打印
    public void display2(Node newHead) {
        Node cur = newHead;
        while (cur != null){
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

}
