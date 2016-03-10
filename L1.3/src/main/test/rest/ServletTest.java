package rest;

import main.RestApplication;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;

import static org.junit.Assert.assertEquals;

/**
 * Created by esin on 10.03.2016.
 */
public class ServletTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new RestApplication();
    }

    @Test
    public void testGetAdminUser() {
        final String adminJson = target("user").path("admin").request().get(String.class);
        assertEquals("{\"login\":\"admin\",\"password\":\"admin\"}", adminJson);
    }
}
