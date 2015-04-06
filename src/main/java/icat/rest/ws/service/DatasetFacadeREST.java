/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.service;

import icat.rest.ws.converter.DatafileLocation;
import icat.rest.ws.converter.DatasetConverter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.apache.log4j.Logger;
import org.icatproject.core.IcatException;
import org.icatproject.core.entity.Dataset;
import org.icatproject.core.entity.Datafile;
import org.icatproject.core.manager.BeanManager;
import org.icatproject.core.manager.SearchResponse;

/**
 *
 * @author 3qr
 */
@Stateless
@Path("dataset/")
public class DatasetFacadeREST extends AbstractFacade<Dataset> {

  @PersistenceContext(unitName = "icat4PU")
  private EntityManager em;
  private static final Logger log = Logger.getLogger(DatasetFacadeREST.class);

  public DatasetFacadeREST() {
    super(Dataset.class);
  }

  @GET
  @Path("{facil}/{inst}/{run}")
  @Produces({"application/xml", "application/json"})
  public DatasetConverter getRunInfo(@PathParam("facil") String facility, @PathParam("inst") String instrument, @PathParam("run") String run, @Context HttpServletRequest requestContext) {
    try {
      String yourIP = requestContext.getRemoteAddr().toString();
      log.info("Beginning getRunInfo: " + yourIP);
      String query = "Dataset INCLUDE Investigation, Datafile, DatasetType, DatasetParameter, ParameterType [ name = ':run' ] <-> Investigation <-> Instrument [ name = ':instrument']";
      query = query.replace(":instrument", instrument).replace(":run", run);
      SearchResponse results = BeanManager.search(RestfulConstant.RESTFUL_USER, query, em);
      log.info("getRunInfo found " + results.getList().size() + " metadata set(s) for instrument " + instrument + " and run " + run);
      ArrayList<Dataset> list = new ArrayList<Dataset>();
      if (!results.getList().isEmpty()) {
        Iterator iter = results.getList().iterator();
        while (iter.hasNext()) {
          Dataset ds = (Dataset) iter.next();
          list.add(ds);
        }
        log.info("Ending getRunInfo: ");
        return new DatasetConverter(list);
      } 
      log.info("Ending getRunInfo: ");
      return new DatasetConverter();
    } catch (IcatException ex) {
      log.error("In getRunInfo: got IcatException " + ex.getMessage());
      return new DatasetConverter();
    }
  }
  
    
  @GET
  @Path("{facil}/{inst}/{run}/lite")
  @Produces({"application/xml", "application/json"})
  public DatasetConverter getRunInfoLite(@PathParam("facil") String facility, @PathParam("inst") String instrument, @PathParam("run") String run, @Context HttpServletRequest requestContext) {
    try {
      String yourIP = requestContext.getRemoteAddr().toString();
      log.info("Beginning getRunInfoLite: " + yourIP);
      String query = "Dataset INCLUDE Investigation, DatasetType, DatasetParameter, ParameterType [ name = ':run' ] <-> Investigation <-> Instrument [ name = ':instrument']";
      query = query.replace(":instrument", instrument).replace(":run", run);
      SearchResponse results = BeanManager.search(RestfulConstant.RESTFUL_USER, query, em);
      log.info("Ending getRunInfoLite, found " + results.getList().size() + " metadata set(s) for instrument " + instrument + " and run " + run);
      
      ArrayList<String> list = new ArrayList<String>();
      String filename = instrument + "_" + run + "%";
      String dfQuery = "Datafile.location <-> Datafile [name LIKE ':filename']";
      dfQuery = dfQuery.replace(":filename", filename);
      SearchResponse dfResults = BeanManager.search(RestfulConstant.RESTFUL_USER, dfQuery, em);
      Iterator dfIter = dfResults.getList().iterator();
      while (dfIter.hasNext()) {
        String location = (String) dfIter.next();
        log.info("location: " + location);
        list.add(location);
      }

      if (!results.getList().isEmpty()) {
        Iterator iter = results.getList().iterator();
        while (iter.hasNext()) {
          Dataset ds = (Dataset) iter.next();
          if (ds.getType().getName().equalsIgnoreCase("experiment_raw")) {
            return new DatasetConverter(ds, new DatafileLocation(list));
          }
        }
      }
      return new DatasetConverter();
    } catch (IcatException ex) {
      log.error("In getRunInfoLite: got IcatException " + ex.getMessage());
      return new DatasetConverter();
    }
  } 

  @GET
  @Path("{facil}/{inst}/{run}/metaOnly")
  @Produces({"application/xml", "application/json"})
  public DatasetConverter getMeta(@PathParam("facil") String facility, @PathParam("inst") String instrument, @PathParam("run") String run, @Context HttpServletRequest requestContext) {
    try {
      String yourIP = requestContext.getRemoteAddr().toString();
      log.info("Beginning getMeta: " + yourIP);
      String query = "Dataset INCLUDE Investigation, DatasetType, DatasetParameter, ParameterType [ name = ':run' ] <-> Investigation <-> Instrument [ name = ':instrument']";
      query = query.replace(":instrument", instrument).replace(":run", run);
      SearchResponse results = BeanManager.search(RestfulConstant.RESTFUL_USER, query, em);
      log.info("Ending getMeta, found " + results.getList().size() + " metadata set(s) for instrument " + instrument + " and run " + run);
      if (!results.getList().isEmpty()) {
        Iterator iter = results.getList().iterator();
        while (iter.hasNext()) {
          Dataset ds = (Dataset) iter.next();
          if (ds.getType().getName().equalsIgnoreCase("experiment_raw")) {
            return new DatasetConverter(ds);
          }
        }
      }
      return new DatasetConverter();
    } catch (IcatException ex) {
      log.error("In getMeta: got IcatException " + ex.getMessage());
      return new DatasetConverter();
    }
  }

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }
}
