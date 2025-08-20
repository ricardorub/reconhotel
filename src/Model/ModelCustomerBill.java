package Model;

public class ModelCustomerBill {

    private String billid;
    private String roomnumber;
    private String name;
    private String mobile;
    private String nationality;
    private String gender;
    private String email;
    private String id;
    private String address;
    private String date;
    private String outdate;
    private String bed;
    private String roomtype;
    private String price;
    private String days;
    private String amount;

    public ModelCustomerBill(String billid, String roomnumber, String name, String mobile, String nationality, String gender, String email, String id, String address, String date, String outdate, String bed, String roomtype, String price, String days, String amount) {
        this.billid = billid;
        this.roomnumber = roomnumber;
        this.name = name;
        this.mobile = mobile;
        this.nationality = nationality;
        this.gender = gender;
        this.email = email;
        this.id = id;
        this.address = address;
        this.date = date;
        this.outdate = outdate;
        this.bed = bed;
        this.roomtype = roomtype;
        this.price = price;
        this.days = days;
        this.amount = amount;
    }

    // Getters for all fields
    public String getBillid() { return billid; }
    public String getRoomnumber() { return roomnumber; }
    public String getName() { return name; }
    public String getMobile() { return mobile; }
    public String getNationality() { return nationality; }
    public String getGender() { return gender; }
    public String getEmail() { return email; }
    public String getId() { return id; }
    public String getAddress() { return address; }
    public String getDate() { return date; }
    public String getOutdate() { return outdate; }
    public String getBed() { return bed; }
    public String getRoomtype() { return roomtype; }
    public String getPrice() { return price; }
    public String getDays() { return days; }
    public String getAmount() { return amount; }
}
