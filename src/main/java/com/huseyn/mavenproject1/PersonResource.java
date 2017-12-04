
package com.huseyn.mavenproject1;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("personresource")
public class PersonResource {
    
    PersonRepository repository=new PersonRepository();
    
    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Person> getPersons(){
        
    return repository.getPersons();
    
    }
    
    @GET
    @Path("person/{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Person getPerson(@PathParam("id") int id){
        
    return repository.getPerson(id);
    
    }
    
    @POST
    @Path("person")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    /*
    @Consumes(MediaType.APPLICATION_ATOM_XML)--it means that you can send only xml format
    */
    public Person createPerson(Person person){
        
    System.out.println(person);
    repository.create(person);
    return person;
    }
    
   @PUT
   @Path("person")
   @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
   public Person updatePerson(Person person){
       
       if (repository.getPerson(person.getId()).getId()==0){
       repository.create(person);
       }
       else{
       repository.update(person);
       }
        return person;
   }
   
   @DELETE
   @Path("person/{id}")
   @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
   public Person deletePerson(@PathParam("id") int id){
   
   Person p=new Person();
   
   if (p.getId()!=0)
      repository.delete(id);
       
   return p;
   }
    
}
