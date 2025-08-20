package ModelData;

/**
 *
 * @author Lenovo
 */
public class Cola {
    private Nodo front;
    private Nodo rear;
    private int size;

    public Cola() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(Object data) {
        Nodo newNode = new Nodo(data);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    public Object dequeue() {
        if (isEmpty()) {
            return null; // Or throw an exception
        }
        Object data = front.getData();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    public Object peek() {
        if (isEmpty()) {
            return null; // Or throw an exception
        }
        return front.getData();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}

