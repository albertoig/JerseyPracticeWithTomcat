package com.albertoig.jersey;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.File;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

/**
 * Created by aiglesias on 1/10/15.
 */
@Path("/images")
public class Image{

    @Context
    private ServletContext oContext;

    private static String imagePath = "./Uploads";

    @GET
    @Path("/image/{image}")
    @Produces("image/*")
    public Response getImage(@PathParam("image") String image) {
        File f = new File(oContext.getRealPath("/Uploads") + "/"+image);

        if (!f.exists()) {
            throw new WebApplicationException(404);
        }

        String mt = new MimetypesFileTypeMap().getContentType(f);
        return Response.ok(f, mt).build();
    }

    @GET
    @Path("/path")
    @Produces(MediaType.TEXT_PLAIN)
    public String getActualPath(){
        return "Path: " + oContext.getRealPath("/Uploads");
    }
}
