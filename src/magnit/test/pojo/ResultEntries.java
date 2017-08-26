package magnit.test.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;


@XmlRootElement(name="entries")
public class ResultEntries implements Serializable {

    private ArrayList<ResultEntry> entries;

    public ArrayList<ResultEntry> getEntries() {
        return entries;
    }

    @XmlElement(name="entry")
    public void setEntries(ArrayList<ResultEntry> entries) {
        this.entries = entries;
    }
}
