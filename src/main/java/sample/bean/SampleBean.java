package sample.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SampleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @QueryParam("name")
    @NotNull
    private String name;

    public SampleBean() {
    }

    public SampleBean(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}