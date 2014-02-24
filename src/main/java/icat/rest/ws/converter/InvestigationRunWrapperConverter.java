/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icat.rest.ws.converter;

import java.util.TreeSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

/**
 *
 * @author 3qr
 */
@XmlRootElement(name = "runs")
public class InvestigationRunWrapperConverter {

  private static Logger log = Logger.getLogger(InvestigationRunWrapperConverter.class);
  @XmlElement(name = "runRange")
  private String runRange;

  public InvestigationRunWrapperConverter() {
  }

  public InvestigationRunWrapperConverter(TreeSet set) {
    this.runRange = RunRangeCalculator.getRunRange(set);
  }
}
