/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.Date;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import org.icatproject.core.entity.Dataset;
import org.icatproject.core.entity.DatasetParameter;
import org.icatproject.core.entity.ParameterType;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "run")
public class InvestigationRunInfoConverter implements Comparable<InvestigationRunInfoConverter> {

  @XmlAttribute
  private String id;
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
  private static Logger log = Logger.getLogger(InvestigationRunInfoConverter.class);

  public InvestigationRunInfoConverter() {
    log.info("default constructor");
  }

  public InvestigationRunInfoConverter(Dataset ds) {
    log.debug("Begin  RunInfoConverter name: " + ds.getName());

    this.id = ds.getName();
    this.title = ds.getDescription();
    this.startTime = ds.getStartDate();
    this.endTime = ds.getEndDate();
    log.debug("getParameters: " + ds.getParameters().size());
    Iterator iter = ds.getParameters().iterator();
    while (iter.hasNext()) {
      DatasetParameter dsp = (DatasetParameter) iter.next();
      ParameterType type = dsp.getType();
      log.debug("dataset parameter type: " + type.getName());
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

    log.debug("End RunInfoConverter(ds)");
  }

  public int compareTo(InvestigationRunInfoConverter compareRunInfoConverter) {

    int thisId = Integer.parseInt(id);
    int compareId = Integer.parseInt(compareRunInfoConverter.id);
    return thisId - compareId;

  }

  public String toString() {
    return this.id + "-" + this.title;
  }
}
