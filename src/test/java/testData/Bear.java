package testData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.util.Objects;

public class Bear {
    private int bear_id;
    @Expose
    private BearType bear_type;
    @Expose
    private String bear_name;
    @Expose
    private double bear_age;

    public Bear(BearType type, String name, double age) {
        this.bear_type = type;
        this.bear_name = name;
        this.bear_age = age;
    }

    public int getBear_id() {
        return bear_id;
    }

    public void setBear_id(int bear_id) {
        this.bear_id = bear_id;
    }

    public BearType getBear_type() {
        return bear_type;
    }

    public void setBear_type(BearType bear_type) {
        this.bear_type = bear_type;
    }

    public String getBear_name() {
        return bear_name;
    }

    public void setBear_name(String bear_name) {
        this.bear_name = bear_name;
    }

    public double getBear_age() {
        return bear_age;
    }

    public void setBear_age(double bear_age) {
        this.bear_age = bear_age;
    }

    public String getRequestBodyForCreationOrUpdate() {
        Gson gson = new GsonBuilder().setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bear bear = (Bear) o;
        return getBear_id() == bear.getBear_id() &&
            Double.compare(bear.getBear_age(), getBear_age()) == 0 &&
            getBear_type() == bear.getBear_type() &&
            getBear_name().equals(bear.getBear_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBear_id(), getBear_type(),
            getBear_name(), getBear_age());
    }

    @Override
    public String toString() {
        return "{" +
            "bear_id=" + bear_id +
            ", bear_type=" + bear_type +
            ", bear_name='" + bear_name + '\'' +
            ", bear_age=" + bear_age +
            '}';
    }
}
