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
@XmlRootElement(name = "runs")
public class RunConverter {

  private static Logger log = Logger.getLogger(RunConverter.class);

  @XmlElement
  private String runRange;
  
  public RunConverter() {
  }

  public RunConverter(String runRange) {
    this.runRange = runRange;
  }
}
