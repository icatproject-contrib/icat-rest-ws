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
public class InvestigationRunInfoWrapperConverter {

  private static Logger log = Logger.getLogger(InvestigationRunInfoWrapperConverter.class);
  @XmlElement
  private ArrayList<InvestigationRunInfoConverter> run;

  public InvestigationRunInfoWrapperConverter() {
  }

  public InvestigationRunInfoWrapperConverter(ArrayList<InvestigationRunInfoConverter> run) {
    this.run = run;
  }
}
