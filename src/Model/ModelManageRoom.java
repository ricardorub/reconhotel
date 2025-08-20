package Model;

public class ModelManageRoom {
    private String roomnumber;
    private String roomtype;
    private String bed;
    private double price;
    private String status;

    public ModelManageRoom(String roomnumber, String roomtype, String bed, double price, String status) {
        this.roomnumber = roomnumber;
        this.roomtype = roomtype;
        this.bed = bed;
        this.price = price;
        this.status = status;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

