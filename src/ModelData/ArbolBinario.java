package ModelData;

public class ArbolBinario {
    private NodoArbol root;

    public ArbolBinario() {
        this.root = null;
    }

    public void insert(int data) {
        root = insertRec(root, data);
    }

    private NodoArbol insertRec(NodoArbol root, int data) {
        if (root == null) {
            root = new NodoArbol(data);
            return root;
        }
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        return root;
    }

    public NodoArbol search(int data) {
        return searchRec(root, data);
    }

    private NodoArbol searchRec(NodoArbol root, int data) {
        if (root == null || root.data == data) {
            return root;
        }
        if (data < root.data) {
            return searchRec(root.left, data);
        }
        return searchRec(root.right, data);
    }
}
