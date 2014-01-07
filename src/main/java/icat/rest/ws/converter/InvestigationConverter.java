/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import org.icatproject.core.entity.Investigation;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "metadata")
public class InvestigationConverter extends Investigation {

  @XmlElement
  private String facility;
  @XmlElement
  private String instrument;
  @XmlElement
  private String proposal;
  @XmlElement
  private String collection;
  @XmlElement
  private String title;
  @XmlElement
  private Date startTime;
  @XmlElement
  private Date createTime;
  private static Logger log = Logger.getLogger(InvestigationConverter.class);

  public InvestigationConverter() {
  }

  public InvestigationConverter(Investigation inv) {

    this.facility = inv.getFacility().getName();
    this.instrument = inv.getInstrument().getName();
    this.proposal = inv.getName();
    this.collection = inv.getVisitId();
    this.title = inv.getTitle();
    this.createTime = inv.getCreateTime();
    this.startTime = inv.getStartDate();
  }
}
