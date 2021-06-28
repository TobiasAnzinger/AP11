package todoList;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLHandler {

    @XmlElement(name = "list")
    @XmlElementWrapper(name = "entry")
    MyListModel<TodoList.Entry> myListModel = new MyListModel<>();

}
