package ch.derlin.jaxrs.ws;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import sample.bean.SampleBean;
import ch.derlin.jaxrs.beans.InputExample;

/**
 * A simple webservice which uses complete bean validation.
 * <p/>
 * date: 09.05.2016
 *
 * @author Lucy Linder
 */
@Path("/")
public class ExampleWebService {

    @Inject
    private Validator validator;

    @GET
    @Path("/test2")
    @Produces(MediaType.APPLICATION_JSON)
    public SampleBean index(@QueryParam("name") String name) {

        SampleBean bean = new SampleBean(name);
        Set<ConstraintViolation<SampleBean>> results = validator.validate(bean);

        if (!results.isEmpty()) {
            throw new ConstraintViolationException(results);
        }

        return bean;
    }

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public SampleBean index(@Valid @BeanParam SampleBean bean) {
        return bean;
    }

    @GET
    public String test() {
        return "It works !";
    }

    // note the @Valid annotation, which ensures the annotations
    // of the InputExample class are validated.
    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public InputExample inputValidation(@Valid InputExample input) {
        System.out.println(input);
        return input; // return it, to test the custom serializer
    }
}//end class
