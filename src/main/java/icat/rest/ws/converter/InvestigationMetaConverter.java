/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import org.apache.log4j.Logger;
import org.icatproject.core.entity.Investigation;

/**
 *
 * @author 3qr
 */
public class InvestigationMetaConverter {

  @XmlAttribute
  public String id;
  @XmlElement
  public String title;
  @XmlElement
  public String collection;
  @XmlElement
  public Date createTime;
  private static Logger log = Logger.getLogger(InvestigationMetaConverter.class);

  public InvestigationMetaConverter() {
  }

  public InvestigationMetaConverter(Investigation inv) {

    this.id = inv.getName();
    this.title = inv.getTitle();
    this.collection = inv.getVisitId();
    this.createTime = inv.getCreateTime();
  }
}
