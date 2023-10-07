import vsu.oop.task1.Btree;

public class Main {
    public static void main(String[] args) {
        Btree t = new Btree(3);
        t.insert(1);
        t.insert(3);
        t.insert(7);
        t.insert(10);
        t.insert(11);
        t.insert(13);
        t.insert(14);
        t.insert(15);
        t.insert(18);
        t.insert(16);
        t.insert(19);
        t.insert(24);
        t.insert(25);
        t.insert(26);
        t.insert(21);
        t.insert(4);
        t.insert(5);
        t.insert(20);
        t.insert(22);
        t.insert(2);
        t.insert(17);
        t.insert(12);
        t.insert(6);
        System.out.println("Traversal of the constructed tree is ");
        t.traverse();
        t.remove(26);
        System.out.println("Traversal of b-tree after delete key 6 ");
        t.traverse();
    }
}