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
@XmlRootElement(name = "lastRun")
public class LastRunConverter {

  private static Logger log = Logger.getLogger(LastRunConverter.class);

  @XmlElement
  private long number;

  public LastRunConverter() {
  }

  public LastRunConverter(long number) {
    this.number = number;
  }
}
