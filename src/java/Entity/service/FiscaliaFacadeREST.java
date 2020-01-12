/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.service;

import Entity.Fiscalia;
import Generic.JsonGenerator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author dannyalvizures
 */
@Stateless
@Path("rest")
public class FiscaliaFacadeREST extends AbstractFacade<Fiscalia> {

    @PersistenceContext(unitName = "restFulMPPU")
    private EntityManager em;

    public FiscaliaFacadeREST() {
        super(Fiscalia.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Fiscalia entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Fiscalia entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Fiscalia find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Fiscalia> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Fiscalia> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("fiscalia")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String listar() {
        try {
            List<Fiscalia> lista = super.listarFiscalia();
            if(lista != null){
                return JsonGenerator.generateListJson(lista);
            }else{
                return JsonGenerator.jsonSuccessList();
            }
            
        } catch (Exception e) {
            return JsonGenerator.jsonServerError();
        }
    }

    @GET
    @Path("fiscalia/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String buscarFiscalia(@PathParam("id") Integer id) {
        try {
            Object respuestaFiscalia = super.buscarFiscalia(id);
            if(respuestaFiscalia != null){
                Object[] f = (Object[]) respuestaFiscalia;
                Fiscalia fiscalia = new Fiscalia((int) f[0], (String) f[1], (String) f[2], (String) f[3]);
                return JsonGenerator.generateOneJson(fiscalia);
            }else{
                return JsonGenerator.jsonSuccessOne();
            }   
        } catch (Exception e) {
            return JsonGenerator.jsonServerError();
        }
    }

    @POST
    @Path("fiscalia")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String agregarFiscalia(String body) throws ParseException {
        try{
            Object obj = new JSONParser().parse(body);
            JSONObject jo = (JSONObject) obj;
            String nombre = (String) jo.get("nombre");
            String direccion = (String) jo.get("direccion");
            String telefono = (String) jo.get("telefono");
            
            if(super.crearFiscalia(nombre, direccion, telefono)){
                return JsonGenerator.jsonSuccess();
            }else{
                return JsonGenerator.jsonDbError();
            }
        }catch(ParseException p){
            return JsonGenerator.jsonParserError();
        }catch(Exception e){
            return JsonGenerator.jsonServerError();
        }
    }

    @DELETE
    @Path("fiscalia/{id}")
    public String eliminarFiscalia(@PathParam("id") Integer id) {
        try {
            if(super.eliminarFiscalia(id)){
                return JsonGenerator.jsonSuccess();
            }else{
                return JsonGenerator.jsonDbError();
            }
        } catch (Exception e) {
            return JsonGenerator.jsonServerError();
        }
    }

    @PUT
    @Path("fiscalia/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String editarFiscalia(@PathParam("id") Integer id, String body) {
        try {
            Object obj = new JSONParser().parse(body);
            JSONObject jo = (JSONObject) obj;
            String nombre = (String) jo.get("nombre");
            String direccion = (String) jo.get("direccion");
            String telefono = (String) jo.get("telefono");
            
            if(super.modificarFiscalia(id,nombre, direccion, telefono)){
                return JsonGenerator.jsonSuccess();
            }else{
                return JsonGenerator.jsonDbError();
            }
        } catch (Exception e) {
            return JsonGenerator.jsonServerError();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
