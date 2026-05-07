package cm.enspm.studia.model.fx;

import javafx.beans.property.SimpleStringProperty;

public class FxAccueil{

    private SimpleStringProperty fname;
    private SimpleStringProperty lname;
    private SimpleStringProperty phone;
    private SimpleStringProperty email;

    public FxAccueil(String fname, String lname, String phone, String email) {
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
    }

    public String getFname() {
        return fname.get();
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public String getLname() {
        return lname.get();
    }

    public void setLname(String lname) {
        this.lname.set(lname);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}