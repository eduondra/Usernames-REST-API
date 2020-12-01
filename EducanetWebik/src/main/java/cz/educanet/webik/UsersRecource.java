package cz.educanet.webik;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("users")   //localhost:8888/nazev-appky/api/users
@Produces(MediaType.APPLICATION_JSON)
public class UsersRecource {

    private static List<User> names = new ArrayList<>();

    @GET
    public Response getAll(){
        return Response.ok(
                names
        ).build();
    }

    @POST
    public Response createUser(@QueryParam("username") String username,@QueryParam("password") String password){
        User postedUser = new User(username, password);
        if (userCheck(postedUser)){
            return Response.ok(
                    "user exists"
            ).build();
        } else {
            names.add(postedUser);
            return Response.ok(
                    names
            ).build();
        }
    }

    @PUT
    @Path("/{username}")
    public Response changeuser(@QueryParam("username") String username, @QueryParam("password") String newUsername){
        User changeUser = new User(username, "");
        if(userCheck(changeUser)){
            for (int i = 0; i < names.size(); i++){
                if(names.get(i).getUsername().equals(changeUser.getUsername())){
                    names.get(i).changeUsername(newUsername);
                    return Response.ok(
                            "new username" + newUsername
                    ).build();
                }

            }

        }else return Response.ok("username doesnt exist").build();

        return Response.ok(
                names
        ).build();

    }

    public Boolean userCheck(User user){
        for(int i = 0; i < names.size(); i++){
            if (names.get(i).getUsername().equals(user.getUsername())){

                return true;
            }
        }
        return false;
    }

    public Boolean usernameCheck(User user){
        for (int i = 0; i < names.size(); i++){
            if (names.get(i).getUsername().equals(user.getUsername())){
                return true;
            }
        }
        return false;
     }
    }

