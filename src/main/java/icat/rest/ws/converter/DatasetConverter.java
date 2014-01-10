/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import org.icatproject.core.entity.Datafile;
import org.icatproject.core.entity.Dataset;
import org.icatproject.core.entity.DatasetParameter;
import org.icatproject.core.entity.ParameterType;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "metadata")
public class DatasetConverter extends Dataset {

  @XmlElement
  private String proposal;
  @XmlElement
  private String title;
  @XmlElement
  private Date startTime;
  @XmlElement
  private Date endTime;
  @XmlElement
  private String duration;
  @XmlElement
  private String protonCharge;
  @XmlElement
  private String totalCounts;
  @XmlElement
  private DatafileLocation locations;
  private static Logger log = Logger.getLogger(DatasetConverter.class);

  public DatasetConverter() {
    log.info("default constructor");
  }

  public DatasetConverter(Dataset ds) {
    log.info("Begin DatasetConverter(Dataset ds)");
    this.proposal = ds.getInvestigation().getName();
    this.title = ds.getDescription();
    this.startTime = ds.getStartDate();
    this.endTime = ds.getEndDate();
    Iterator iter = ds.getParameters().iterator();
    while (iter.hasNext()) {
      DatasetParameter dsp = (DatasetParameter) iter.next();
      ParameterType type = dsp.getType();
      log.info("dataset parameter type: " + type.getName());
      if (type.getName().equalsIgnoreCase("duration")) {
        log.debug("dataset duration: " + dsp.getNumericValue());
        this.duration = String.valueOf(dsp.getNumericValue());
      } else if (type.getName().equalsIgnoreCase("proton_charge")) {
        log.debug("dataset duration: " + dsp.getStringValue());
        this.protonCharge = dsp.getStringValue();
      } else if (type.getName().equalsIgnoreCase("total_counts")) {
        log.debug("dataset total_counts: " + dsp.getNumericValue());
        this.totalCounts = String.valueOf(dsp.getNumericValue());
      }
    }
    log.info("End DatasetConverter(Dataset ds)");
  }

  public DatasetConverter(ArrayList<Dataset> dsList) {
    log.info("Begin DatasetConverter(ArrayList<Dataset> dsList)");
    ArrayList<String> list = new ArrayList<String>();
    String location;
    for (int i = 0; i < dsList.size(); i++) {
      Dataset ds = dsList.get(i);
      if (ds.getType().getName().equalsIgnoreCase("experiment_raw")) {
        this.proposal = ds.getInvestigation().getName();
        this.title = ds.getDescription();
        this.startTime = ds.getStartDate();
        this.endTime = ds.getEndDate();
        log.debug("getParameters: " + ds.getParameters().size());
        Iterator iter = ds.getParameters().iterator();
        while (iter.hasNext()) {
          DatasetParameter dsp = (DatasetParameter) iter.next();
          ParameterType type = dsp.getType();
          log.info("dataset parameter type: " + type.getName());
          if (type.getName().equalsIgnoreCase("duration")) {
            log.debug("dataset duration: " + dsp.getNumericValue());
            this.duration = String.valueOf(dsp.getNumericValue());
          } else if (type.getName().equalsIgnoreCase("proton_charge")) {
            log.debug("dataset duration: " + dsp.getStringValue());
            this.protonCharge = dsp.getStringValue();
          } else if (type.getName().equalsIgnoreCase("total_counts")) {
            log.debug("dataset total_counts: " + dsp.getNumericValue());
            this.totalCounts = String.valueOf(dsp.getNumericValue());
          }
        }
      }
      Iterator iter2 = ds.getDatafiles().iterator();
      while (iter2.hasNext()) {
        Datafile df = (Datafile) iter2.next();
        location = df.getLocation();
        if (!location.isEmpty()) {
          list.add(location);
        }
      }
    }

    this.locations = new DatafileLocation(list);
    log.info("End DatasetConverter(ArrayList<Dataset> dsList)");
  }
}
