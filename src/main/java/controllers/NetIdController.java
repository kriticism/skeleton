package controllers;

// import io.dropwizard.jersey.sessions.Session;

// import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/netid")
@Produces(MediaType.WILDCARD)
public class NetIdController {

    @GET
    public String netid() {
        return "ks2259";
    }
}
