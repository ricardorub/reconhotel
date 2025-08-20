/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelData;

public class ListaSimple {
    
    private Nodo head;

    public ListaSimple() {
        this.head = null;
    }

    public void insert(Object data) {
        Nodo newNode = new Nodo(data);
        if (head == null) {
            head = newNode;
        } else {
            Nodo current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public Nodo getHead() {
        return head;
    }
}

