package todoList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyListModel<E> extends AbstractListModel<E> implements Collection<E> {

    private ArrayList<E> delegate = new ArrayList<>();


    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public E getElementAt(int index) {
        return null;
    }

    public void trimToSize() {
        delegate.trimToSize();
    }

    public void ensureCapacity(int minCapacity) {
        delegate.ensureCapacity(minCapacity);
    }

    public int size() {
        return delegate.size();
    }

    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    public boolean contains(Object o) {
        return delegate.contains(o);
    }

    public int indexOf(Object o) {
        return delegate.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return delegate.lastIndexOf(o);
    }

    public Object[] toArray() {
        return delegate.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return delegate.toArray(a);
    }

    public E get(int index) {
        return delegate.get(index);
    }

    public E set(int index, E element) {
        return delegate.set(index, element);
    }

    public boolean add(E e) {
        return delegate.add(e);
    }

    public void add(int index, E element) {
        delegate.add(index, element);
    }

    public E remove(int index) {
        return delegate.remove(index);
    }

    public boolean remove(Object o) {
        return delegate.remove(o);
    }

    public void clear() {
        delegate.clear();
    }

    public boolean addAll(Collection<? extends E> c) {
        return delegate.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return delegate.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return delegate.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return delegate.retainAll(c);
    }

    public ListIterator<E> listIterator(int index) {
        return delegate.listIterator(index);
    }

    public ListIterator<E> listIterator() {
        return delegate.listIterator();
    }

    public Iterator<E> iterator() {
        return delegate.iterator();
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return delegate.subList(fromIndex, toIndex);
    }

    public void forEach(Consumer<? super E> action) {
        delegate.forEach(action);
    }

    public Spliterator<E> spliterator() {
        return delegate.spliterator();
    }

    public boolean removeIf(Predicate<? super E> filter) {
        return delegate.removeIf(filter);
    }

    public void replaceAll(UnaryOperator<E> operator) {
        delegate.replaceAll(operator);
    }

    public void sort(Comparator<? super E> c) {
        delegate.sort(c);
    }

    public boolean containsAll(Collection<?> c) {
        return delegate.containsAll(c);
    }

    public Stream<E> stream() {
        return delegate.stream();
    }

    public Stream<E> parallelStream() {
        return delegate.parallelStream();
    }

    static final String path = "src/main/java/todoList/savefile.json";
    static final String xmlPath = "src/main/java/todoList/XMLHandler.xml";


    public static boolean save2(JList<TodoList.Entry> list) {
        JSONObject obj = new JSONObject();
        MyListModel<TodoList.Entry> myListModel = (MyListModel<TodoList.Entry>) list.getModel();
        List<TodoList.Entry> l = IntStream.range(0, myListModel.getSize()).mapToObj(myListModel::getElementAt)
                .collect(Collectors.toList());
        try {
            final JAXBContext context = JAXBContext.newInstance(XMLHandler.class);
            final Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(myListModel, System.out);
            m.marshal(myListModel, new File(xmlPath));
        } catch (javax.xml.bind.JAXBException e) {
            e.printStackTrace();
        }
        return saveToFile(obj.toJSONString());
    }


    private static boolean saveToFile(String s) {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(s);
            fw.flush();
            fw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static MyListModel<TodoList.Entry> read() throws Exception {
        String f = readFile();
        System.out.println(f);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject((Map) parser.parse(f));
        JSONArray jsonArray = (JSONArray) jsonObject.get("list");
        MyListModel<TodoList.Entry> lm = new MyListModel<>();
        for (Object o : jsonArray) {
            lm.add((TodoList.Entry) o);
        }
        return lm;
    }

    private static String readFile() {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            java.lang.String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
