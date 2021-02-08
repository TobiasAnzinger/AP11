package aufgabeWallet;

public class Node {
    private Money content;
    private Node next;

    public Node(Money money, Node next) {
        content = money;
        this.next = next;
    }
    public void setNext(Node next) {
        this.next = next;
    }
    public Node getNext() {
        return next;
    }
    public Money getMoney() {
        return content;
    }
}