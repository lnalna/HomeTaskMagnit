package magnit.test.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;


public class ResultEntry implements Serializable{

    private Integer field;

    public Integer getField() {
        return field;
    }

    @XmlAttribute(name="field")
    public void setField(Integer field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "ResultEntry{" +
                "field=" + field +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultEntry that = (ResultEntry) o;

        return field.equals(that.field);

    }

    @Override
    public int hashCode() {
        return field.hashCode();
    }
}
