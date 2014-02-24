/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.service;

import icat.rest.ws.converter.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.icatproject.core.entity.Investigation;
import org.icatproject.core.manager.SearchResponse;
import org.icatproject.core.manager.BeanManager;
import org.icatproject.core.IcatException;

/**
 *
 * @author 3qr
 */
@Stateless
@Path("experiment/")
public class InvestigationFacadeREST extends AbstractFacade<Investigation> {

  @PersistenceContext(unitName = "icat4PU")
  private EntityManager em;
  private static final Logger log = Logger.getLogger(InvestigationFacadeREST.class);

  public InvestigationFacadeREST() {
    super(Investigation.class);
  }

  @GET
  @Path("{facil}")
  @Produces({"application/xml", "application/json"})
  public InvestigationInstrumentWrapperConverter getInstruments(@PathParam("facil") String facility, @Context HttpServletRequest requestContext) {
    ArrayList<String> list = new ArrayList<String>();
    try {
      String yourIP = requestContext.getRemoteAddr().toString();
      log.info("Beginning getInstruments(): " + yourIP);
      String query = "Instrument.name ORDER BY name <-> Facility [ name = ':facility']";
      query = query.replace(":facility", facility);
      SearchResponse results = BeanManager.search(RestfulConstant.RESTFUL_USER, query, em);
      Iterator iter = results.getList().iterator();
      while (iter.hasNext()) {
        list.add((String) iter.next());
      }
      log.info("Ending getInstruments(): " + list.size());
    } catch (IcatException ex) {
      log.error("In getInstruments: got IcatException " + ex.getMessage());
    } catch (Exception ex) {
      log.error("In getInstruments: got Exception " + ex.getMessage());
    } finally {
      return new InvestigationInstrumentWrapperConverter(list);
    }
  }

  @GET
  @Path("{facil}/{inst}")
  @Produces({"application/xml", "application/json"})
  public InvestigationProposalWrapperConverter getInvestigations(@PathParam("facil") String facility, @PathParam("inst") String instrument, @Context HttpServletRequest requestContext) {
    try {
      String yourIP = requestContext.getRemoteAddr().toString();
      log.info("Beginning getInvestigations: " + yourIP);
      String query = "DISTINCT Investigation.name ORDER BY name <-> Instrument [ name = ':instrument']";
      query = query.replace(":instrument", instrument);
      SearchResponse results = BeanManager.search(RestfulConstant.RESTFUL_USER, query, em);
      ArrayList<String> list = new ArrayList<String>();
      Iterator iter = results.getList().iterator();
      while (iter.hasNext()) {
        list.add((String) iter.next());
      }
      log.info("Ending getInvestigations, found " + list.size() + " proposals for instrument " + instrument);
      return new InvestigationProposalWrapperConverter(list);
    } catch (IcatException ex) {
      log.error("In getInvestigations: got IcatException " + ex.getMessage());
      return new InvestigationProposalWrapperConverter();
    }
  }

  @GET
  @Path("{facil}/{inst}/meta")
  @Produces({"application/xml", "application/json"})
  public InvestigationMetaWrapperConverter getInvestigationsMeta(@PathParam("facil") String facility, @PathParam("inst") String instrument, @Context HttpServletRequest requestContext) {
    try {
      String yourIP = requestContext.getRemoteAddr().toString();
      log.info("Beginning getInvestigationsMeta: " + yourIP);
      String query = "Investigation <-> Instrument [name = ':instrument']";
      query = query.replace(":instrument", instrument);
      SearchResponse results = BeanManager.search(RestfulConstant.RESTFUL_USER, query, em);
      log.info("Ending getInvestigationsMeta, found " + results.getList().size() + " proposals for instrument " + instrument);
      ArrayList<Investigation> invList = new ArrayList<Investigation>();
      if (results.getList().isEmpty()) {
        return new InvestigationMetaWrapperConverter();
      } else {
        Iterator iter = results.getList().iterator();
        while (iter.hasNext()) {
          Investigation inv = (Investigation) iter.next();
          invList.add(inv);
        }
        return new InvestigationMetaWrapperConverter(invList);
      }
    } catch (IcatException ex) {
      log.error("In getInvestigationsMeta: got IcatException " + ex.getMessage());
      return new InvestigationMetaWrapperConverter();
    }
  }
    
