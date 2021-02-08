package aufgabeWallet;

public class Wallet {

    private Node head = null;

    public Wallet() {

    }

    private void add(Money money) {
        if(this.isEmpty()) {
            head = new Node(money, null);
        }
        else {
            findLastElement().setNext(new Node(money, null));;
        }
    }

    private boolean isEmpty() {
        if(head == null) {
            return true;
        }
        return false;
    }

    private boolean contains(Money money) {
        Node current = head;
        Node next = head.getNext();
        while(current != null) {
            if(current.getMoney() == money) {
                return true;
            }
            current = next;
            next = next.getNext();
        }
        return false;
    }
    void remove(Money money) {
        Node predecessor = null;
        Node current = head;
        Node next = head.getNext();

        if(current.getMoney() == money) {
            head=current.getNext();
        }
        else {
            while(current != null) {
                if(current.getMoney() == money) {
                    predecessor.setNext(next);
                }
                predecessor = current;
                current = next;
                next = next.getNext();
            }
        }
    }

    private Node findLastElement() {
        Node current = head;
        Node next = head.getNext();
        while(next != null) {
            current = next;
            next = next.getNext();
        }
        return current;
    }
}
