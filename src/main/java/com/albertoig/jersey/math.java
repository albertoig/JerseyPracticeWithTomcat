package com.albertoig.jersey;

/**
 * Created by aiglesias on 29/9/15.
 */
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/factorial")
public class math {

    @GET
    public String factorial(@QueryParam("base") long base) {
        return Long.toString(getFactorial(base));
    }

    private long getFactorial(long base){
        if(base >= 1){
            return getFactorial(base -1) * base;
        }
        return 1;
    }

    @GET @Path("/{base}")
    public String factorialWithUrlParam(@PathParam("base") long base) {
        return Long.toString(getFactorial(base));
    }

}