  @GET
  @Path("{facil}/{inst}/all")
  @Produces({"application/xml", "application/json"})
  public InvestigationAllWrapperConverter getInvestigationsAll(@PathParam("facil") String facility, @PathParam("inst") String instrument, @Context HttpServletRequest requestContext) {
    try {
      String yourIP = requestContext.getRemoteAddr().toString();
      log.info("Beginning getInvestigationsAll: " + yourIP);
      String query = "Investigation INCLUDE Dataset, DatasetType <-> Instrument [name = ':instrument']";
      query = query.replace(":instrument", instrument);
      SearchResponse results = BeanManager.search(RestfulConstant.RESTFUL_USER, query, em);
      log.info("Ending getInvestigationsAll, found " + results.getList().size() + " proposals for instrument " + instrument);
      ArrayList<Investigation> invList = new ArrayList<Investigation>();
      if (results.getList().isEmpty()) {
        return new InvestigationAllWrapperConverter();
      } else {
        Iterator iter = results.getList().iterator();
        while (iter.hasNext()) {
          Investigation inv = (Investigation) iter.next();
          invList.add(inv);
        }
        return new InvestigationAllWrapperConverter(invList);
      }
    } catch (IcatException ex) {
      log.error("In getInvestigationsAll: got IcatException " + ex.getMessage());
      return new InvestigationAllWrapperConverter();
    }
  }

  @GET
  @Path("{facil}/{inst}/{prop}")
  @Produces({"application/xml", "application/json"})
  public InvestigationRunWrapperConverter getRuns(@PathParam("facil") String facility, @PathParam("inst") String instrument, @PathParam("prop") String proposal, @Context HttpServletRequest requestContext) {
    try {
      String yourIP = requestContext.getRemoteAddr().toString();
      log.info("Beginning getRuns: " + yourIP);
      String query = "DISTINCT Dataset.name ORDER BY name <-> Investigation [name = ':proposal'] <-> Instrument [ name = ':instrument']";
      query = query.replace(":instrument", instrument).replace(":proposal", proposal);
      SearchResponse results = BeanManager.search(RestfulConstant.RESTFUL_USER, query, em);
      Iterator iter = results.getList().iterator();
      log.info("Ending getRuns, results size: " + results.getList().size());
      TreeSet set = new TreeSet();
      while (iter.hasNext()) {
        String runNumber = (String) iter.next();
        set.add(Integer.parseInt(runNumber));
      }
      return new InvestigationRunWrapperConverter(set);
    } catch (IcatException ex) {
      log.error("In getRuns: got IcatException " + ex.getMessage());
      return new InvestigationRunWrapperConverter();
    }
  }

  @GET
  @Path("{facil}/{inst}/{prop}/meta")
  @Produces({"application/xml", "application/json"})
  public InvestigationAllWrapperConverter getRunsMeta(@PathParam("facil") String facility, @PathParam("inst") String instrument, @PathParam("prop") String proposal, @Context HttpServletRequest requestContext) {
    try {
      String yourIP = requestContext.getRemoteAddr().toString();
      log.info("Beginning getRunsMeta: " + yourIP);
      String query = "Investigation INCLUDE Dataset [ name = ':proposal' ] <-> Instrument [ name = ':instrument']";
      query = query.replace(":instrument", instrument).replace(":proposal", proposal);
      SearchResponse results = BeanManager.search(RestfulConstant.RESTFUL_USER, query, em);
      log.info("Ending getRunsMeta, found " + results.getList().size() + " proposals for instrument " + instrument);
      if (results.getList().isEmpty()) {
        return new InvestigationAllWrapperConverter();
      } else {
        ArrayList<Investigation> list = new ArrayList<Investigation>();
        Iterator iter = results.getList().iterator();
        while (iter.hasNext()) {
          Investigation inv = (Investigation) iter.next();
          list.add(inv);
        }
        return new InvestigationAllWrapperConverter(list);
      }
    } catch (IcatException ex) {
      log.error("In getRunsMeta: got IcatException " + ex.getMessage());
      return new InvestigationAllWrapperConverter();
    }
  }

  @GET
  @Path("{facil}/{inst}/{prop}/all")
  @Produces({"application/xml", "application/json"})
  public InvestigationDatasetAllWrapperConverter getRunsAll(@PathParam("facil") String facility, @PathParam("inst") String instrument, @PathParam("prop") String proposal, @Context HttpServletRequest requestContext) {
    try {
      String yourIP = requestContext.getRemoteAddr().toString();
      log.info("Beginning getRunsAll: " + yourIP);
      String query = "Investigation INCLUDE Dataset, DatasetParameter, DatasetType, ParameterType [name = ':proposal'] <-> Instrument [name = ':instrument']";
      query = query.replace(":instrument", instrument).replace(":proposal", proposal);
      SearchResponse results = BeanManager.search(RestfulConstant.RESTFUL_USER, query, em);
      log.info("Ending getRunsAll, found " + results.getList().size() + " proposals for instrument " + instrument + " and proposal" + proposal);
      if (results.getList().isEmpty()) {
        return new InvestigationDatasetAllWrapperConverter();
      } else {
        ArrayList<Investigation> list = new ArrayList<Investigation>();
        Iterator iter = results.getList().iterator();
        while (iter.hasNext()) {
          Investigation inv = (Investigation) iter.next();
          list.add(inv);
        }
        return new InvestigationDatasetAllWrapperConverter(list);
      }
    } catch (IcatException ex) {
      log.error("In getRunsAll: got IcatException " + ex.getMessage());
      return new InvestigationDatasetAllWrapperConverter();
    }
  }

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }
}
