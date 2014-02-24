/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "icat-run")
public class DatafileKeys {

  private static Logger log = Logger.getLogger(DatafileKeys.class);
  @XmlElement
  private String facil;
  @XmlElement
  private String inst;
  @XmlElement
  private String prop;
  @XmlElement
  private String coll;
  @XmlElement
  private String run;

  public DatafileKeys() {
  }

  public DatafileKeys(String facility, String instrument, String proposal, String collection, String runNumber) {
    this.facil = facility;
    this.inst = instrument;
    this.prop = proposal;
    this.coll = collection;
    this.run = runNumber;
  }
}
