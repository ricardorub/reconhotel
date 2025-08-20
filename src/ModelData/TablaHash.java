package ModelData;

import Model.ModelManageRoom;
import java.util.LinkedList;

public class TablaHash {

    private LinkedList<ModelManageRoom>[] tabla;
    private int tamano;

    public TablaHash(int tamano) {
        this.tamano = tamano;
        this.tabla = new LinkedList[tamano];
        for (int i = 0; i < tamano; i++) {
            this.tabla[i] = new LinkedList<>();
        }
    }

    private int hash(String roomNumber) {
        int hashCode = roomNumber.hashCode();
        return (hashCode & 0x7fffffff) % tamano;
    }

    public void insertar(ModelManageRoom room) {
        String roomNumber = room.getRoomnumber();
        int index = hash(roomNumber);
        for (ModelManageRoom r : tabla[index]) {
            if (r.getRoomnumber().equals(roomNumber)) {
                // Update existing room
                r.setRoomtype(room.getRoomtype());
                r.setBed(room.getBed());
                r.setPrice(room.getPrice());
                r.setStatus(room.getStatus());
                return;
            }
        }
        tabla[index].add(room);
    }

    public ModelManageRoom buscar(String roomNumber) {
        int index = hash(roomNumber);
        for (ModelManageRoom room : tabla[index]) {
            if (room.getRoomnumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }

    public void eliminar(String roomNumber) {
        int index = hash(roomNumber);
        tabla[index].removeIf(room -> room.getRoomnumber().equals(roomNumber));
    }

    public LinkedList<ModelManageRoom> obtenerTodos() {
        LinkedList<ModelManageRoom> allRooms = new LinkedList<>();
        for (int i = 0; i < tamano; i++) {
            allRooms.addAll(tabla[i]);
        }
        return allRooms;
    }
}

