/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "runs")
public class RunsInfoConverter {

  private static Logger log = Logger.getLogger(RunsInfoConverter.class);
  
  @XmlElement
  private ArrayList<RunInfoConverter> run;

  public RunsInfoConverter() {
  }

  public RunsInfoConverter(ArrayList<RunInfoConverter> run) {
    this.run= run;
  }
}
