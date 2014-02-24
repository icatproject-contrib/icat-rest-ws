/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "locations")
public class DatafileLocation {

  private static Logger log = Logger.getLogger(DatafileLocation.class);
  @XmlElement
  private Collection<String> location;

  public DatafileLocation() {
  }

  public DatafileLocation(Collection<String> location) {
    this.location = location;
  }
}
