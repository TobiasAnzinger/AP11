package todoList;

public class Entry {

    public String description;


    public Entry(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description + TodoList.getCount();
    }
}
